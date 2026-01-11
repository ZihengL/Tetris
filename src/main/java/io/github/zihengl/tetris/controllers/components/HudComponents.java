package io.github.zihengl.tetris.controllers.components;


import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.services.IntegerGetter;

/**
 * @author Zi
 * @date 1/11/2026
 */

public enum HudComponents {

    SCORE("SCORE", Tetris.instance::getScore),
    LEVEL("LEVEL", Tetris.instance::getLevel),
    LINES("LINES", Tetris.instance::getLines);

    public final String title;
    public final IntegerGetter getter;

    private HudComponents(String title, IntegerGetter getter) {
        this.title = title;
        this.getter = getter;
    }
}
