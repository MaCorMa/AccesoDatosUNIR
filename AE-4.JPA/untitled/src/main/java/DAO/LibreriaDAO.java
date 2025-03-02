package DAO;

import database.HibernateUtil;
import model.Editorial;
import model.Libreria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LibreriaDAO {
    private Session session;

    public void altaLibreria(Libreria libreria){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(libreria);

        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion
    }
    public List<Libreria> getAllLibrerias(){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query<Libreria> query = session.createQuery("FROM Libreria", Libreria.class);
        List<Libreria> listaLibrerias = query.list();
        session.getTransaction().commit();
        session.close();

        return listaLibrerias;
    }
}
