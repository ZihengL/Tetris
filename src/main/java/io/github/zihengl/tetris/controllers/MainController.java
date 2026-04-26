package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.menu.PlayCommand;
import io.github.zihengl.tetris.controllers.commands.menu.QuitCommand;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController implements Observer {
    @FXML private StackPane mainpane;

    @FXML private VBox menupane;
    @FXML private Button btnPlay;
    @FXML private Button btnQuit;

    @FXML private HBox gamepane;
    @FXML private GamepaneController gamepaneController;

    @FXML
    private void initialize() {
        GridPane gridpane = this.gamepaneController.getGridpane();
        this.btnPlay.setOnAction(new PlayCommand(this.menupane, gridpane));
        this.btnQuit.setOnAction(new QuitCommand());

        this.menupane.setVisible(true);
        this.menupane.toFront();

        Tetris.instance.addObserver(this);

        // TODO: CONSIDER ADDING PAUSE FEATURE IN THE FUTURE
    }

    @Override
    public void update(Observable observable) {
        Tetris tetris = (Tetris) observable;

        if (tetris.isGameover())
            this.menupane.toFront();
    }
}