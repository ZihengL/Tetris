package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Orientations {

    NORTH(new Point(0, 1)),
    WEST(new Point(-1, 0)),
    SOUTH(new Point(0, -1)),
    EAST(new Point(1, 0));

    public final Point p;

    private Orientations(Point p) {
        this.p = p;
    }

    public Orientations rotateRight() {
        Orientations[] values = Orientations.values();
        return this.equals(NORTH) ? EAST : values[this.ordinal() - 1];
    }

    public Orientations rotateLeft() {
        Orientations[] values = Orientations.values();
        return this.equals(EAST) ? NORTH : values[this.ordinal() + 1];
    }

    public Orientations opposite() {
        return this.rotateRight().rotateRight();
    }

    public boolean isFlipped() {
        return (this.ordinal() + 1) % 2 == 0;
    }

    // Wrapper for Point.add()
    public Point add(Orientations o) {
        return this.p.add(o.p);
    }
}
