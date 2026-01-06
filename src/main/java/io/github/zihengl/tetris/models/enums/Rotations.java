package io.github.zihengl.tetris.models.enums;

import io.github.zihengl.tetris.models.objects.Point;

public enum Rotations {

    NtoE(Orientations.NORTH, Orientations.EAST),
    EtoN(Orientations.EAST, Orientations.NORTH),
    EtoS(Orientations.EAST, Orientations.SOUTH),
    StoE(Orientations.SOUTH, Orientations.EAST),
    StoW(Orientations.SOUTH, Orientations.WEST),
    WtoS(Orientations.WEST, Orientations.SOUTH),
    WtoN(Orientations.WEST, Orientations.NORTH),
    NtoW(Orientations.NORTH, Orientations.WEST);

    public final Orientations before;
    public final Orientations after;

    public final Point multiplier;

    private Rotations(Orientations before, Orientations after) {
        this.before = before;
        this.after = after;
        this.multiplier = before.unit.add(after.unit);
    }

    public Rotations next() {
        return this.equals(NtoW) ? NtoE : values()[this.ordinal() + 1];
    }

    public Rotations previous() {
        return this.equals(NtoE) ? NtoW : values()[this.ordinal() - 1];
    }

    public Rotations invert() {
        Rotations next = this.next();
        if (next.before.equals(this.after))
            return next;

        return this.previous();
    }

    public Point applyRotation(Point p) {
        if (this.after.isFlipped())
            return p.invert().componentProd(this.multiplier);

        return p.componentProd(this.multiplier);
    }

    public Point[] getKickTable(Tetrominos type) {
        if (type.kicks.equals(KickTables.C))
            return new Point[0];

        return type.kicks.tables[this.ordinal()];
    }

    // STATIC

    public static Rotations getRotation(Orientations before, Orientations after) {
        for (Rotations r : Rotations.values())
            if (before.equals(r.before) && after.equals(r.after))
                return r;

        return null;
    }
}
