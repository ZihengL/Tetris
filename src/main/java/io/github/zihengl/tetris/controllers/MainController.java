package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.*;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.Callbacker;
import io.github.zihengl.tetris.models.services.Shifter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {

    public GridPane gridpane;
    @FXML private StackPane stacks;

    private double time;

    public VBox mainpane;

    @FXML
    private void initialize() {
        this.mainpane.setOnMouseClicked(mouseEvent -> {
            this.mainpane.requestFocus();
        });

        Tetris tetris = new Tetris();

        // NOTE: SHIFT CONTROLS
        Shifter shifter = tetris::shift;
        for (ShiftKeys key : ShiftKeys.values())
            this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new ShiftCommand(key, shifter));

        // NOTE: ROTATION CONTROLS
        Callbacker leftRotator = tetris::rotateLeft,
                   rightRotator = tetris::rotateRight;
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new KeyEventCommand(KeyCode.Z, leftRotator));
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new KeyEventCommand(KeyCode.X, rightRotator));
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new KeyEventCommand(KeyCode.UP, rightRotator));

        // NOTE: DROP CONTROLS
        Callbacker dropper = tetris::drop;
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new KeyEventCommand(KeyCode.SPACE, dropper));

        // TODO: ADD PAUSE BUTTON IN THE FUTURE
        // NOTE: USE STACKPANE TO SHOW MENU WHEN PAUSED
        // TOGGLE BETWEEN GAMESTATES FOR PAUSING

        // NOTE: FOR CONSOLE TESTING
        this.time = 0;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    this.time += 0.1;
                    if (this.time == 1.) {
                        tetris.shift(Orientations.SOUTH);
                        this.time = 0;
                    }

                    System.out.println(tetris);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}