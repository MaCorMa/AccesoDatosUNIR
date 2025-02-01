import database.HibernateUtil;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        //ver archivo apuntes para el esquema


        
        //inseción
        /*
        //para poner ejemplo
        Scanner scanner = new Scanner(System.in);

            //Necesitamos el SessionFactory
        SessionFactory sessionFactory =  new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession(); //crea objeto Session para recoger el SessionFactory de arriba
        session.beginTransaction(); // si no, dará error al intentar CRUD indicando que falta transacción


        //datos
        System.out.println("Introduce todos los datos del trabajador: nombre, apellido, direccion, telefono");
        // .save() guarda en local y necesita .export() para llevar a BD, .persist() lo hace en un paso
        session.persist(new Trabajador(scanner.next(), scanner.next(),scanner.next(),scanner.next()));
        
        // Se ordena ejecución a la transacción
        session.getTransaction().commit();
        //Se cierra session
        session.close();
         */


        
        //obtención  → necesitabamos select - PreparedStatement, ResultSet
        /*
        rs = rs.execute()
        while(rs.next()){
            String nombre = rs.getString(COLUMN.atributo)
            String apellido = rs.getString(COLUMN2.atributo)
            String tlf = rs.getString(COLUMN3.atributo)
            Trabajador t = new Trabajador(nombre, apellido, tlf);
        }
         */
         /*
        //estas tres lineas seran siempre comunes
        SessionFactory sessionFactory =  new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //para obtención se usa el método get
        Trabajador t = session.get(Trabajador.class,1); //simplemente se le indica la clase de la cual queremos el objeto y el identificador en la BD
        System.out.println(t.getNombre());

        //estas dos al final también
        session.getTransaction().commit();
        session.close();
         */

        //update
        //estas tres lineas seran siempre comunes
        /*
        SessionFactory sessionFactory =  new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //para update primero obtener objeto con get, actualizo con setter y actualizo con el merge
        Trabajador t = session.get(Trabajador.class,1);
        t.setDireccion("Casetes");
        session.merge(t);

        //estas dos al final también
        session.getTransaction().commit();
        session.close();
         */

        //delete
        /*
        SessionFactory sessionFactory =  new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //para update primero obtener objeto con get, actualizo con setter y actualizo con el merge
        Trabajador t = new Trabajador(1);
        session.delete(t);

        //estas dos al final también
        session.getTransaction().commit();
        session.close();
         */

        //PARA ACCIONES MÁS AVANZADAS, ES NECESARIO HQL (HIBERNATE QUERY LANGUAGE)
        //Query query = s.createQuery("HQL",clase_mapeo)


        //Obtencion todos los elementos
        
        SessionFactory sessionFactory =  new HibernateUtil().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //Objeto Query de hibernate tipado con la clase que queremos obtener
        //indica que lo que devuelva la query a la BD es de la clase indicada
        // se selecciona FROM clase, no FROM columna
        Query<Trabajador> query =  session.createQuery("SELECT t FROM Trabajador t", Trabajador.class);
        List<Trabajador> listaTrabajadores = query.list();//este es el método que devuele el resultSet de la query

        //estas dos al final también
        session.getTransaction().commit();
        session.close();
        for (Trabajador trabajador:listaTrabajadores){
            System.out.println(trabajador.toString());
        }

    }
}
