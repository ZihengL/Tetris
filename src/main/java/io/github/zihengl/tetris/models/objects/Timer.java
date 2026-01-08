package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.services.Callbacker;
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
    private final Callbacker updater;

    private int time = 0;

    public Timer(Callbacker updater) {
        this.updater = updater;

        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(this.getSpeed()), event -> {
                    if (++this.time != 5) return;

                    this.updater.callback();
                    this.time = 0;
                })
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
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
