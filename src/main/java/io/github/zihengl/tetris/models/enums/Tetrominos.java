package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Tetrominos {

    J(new Point[] { new Point(-1, -1), new Point(0, -1), new Point(0, 1) }, KickTables.A),
    L(new Point[] { new Point(0, 1), new Point(0, -1), new Point(1, -1) }, KickTables.A),
    S(new Point[] { new Point(-1, 0), new Point(0, 1), new Point(1, 1) }, KickTables.A),
    T(new Point[] { new Point(-1, 0), new Point(0, 1), new Point(1, 0) }, KickTables.A),
    Z(new Point[] { new Point(-1, 1), new Point(0, 1), new Point(1, 0) }, KickTables.A),
    I(new Point[] { new Point(0, -2), new Point(0, -1), new Point(0, 1) }, KickTables.B),
    O(new Point[] { new Point(1, 0), new Point(0, 1), new Point(1, 1) }, KickTables.C);

    public final Point[] offsets;
    public final KickTables kicks;

    // Offsets assumes it's pointing towards the first Quadrant.
    private Tetrominos(Point[] offsets, KickTables kicks) {
        this.offsets = offsets;
        this.kicks = kicks;
    }
}
