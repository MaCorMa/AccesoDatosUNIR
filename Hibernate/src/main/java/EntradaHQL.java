import Dao.ClienteDAO;
import Dao.HabitacionDAO;
import Dao.TrabajadorDAO;
import database.HibernateUtil;
import model.Cliente;
import model.Direccion;
import model.Habitacion;
import model.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EntradaHQL {

    public static void main(String[] args) {

        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        HabitacionDAO habitacionDAO = new HabitacionDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

       // trabajadorDAO.insertarTrabajador(new Trabajador("Mestral","Sanchis",
        //        new Direccion("Vilamarxant","Valencia"), new Direccion("Turia","Camp de Turia"),"123456"));

        //trabajadorDAO.seleccionarTodosByLocalidad("Vilamarxant");
        //trabajadorDAO.seleccionarTodosByProvincia("Valencia");
        //trabajadorDAO.actualizarNombre("Xixito");

        /*
        for(int i=10; i<21;i++){
            habitacionDAO.crearHabitacion(new Habitacion(1,i,4));
        } */ //crear habitaciones automatico


        //con habitacion
        //trabajadorDAO.insertarTrabajador(new Trabajador("Ana","Sapi",
        //        new Direccion("Alzira","Valencia"), new Direccion("Valencia",
        //        "Valencia"),"8888"),new Habitacion(12,3,300));

        //trabajadorDAO.seleccionHabitacionTrabajador(6);
        //habitacionDAO.getTrabajadorHabitacion(12);

        //clienteDAO.crearCliente(new Cliente("Maria"),11);

        //habitacionDAO.getAllClientes(11);

        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        /*
        Cliente cliente = session.get(Cliente.class,1);
        /*
        for(Trabajador trabajador :cliente.getListaTrabajadores()){
            System.out.println(trabajador.getNombre());
        }
        */
        /*
        Trabajador trabajador = session.get(Trabajador.class,3);
        for (Cliente cliente : trabajador.getListaCliente()){
            System.out.println(cliente.getNombre());
        }
         */

        //HACER UNA INSERCIÓN
        Cliente cliente = session.get(Cliente.class,1);
        Trabajador trabajador = session.get(Trabajador.class,7);

        cliente.getListaTrabajadores().add(trabajador);
        trabajador.getListaCliente().add(cliente);

        //persistir cambios
        session.persist(cliente);
        session.persist(trabajador);

        session.getTransaction().commit();
        session.close();
    }

}
