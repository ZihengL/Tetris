package io.github.zihengl.tetris.controllers.commands.controls;

import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.Callbacker;
import javafx.scene.input.KeyCode;

/**
 * Premapped key associations so that controls aren't
 * hardcoded in the GamepaneController. Added parameter-free
 * commands in Tetris so that
 *
 * @author Zi
 * @date 1/10/2026
 */

public enum Keymaps {

    UP(KeyCode.UP, Tetris.instance::rotateRight),
    LEFT(KeyCode.LEFT, Tetris.instance::shiftLeft),
    DOWN(KeyCode.DOWN, Tetris.instance::shiftDown),
    RIGHT(KeyCode.RIGHT, Tetris.instance::shiftRight),
    Z(KeyCode.Z, Tetris.instance::rotateLeft),
    X(KeyCode.X, Tetris.instance::rotateRight),
    SPACE(KeyCode.SPACE, Tetris.instance::drop);

    public final KeyCode code;
    public final Callbacker callbacker;

    private Keymaps(KeyCode code, Callbacker callbacker) {
        this.code = code;
        this.callbacker = callbacker;
    }
}
