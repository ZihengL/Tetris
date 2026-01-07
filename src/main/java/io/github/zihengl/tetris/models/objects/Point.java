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

    public Point add(Point p) {
        return new Point(this.x + p.x, this.y + p.y);
    }

    // Returns point with flipped x/y values.
    public Point invert() {
        return new Point(this.y, this.x);
    }

    // Returns point with negative x/y values.
    public Point opposite() {
        return new Point(-this.x, -this.y);
    }

    public Point dotProduct(int mag) {
        return new Point(this.x * mag, this.y * mag);
    }

    public Point compProduct(Point mag) {
        return new Point(this.x * mag.x, this.y * mag.y);
    }
}
