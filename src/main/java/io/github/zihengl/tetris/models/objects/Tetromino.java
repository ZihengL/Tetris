package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Rotations;
import io.github.zihengl.tetris.models.enums.Tetrominos;

public class Tetromino extends Brick {

    protected Brick[] bricks;
    protected Orientations orientation;

    public Tetromino(int x, int y, Tetrominos type) {
        super(x, y, type);

        Point[] offsets = this.type.offsets;
        this.bricks = new Brick[offsets.length];
        for (int i = 0; i < offsets.length; i++)
            this.bricks[i] = new Brick(this.x + offsets[i].x, this.y + offsets[i].y, this.type);

        this.orientation = Orientations.NORTH;
    }

    public Point getOffset(int index) {
        return this.type.offsets[index];
    }

    public Orientations getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Orientations orientation) {
        this.orientation = orientation;
    }

    public void transmitTo(Grid grid) {
        super.transmitTo(grid);

        for (Brick brick : this.bricks)
            brick.transmitTo(grid);
    }

    // OTHER

    public boolean isValid(Grid grid) {
        for (Brick b : this.bricks)
            if (b.isOutOfBounds() || grid.isFilledAt(b))
                return false;

        return !this.isOutOfBounds() && !grid.isFilledAt(this);
    }

    public void translate(Point displacement) {
        super.translate(displacement);
        for (Brick brick : this.bricks)
            brick.translate(displacement);
    }

    public void rotate(Rotations rotation, Grid grid) {
        for (int i = 0; i < this.bricks.length; i++) {
            Point offset = this.getOffset(i),
                  rotatedOffset = rotation.applyRotation(offset),
                  location = this.add(rotatedOffset);

            this.bricks[i].set(location);
        }

        if (!this.kick(rotation, grid))
            this.rotate(rotation.invert(), grid);
    }

    public boolean kick(Rotations rotation, Grid grid) {
        for (Point kick : rotation.getKickTable(this.type)) {
            this.translate(kick);

            if (this.isValid(grid)) {
                this.setOrientation(rotation.after);
                return true;
            }

            this.translate(kick.opposite());
        }

        return false;
    }

//    public void update() {
//        for (int i = 0; i < this.bricks.length; i++) {
//            Point location = this.add(this.getOffset(i));
//            this.bricks[i].set(location);
//        }
//    }

    // TEMPORARY FOR CONSOLE TESTING
    public boolean isAt(int x, int y) {
        for (Brick brick : this.bricks)
            if (brick.x == x && brick.y == y)
                return true;

        return this.x == x && this.y == y;
    }
}
