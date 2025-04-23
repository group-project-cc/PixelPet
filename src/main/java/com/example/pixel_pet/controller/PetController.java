package com.example.pixel_pet.controller;

import com.example.pixel_pet.model.Pet;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.net.URL;

public class PetController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ComboBox<String> petSelector;

    @FXML
    private ComboBox<String> actionSelector;

    @FXML
    private TextField petNameField;

    @FXML
    private Label petStatusLabel;

    @FXML
    private ProgressBar hungerBar, happinessBar;

    @FXML
    private WebView petGifView;

    private Pet currentPet;

    @FXML
    private void initialize() {
        petSelector.setItems(FXCollections.observableArrayList("cats", "puppy", "snowy"));
        actionSelector.setItems(FXCollections.observableArrayList(
                "idle", "sleeping", "eating", "petting",
                "walking_left", "walking_right", "sit_to_sleep", "sleep_to_sit", "scarf"
        ));

        petSelector.setOnAction(e -> adoptPet());
    }

    private void adoptPet() {
        String type = petSelector.getValue();
        currentPet = new Pet("Unnamed", type);
        setTheme(type);
        updatePetGif(type, "idle");
        updatePetStatus();
    }

    @FXML
    private void namePet() {
        String name = petNameField.getText();
        if (currentPet != null && !name.isBlank()) {
            currentPet.setName(name);
            updatePetStatus();
        }
    }

    @FXML
    private void feedPet() {
        if (currentPet != null) {
            currentPet.feed();
            hungerBar.setProgress(currentPet.getHungerLevel() / 100.0);
            updatePetStatus();
            updatePetGif(currentPet.getType(), "eating");
        }
    }

    @FXML
    private void playWithPet() {
        if (currentPet != null) {
            currentPet.play();
            happinessBar.setProgress(currentPet.getHappinessLevel() / 100.0);
            updatePetStatus();
            updatePetGif(currentPet.getType(), "petting");
        }
    }

    @FXML
    private void performAction() {
        if (currentPet != null) {
            String action = actionSelector.getValue();
            if (action != null) {
                updatePetGif(currentPet.getType(), action);
            }
        }
    }

    private void updatePetGif(String type, String action) {
        String gifFileName;

        switch (type) {
            case "cats" -> {
                switch (action) {
                    case "idle" -> gifFileName = "idle_cat.gif";
                    case "sleeping" -> gifFileName = "sleeping_cat.gif";
                    case "walking_left" -> gifFileName = "walk_Left.gif";
                    case "walking_right" -> gifFileName = "walk_right.gif";
                    case "sit_to_sleep" -> gifFileName = "idle_to_sleep.gif";
                    case "sleep_to_sit" -> gifFileName = "sleep_to_idle.gif";
                    default -> gifFileName = "idle_cat.gif";
                }
            }
            case "puppy" -> {
                switch (action) {
                    case "idle" -> gifFileName = "puppy_wagging.gif";
                    case "sleeping" -> gifFileName = "puppy_sleeping.gif";
                    case "eating" -> gifFileName = "puppy_eating.gif";
                    case "petting" -> gifFileName = "puppy_petting.gif";
                    default -> gifFileName = "puppy_jumping.gif";
                }
            }
            case "snowy" -> {
                switch (action) {
                    case "idle" -> gifFileName = "idle.gif";
                    case "sleeping" -> gifFileName = "sleepy_snowy.gif";
                    case "eating" -> gifFileName = "nom_nom.gif";
                    case "petting" -> gifFileName = "petting.gif"; // fixed typo
                    case "scarf" -> gifFileName = "eep.gif";
                    default -> gifFileName = "idle.gif";
                }
            }
            default -> gifFileName = "idle.gif";
        }

        String fullPath = "/images/" + type + "/" + gifFileName;
        URL resource = getClass().getResource(fullPath);

        if (resource == null) {
            petStatusLabel.setText("⚠️ Missing GIF: " + fullPath);
            return;
        }

        String gifPath = resource.toExternalForm();
        String html = "<html><body style='margin:0; padding:0; background:black;'>" +
                "<img src='" + gifPath + "' style='width:100%; height:100%; object-fit:contain;'/>" +
                "</body></html>";

        petGifView.getEngine().loadContent(html);
    }

    private void updatePetStatus() {
        if (currentPet != null) {
            petStatusLabel.setText(
                    "✨ " + currentPet.getType().toUpperCase() + " | Name: " + currentPet.getName() +
                            " | Hunger: " + currentPet.getHungerLevel() +
                            " | Happiness: " + currentPet.getHappinessLevel()
            );
        }
    }

    private void setTheme(String petType) {
        String style;
        switch (petType) {
            case "cats":
                style = "-fx-background-color: lavender;";
                break;
            case "puppy":
                style = "-fx-background-color: lightgoldenrodyellow;";
                break;
            case "snowy":
                style = "-fx-background-color: lightcyan;";
                break;
            default:
                style = "-fx-background-color: white;";
                break;
        }
        rootPane.setStyle(style);
    }


}
