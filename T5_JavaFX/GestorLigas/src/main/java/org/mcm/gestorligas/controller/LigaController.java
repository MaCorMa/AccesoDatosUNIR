package org.mcm.gestorligas.controller;

import org.hibernate.Session;
import org.mcm.gestorligas.dao.*;
import org.mcm.gestorligas.database.HibernateUtils;
import org.mcm.gestorligas.model.*;

import java.util.List;


public class LigaController {

    private EntrenadorDao entrenadorDao;
    private JugadorDao jugadorDao;
    private LigaDao ligaDao;
    private EquipoDao equipoDao;
    private CompeticionDao competicionDao;

    public LigaController(){
        entrenadorDao = new EntrenadorDao();
        equipoDao = new EquipoDao();
        ligaDao = new LigaDao();
        jugadorDao = new JugadorDao();
        competicionDao = new CompeticionDao();
    }


    public void agregarEntrenador(Entrenador entrenador){

        //lógica negocio, ejemplo

        if(entrenador.getCalificacion()<10){
            System.out.println("No sirve para el equipo");
        }else {
            entrenadorDao.agregarEntrenador(entrenador);
        }
    }

    public void contratarEntrenador(int id, int idEquipo){
        Entrenador entrenador = entrenadorDao.obtenerEntrenador(id);
        //System.out.println(entrenador.getNombre());
        if(entrenador.getTitulos()>4){
            //contratado
            Equipo equipo = equipoDao.obtenerEquipo(idEquipo);
            equipo.setEntrenador(entrenador);
            equipoDao.actualizarEquipo(equipo);

        }else {
            //si titulos menos que 4
            System.out.println("Entrenador no válido");
        }
    }

    public void darAltaLiga(Liga liga){
        ligaDao.crearLiga(liga);
    }

    public void inscribirseLiga(int idEquipo, int idLiga){
        Equipo equipo = equipoDao.obtenerEquipo(idEquipo);
        Liga liga = ligaDao.getLiga(idLiga);

        equipo.setLiga(liga);

        equipoDao.actualizarEquipo(equipo);

    }

    public void crearJugador(Jugador jugador, int idPosicion){
        Session session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Posicion posicion = session.get(Posicion.class, idPosicion);

        session.getTransaction().commit();
        session.close();

        jugadorDao.crearJugador(jugador, posicion);
    }

    public void contratarJugador(int idJugador, int idEquipo){
        Equipo equipo = equipoDao.obtenerEquipo(idEquipo);
        Jugador jugador = jugadorDao.obtenerJugador(idJugador);

         //lógica
        jugador.setEquipo(equipo);
        jugadorDao.actualizarJugador(jugador);
    }

    public void analizarPlantilla(int id){
        List<Jugador> jugadores = equipoDao.obtenerPlantilla(id);

        for (Jugador jugador:jugadores){
            System.out.println(jugador.getNombre());
        }
    }

    public void getEquiposCompeticion(int id){
        List<Equipo>list = competicionDao.getEquiposCompeticion(id);
        for (Equipo equipo:list){
            System.out.println(equipo.getNombre());
        }
    }

    public void obtenerLigas(){
        for(Liga liga : ligaDao.getAllLigas()){
            System.out.println("El nombre de la liga es: "+liga.getNombre());
            for(Equipo equipo:liga.getEquipos()){
                System.out.println("\t"+equipo.getNombre());
            }
        }
    }
    public void buscarPorNacionalidad(String nac){
        for(Jugador j:jugadorDao.obtenerJugadoresNacionalidad(nac)){
            System.out.println(j.getNombre());
        }
    }
    public void buscarPorNacionalidadNamed(String nac){
        for(Jugador j:jugadorDao.obtenerJugadoresNacionalidadNamed(nac)){
            System.out.println(j.getNombre());
        }
    }
    public void analizarPlantillaNamed(){
        for(Jugador j:jugadorDao.getAllNamed()){
            System.out.println(j.getNombre());
        }
    }
}
