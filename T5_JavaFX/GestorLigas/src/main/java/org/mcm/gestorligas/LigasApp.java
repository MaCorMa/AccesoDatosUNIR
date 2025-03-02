package org.mcm.gestorligas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LigasApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LigasApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());  //,width , height)
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show(); //hace visible la ventana
    }

    public static void main(String[] args) {
        launch();
    }
}