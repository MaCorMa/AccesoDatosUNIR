package DAO;

import database.HibernateUtil;
import model.Curso;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class CursoDAO {

    private Session session;

    public void altaCurso(Curso curso){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(curso);

        session.getTransaction().commit();
        session.close();
    }

    public List<Curso> getAllCursos(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        Query<Curso> query = session.createQuery("FROM Curso", Curso.class);
        List<Curso> listaCursos = query.list();

        session.getTransaction().commit();
        session.close();

        return listaCursos;
    }
}
