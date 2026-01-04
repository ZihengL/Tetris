package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Quadrants {

    I(new Point(1, 1)),
    II(new Point(-1, 1)),
    III(new Point(-1, -1)),
    IV(new Point(1, -1));

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

    public Point compensate(Point p) {
        return this.isFlipped() ?
                new Point(p.y * this.p.x, p.x * this.p.y) :
                new Point(p.x * this.p.x, p.y * this.p.y);
    }
}
