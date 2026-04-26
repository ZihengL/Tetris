package io.github.zihengl.tetris.controllers.commands.menu;


import io.github.zihengl.tetris.controllers.commands.Command;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * Class: QuitCommand
 * Created on: 4/26/2026
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class QuitCommand implements Command<ActionEvent> {


    @Override
    public void handle(ActionEvent event) {
        Platform.exit();
    }
}
