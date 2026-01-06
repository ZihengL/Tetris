package io.github.zihengl.tetris.models.objects;

public class Grid {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int BUFFER = 20;
    public static final Point PIVOT_SPAWN = new Point(WIDTH / 2 - 1, HEIGHT - 2);

    public final Brick[][] bricks;

    public Grid() {
        this.bricks = new Brick[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                this.bricks[y][x] = new Brick(x, y);
    }

    public Brick get(int x, int y) {
        return this.bricks[y][x];
    }

    public Brick get(Point p) {
        return this.bricks[p.y][p.x];
    }

    // OTHER

    public void fill(Point p) {
        this.get(p).fill();
    }

    public void empty(Point p) {
        this.get(p).empty();
    }

    public boolean isFilledAt(Point p) {
        return this.bricks[p.y][p.x].isFilled();
    }

    public boolean isRowFilled(int index) {
        for (Brick brick : this.bricks[index])
            if (!brick.isFilled())
                return false;

        return true;
    }

    public void emptyRow(int index) {
        for (Brick brick : this.bricks[index])
            brick.empty();
    }
    
    public void collapse() {
        for (int x = 0; x < Grid.WIDTH; x++)
            for (int y = 1; y < Grid.HEIGHT; y++) {
                Brick top = this.bricks[y][x],
                      bot = this.bricks[y - 1][x];
                if (top.isFilled() && !bot.isFilled()) {

                }
            }
    }
}
