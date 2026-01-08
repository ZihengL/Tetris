package io.github.zihengl.tetris.controllers.commands.controls;

import io.github.zihengl.tetris.models.services.Dropper;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class DropCommand implements EventHandler<KeyEvent> {

    private final KeyCode code;
    private final Dropper dropper;

    public DropCommand(KeyCode code, Dropper dropper) {
        this.code = code;
        this.dropper = dropper;
    }


    @Override
    public void handle(KeyEvent event) {
        if (this.code == event.getCode())
            this.dropper.drop();
    }
}
