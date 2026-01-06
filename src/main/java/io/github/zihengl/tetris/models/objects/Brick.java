package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetrominos;

public class Brick extends Point {

    private boolean filled; // TODO: CONSIDER CHANGING THIS TO TYPE FOR THE UI BRICK COLOR
    protected Tetrominos type;

    public Brick(int x, int y) {
        this(x, y, false);
    }

    public Brick(int x, int y, boolean filled) {
        super(x, y);
        this.filled = filled;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void fill() {
        this.filled = true;
    }

    public void empty() {
        this.filled = false;
    }

    public boolean isOutOfBounds() {
        return this.x < 0 || this.y < 0 ||
            this.x >= Grid.WIDTH || this.y >= Grid.HEIGHT;
    }

    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
