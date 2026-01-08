package io.github.zihengl.tetris.controllers.commands.controls;

import io.github.zihengl.tetris.models.services.Shifter;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * @author Zi
 * @date 1/7/2026
 */

public class ShiftCommand implements EventHandler<KeyEvent> {

    private final ShiftKeys keys;
    private final Shifter shifter;

    public ShiftCommand(ShiftKeys keys, Shifter shifter) {
        this.keys = keys;
        this.shifter = shifter;
    }

    @Override
    public void handle(KeyEvent event) {
        if (this.keys.code == event.getCode())
            this.shifter.shift(this.keys.orientation);
    }
}
