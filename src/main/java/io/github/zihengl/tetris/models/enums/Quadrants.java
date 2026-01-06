package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Quadrants {

    I(Orientations.EAST.add(Orientations.NORTH)),   // (1, 1)
    II(Orientations.NORTH.add(Orientations.WEST)),  // (-1, 1)
    III(Orientations.WEST.add(Orientations.SOUTH)), // (-1, -1)
    IV(Orientations.SOUTH.add(Orientations.EAST));  // (1, -1)

    public final Point p;

    Quadrants(Point p) {
        this.p = p;
    }

    public Quadrants next() {
        Quadrants[] values = Quadrants.values();
        return this.equals(IV) ? I : values[this.ordinal() + 1];
    }

    public Quadrants previous() {
        Quadrants[] values = Quadrants.values();
        return this.equals(I) ? IV : values[this.ordinal() - 1];
    }

    public Quadrants opposite() {
        return this.next().next();
    }

    // returns true if equals II or IV
    public boolean isFlipped() {
        return (this.ordinal() + 1) % 2 == 0;
    }

    public Point turn(Point p) {
        return this.isFlipped() ?
                new Point(p.y * this.p.x, p.x * this.p.y) :
                new Point(p.x * this.p.x, p.y * this.p.y);
    }

    public Orientations getEquivalent() {
        return Orientations.values()[this.ordinal()];
    }
}
