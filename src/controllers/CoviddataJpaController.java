/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Country;
import entities.Coviddata;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author panti
 */
public class CoviddataJpaController implements Serializable {

    public CoviddataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coviddata coviddata) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Country country = coviddata.getCountry();
            if (country != null) {
                country = em.getReference(country.getClass(), country.getCountry());
                coviddata.setCountry(country);
            }
            em.persist(coviddata);
            if (country != null) {
                country.getCoviddataList().add(coviddata);
                country = em.merge(country);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coviddata coviddata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coviddata persistentCoviddata = em.find(Coviddata.class, coviddata.getCoviddata());
            Country countryOld = persistentCoviddata.getCountry();
            Country countryNew = coviddata.getCountry();
            if (countryNew != null) {
                countryNew = em.getReference(countryNew.getClass(), countryNew.getCountry());
                coviddata.setCountry(countryNew);
            }
            coviddata = em.merge(coviddata);
            if (countryOld != null && !countryOld.equals(countryNew)) {
                countryOld.getCoviddataList().remove(coviddata);
                countryOld = em.merge(countryOld);
            }
            if (countryNew != null && !countryNew.equals(countryOld)) {
                countryNew.getCoviddataList().add(coviddata);
                countryNew = em.merge(countryNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = coviddata.getCoviddata();
                if (findCoviddata(id) == null) {
                    throw new NonexistentEntityException("The coviddata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coviddata coviddata;
            try {
                coviddata = em.getReference(Coviddata.class, id);
                coviddata.getCoviddata();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coviddata with id " + id + " no longer exists.", enfe);
            }
            Country country = coviddata.getCountry();
            if (country != null) {
                country.getCoviddataList().remove(coviddata);
                country = em.merge(country);
            }
            em.remove(coviddata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coviddata> findCoviddataEntities() {
        return findCoviddataEntities(true, -1, -1);
    }

    public List<Coviddata> findCoviddataEntities(int maxResults, int firstResult) {
        return findCoviddataEntities(false, maxResults, firstResult);
    }

    private List<Coviddata> findCoviddataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coviddata.class));
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

    public Coviddata findCoviddata(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coviddata.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoviddataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coviddata> rt = cq.from(Coviddata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
