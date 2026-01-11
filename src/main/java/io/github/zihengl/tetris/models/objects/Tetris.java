package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Gamestates;
import io.github.zihengl.tetris.models.enums.Orientations;
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
 * Equivalent to a CommandManager class where all
 * actions on the game are filtered through.
 */

public class Tetris extends Observable {

    public static final Tetris instance = new Tetris();

    public static final int PTS_PER_LINE = 100;
    public static final int LINES_PER_LVL = 10;
    public static final int PEEK_SIZE = 3;

    private final Grid grid;
    private final Timer timer;
    private final LinkedList<Tetros> queue;

    private Tetro tetro;
    private int score;
    private Gamestates state;

    private Tetris() {
        this.grid = new Grid();
        this.timer = new Timer();
        this.queue = new LinkedList<Tetros>();

        this.score = 0;
        this.state = Gamestates.ONGOING;
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

    public Timer getTimer() {
        return this.timer;
    }

    public Tetros getInQueue(int index) {
        return this.queue.get(index);
    }

    public int getLevel() {
        return this.getLines() / LINES_PER_LVL + 1;
    }

    public int getLines() {
        return this.score / PTS_PER_LINE;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGamestate(Gamestates state) {
        this.state = state;
    }

    // VALIDATION

    public boolean isValid() {
        return this.grid.isValid(this.tetro);
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
        this.rotate(Rotation.clockwise(this.tetro.orientation));
    }

    /**
     * Determines Tetro's rotation type based on the current value of
     * its orientation, and the return value of its next() method,
     * then invokes the rotate() method with the rotation in parameter.
     */
    public void rotateLeft() {
        this.rotate(Rotation.counterClockwise(this.tetro.orientation));
    }

    /**
     * Syphons at Tetro's current position before applying the
     * rotation in parameter where its type is transmitted to
     * the grid in its final position.
     * @param rotation defines the attributes of the rotation.
     */
    public void rotate(Rotation rotation) {
        this.syphon();
        this.tetro.rotate(rotation, this.grid);
        this.transmit();
    }

    public void shiftLeft() {
        this.shift(Orientations.WEST);
    }

    public void shiftDown() {
        this.shift(Orientations.SOUTH);
    }

    public void shiftRight() {
        this.shift(Orientations.EAST);
    }

    /**
     * Syphons the Tetro before translating towards the
     * parametered orientation unit vector to perform the
     * translation. If its new position is invalid, then
     * translates it back to its original position, and if
     * the orientation is SOUTH, then settles it as its final
     * position. In either case, the Tetro is transmitted back
     * to the Grid.
     * @param o indicates the orientation of the shift.
     */
    public void shift(Orientations o) {
        this.syphon();

        this.tetro.translate(o.unit);
        if (!this.isValid()) {
            this.tetro.translate(o.opposite().unit);

            if (o.equals(Orientations.SOUTH)) {
                this.transmit();
                this.settle();
                return;
            }
        }

        this.transmit();
    }

    /**
     * Syphon's from the Grid, then shifts the Tetro downwards
     * as long as its current position is valid. Then, pushes
     * the Tetro back upwards to its last valid position as its
     * final position to settle in.
     */
    public void drop() {
        this.syphon();
        while (this.isValid())
            this.tetro.translate(Orientations.SOUTH.unit);

        this.tetro.translate(Orientations.NORTH.unit);
        this.transmit();

        this.settle();
    }

    // OTHER

    public void syphon() {
        this.grid.syphon(this.tetro);
    }

    public void transmit() {
        this.grid.transmit(this.tetro);
    }

    /**
     * Template method invoked after a Tetro
     * settles into its final position.
     */
    public void settle() {
        this.checkGameover();
        this.checkScore(0);
        this.nextTetro();

        this.notifyObservers();
    }

    /**
     * If any brick fills this threshold, the
     * game is determined to be over.
     */
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
            this.score += PTS_PER_LINE;

            this.checkScore(row);
        }
        else if (row < Grid.BUFFER)
            this.checkScore(row + 1);
        else
            this.timer.updateThreshold();
    }

    /**
     * If current Gamestate isn't GAMEOVER, then creates a new Tetro
     * object of the type in queue.
     */
    public void nextTetro() {
        if (this.queue.size() <= PEEK_SIZE) {
            Tetros[] tetros = Tetros.values();
            List<Tetros> tetrosList = Arrays.asList(tetros);
            Collections.shuffle(tetrosList);

            this.queue.addAll(tetrosList);
        }

        this.tetro = new Tetro(Grid.SPAWN.x, Grid.SPAWN.y, this.queue.pop());
    }

    // TODO: REFINE
    public void reset() {
        this.setScore(0);
        this.timer.stop();

        this.grid.reset();
        this.queue.clear();
        this.nextTetro();
        this.setGamestate(Gamestates.ONGOING);

        this.timer.play();
        this.notifyObservers();
    }

    public String toString() {
        StringBuilder msg = new StringBuilder("\n");

        if (this.isGameover())
            return this.state.name();

        for (int y = this.grid.height() - 1; y >= 0; y--, msg.append("\n")) {
            for (int x = 0; x < this.grid.width(y); x++) {
                if (this.tetro.isAt(x, y))
                    msg.append(this.tetro.isPivot(x, y) ? "1" : "2");
                else
                    msg.append(this.grid.get(x, y).isFilled() ? "O" : "-");

                msg.append("\t");
            }
        }

        return msg.toString();
    }
}
