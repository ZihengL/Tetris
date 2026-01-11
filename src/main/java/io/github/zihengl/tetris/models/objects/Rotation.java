package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Tetros;

/**
 * @author Zi
 * @date 1/10/2026
 */

public class Rotation {

    public static Rotation clockwise(Orientations orientation) {
        return new Rotation(orientation, orientation.previous(), true);
    }

    public static Rotation counterClockwise(Orientations orientation) {
        return new Rotation(orientation, orientation.next(), false);
    }

    // INSTANCE

    public final Orientations before;
    public final Orientations after;

    public final boolean isClockwise;

    private Rotation(Orientations before, Orientations after, boolean isClockwise) {
        this.before = before;
        this.after = after;
        this.isClockwise = isClockwise;
    }

    public Point applyTo(Point offset) {
        if (this.after.isFlipped())
            offset = offset.flip();

        return offset.compProduct(this.after.quadrant);
    }

    public Rotation invert() {
        return new Rotation(this.after, this.before, !this.isClockwise);
    }

    public Point[] getKickTable(Tetros type) {
        Point[][] tables = type.kicks.tables;

        if (this.isClockwise)
            return tables[this.before.ordinal()];

        int middle = tables.length / 2;
        return tables[middle + this.before.ordinal()];
    }

    // STATIC
}
