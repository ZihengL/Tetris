package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {

    @FXML private VBox mainpane;
    @FXML private StackPane stacks;

    @FXML private HBox gamepane;
    @FXML private GamepaneController gamepaneController;

    @FXML private VBox menupane;
    @FXML private MenupaneController menupaneController;

    @FXML
    private void initialize() {
        this.mainpane.setOnMouseClicked(mouseEvent -> {
            this.gamepaneController.requestFocus();
            this.gamepaneController.play();
        });

        // TODO: ADD PAUSE BUTTON IN THE FUTURE
        // NOTE: USE STACKPANE TO SHOW MENU WHEN PAUSED
        // TOGGLE BETWEEN GAMESTATES FOR PAUSING
    }

    public void toggleScreen() {

    }
}