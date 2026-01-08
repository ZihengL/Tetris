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

    /**
     * @return new Point with inverted x/y values
     */
    public Point flip() {
        return new Point(this.y, this.x);
    }

    /**
     * @return new Point with negative x/y values
     */
    public Point invert() {
        return new Point(-this.x, -this.y);
    }

    public Point dotProduct(int scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }

    /**
     * @param other Point to multiply with
     * @return new Point that is the resultant of multiplying each
     * component of this Point with the matching other component of
     * the other Point.
     */
    public Point compProduct(Point other) {
        return new Point(this.x * other.x, this.y * other.y);
    }
}
