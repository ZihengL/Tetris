package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

/**
 * Ripoff of the Super-Rotation-System.
 *
 * Originally planned to implement a dynamic system where you'd use the
 * inverse value, point.invert(), of the offsets of the invalid points,
 * but covering for all edge-cases would've been a nightmare and too time-consuming
 * to do for the planned timeline of this project.
 *
 * @author Zi
 * @date 1/8/2026
 */

public enum Kicks {
    A(new Point[][] {
            // NORTH -> EAST
            new Point[] { new Point(0, 0),  new Point(-1, 0),   new Point(-1, 1),   new Point(0, -2),   new Point(-1, -2) },
            // WEST -> NORTH
            new Point[] { new Point(0, 0),  new Point(-1, 0),   new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            // SOUTH -> WEST
            new Point[] { new Point(0, 0),  new Point(1, 0),    new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            // EAST -> SOUTH
            new Point[] { new Point(0, 0),  new Point(1, 0),    new Point(1, -1),   new Point(0, 2),    new Point(1, 2) },

            // NORTH -> WEST
            new Point[] { new Point(0, 0),  new Point(1, 0),    new Point(1, 1),    new Point(0, -2),   new Point(1, -2) },
            // WEST -> SOUTH
            new Point[] { new Point(0, 0),  new Point(-1, 0),   new Point(-1, -1),  new Point(0, 2),    new Point(-1, 2) },
            // SOUTH -> EAST
            new Point[] { new Point(0, 0),  new Point(-1, 0),   new Point(-1, 1),   new Point(0, -2),   new Point(-1, -2) },
            // EAST -> NORTH
            new Point[] { new Point(0, 0),  new Point(1, 0),    new Point(1, -1),   new Point(0, 2),    new Point(1, 2) }
    }),

    B(new Point[][] {
            // NORTH -> EAST
            new Point[] { new Point(0, 0),  new Point(-2, 0), new Point(1, 0),   new Point(-2, -1),  new Point(1, 2) },
            // WEST -> NORTH
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(-2, 0),  new Point(1, -2),   new Point(-2, 1) },
            // SOUTH -> WEST
            new Point[] { new Point(0, 0),  new Point(1, 0),  new Point(-2, 0),  new Point(1, -2),   new Point(-2, 1) },
            // EAST -> SOUTH
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(2, 0),   new Point(-1, 2),   new Point(2, -1) },

            // NORTH -> WEST
            new Point[] { new Point(0, 0),  new Point(-1, 0), new Point(2, 0),   new Point(-1, 2),   new Point(2, -1) },
            // WEST -> SOUTH
            new Point[] { new Point(0, 0),  new Point(-2, 0), new Point(1, 0),   new Point(-2, -1),  new Point(1, 2) },
            // SOUTH -> EAST
            new Point[] { new Point(0, 0),  new Point(2, 0),  new Point(-1, 0),  new Point(2, 1),    new Point(-1, -2) },
            // EAST -> NORTH
            new Point[] { new Point(0, 0),  new Point(2, 0),  new Point(-1, 0),  new Point(2, 1),    new Point(-1, -2) }
    }),

    C(null);

    public final Point[][] tables;

    private Kicks(Point[][] tables) {
        this.tables = tables;
    }
}
