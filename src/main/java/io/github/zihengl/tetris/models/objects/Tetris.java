package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.KickTables;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Rotations;
import io.github.zihengl.tetris.models.enums.Tetrominos;
import io.github.zihengl.tetris.models.objects.observer.Observable;
import io.github.zihengl.tetris.models.services.Rotator;

public class Tetris extends Observable {

    public static final int CLEAR_POINTS = 100;

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

    // PLAYER CONTROLS

    public void rotateRight() {
        Orientations before = this.tetro.getOrientation(),
                     after = before.previous();

        Rotations rotation = Rotations.getRotation(before, after);
        this.tetro.rotate(rotation, this.grid);
    }

    public void rotateLeft() {
        Orientations before = this.tetro.getOrientation(),
                     after = before.next();

        Rotations rotation = Rotations.getRotation(before, after);
        this.tetro.rotate(rotation, this.grid);
    }

//    public void rotate(Rotator forward, Rotator backwards) {
//        Rotations rotation = forward.rotate();
//        if (this.isValid()) return;
//
//        Tetrominos type = this.tetro.getType();
//        for (Point offset : rotation.getKickTable(type)) {
//            this.tetro.translate(offset);
//            if (this.isValid()) return;
//
//            this.tetro.translate(offset.invert());
//        }
//        backwards.rotate();
//    }

    public boolean shift(Orientations o) {
        this.tetro.translate(o.unit);

        if (!this.tetro.isValid(this.grid)) {
            this.tetro.translate(o.opposite().unit);

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
        this.tetro.transmitTo(this.grid);

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
            if (!brick.isOccupied()) {
                this.gameover = true;
                return;
            }
    }

    public void checkScore() {
        int multiplier = 1;

        for (int i = 0; i < Grid.BUFFER; i++)
            if (this.grid.isRowFilled(i)) {
                this.grid.collapseFrom(i);
                multiplier++;
            }
        this.score += multiplier * Tetris.CLEAR_POINTS;
    }

    // For console testing
    public String toString() {
        StringBuilder msg = new StringBuilder();

        for (int y = Grid.HEIGHT - 1; y >= 0; y--) {
            msg.append("\n");

            for (int x = 0; x < Grid.WIDTH; x++) {
                String value = this.grid.get(x, y).isOccupied() ? "1" : "0";
                value = this.tetro.isAt(x, y) ? "2" : value;

                msg.append(value).append("\t");
            }
        }

        return msg.toString();
    }
}
