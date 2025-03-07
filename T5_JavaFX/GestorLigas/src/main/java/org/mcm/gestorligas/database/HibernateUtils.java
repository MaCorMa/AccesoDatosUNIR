package org.mcm.gestorligas.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtils {
    
    private static SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            createSessionFactory();
        }
        return sessionFactory;
    }

    private void createSessionFactory() {
        //TODO crear file asociado a los productos
        File file = new File("src/main/resources/org/mcm/gestorligas/hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
    }
}
