package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Gamestates;
import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.enums.Rotations;
import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.observer.Observable;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zi
 * @date 1/8/2026
 *
 * Equivalent to a CommandManager class, managing all
 */

public class Tetris extends Observable {

    public static final int CLEAR_POINTS = 100;
    public static final int PEEK_SIZE = 3;

    private final LinkedList<Tetros> queue;
    private final Grid grid;
    private Tetro tetro;

    private int score = 0;
    private Gamestates state = Gamestates.ONGOING;
    private Timer timer;

    public Tetris() {
        this.queue = new LinkedList<Tetros>();
        this.grid = new Grid();

        this.nextTetro();
    }

    // GETTERS & SETTERS

    public Grid getGrid() {
        return this.grid;
    }

    public Tetro getTetro() {
        return this.tetro;
    }

    public int getScore() {
        return this.score;
    }

    public Gamestates getState() {
        return this.state;
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

    /**
     * Determines Tetro's rotation type based on the current value of
     * its orientation, and the return value of its previous() method,
     * then invokes the rotate() method with the rotation in parameter.
     */
    public void rotateRight() {
        Orientations before = this.tetro.getOrientation(),
                     after = before.previous();

        this.rotate(Rotations.getRotation(before, after));
    }

    /**
     * Determines Tetro's rotation type based on the current value of
     * its orientation, and the return value of its next() method,
     * then invokes the rotate() method with the rotation in parameter.
     */
    public void rotateLeft() {
        Orientations before = this.tetro.getOrientation(),
                     after = before.next();

        this.rotate(Rotations.getRotation(before, after));
    }

    /**
     * Removes the
     * @param rotation
     */
    public void rotate(Rotations rotation) {
        this.grid.syphon(this.tetro);

        this.tetro.rotate(rotation, this.grid);

        this.grid.transmit(this.tetro);
    }

    public boolean shift(Orientations o) {
        this.grid.syphon(this.tetro);

        this.tetro.translate(o.unit);
        if (!this.tetro.isValid(this.grid)) {
            this.tetro.translate(o.opposite().unit);

            if (o.equals(Orientations.SOUTH)) {
                this.settle();
                return false;
            }
        }

        this.grid.transmit(this.tetro);
        return true;
    }

    // TODO: CHANGE THIS SO THAT WE'RE NOT SYPHONING AND TRANSMITTING REPEATEDLY
    public void drop() {
        if (this.shift(Orientations.SOUTH))
            this.drop();
    }

    // OTHER

    /**
     * Template method called upon whenever the current
     * Tetro gets transferred to the Grid.
     */
    public void settle() {
        this.grid.transmit(this.tetro);

        this.checkGameover();
        this.checkScore(0);
        this.nextTetro();

//        this.notifyObservers();
    }

    public void checkGameover() {
        for (Brick brick : this.grid.bricks[Grid.BUFFER])
            if (brick.isFilled()) {
                this.setGamestate(Gamestates.GAMEOVER);
                return;
            }
    }

    public void checkScore(int row) {
        if (this.grid.isRowFilled(row)) {
            this.grid.collapseFrom(row);
            this.score += CLEAR_POINTS;
        }

        if (row < Grid.BUFFER)
            this.checkScore(row + 1);
    }

    /**
     * If current Gamestate isn't GAMEOVER, then creates a new Tetro
     * object of the type in queue.
     */
    public void nextTetro() {
        if (this.isGameover()) return;

        if (this.queue.size() <= PEEK_SIZE) {
            Tetros[] tetros = Tetros.values();
            List<Tetros> tetrosList = Arrays.asList(tetros);
            Collections.shuffle(tetrosList);

            this.queue.addAll(tetrosList);
        }

        this.tetro = new Tetro(Grid.SPAWN.x, Grid.SPAWN.y, this.queue.pop());
    }

    // For console testing
    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("\n");

        if (this.isGameover())
            return this.state.name();

        for (int y = this.grid.height() - 1; y >= 0; y--) {
            String filler = y == Grid.BUFFER ? "=" : "-";
            msg.append("\n");

            for (int x = 0; x < this.grid.width(y); x++, msg.append("\t"))
                if (this.tetro.isAt(x, y))
                    msg.append(0);
                else if (this.grid.get(x, y).isFilled())
                    msg.append(1);
                else
                    msg.append(filler);
        }

        return msg.toString();
    }
}
