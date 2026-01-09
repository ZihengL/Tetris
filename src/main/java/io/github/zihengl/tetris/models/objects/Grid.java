package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.observer.Observable;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Grid {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 30;
    public static final int BUFFER = 20;
    public static final Point SPAWN = new Point(WIDTH / 2 - 1, HEIGHT - 2);

    public final GridBrick[][] bricks;

    public Grid() {
        this.bricks = new GridBrick[HEIGHT][WIDTH];
        for (int y = 0; y < this.bricks.length; y++)
            for (int x = 0; x < this.bricks[y].length; x++)
                this.bricks[y][x] = new GridBrick(x, y, null);
    }

    public int height() {
        return this.bricks.length;
    }

    public int width(int y) {
        return this.bricks[y].length;
    }

    public GridBrick get(int x, int y) {
        return this.bricks[y][x];
    }

    public GridBrick get(Point point) {
        return this.bricks[point.y][point.x];
    }

    // VALIDATION

    public boolean isValid(Tetro tetro) {
        for (Brick brick : tetro.bricks)
            if (!this.isValid(brick))
                return false;

        return this.isValid(tetro);
    }

    public boolean isValid(Point point) {
        return !this.isOutOfBounds(point) &&
               !this.isFilledAt(point);
    }

    public boolean isOutOfBounds(Point point) {
        return point.x < 0 ||
               point.y < 0 ||
               point.x >= this.width(point.y) ||
               point.y >= this.height();
    }

    public boolean isFilledAt(Point point) {
        return this.get(point).isFilled();
    }

    public boolean isRowFilled(int index) {
        for (GridBrick brick : this.bricks[index])
            if (!this.isFilledAt(brick))
                return false;

        return true;
    }

    // OTHER

    public void reset() {
        for (int y = 0; y < this.height(); y++)
            this.emptyRow(y);
    }

    public void emptyRow(int index) {
        for (GridBrick brick : this.bricks[index])
            brick.setType(null);
    }
    
    public void collapseFrom(int row) {
        for (int y = row; y < BUFFER; y++)
            for (int x = 0; x < this.width(y); x++) {
                GridBrick bot = this.bricks[y][x],
                          top = this.bricks[y + 1][x];

                bot.setType(top.type);
            }
    }

    public void syphon(Tetro tetro) {
        this.get(tetro).setType(null);

        for (Brick brick : tetro.bricks)
            this.get(brick).setType(null);
    }

    public void transmit(Tetro tetro) {
        this.get(tetro).setType(tetro.type);

        for (Brick brick : tetro.bricks)
            this.get(brick).setType(tetro.type);
    }
}
