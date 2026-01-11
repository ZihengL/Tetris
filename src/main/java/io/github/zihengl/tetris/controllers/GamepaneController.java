package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.controls.KeyEventCommand;
import io.github.zihengl.tetris.controllers.commands.controls.Keymaps;
import io.github.zihengl.tetris.controllers.components.BrickComponent;
import io.github.zihengl.tetris.controllers.components.HudComponent;
import io.github.zihengl.tetris.controllers.components.HudComponents;
import io.github.zihengl.tetris.controllers.components.TetropeekComponent;
import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.objects.Grid;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class GamepaneController implements Observer {

    public static final String RES_DIR = "/io/github/zihengl/tetris/img/";

    @FXML private GridPane gridpane;

    // HUD
    @FXML private VBox peekbox;

    @FXML private VBox scorebox;
    @FXML private HudComponent scoreboxController;
    @FXML private VBox levelbox;
    @FXML private HudComponent levelboxController;
    @FXML private VBox linesbox;
    @FXML private HudComponent linesboxController;

    @FXML
    private void initialize() {
        this.gridpane.setOnMouseClicked(mouseEvent -> {
            this.gridpane.requestFocus();
        });

        Tetris tetris = Tetris.instance;

        // GRIDPANE
        Grid grid = tetris.getGrid();
        for (int y = 0; y < Grid.BUFFER; y++)
            for (int x = 0; x < Grid.WIDTH; x++) {
                BrickComponent component = new BrickComponent();
                grid.get(x, y).setUpdater(component);

                this.gridpane.add(component, x, Grid.BUFFER - y);
            }

        // CONTROLS
        for (Keymaps keymap : Keymaps.values())
            this.gridpane.addEventHandler(
                    KeyEvent.KEY_PRESSED,
                    new KeyEventCommand(keymap.code, keymap.callbacker)
            );

        // PEEK
        for (int i = Tetris.PEEK_SIZE - 1; i >= 0; i--) {
            TetropeekComponent component = new TetropeekComponent(i);
            tetris.addObserver(component);

            this.peekbox.getChildren().add(component);
        }

        this.scoreboxController.set("SCORE", tetris::getScore);
        this.levelboxController.set("LEVEL", tetris::getLevel);
        this.linesboxController.set("LINES", tetris::getLines);

        tetris.addObserver(this);
        this.update(tetris);
    }

    @Override
    public void update(Observable observable) {
        this.scoreboxController.update();
        this.levelboxController.update();
        this.linesboxController.update();

//        int score = tetris.getScore();
//        this.txtScore.setText(String.valueOf(score));
//
//        int level = tetris.getLevel();
//        this.txtLevel.setText(String.valueOf(level));
//
//        int lines = tetris.getLines();
//        this.txtLines.setText(String.valueOf(lines));
    }

    public Image getIcon(Tetros type) {
        String path = RES_DIR + "ICON_" + type + ".png";
        URL url = GamepaneController.class.getResource(path);

        return new Image(url.toString());
    }

    public void requestFocus() {
        this.gridpane.setFocusTraversable(true);
        this.gridpane.requestFocus();
    }

    public void play() {
        this.gridpane.requestFocus();
        Tetris.instance.reset();
    }
}
