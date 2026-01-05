package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Quadrants;
import io.github.zihengl.tetris.models.enums.Tetrominos;

public class Tetromino extends Cell {

    public final Tetrominos type;

    protected Brick[] bricks;
    protected Quadrants quadrant;

    public Tetromino(int x, int y, Tetrominos type) {
        super(x, y, true);
        this.type = type;

        Point[] offsets = this.type.offsets;
        this.bricks = new Brick[offsets.length];
        for (int i = 0; i < offsets.length; i++)
            this.bricks[i] = new Brick(this.x + offsets[i].x, this.y + offsets[i].y, i);

        this.quadrant = Quadrants.I;
    }

    public Point getOffset(int index) {
        return this.type.offsets[index];
    }

    public Quadrants getQuadrant() {
        return this.quadrant;
    }

    // OTHER

    public void translate(Point displacement) {
        super.translate(displacement);

        this.update();
    }

    public boolean isValid(Grid grid) {
        for (Brick b : this.bricks)
            if (b.isOutOfBounds() || grid.isFilledAt(b))
                return false;

        return !(this.isOutOfBounds() || grid.isFilledAt(this));
    }

    public void rotateRight() {
        this.quadrant = this.quadrant.next();

        this.update();
    }

    public void rotateLeft() {
        this.quadrant = this.quadrant.previous();

        this.update();
    }

    public void update() {
        for (Brick brick : this.bricks)
            brick.update(this);
    }

    public boolean isAt(int x, int y) {
        for (Brick brick : this.bricks)
            if (brick.x == x && brick.y == y)
                return true;

        return this.x == x && this.y == y;
    }
}
