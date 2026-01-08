package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.objects.Grid;
import io.github.zihengl.tetris.models.objects.Point;
import io.github.zihengl.tetris.models.objects.Tetris;
import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class BrickComponent implements Observer {

    public static final String IMG = "io/github/zihengl/tetris/img";

    @FXML private ImageView view;
    @FXML private Image image;

    public final int x;
    @FXML
    private void initialize() {

    }

    public final int y;

    public BrickComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(Observable observable) {
        Tetris tetris = (Tetris) observable;


    }
}
