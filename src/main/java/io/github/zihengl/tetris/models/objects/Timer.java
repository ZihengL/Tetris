package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Zi
 * @date 1/8/2026
 */

public class Timer {

    public static final double TICKRATE = 1. / 60.;
    public static final int[] GRAVITY = { 60, 50, 40, 30, 20, 10, 8, 6, 4, 2, 1 };

    private final Timeline timeline;

    private int frame;
    private int threshold;

    public Timer() {
        this.timeline = new Timeline(
            new KeyFrame(Duration.seconds(TICKRATE), event -> {
                switch (Tetris.instance.getState()) {
                    case ONGOING -> {
                        this.update();
                        return;
                    }
                    case PAUSED -> {
                        this.pause();
                        return;
                    }
                    default -> {
                        this.stop();
                        return;
                    }
                }
            })
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.frame = 0;
    }

    public void update() {
        if (++this.frame < this.threshold) return;

        Tetris.instance.shift(Orientations.SOUTH);
        this.frame = 0;
    }

    public void play() {
        this.timeline.play();
    }

    public void pause() {
        this.timeline.pause();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void reset() {
        this.timeline.stop();

        this.frame = 0;
        this.threshold = GRAVITY[0];
    }

    public void updateThreshold() {
        int idx = Math.min(Tetris.instance.getLevel(), GRAVITY.length - 1);
        this.threshold = GRAVITY[idx];

        this.frame = 0;
    }
}
