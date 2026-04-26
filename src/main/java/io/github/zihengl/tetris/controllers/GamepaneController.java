package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.KeyEventCommand;
import io.github.zihengl.tetris.controllers.commands.controls.Keymaps;
import io.github.zihengl.tetris.controllers.components.BrickComponent;
import io.github.zihengl.tetris.controllers.components.HudComponent;
import io.github.zihengl.tetris.controllers.components.PeekComponent;
import io.github.zihengl.tetris.models.objects.Grid;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class GamepaneController {

    @FXML private GridPane gridpane;

    // HUD
    @FXML private VBox peekbox;

    @FXML private VBox scorebox;
    @FXML private HudComponent scoreboxController;
    @FXML private VBox levelbox;
    @FXML private HudComponent levelboxController;
    @FXML private VBox linesbox;
    @FXML private HudComponent linesboxController;

    public GridPane getGridpane() {
        return this.gridpane;
    }

    @FXML
    private void initialize() {
        Tetris tetris = Tetris.instance;

        // GRID
        Grid grid = tetris.getGrid();
        for (int y = 0; y < Grid.BUFFER; y++)
            for (int x = 0; x < Grid.WIDTH; x++) {
                BrickComponent component = new BrickComponent();

                grid.get(x, y).setUpdater(component);
                this.gridpane.add(component, x, Grid.BUFFER - y);
            }

        // CONTROLS
        for (Keymaps keymap : Keymaps.values()) {
            KeyEventCommand keyCommand = new KeyEventCommand(keymap.code, keymap.callbacker);
            this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED, keyCommand);
        }

        // PEEK
        for (int i = 0; i < Tetris.PEEK_SIZE; i++) {
            PeekComponent component = new PeekComponent(i);
            tetris.addObserver(component);

            this.peekbox.getChildren().add(component);
        }

        // SCORE, LEVEL, LINES
        this.scoreboxController.set("SCORE", tetris::getScore, tetris);
        this.levelboxController.set("LEVEL", tetris::getLevel, tetris);
        this.linesboxController.set("LINES", tetris::getLines, tetris);
    }
}
