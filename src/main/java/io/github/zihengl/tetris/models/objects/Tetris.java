package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Tetrominos;
import io.github.zihengl.tetris.models.objects.observer.Observable;

import java.util.Random;

public class Tetris extends Observable {

    public static final int ROW_SCORE = 1000;
    private static final Random randomizer = new Random();

    private final Grid grid;
    private Tetromino tetro;
    private Tetrominos queue;

    private int score = 0;
    private boolean gameover = false;

    public Tetris() {
        this.grid = new Grid();

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[randomizer.nextInt(values.length)];
        this.nextTetro();

        this.tetro = new Tetromino(Grid.ANCHOR_X, Grid.ANCHOR_Y, Tetrominos.T);
    }

    // GETTERS & SETTERS

    public Grid getGrid() {
        return this.grid;
    }

    public Tetromino getTetro() {
        return this.tetro;
    }

    public Tetrominos getQueue() {
        return this.queue;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isGameover() {
        return this.gameover;
    }

    public void addToScore(int points) {
        this.score += points;
    }

    // PLAYER CONTROLS

    public void rotateRight() {
        this.tetro.rotateRight();

        if (!this.tetro.isValid(this.grid))
            this.tetro.rotateLeft();
    }

    public void rotateLeft() {
        this.tetro.rotateLeft();

        if (!this.tetro.isValid(this.grid))
            this.tetro.rotateRight();
    }

    public void drop() {
        if (this.shift(Orientations.SOUTH))
            this.drop();
    }

    public boolean shift(Orientations o) {
        this.tetro.translate(o.p);

        if (!this.tetro.isValid(this.grid)) {
            this.tetro.translate(o.opposite().p);

            if (o.equals(Orientations.SOUTH)) {
                this.settleTetro();
                return false;
            }
        }

        return true;
    }

    // OTHER

    public void nextTetro() {
        this.tetro = new Tetromino(Grid.ANCHOR_X, Grid.ANCHOR_Y, this.queue);

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[randomizer.nextInt(values.length)];
    }

    public void settleTetro() {
        this.grid.fill(this.tetro);
        for (Brick brick : this.tetro.bricks)
            this.grid.fill(brick);

        this.check();
        if (!this.gameover)
            this.nextTetro();
    }

    public void check() {
        this.checkGameover();
        this.checkScore();
    }

    public void checkGameover() {
        for (Cell cell : this.grid.cells[Grid.BUFFER])
            if (cell.isFilled()) {
                this.gameover = true;
                return;
            }
    }

    public void checkScore() {
        int multiplier = 1;

        for (int i = 0; i < Grid.BUFFER; i++)
            if (this.grid.isRowFilled(i)) {
                this.grid.emptyRow(i);
                this.addToScore(ROW_SCORE * multiplier++);

                // TODO: MAKE ROWS THAT ARE HIGHER FALL DOWN
            }
    }

//    public String toString() {
//        StringBuilder msg = new StringBuilder();
//
//        msg.append(this.tetro.type).append("\n");
//        msg.append(this.tetro).append("\n");
//        for (Brick b : this.tetro.bricks)
//            msg.append(b).append("\n");
//
//        return msg.toString();
//    }

    public String toString() {
        StringBuilder msg = new StringBuilder();

        for (int y = Grid.HEIGHT - 1; y >= 0; y--) {
            msg.append("\n");

            for (int x = 0; x < Grid.WIDTH; x++) {
                String value = this.grid.get(x, y).isFilled() ? "1" : "-";
                value = this.tetro.isAt(x, y) ? "2" : value;

                msg.append(value).append("\t");
            }
        }

        return msg.toString();
    }
}
