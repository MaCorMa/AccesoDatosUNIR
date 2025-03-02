package DAO;

import database.HibernateUtil;
import model.Editorial;
import org.hibernate.Session;

public class EditorialDAO {

    private Session session;

    public void altaEditorial(Editorial editorial){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(editorial);


        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion

    }
}
