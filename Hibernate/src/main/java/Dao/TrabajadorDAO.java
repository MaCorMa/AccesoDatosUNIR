package Dao;

import database.HibernateUtil;
import model.Habitacion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TrabajadorDAO {

    private Session session;

    public TrabajadorDAO(){
        //inicializar la sesion -> NO HACER NUNCA, no queremos crear la sesion al crear el DAO
    }

    public void insertarTrabajador(Trabajador trabajador){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        session.persist(trabajador); 


        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion
    }

    public void seleccionarTodos(){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String querySTR = "SELECT FROM Trabajador t";
        Query<Trabajador> query = session.createQuery(querySTR, Trabajador.class);
        List<Trabajador> listaTrabajadores = query.list();

        for(Trabajador t : listaTrabajadores){
            System.out.println(t.toString());
        }

        session.getTransaction().commit();
        session.close();
    }
    public void seleccionarTodosByLocalidad(String localidad){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String querySTR = "SELECT t FROM Trabajador t WHERE t.direccion.localidad=:localidad";
        Query<Trabajador> query = session.createQuery(querySTR, Trabajador.class);

        query.setParameter("localidad",localidad);

        List<Trabajador> listaTrabajadores = query.list();

        for(Trabajador t : listaTrabajadores){
            System.out.println(t.toString());
        }

        session.getTransaction().commit();
        session.close();
    }

    public void seleccionarTodosByProvincia(String provincia){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //usando @NamedQuery
        Query<Trabajador> query = session.createNamedQuery("Trabajador.findAllByProvincia", Trabajador.class);
        query.setParameter("provincia", provincia);

        List<Trabajador> listaTrabajadores = query.list();

        for(Trabajador t : listaTrabajadores){
            System.out.println(t.toString());
        }

        session.getTransaction().commit();
        session.close();
    }

    public void actualizarNombre(String nombre){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String querySTR = "UPDATE Trabajador SET nombre =:nombre WHERE apellido=:apellido";
        Query query = session.createQuery(querySTR);
        query.setParameter("nombre", nombre);
        query.setParameter("apellido", "Sanchis");

        int row = query.executeUpdate();
        System.out.println("El numero de filas afectadas es de: "+row);

        session.getTransaction().commit();
        session.close();
    }

    public void insertarTrabajador(Trabajador trabajador, Habitacion habitacion){

        session = new HibernateUtil().getSessionFactory().getCurrentSession();  //sacar sesion
        session.beginTransaction(); //iniciar transaccion

        trabajador.setHabitacion(habitacion);
        session.persist(trabajador);
        
        session.getTransaction().commit(); //garantizo transaccion
        session.close(); //cierro sesion

    }

    public void seleccionHabitacionTrabajador(int id){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Trabajador trabajador =  session.get(Trabajador.class,id);
        System.out.println(trabajador.getHabitacion().getNumero());

        session.getTransaction().commit();
        session.close();
    }
}
