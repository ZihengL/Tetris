package io.github.zihengl.tetris.controllers;

import io.github.zihengl.tetris.controllers.commands.menu.PlayCommand;
import io.github.zihengl.tetris.controllers.commands.menu.QuitCommand;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class MenupaneController {

    @FXML private VBox menupane;

    @FXML private Button btnPlay;
    @FXML private Button btnQuit;

    @FXML
    private void initialize() {
//        this.btnPlay.setOnAction(new PlayCommand());
//        this.btnQuit.setOnAction(new QuitCommand());
    }
}
