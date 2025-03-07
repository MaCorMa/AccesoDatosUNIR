package org.mcm.gestorligas.dao;

import org.hibernate.Session;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Equipo;
import org.mcm.gestorligas.model.Jugador;

import java.util.List;

public class EquipoDao {

    Session session;

    public Equipo obtenerEquipo(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Equipo equipo = session.get(Equipo.class, id);

        session.getTransaction().commit();
        session.close();

        return equipo;
    }
    public void actualizarEquipo(Equipo equipo){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.merge(equipo);

        session.getTransaction().commit();
        session.close();
    }

    public List<Jugador> obtenerPlantilla (int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Equipo equipo = session.get(Equipo.class,id);

        session.getTransaction().commit();
        session.close();

        return equipo.getJugadores();
    }
}
