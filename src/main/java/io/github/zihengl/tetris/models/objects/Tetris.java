package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Gamestates;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Rotations;
import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.observer.Observable;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Tetris extends Observable {

    public static final int CLEAR_POINTS = 100;

    private final Grid grid;
    private Tetro tetro;
    private Tetros queue;

    private int score = 0;
    private Gamestates state = Gamestates.ONGOING;

    private Timer timer;

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

    public void setGamestate(Gamestates state) {
        this.state = state;
    }

    // VALIDATION

    public boolean isValid() {
        return this.tetro.isValid(this.grid);
    }

    public boolean isGameover() {
        return this.state.equals(Gamestates.GAMEOVER);
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

    /**
     * Template method called upon whenever the Tetro 
     */
    public void settleTetro() {
        this.tetro.transmitTo(this.grid);

        this.checkGameover();
        this.checkScore();

        this.nextTetro();
    }

    public void checkGameover() {
        for (Brick brick : this.grid.bricks[Grid.BUFFER])
            if (brick.isFilled()) {
                this.setGamestate(Gamestates.GAMEOVER);
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

    /**
     * If current Gamestate isn't GAMEOVER, then creates a new Tetro
     * object of the type in queue.
     */
    public void nextTetro() {
        if (this.isGameover()) return;

        this.tetro = new Tetro(Grid.SPAWN.x, Grid.SPAWN.y, this.queue);
        this.queue = Tetros.values()[(int) (Math.random() * Tetros.values().length - 1)];
    }

    // For console testing
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("\n");

        if (this.isGameover()) return "GAME OVER";

        for (int y = this.grid.height() - 1; y >= 0; y--, msg.append("\n")) {
            String filler = y == Grid.BUFFER ? "=" : "-";

            for (int x = 0; x < this.grid.width(y); x++, msg.append("\t"))
                if (this.tetro.isAt(x, y))
                    msg.append("0");
                else if (this.grid.get(x, y).isFilled())
                    msg.append("1");
                else
                    msg.append(filler);
        }

        return msg.toString();
    }
}
