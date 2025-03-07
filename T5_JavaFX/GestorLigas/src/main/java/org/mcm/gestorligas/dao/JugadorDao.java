package org.mcm.gestorligas.dao;

import lombok.NoArgsConstructor;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.Equipo;
import org.mcm.gestorligas.model.Jugador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mcm.gestorligas.model.Posicion;

import java.util.List;

@NoArgsConstructor
public class JugadorDao {

    private Session session;

    public List<Jugador> getAllJugadores(){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // SELECT * -> foreach / filter -> si hay uno en la lista -> loginok
        // SELECT * FROM usuarios WHERE username = correo AND password = pass
        Query<Jugador> query = session.createQuery("FROM Jugador");
        List<Jugador> listaJugadores = query.list();
        session.getTransaction().commit();
        session.close();

        return listaJugadores;
    }

    public void removeJugador(Jugador jugador){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(jugador);
        session.getTransaction().commit();
        session.close();

    }

    public void addJugador(Jugador jugador){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(jugador);
        session.getTransaction().commit();
        session.close();

    }

    public void crearJugador(Jugador jugador, Posicion posicion){

        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        jugador.setPosicion(posicion);
        session.merge(jugador);
        session.getTransaction().commit();
        session.close();

    }
    public void actualizarJugador(Jugador jugador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.merge(jugador);
        
        session.getTransaction().commit();
        session.close();
    }

    public Jugador obtenerJugador(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Jugador jugador = session.get(Jugador.class,id);

        session.getTransaction().commit();
        session.close();

        return  jugador;
    }

    public List<Jugador> obtenerJugadoresNacionalidad(String nacionalidad){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //UPDATE XXX SET YYY=ZZZ WHERE MMM=NNN
        String querySTR = "FROM Jugador j WHERE j.nacionalidad = :nacionalidad";
        Query<Jugador> query = session.createQuery(querySTR, Jugador.class);
        query.setParameter("nacionalidad",nacionalidad);

        List<Jugador>jugadores = query.list();

        session.getTransaction().commit();
        session.close();
        
        return jugadores;
    }
    public List<Jugador> obtenerJugadoresNacionalidadNamed(String nacionalidad){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //igual que arriba pero con NamedQuery
        Query<Jugador> query = session.createNamedQuery("Jugador.findNacionalidad", Jugador.class);
        query.setParameter("nacionalidad",nacionalidad);

        List<Jugador>jugadores = query.list();

        session.getTransaction().commit();
        session.close();

        return jugadores;
    }
    public List<Jugador> getAllNamed(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //igual que getAll pero con NamedQuery
        Query<Jugador> query = session.createNamedQuery("Jugador.findAll", Jugador.class);

        List<Jugador>jugadores = query.list();

        session.getTransaction().commit();
        session.close();

        return jugadores;
    }


}
