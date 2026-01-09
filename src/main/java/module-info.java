module io.github.zihengl.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.zihengl.tetris to javafx.fxml;
    exports io.github.zihengl.tetris;
    exports io.github.zihengl.tetris.controllers;
    exports io.github.zihengl.tetris.controllers.components;
    exports io.github.zihengl.tetris.controllers.commands;
    exports io.github.zihengl.tetris.controllers.commands.controls;
    exports io.github.zihengl.tetris.models.enums;
    exports io.github.zihengl.tetris.models.objects;
    exports io.github.zihengl.tetris.models.services;
    exports io.github.zihengl.tetris.models.observer;
    opens io.github.zihengl.tetris.controllers to javafx.fxml;
    opens io.github.zihengl.tetris.controllers.components to javafx.fxml;
}