package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.observer.Observable;
import io.github.zihengl.tetris.models.observer.Observer;
import io.github.zihengl.tetris.models.services.IntegerGetter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @author Zi
 * @date 1/11/2026
 */

public class HudComponent implements Observer {

    @FXML private Text txtTitle;
    @FXML private Text txtContent;

    private IntegerGetter getter;

    public void set(String title, IntegerGetter getter, Observable observable) {
        this.txtTitle.setText(title);
        this.getter = getter;

        observable.addObserver(this);
        this.update(observable);
    }

    public void update(Observable observable) {
        String value = String.valueOf(this.getter.getInt());
        this.txtContent.setText(value);
    }
}
