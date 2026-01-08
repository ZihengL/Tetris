package io.github.zihengl.tetris.controllers.commands;


import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author Zi
 * @date 1/7/2026
 */

public interface Command<T extends Event> extends EventHandler<T> {

    void handle(T event);
}