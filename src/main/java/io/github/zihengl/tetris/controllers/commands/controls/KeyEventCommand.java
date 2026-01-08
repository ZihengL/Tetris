package io.github.zihengl.tetris.controllers.commands.controls;

import io.github.zihengl.tetris.models.services.Callbacker;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Zi
 * @date 1/8/2026
 *
 * Generic command class responding to key presses to callback
 * a method without parameters if the key pressed is equal to the code here.
 */

public class KeyEventCommand implements EventHandler<KeyEvent> {

    protected final KeyCode code;
    protected final Callbacker callbacker;

    public KeyEventCommand(KeyCode code, Callbacker callbacker) {
        this.code = code;
        this.callbacker = callbacker;
    }

    @Override
    public void handle(KeyEvent event) {
        if (this.code == event.getCode())
            this.callbacker.callback();
    }
}
