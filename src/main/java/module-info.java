module io.github.zihengl.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.zihengl.tetris to javafx.fxml;
    exports io.github.zihengl.tetris;
}