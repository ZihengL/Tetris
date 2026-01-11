package io.github.zihengl.tetris.models.objects;

/**
 * @author Zi
 * @date 1/8/2026
 *
 * Generic class representing a point.
 */

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point location) {
        this.x = location.x;
        this.y = location.y;
    }

    public void translate(Point displacement) {
        this.x += displacement.x;
        this.y += displacement.y;
    }

    // OTHER

    public Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
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

    /**
     * @param scalar defines the scale, or magnitude of the operation
     * @return new Point after multiplying
     */
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

    public String toString() {
        return String.format("[%02d, %02d]", this.x, this.y);
    }
}
