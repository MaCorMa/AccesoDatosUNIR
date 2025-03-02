package DAO;

import database.HibernateUtil;
import model.Autor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AutorDAO {

    private Session session;


    public void altaAutor(Autor autor){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(autor);

        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion

    }

    public List<Autor> getAllAutores(){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        Query<Autor>query = session.createQuery("FROM Autor", Autor.class);
        List<Autor> listaAutores = query.list();

        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion

        return listaAutores;

    }

}
