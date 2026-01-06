package io.github.zihengl.tetris.models.objects;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void translate(Point displacement) {
        this.x += displacement.x;
        this.y += displacement.y;
    }

    // OTHER

    public Point dotProduct(int scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }

    public Point add(Point p) {
        return new Point(this.x + p.x, this.y + p.y);
    }

    public Point invert() {
        return new Point(-this.x, -this.y);
    }
}
