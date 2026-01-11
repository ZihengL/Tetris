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
 * @date 1/9/2026
 */

public class TetropeekComponent extends ImageView implements Observer {

    public static final String RES_DIR = "/io/github/zihengl/tetris/img/";
    public static final int PEEK_ICON_SIZE = 100;

    private final int index;

    public TetropeekComponent(int index) {
        super();
        this.index = index;

        this.setFitWidth(PEEK_ICON_SIZE);
        this.setFitHeight(PEEK_ICON_SIZE);
    }

    @Override
    public void update(Observable observable) {
        Tetris tetris = (Tetris) observable;
        Tetros type = tetris.getInQueue(this.index);

        String path = String.format("%sICON_%s.png", RES_DIR, type);
        URL url = this.getClass().getResource(path);
        this.setImage(new Image(url.toString()));
    }
}
