/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.Country;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Coviddata;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 */
public class CountryJpaController implements Serializable {

    public CountryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Country country) {
        if (country.getCoviddataList() == null) {
            country.setCoviddataList(new ArrayList<Coviddata>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Coviddata> attachedCoviddataList = new ArrayList<Coviddata>();
            for (Coviddata coviddataListCoviddataToAttach : country.getCoviddataList()) {
                coviddataListCoviddataToAttach = em.getReference(coviddataListCoviddataToAttach.getClass(), coviddataListCoviddataToAttach.getCoviddata());
                attachedCoviddataList.add(coviddataListCoviddataToAttach);
            }
            country.setCoviddataList(attachedCoviddataList);
            em.persist(country);
            for (Coviddata coviddataListCoviddata : country.getCoviddataList()) {
                Country oldCountryOfCoviddataListCoviddata = coviddataListCoviddata.getCountry();
                coviddataListCoviddata.setCountry(country);
                coviddataListCoviddata = em.merge(coviddataListCoviddata);
                if (oldCountryOfCoviddataListCoviddata != null) {
                    oldCountryOfCoviddataListCoviddata.getCoviddataList().remove(coviddataListCoviddata);
                    oldCountryOfCoviddataListCoviddata = em.merge(oldCountryOfCoviddataListCoviddata);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Country country) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country persistentCountry = em.find(Country.class, country.getCountry());
            List<Coviddata> coviddataListOld = persistentCountry.getCoviddataList();
            List<Coviddata> coviddataListNew = country.getCoviddataList();
            List<String> illegalOrphanMessages = null;
            for (Coviddata coviddataListOldCoviddata : coviddataListOld) {
                if (!coviddataListNew.contains(coviddataListOldCoviddata)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Coviddata " + coviddataListOldCoviddata + " since its country field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Coviddata> attachedCoviddataListNew = new ArrayList<Coviddata>();
            for (Coviddata coviddataListNewCoviddataToAttach : coviddataListNew) {
                coviddataListNewCoviddataToAttach = em.getReference(coviddataListNewCoviddataToAttach.getClass(), coviddataListNewCoviddataToAttach.getCoviddata());
                attachedCoviddataListNew.add(coviddataListNewCoviddataToAttach);
            }
            coviddataListNew = attachedCoviddataListNew;
            country.setCoviddataList(coviddataListNew);
            country = em.merge(country);
            for (Coviddata coviddataListNewCoviddata : coviddataListNew) {
                if (!coviddataListOld.contains(coviddataListNewCoviddata)) {
                    Country oldCountryOfCoviddataListNewCoviddata = coviddataListNewCoviddata.getCountry();
                    coviddataListNewCoviddata.setCountry(country);
                    coviddataListNewCoviddata = em.merge(coviddataListNewCoviddata);
                    if (oldCountryOfCoviddataListNewCoviddata != null && !oldCountryOfCoviddataListNewCoviddata.equals(country)) {
                        oldCountryOfCoviddataListNewCoviddata.getCoviddataList().remove(coviddataListNewCoviddata);
                        oldCountryOfCoviddataListNewCoviddata = em.merge(oldCountryOfCoviddataListNewCoviddata);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = country.getCountry();
                if (findCountry(id) == null) {
                    throw new NonexistentEntityException("The country with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country country;
            try {
                country = em.getReference(Country.class, id);
                country.getCountry();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The country with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Coviddata> coviddataListOrphanCheck = country.getCoviddataList();
            for (Coviddata coviddataListOrphanCheckCoviddata : coviddataListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Country (" + country + ") cannot be destroyed since the Coviddata " + coviddataListOrphanCheckCoviddata + " in its coviddataList field has a non-nullable country field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(country);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Country> findCountryEntities() {
        return findCountryEntities(true, -1, -1);
    }

    public List<Country> findCountryEntities(int maxResults, int firstResult) {
        return findCountryEntities(false, maxResults, firstResult);
    }

    private List<Country> findCountryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Country.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Country findCountry(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Country.class, id);
        } finally {
            em.close();
        }
    }

    public int getCountryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Country> rt = cq.from(Country.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
