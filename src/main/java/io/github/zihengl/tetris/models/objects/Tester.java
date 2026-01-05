package io.github.zihengl.tetris.models.objects;

import io.github.zihengl.tetris.models.enums.Orientations;

public class Tester {

    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        System.out.println(tetris);

        tetris.shift(Orientations.EAST);
        System.out.println(tetris);

        tetris.rotateRight();
        System.out.println(tetris);

        tetris.rotateRight();
        System.out.println(tetris);
    }
}
