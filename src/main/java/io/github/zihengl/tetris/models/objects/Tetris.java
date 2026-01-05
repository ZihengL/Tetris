package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Tetrominos;

import java.util.Random;

public class Tetris {

    private static final Random randomizer = new Random();

    private final Grid grid;
    private Tetromino tetro;
    private Tetrominos queue;

    public Tetris() {
        this.grid = new Grid();
        this.tetro = new Tetromino(Grid.ANCHOR_X, Grid.ANCHOR_Y, Tetrominos.T);

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[randomizer.nextInt(values.length)];
        this.nextTetro();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public Tetromino getTetro() {
        return this.tetro;
    }

    public void nextTetro() {
        this.tetro = new Tetromino(Grid.ANCHOR_X, Grid.ANCHOR_Y, this.queue);

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[randomizer.nextInt(values.length)];
    }

    // CONTROLS

    public void shift(Orientations o) {
        this.tetro.translate(o.p);

        if (this.tetro.isValid(this.grid)) {
            this.tetro.translate(o.opposite().p);

            if (o.equals(Orientations.SOUTH))
                this.settleTetromino();
        }
    }

    public void drop() {
        Orientations o = Orientations.SOUTH;
        while (!this.tetro.isValid(this.grid))
            this.tetro.translate(o.p);

        this.tetro.translate(o.opposite().p);
        this.settleTetromino();
    }

    public void rotateRight() {
        this.tetro.rotateRight();

        if (this.tetro.isValid(this.grid))
            this.tetro.rotateLeft();
    }

    public void rotateLeft() {
        this.tetro.rotateLeft();

        if (this.tetro.isValid(this.grid))
            this.tetro.rotateRight();
    }

    // OTHER

    public void settleTetromino() {
        this.grid.fill(this.tetro);

        for (Brick brick : this.tetro.bricks)
            this.grid.fill(brick);
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();

        msg.append(this.tetro.type).append("\n");
        msg.append(this.tetro).append("\n");
        for (Brick b : this.tetro.bricks)
            msg.append(b).append("\n");

        return msg.toString();
    }
}
