package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.KeyEventCommand;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftCommand;
import io.github.zihengl.tetris.controllers.commands.controls.ShiftKeys;
import io.github.zihengl.tetris.controllers.components.BrickComponent;
import io.github.zihengl.tetris.models.objects.Grid;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.Callbacker;
import io.github.zihengl.tetris.models.services.Shifter;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class GameController {

    @FXML private GridPane gridpane;
    @FXML private VBox hudbox;

    @FXML
    private void initialize() {

        // SHIFT CONTROLS
        Shifter shifter = this.tetris::shift;
        for (ShiftKeys key : ShiftKeys.values())
            this.gridpane.addEventHandler(KeyEvent.KEY_PRESSED,
                    new ShiftCommand(key, shifter));

        // ROTATION CONTROLS
        Callbacker leftRotator = this.tetris::rotateLeft,
                rightRotator = this.tetris::rotateRight;
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

    private Tetris tetris;

    public void setGridpane(Tetris tetris) {
        this.tetris = tetris;

        Grid grid = this.tetris.getGrid();
        for (int y = 0; y < grid.height(); y++) {
            for (int x = 0; x < grid.width(y); x++) {
                BrickComponent component = new BrickComponent(x, y);

                this.tetris.addObserver(component);
                // TODO: MAKE COMPONENT INTO A NODE TO DO BELOW
                // NOTE: ALSO ADD THE NOTIFYOBSERVERS() INVOCATIONS IN TETRIS CLASS
                // gridpane.addchild(x, y, component)
            }
        }
    }

    public void reset() {
    }
}
