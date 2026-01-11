package io.github.zihengl.tetris.controllers.components;

import io.github.zihengl.tetris.models.services.IntegerGetter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @author Zi
 * @date 1/11/2026
 */

public class HudComponent {

    @FXML private Text txtTitle;
    @FXML private Text txtContent;

    private IntegerGetter getter;

    public void set(String title, IntegerGetter getter) {
        this.txtTitle.setText(title);
        this.getter = getter;
    }

    public void update() {
        this.txtContent.setText(String.valueOf(this.getter.getInt()));
    }
}
