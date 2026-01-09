package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.enums.Tetros;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class BrickComponent extends ImageView implements Observer  {

    public static final String RES_DIR = "/io/github/zihengl/tetris/img/";

    public final int x;
    public final int y;

    private Tetros type;

    public BrickComponent(int x, int y) {
        super();
        this.x = x;
        this.y = y;

        this.type = Tetros.EMPTY;
        this.updateImage();
    }

    @Override
    public void update(Observable observable) {
        Tetris tetris = (Tetris) observable;

        Tetros type = tetris.getTypeAt(this.x, this.y);
        if (!this.type.equals(type)) {
            this.type = type;
            this.updateImage();
        }
    }

    public void updateImage() {
        URL url = BrickComponent.class.getResource(
                RES_DIR + this.type.name() + ".jpg");

        this.setImage(new Image(url.toString()));
        this.setFitWidth(30);
        this.setFitHeight(30);
    }
}
