package org.mcm.gestorligas.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Liga;

import java.util.List;

public class LigaDao {

    Session session;

    public void crearLiga(Liga liga){
        
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.persist(liga);

        session.getTransaction().commit();
        session.close();
    }

    public Liga getLiga(int idLiga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Liga liga = session.get(Liga.class, idLiga);

        session.getTransaction().commit();
        session.close();

        return liga;
    }

    public List<Liga> getAllLigas(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //complejo ->HQL, en caso de no poder ->SQL(último recurso)
        Query<Liga> query = session.createQuery("FROM Liga",Liga.class);
        //Liga es la entidad a la que hace referencia
        //Liga.class es el dato de mapeo
        List<Liga> ligas = query.list();


        session.getTransaction().commit();
        session.close();

        return ligas;
    }
}
