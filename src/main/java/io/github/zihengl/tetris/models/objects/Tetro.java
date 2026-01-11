package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Tetros;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Tetro extends Brick {

    protected Brick[] bricks;
    protected Orientations orientation;

    public Tetro(int x, int y, Tetros type) {
        super(x, y, type);

        Point[] offsets = this.type.offsets;
        this.bricks = new Brick[offsets.length];
        for (int i = 0; i < offsets.length; i++) {
            Point offset = offsets[i];
            this.bricks[i] = new Brick(x + offset.x, y + offset.y, type);
        }

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

    // OTHER

    public void translate(Point displacement) {
        super.translate(displacement);
        for (Brick brick : this.bricks)
            brick.translate(displacement);
    }

    /**
     * Gets the matching offset of each dependent Brick,
     * and applies the rotation before translating by
     * this Tetro's x/y values. Then, if kick() returns
     * false, then rotate back to its original position.
     *
     * @param rotation The Orientations before/after the operation
     * @param grid The grid used to validate the rotation
     * @return true if the rotation was performed successfully.
     */
    public void rotate(Rotation rotation, Grid grid) {
        if (this.type.equals(Tetros.O)) return;

        for (int i = 0; i < this.bricks.length; i++) {
            Point rotatedOffset = rotation.applyTo(this.getOffset(i)),
                  position = this.add(rotatedOffset);
            this.bricks[i].set(position);
        }

        if (!this.kick(rotation, grid))
            this.rotate(rotation.invert(), grid);
    }

    public boolean kick(Rotation rotation, Grid grid) {
        for (Point kick : rotation.getKickTable(this.type)) {
            this.translate(kick);

            if (grid.isValid(this)) {
                this.setOrientation(rotation.after);
                return true;
            }

            this.translate(kick.invert());
        }

        return false;
    }

    public boolean isAt(int x, int y) {
        for (Brick b : this.bricks)
            if (b.x == x && b.y == y)
                return true;

        return this.x == x && this.y == y;
    }

    public boolean isPivot(int x, int y) {
        return this.x == x && this.y == y;
    }

    public String toString() {
        String result = super.toString();

        for (Brick b : this.bricks)
            result += "\t" + b;

        return result;
    }
}
