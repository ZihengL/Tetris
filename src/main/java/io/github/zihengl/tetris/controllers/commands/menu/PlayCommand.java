package io.github.zihengl.tetris.controllers.commands.menu;


import io.github.zihengl.tetris.controllers.MainController;
import io.github.zihengl.tetris.controllers.commands.Command;
import io.github.zihengl.tetris.models.objects.Tetris;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Class: PlayCommand
 * Created on: 4/26/2026
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PlayCommand implements Command<ActionEvent> {

    private final VBox menupane;
    private final GridPane gridpane;

    public PlayCommand(VBox menupane, GridPane gridpane) {
        this.menupane = menupane;
        this.gridpane = gridpane;
    }

    @Override
    public void handle(ActionEvent event) {
        this.gridpane.setFocusTraversable(true);
        this.gridpane.requestFocus();
        this.menupane.toBack();

        Tetris.instance.reset();
    }
}
