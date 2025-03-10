package org.mcm.gestorligas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mcm.gestorligas.LigasApp;
import org.mcm.gestorligas.dao.JugadorDao;
import org.mcm.gestorligas.model.Jugador;
import org.mcm.gestorligas.controller.DetailController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn columnaNombre, columnaGoles, columnaNacionalidad, columnaValor;

    private ObservableList<Jugador> listaJugadores;

    @FXML
    private Button btnVolver;

    @FXML
    private MenuItem menuSalir, menuBorrar, menuAnadir, menuAutor;

    private JugadorDao jugadorDao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instancias();
        acciones();

    }

    private void acciones() {
        menuBorrar.setOnAction(event -> {
            if (tabla.getSelectionModel().getSelectedIndex()!=-1){
                Jugador jugadorSeleccionado = (Jugador) tabla.getSelectionModel().getSelectedItem();
                //int indice = tabla.getSelectionModel().getSelectedIndex();
                listaJugadores.remove(jugadorSeleccionado);
                jugadorDao.removeJugador(jugadorSeleccionado);
                tabla.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setContentText("No hay seleccionado");
                alert.showAndWait();
            }

        });
        menuSalir.setOnAction(event -> {
            System.exit(0);
        });
        menuAutor.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El autor de la aplicación es Manuel Correcher");
            alert.setTitle("Ayuda");
            alert.showAndWait();
        });
        menuAnadir.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(LigasApp.class.getResource("detail-view.fxml"));
            try {
                Parent root = loader.load();
                // setData -> necesito una
                DetailController detailController = loader.getController();
                detailController.setData(this);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);//sólo se ejecuta la de debajo si se cierra esta->MODAL
                stage.initOwner(btnVolver.getScene().getWindow());//indicar que pertenece a la ventana anterior
                stage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnVolver.setOnAction(event -> {

            Jugador jugador= tabla.getSelectionModel().getSelectedItem();
            System.out.println(jugador.getId());
        });
    }

    private void instancias() {
        menuSalir.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_ANY));
        menuAutor.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY));
        menuBorrar.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));
        columnaGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        columnaValor.setCellValueFactory(new PropertyValueFactory<>("valorMercado"));
        jugadorDao = new JugadorDao();
        listaJugadores = FXCollections.observableArrayList();
        listaJugadores.addAll(jugadorDao.getAllJugadores());
        tabla.setItems(listaJugadores);
    }

    public void agregarJugador(Jugador j){
        listaJugadores.add(j);
    }
}
