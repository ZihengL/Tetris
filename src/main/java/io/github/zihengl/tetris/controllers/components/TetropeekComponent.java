package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * @author Zi
 * @date 1/9/2026
 */

public class TetropeekComponent implements Observer {

    public static final String RES_DIR = "/io/github/zihengl/tetris/img/";

    @FXML private ImageView view;

    @Override
    public void update(Observable observable) {
        Tetris tetris = (Tetris) observable;

//        String path = RES_DIR + (type == null ? "EMPTY" : type) + ".jpg";
    }
}
