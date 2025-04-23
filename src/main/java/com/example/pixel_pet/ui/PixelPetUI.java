// File: PixelPetUI.java (JavaFX Launcher)
package com.example.pixel_pet.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PixelPetUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(PixelPetUI.class.getResource("/fxml/dashboard.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("PixelPet");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
