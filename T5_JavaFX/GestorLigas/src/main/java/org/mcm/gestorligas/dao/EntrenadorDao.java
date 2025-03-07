package org.mcm.gestorligas.dao;

import org.hibernate.Session;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Entrenador;

public class EntrenadorDao {

    Session session;

    public void agregarEntrenador(Entrenador entrenador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.persist(entrenador);

        session.getTransaction().commit();
        session.close();
    }

    public Entrenador obtenerEntrenador(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Entrenador entrenador = session.get(Entrenador.class, id);

        session.getTransaction().commit();
        session.close();

        return entrenador;
    }

}
