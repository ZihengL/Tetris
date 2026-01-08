package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.DropCommand;
import io.github.zihengl.tetris.controllers.commands.controls.RotateCommand;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftKeys;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftCommand;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.Dropper;
import io.github.zihengl.tetris.models.services.Rotator;
import io.github.zihengl.tetris.models.services.Shifter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {

    public GridPane gridpane;

    private double time;

//    public Tetris tetris = Tetris.tetris;
    public VBox mainpane;

    @FXML
    private void initialize() {
        this.mainpane.setOnMouseClicked(mouseEvent -> {
            this.mainpane.requestFocus();
        });

        Tetris tetris = new Tetris();

        // NOTE: TRANSLATION CONTROLS
        Shifter shifter = tetris::shift;
        for (ShiftKeys key : ShiftKeys.values())
            this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new ShiftCommand(key, shifter));

        // NOTE: ROTATION CONTROLS
        Rotator leftRotator = tetris::rotateLeft,
                rightRotator = tetris::rotateRight;
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new RotateCommand(KeyCode.Z, leftRotator));
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new RotateCommand(KeyCode.X, rightRotator));
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new RotateCommand(KeyCode.UP, rightRotator));

        // NOTE: DROP CONTROLS
        Dropper dropper = tetris::drop;
        this.mainpane.addEventHandler(KeyEvent.KEY_PRESSED,
                                new DropCommand(KeyCode.SPACE, dropper));

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