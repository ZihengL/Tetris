package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Tetrominos {

    L(new Point[] { new Point(0, 1), new Point(0, -1), new Point(1, -1) }),
    J(new Point[] { new Point(-1, -1), new Point(0, -1), new Point(0, 1) }),
    Z(new Point[] { new Point(-1, 1), new Point(0, 1), new Point(1, 0) }),
    S(new Point[] { new Point(-1, 0), new Point(0, 1), new Point(1, 1) }),
    I(new Point[] { new Point(0, -2), new Point(0, -1), new Point(0, 1) }),
    T(new Point[] { new Point(-1, 0), new Point(0, 1), new Point(1, 0) }),
    O(new Point[] { new Point(1, 0), new Point(0, 1), new Point(1, 1) });

    public final Point[] offsets;

    // Offsets assumes it's pointing towards the first Quadrant.
    private Tetrominos(Point[] offsets) {
        this.offsets = offsets;
    }
}
