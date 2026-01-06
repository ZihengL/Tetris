package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Orientations {

    NORTH(new Point(0, 1)),
    WEST(new Point(-1, 0)),
    SOUTH(new Point(0, -1)),
    EAST(new Point(1, 0));

    public final Point unit;

    private Orientations(Point unit) {
        this.unit = unit;
    }

    public Orientations next() {
        return this.equals(NORTH) ? EAST : values()[this.ordinal() + 1];
    }

    public Orientations previous() {
        return this.equals(EAST) ? NORTH : values()[this.ordinal() - 1];
    }

    public Orientations opposite() {
        return this.next().next();
    }

    public boolean isFlipped() {
        return (this.ordinal() + 1) % 2 == 0;
    }
}
