package DAO;

import database.HibernateUtil;
import model.Persona;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class PersonaDAO {

    private Session session;

    public void altaPersona(Persona persona){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.persist(persona);

        session.getTransaction().commit();
        session.close();
    }

    public List<Persona> getAllPersonas(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query<Persona> query = session.createQuery("FROM Persona", Persona.class);
        List<Persona> listaPersonas = query.list();

        session.getTransaction().commit();
        session.close();

        return listaPersonas;
    }
}
