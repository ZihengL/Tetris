package io.github.zihengl.tetris.models.objects;

public class Grid {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int BUFFER = 20;
    public static final int ANCHOR_X = WIDTH / 2;
    public static final int ANCHOR_Y = HEIGHT - 1;

    public final Cell[][] cells;

    public Grid() {
        this.cells = new Cell[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                this.cells[y][x] = new Cell(x, y);
    }

    public Cell get(int x, int y) {
        return this.cells[y][x];
    }

    public Cell get(Point p) {
        return this.cells[p.y][p.x];
    }

    // OTHER

    // TODO: CONSIDER DOING: FOREACH BRICK -> THIS.GET(BRICK) = BRICK;
    public void fill(Point p) {
        this.get(p).fill();
    }

    public void empty(Point p) {
        this.get(p).empty();
    }

    public boolean isFilledAt(Point p) {
        return this.cells[p.y][p.x].isFilled();
    }

    public boolean isRowFilled(int index) {
        for (Cell cell : this.cells[index])
            if (!cell.isFilled())
                return false;

        return true;
    }

    public void emptyRow(int index) {
        for (Cell cell : this.cells[index])
            cell.empty();
    }

}
