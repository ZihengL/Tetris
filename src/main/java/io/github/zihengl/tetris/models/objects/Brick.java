package io.github.zihengl.tetris.models.objects;

public class Brick extends Cell {

    public final int ordinal;

    public Brick(int x, int y, int ordinal) {
        super(x, y, true);
        this.ordinal = ordinal;
    }

    public void update(Tetromino pivot) {
        Point offset = pivot.getOffset(this.ordinal),
              coords = pivot.quadrant.compensate(offset);

        this.set(coords);
    }
}
