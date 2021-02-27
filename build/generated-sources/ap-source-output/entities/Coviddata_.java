package entities;

import entities.Country;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-02-27T19:51:37")
@StaticMetamodel(Coviddata.class)
public class Coviddata_ { 

    public static volatile SingularAttribute<Coviddata, Country> country;
    public static volatile SingularAttribute<Coviddata, Short> datakind;
    public static volatile SingularAttribute<Coviddata, Date> trndate;
    public static volatile SingularAttribute<Coviddata, Integer> qty;
    public static volatile SingularAttribute<Coviddata, Integer> coviddata;
    public static volatile SingularAttribute<Coviddata, Integer> proodqty;

}