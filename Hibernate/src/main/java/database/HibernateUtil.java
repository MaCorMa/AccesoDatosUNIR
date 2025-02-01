package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    //getSession

    public static SessionFactory getSessionFactory() {

        if(sessionFactory==null){
            createSessionFactory();
        }
        return sessionFactory;
    }

    //createSession
    private static void createSessionFactory() {
        //si el file de conf se llama hibernate.cfg.xml y se encuentra en resources, lo pila por defecto
        //si no es así, hay que indicarle el path
        sessionFactory = new Configuration().configure().buildSessionFactory(); //crea una session con la conf indicada
    }

    //closeSession
    public void closeSessionFactory(){
        sessionFactory.close();
        sessionFactory=null;
    }
}
