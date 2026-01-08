package io.github.zihengl.tetris.models.objects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Timer {

    private final Timeline timeline;

    private int time;

    public Timer() {
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(this.getSpeed()), event -> {
                    // TODO:
                })
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.time = 0;
    }

    public double getSpeed() {
        return 1.;
    }

    public int getTime() {
        return this.time;
    }

    public void play() {
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void pause() {
        this.timeline.pause();
    }

    public void reset() {
        this.time = 0;
        this.timeline.playFromStart();
    }
}
