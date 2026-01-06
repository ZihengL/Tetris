package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Tetrominos;
import io.github.zihengl.tetris.models.objects.observer.Observable;
import io.github.zihengl.tetris.models.services.Rotator;

public class Tetris extends Observable {

    public static final int ROW_SCORE = 100;

    private final Grid grid;
    private Tetromino tetro;
    private Tetrominos queue;

    private int score = 0;
    private boolean gameover = false;

    public Tetris() {
        this.grid = new Grid();

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[(int) (Math.random() * values.length)];
        this.nextTetro();
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

    public boolean isValid() {
        return this.tetro.isValid(this.grid);
    }

    public void addToScore(int points) {
        this.score += points;
    }

    // PLAYER CONTROLS

    public void rotateRight() {
        this.rotate(this.tetro::rotateRight, this.tetro::rotateLeft);
    }

    public void rotateLeft() {
        this.rotate(this.tetro::rotateLeft, this.tetro::rotateRight);
    }

    public void rotate(Rotator forward, Rotator backwards) {
        Orientations before = this.tetro.quadrant.getEquivalent();
        forward.rotate();

        if (!this.isValid()) {
            Orientations after = this.tetro.quadrant.getEquivalent();
            for (Point offset : this.tetro.getKickTable(before, after)) {
                this.tetro.translate(offset);
                if (!this.isValid())
                    this.tetro.translate(offset.invert());
            }

            if (!this.isValid())
                backwards.rotate();
        }
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

    public void drop() {
        if (this.shift(Orientations.SOUTH))
            this.drop();
    }

    // OTHER

    public void nextTetro() {
        Point pivot = Grid.PIVOT_SPAWN;
        this.tetro = new Tetromino(pivot.x, pivot.y, this.queue);

        Tetrominos[] values = Tetrominos.values();
        this.queue = values[(int) (Math.random() * values.length)];
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
        for (Brick brick : this.grid.bricks[Grid.BUFFER])
            if (brick.isFilled()) {
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
            }

        this.grid.collapse();
    }

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
