package org.mcm.gestorligas.dao;

import org.hibernate.Session;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Competicion;
import org.mcm.gestorligas.model.Equipo;

import java.util.List;

public class CompeticionDao {

    private Session session;

    public List<Equipo> getEquiposCompeticion(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Competicion competicion = session.get(Competicion.class, id);

        session.getTransaction().commit();
        session.close();

        return competicion.getEquipos();
    }

}
