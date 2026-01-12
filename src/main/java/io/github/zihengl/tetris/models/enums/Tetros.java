package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;
import javafx.scene.image.Image;

/**
 * @author Zi
 * @date 1/8/2026
 *
 * Defines all individual pieces in the classic Tetris game.
 *
 * Offsets defines the deltas between the pivot and each of its
 * dependent subcomponent. Each type of piece also has an associated
 * kick table, used to
 */

public enum Tetros {

    J(new Point[] { new Point(-1, 1),  new Point(-1, 0),   new Point(1, 0) },   Kicks.A),
    L(new Point[] { new Point(-1, 0),    new Point(1, 0),   new Point(1, 1) },  Kicks.A),
    S(new Point[] { new Point(-1, 0),   new Point(0, 1),    new Point(1, 1) },   Kicks.A),
    T(new Point[] { new Point(-1, 0),   new Point(0, 1),    new Point(1, 0) },   Kicks.A),
    Z(new Point[] { new Point(-1, 1),   new Point(0, 1),    new Point(1, 0) },   Kicks.A),
    I(new Point[] { new Point(-2, 0),   new Point(-1, 0),   new Point(1, 0) },   Kicks.B),
    O(new Point[] { new Point(1, 0),    new Point(0, 1),    new Point(1, 1) },   Kicks.C);

    public final Point[] offsets;
    public final Kicks kicks;

    // Offsets assumes it's pointing North.
    private Tetros(Point[] offsets, Kicks kicks) {
        this.offsets = offsets;
        this.kicks = kicks;
    }
}
