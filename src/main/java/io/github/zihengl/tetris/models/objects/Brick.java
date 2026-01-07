package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetrominos;

public class Brick extends Point {

    protected Tetrominos type;

    public Brick(int x, int y) {
        this(x, y, Tetrominos.EMPTY);
    }

    public Brick(int x, int y, Tetrominos type) {
        super(x, y);
        this.type = type;
    }

    public Tetrominos getType() {
        return this.type;
    }

    public void setType(Tetrominos type) {
        this.type = type;
    }

    // Returns true if type != EMPTY
    public boolean isFilled() {
        return !this.type.equals(Tetrominos.EMPTY);
    }

    public boolean isOutOfBounds() {
        return this.x < 0 || this.y < 0 ||
            this.x >= Grid.WIDTH || this.y >= Grid.HEIGHT;
    }

    public void transmitTo(Grid grid) {
        grid.get(this).transmitFrom(this);
    }

    public void transmitFrom(Brick brick) {
        this.type = brick.type;
    }

    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
