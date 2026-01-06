package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

// Lazily implemented copy of the Super Rotation System.
public enum KickTables {

    A(new Point[][] {
            new Point[] { new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(1, 0),  new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            new Point[] { new Point(1, 0),  new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            new Point[] { new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(1, 0),  new Point(1, -1),   new Point(0, 2),    new Point(1, 2) },
            new Point[] { new Point(-1, 0), new Point(-1, 1),   new Point(0, -2),   new Point(-1, -2) },
            new Point[] { new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(1, 0),  new Point(1, -1),   new Point(0, 2),    new Point(1, 2) }
    }),

    B(new Point[][] {
            new Point[] { new Point(-2, 0), new Point(1, 0),    new Point(-2, 1),   new Point(1, -2) },
            new Point[] { new Point(2, 0),  new Point(-1, 0),   new Point(2, -1),   new Point(-1, 2) },
            new Point[] { new Point(-1, 0), new Point(2, 0),    new Point(-1, -2),  new Point(2, 1) },
            new Point[] { new Point(1, 0),  new Point(-2, 0),   new Point(1, 2),    new Point(-2, -1) },
            new Point[] { new Point(2, 0),  new Point(-1, 0),   new Point(2, -1),   new Point(-1, 2) },
            new Point[] { new Point(-2, 0), new Point(1, 0),    new Point(-2, 1),   new Point(1, -2) },
            new Point[] { new Point(1, 0),  new Point(-2, 0),   new Point(1, 2),    new Point(-2, -1) },
            new Point[] { new Point(-1, 0), new Point(2, 0),    new Point(-1, -2),  new Point(2, 1) }
    }),

    C(null);

    public final Point[][] tables;

    private KickTables(Point[][] tables) {
        this.tables = tables;
    }

    public Point[] getTable(Orientations before, Orientations after) {
        if (this.equals(KickTables.C))
            return new Point[] { };

        for (Rotations r : Rotations.values())
            if (before.equals(r.before) && after.equals(r.after))
                return this.tables[r.ordinal()];

        return new Point[] { };
    }
}
