package org.mcm.gestorligas.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mcm.gestorligas.LigasApp;
import org.mcm.gestorligas.dao.UsuarioDao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    //tantas variables como elementos graficos a controlar
    @FXML //con el decorador para linkar con el fxml, va a buscar la variable en el archivo fxml
    private Button btnLogin, btnVaciar; //nombre de variable ha de ser el mismo que en el fxml

    @FXML
    private TextField editCorreo, editPass;

    private UsuarioDao usuarioDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instancias();
        acciones();
        
    }

    private void instancias() {
        usuarioDao = new UsuarioDao();
    }

    private void acciones() {

        /*
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {      //igual a setOnClickListener en kotlin
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        }); */

        btnLogin.setOnAction(actionEvent -> {
            if(editCorreo.getText().isEmpty() || editPass.getText().isEmpty()){
                //aviso
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos");
                alert.setContentText("Faltan datos por rellenar");
                alert.showAndWait();
            }else{
                //ahora mismo se simula
                    //if(editCorreo.getText().equalsIgnoreCase("m@mail.com")&&editPass.getText().equals("1234")){
                //login -> a BBDD -> Hibernate

                 if(usuarioDao.getUusarioLogin(editCorreo.getText(),editPass.getText())!=null) {
                     System.out.println("Login Correcto");
                    //cargar el fxml
                    FXMLLoader fxmlLoader = new FXMLLoader(LigasApp.class.getResource("main-view.fxml")); //carga el fxml

                    try {
                        Parent root = fxmlLoader.load();

                        //Stage
                        //Scene
                        Scene scene = new Scene(root); //si no se especifica, usa el tamaño por defecto del scene segun el fxml
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Ventana principal");
                        //Show
                        stage.show();

                        //cerrar ventana anterior
                        ((Stage)(btnLogin.getScene().getWindow())).close(); //window se castea a Stage y se cierra

                    } catch (IOException e) {
                        System.out.println("Fallo en la carga del XML");
                    }
                }else{
                     System.out.println("Login Incorrecto");
                     //fallo de credenciales
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                     alert.setTitle("Error de credenciales");
                     alert.setContentText("Correo o contraseña incorrectos");
                     alert.showAndWait();
                }
            }
        });


        btnVaciar.setOnAction(actionEvent -> {
            editCorreo.clear();
            editPass.clear();
        });


    }
}
