package DAO;

import database.HibernateUtil;
import model.Estudiante;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class EstudianteDAO {

    private Session session;

    public void altaEstudiante(Estudiante estudiante){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.persist(estudiante);

        session.getTransaction().commit();
        session.close();
    }

    public List<Estudiante> getAllEstudiantes(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query<Estudiante> query = session.createQuery("FROM Estudiante", Estudiante.class);
        List<Estudiante> listaEstudiantes = query.list();

        session.getTransaction().commit();
        session.close();

        return listaEstudiantes;
    }
}
