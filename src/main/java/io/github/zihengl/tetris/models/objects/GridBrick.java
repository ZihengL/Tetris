package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.services.TypeUpdater;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class GridBrick extends Brick {

    protected TypeUpdater updater;

    public GridBrick(int x, int y) {
        super(x, y);
    }

    public GridBrick(int x, int y, Tetros type) {
        super(x, y, type);
    }

    public void setUpdater(TypeUpdater updater) {
        this.updater = updater;
    }

    public void setType(Tetros type) {
        this.type = type;

        if (this.updater != null)
            this.updater.update(this.type);
    }
}
