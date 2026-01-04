package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;

public class Grid {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int BUFFER = 20;
    public static final int ANCHOR_X = WIDTH / 2;
    public static final int ANCHOR_Y = HEIGHT;

    public final Cell[][] cells;
    private Tetromino tetromino;

    public Grid() {
        this.cells = new Cell[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                this.cells[y][x] = new Cell(x, y);
    }

    public Cell get(int x, int y) {
        return this.cells[y][x];
    }

    public Cell get(Point p) {
        return this.cells[p.y][p.x];
    }

    public void fill(Point p) {
        this.get(p).fill();
    }

    public boolean isFilledAt(Point p) {
        return this.cells[p.y][p.x].isFilled();
    }

    public void settle() {
        this.cells[this.tetromino.y][this.tetromino.x].setFilled(true);
        for (Brick brick : this.tetromino.bricks)
            this.cells[brick.y][brick.x].setFilled(true);
    }

    public void shift(Orientations o) {
        this.tetromino.translate(o.p);

        if (this.tetromino.isInvalid(this)) {
            this.tetromino.translate(o.opposite().p);

            if (o.equals(Orientations.SOUTH))
                this.settle();
        }
    }


}
