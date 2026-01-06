package io.github.zihengl.tetris.models.enums;

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

    private Rotations(Orientations before, Orientations after) {
        this.before = before;
        this.after = after;
    }
}
