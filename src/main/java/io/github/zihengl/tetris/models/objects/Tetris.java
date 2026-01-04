package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;

public class Tetris {

    private Grid grid;
    private Tetromino tetromino;

    public Tetris() {
        this.grid = new Grid();
    }

    // CONTROLS

    public void shift(Orientations o) {
        this.tetromino.translate(o.p);

        if (this.tetromino.isInvalid(this.grid)) {
            this.tetromino.translate(o.opposite().p);

            if (o.equals(Orientations.SOUTH))
                this.settleTetromino();
        }
    }

    public void drop() {
        Orientations o = Orientations.SOUTH;

        while (!this.tetromino.isInvalid(this.grid))
            this.tetromino.translate(o.p);
        
        this.tetromino.translate(o.opposite().p);
        this.settleTetromino();
    }

    public void rotateRight() {
        this.tetromino.rotateRight();

        if (this.tetromino.isInvalid(this.grid))
            this.tetromino.rotateLeft();
    }

    public void rotateLeft() {
        this.tetromino.rotateLeft();

        if (this.tetromino.isInvalid(this.grid))
            this.tetromino.rotateRight();
    }

    public void settleTetromino() {
        this.grid.fill(this.tetromino);

        for (Brick brick : this.tetromino.bricks)
            this.grid.fill(brick);
    }
}
