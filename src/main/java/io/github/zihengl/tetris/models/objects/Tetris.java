package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Gamestate;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Rotations;
import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.objects.observer.Observable;

public class Tetris extends Observable {

//    public static final Tetris tetris = new Tetris();

    public static final int CLEAR_POINTS = 100;

    private final Grid grid;
    private Tetro tetro;
    private Tetros queue;

    private int score = 0;
    private Gamestate state = Gamestate.ONGOING;

    public Tetris() {
        this.grid = new Grid();

        Tetros[] values = Tetros.values();
        this.queue = values[(int) (Math.random() * values.length - 1)];
        this.nextTetro();
    }

    // GETTERS & SETTERS

    public Grid getGrid() {
        return this.grid;
    }

    public Tetro getTetro() {
        return this.tetro;
    }

    public Tetros getQueue() {
        return this.queue;
    }

    public int getScore() {
        return this.score;
    }

    public void setState(Gamestate state) {
        this.state = state;
    }

    // VALIDATION

    public boolean isValid() {
        return this.tetro.isValid(this.grid);
    }

    public boolean isGameover() {
        return this.state.equals(Gamestate.GAMEOVER);
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

    // UPDATE

    public void update() {
        // TODO: PUT UPDATE STUFF HERE
    }

    public void settleTetro() {
        this.tetro.transmitTo(this.grid);

        this.checkGameover();
        this.checkScore();
        this.nextTetro();
    }

    public void checkGameover() {
        for (Brick brick : this.grid.bricks[Grid.BUFFER])
            if (brick.isFilled()) {
                this.setState(Gamestate.GAMEOVER);
                return;
            }
    }

    public void checkScore() {
        int score = 0;

        for (int i = 0; i < Grid.BUFFER; i++)
            if (this.grid.isRowFilled(i)) {
                this.grid.collapseFrom(i);
                score += CLEAR_POINTS;
            }
        this.score += score;
    }

    public void nextTetro() {
        if (this.isGameover()) return;

        this.tetro = new Tetro(Grid.SPAWN.x, Grid.SPAWN.y, this.queue);
        this.queue = Tetros.values()[(int) (Math.random() * Tetros.values().length - 1)];
    }

    // For console testing
    public String toString() {
        StringBuilder msg = new StringBuilder();

        if (this.isGameover()) return "GAME OVER";

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
