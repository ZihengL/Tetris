package io.github.zihengl.tetris.controllers.commands.controls;


import io.github.zihengl.tetris.models.enums.Orientations;
import javafx.scene.input.KeyCode;

/**
 * @author Zi
 * @date 1/7/2026
 */

public enum ShiftKeys {

    LEFT(KeyCode.LEFT, Orientations.WEST),
    DOWN(KeyCode.DOWN, Orientations.SOUTH),
    RIGHT(KeyCode.RIGHT, Orientations.EAST);

    public final KeyCode code;
    public final Orientations orientation;

    private ShiftKeys(KeyCode code, Orientations orientation) {
        this.code = code;
        this.orientation = orientation;
    }
}
