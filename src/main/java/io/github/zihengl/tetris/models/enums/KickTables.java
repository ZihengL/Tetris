package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

/**
 * @author Zi
 * @date 1/8/2026
 *
 * Ripoff of the Super-Rotation-System.
 *
 * Originally planned to implement a dynamic system where you'd use the
 * inverse value, point.invert(), of the offsets of the invalid points,
 * but covering for all edge-cases would've been a nightmare and too time-consuming
 * to do for the planned timeline of this project.
 */

public enum KickTables {

    A(new Point[][] {
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(1, -1),   new Point(0, 2),    new Point(1, 2) },
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(-1, 1),   new Point(0, -2),   new Point(-1, -2) },
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(1, -1),   new Point(0, 2),    new Point(1, 2) }
    }),

    B(new Point[][] {
            new Point[] { new Point(0, 0),  new Point(-2, 0), new Point(1, 0),    new Point(-2, 1),   new Point(1, -2) },
            new Point[] { new Point(0, 0),  new Point(2, 0),  new Point(-1, 0),   new Point(2, -1),   new Point(-1, 2) },
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(2, 0),    new Point(-1, -2),  new Point(2, 1) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(-2, 0),   new Point(1, 2),    new Point(-2, -1) },
            new Point[] { new Point(0, 0),  new Point(2, 0),  new Point(-1, 0),   new Point(2, -1),   new Point(-1, 2) },
            new Point[] { new Point(0, 0),  new Point(-2, 0), new Point(1, 0),    new Point(-2, 1),   new Point(1, -2) },
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(-2, 0),   new Point(1, 2),    new Point(-2, -1) },
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(2, 0),    new Point(-1, -2),  new Point(2, 1) }
    }),

    C(new Point[][] {
            new Point[] { new Point(0, 0) }
    });

    public final Point[][] tables;

    private KickTables(Point[][] tables) {
        this.tables = tables;
    }
}
