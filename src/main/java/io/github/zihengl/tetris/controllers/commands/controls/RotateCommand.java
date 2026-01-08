package io.github.zihengl.tetris.controllers.commands.controls;

import io.github.zihengl.tetris.models.services.Rotator;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class RotateCommand implements EventHandler<KeyEvent> {

    private final KeyCode code;
    private final Rotator rotator;

    public RotateCommand(KeyCode code, Rotator rotator) {
        this.code = code;
        this.rotator = rotator;
    }

    @Override
    public void handle(KeyEvent event) {
        if (this.code == event.getCode())
            this.rotator.rotate();
    }
}
