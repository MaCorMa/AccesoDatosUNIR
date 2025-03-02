package DAO;

import database.HibernateUtil;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LibroDAO {

    private Session session;


    public void altaLibro(Libro libro){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(libro);

        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion
    }
    public List<Libro> getAllLibros(){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        Query<Libro> query = session.createQuery("FROM Libro", Libro.class);
        List<Libro>listaLibros = query.list();

        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion

        return listaLibros;
    }

}
