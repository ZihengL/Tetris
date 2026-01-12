package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.services.TypeUpdater;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class BrickComponent extends ImageView implements TypeUpdater {

    public static final int BRICK_SIZE = 35;

    public BrickComponent() {
        super();
        this.setFitWidth(BRICK_SIZE);
        this.setFitHeight(BRICK_SIZE);

        this.update(null);
    }

    @Override
    public void update(Tetros type) {
        this.setImage(ImageAsset.getImage(type));
    }
}
