package org.mcm.gestorligas;

import org.mcm.gestorligas.controller.LigaController;
import org.mcm.gestorligas.model.Entrenador;
import org.mcm.gestorligas.model.Jugador;
import org.mcm.gestorligas.model.Liga;

public class Entrada {


    public static void main(String[] args) {

        LigaController ligaController = new LigaController();
        //ligaController.agregarEntrenador(new Entrenador("ManuelEntrenador",11,5));
        //ligaController.contratarEntrenador(1,1);
        /*
        ligaController.darAltaLiga(new Liga("BBVA"));
        ligaController.darAltaLiga(new Liga("Calcio"));
        ligaController.darAltaLiga(new Liga("BundesLiga"));
        ligaController.darAltaLiga(new Liga("Premier"));
         */
        //ligaController.inscribirseLiga(1,1);
        //ligaController.crearJugador(new Jugador("Julian","Española",10000),4);
        //ligaController.contratarJugador(1,1);
        //ligaController.analizarPlantilla(1);
        //ligaController.getEquiposCompeticion(3);
        //ligaController.obtenerLigas();
        //ligaController.buscarPorNacionalidad("Española");
        ligaController.buscarPorNacionalidadNamed("Italiana");
        ligaController.analizarPlantillaNamed();

    }
}
