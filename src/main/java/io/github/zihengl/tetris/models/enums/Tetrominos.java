package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

import java.util.Random;

public enum Tetrominos {

    L(new Point[] { new Point(0, -1), new Point(0, -2), new Point(1, -2) }),
    J(new Point[] { new Point(0, -1), new Point(0, -2), new Point(-1, -2) }),
    Z(new Point[] { new Point(1, 0), new Point(1, -1), new Point(2, -1) }),
    S(new Point[] { new Point(-1, 0), new Point(-1, -1), new Point(-2, -1) }),
    I(new Point[] { new Point(0, -1), new Point(0, -2), new Point(0, -3) }),
    T(new Point[] { new Point(0, -1), new Point(-1, -1), new Point(1, -1) }),
    O(new Point[] { new Point(1, 0), new Point(0, -1), new Point(1, -1) });

    public final Point[] offsets;

    // Offsets assumes initial Orientation is North.
    private Tetrominos(Point[] offsets) {
        this.offsets = offsets;
    }
}
