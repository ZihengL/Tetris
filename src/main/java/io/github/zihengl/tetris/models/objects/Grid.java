package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetros;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Grid {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int BUFFER = 20;
    public static final Point SPAWN = new Point(WIDTH / 2 - 1, HEIGHT - 2);

    public final Brick[][] bricks;

    public Grid() {
        this.bricks = new Brick[HEIGHT][WIDTH];
        for (int y = 0; y < this.bricks.length; y++)
            for (int x = 0; x < this.bricks[y].length; x++)
                this.bricks[y][x] = new Brick(x, y);
    }

    public int height() {
        return this.bricks.length;
    }

    public int width(int y) {
        return this.bricks[y].length;
    }

    public Brick get(int x, int y) {
        return this.bricks[y][x];
    }

    public Brick get(Point point) {
        return this.bricks[point.y][point.x];
    }

    // OTHER

    public boolean isFilledAt(Point point) {
        return this.bricks[point.y][point.x].isFilled();
    }

    public boolean isRowFilled(int index) {
        for (Brick brick : this.bricks[index])
            if (!brick.isFilled())
                return false;

        return true;
    }

    public void emptyRow(int index) {
        for (Brick brick : this.bricks[index])
            brick.setType(Tetros.EMPTY);
    }
    
    public void collapseFrom(int row) {
//        for (int x = 0; x < Grid.WIDTH; x++)
//            for (int y = row; y < Grid.BUFFER; y++) {
//                Brick brick = this.bricks[y][x],
//                      top = this.bricks[y + 1][x];
//
//                brick.transmitFrom(top);
//            }

        for (int y = row; y < this.bricks.length; y++) {
            for (int x = 0; x < this.bricks[y].length; x++) {
                Brick bot = this.bricks[y][x],
                      top = this.bricks[y + 1][x];

                bot.transmitFrom(top);
            }
        }
    }
}
