package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.services.TypeUpdater;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Brick extends Point {

    protected Tetros type;

    public Brick(int x, int y) {
        this(x, y, null);
    }

    public Brick(int x, int y, Tetros type) {
        super(x, y);
        this.type = type;
    }

    public Tetros getType() {
        return this.type;
    }

    public void setType(Tetros type) {
        this.type = type;
    }

    public boolean isFilled() {
        return this.type != null;
    }

    public boolean isOutOfBounds() {
        return this.x < 0 || this.y < 0 ||
            this.x >= Grid.WIDTH || this.y >= Grid.HEIGHT;
    }

    public void transmitFrom(Brick brick) {
        this.setType(brick.type);
    }

    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
