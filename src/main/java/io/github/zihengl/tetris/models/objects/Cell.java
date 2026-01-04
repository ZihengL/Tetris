package io.github.zihengl.tetris.models.objects;

public class Cell extends Point {

    private boolean filled;

    public Cell(int x, int y) {
        this(x, y, false);
    }

    public Cell(int x, int y, boolean filled) {
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
}
