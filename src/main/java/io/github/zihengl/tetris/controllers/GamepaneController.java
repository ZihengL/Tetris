package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.KeyEventCommand;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftCommand;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftKeys;
import io.github.zihengl.tetris.controllers.components.BrickComponent;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Grid;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.Callbacker;
import io.github.zihengl.tetris.models.services.Shifter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class GamepaneController {

    @FXML private GridPane gridpane;
    @FXML private VBox hudbox;

//    private final Tetris tetris = Tetris.tetris;

    @FXML
    private void initialize() {
        this.gridpane.setOnMouseClicked(mouseEvent -> {
            this.gridpane.requestFocus();
        });

        Tetris tetris = Tetris.tetris;

        // GRIDPANE
        Grid grid = tetris.getGrid();
        for (int y = 0; y < Grid.BUFFER; y++)
            for (int x = 0; x < Grid.WIDTH; x++) {
                BrickComponent component = new BrickComponent();
                grid.get(x, y).setUpdater(component);

                this.gridpane.add(component, x, Grid.BUFFER - y);
            }

        // SHIFT CONTROLS
        Shifter shifter = tetris::shift;
        for (ShiftKeys key : ShiftKeys.values())
            this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                    new ShiftCommand(key, shifter));

        // ROTATION CONTROLS
        Callbacker leftRotator = tetris::rotateLeft,
                   rightRotator = tetris::rotateRight;
        this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyEventCommand(KeyCode.Z, leftRotator));
        this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyEventCommand(KeyCode.X, rightRotator));
        this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyEventCommand(KeyCode.UP, rightRotator));

        // DROP CONTROLS
        Callbacker dropper = tetris::drop;
        this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyEventCommand(KeyCode.SPACE, dropper));
    }

    public void requestFocus() {
        this.gridpane.setFocusTraversable(true);
        this.gridpane.requestFocus();
    }

    public void play() {
        this.gridpane.requestFocus();
        Tetris.tetris.reset();
    }
}
