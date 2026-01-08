package io.github.zihengl.tetris;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;

public class Tester {

    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        System.out.println(tetris.getTetro().getType());
        System.out.println("\nSPAWN" + tetris);

        tetris.shift(Orientations.EAST);
        System.out.println("\nSHIFT EAST" + tetris);

        tetris.shift(Orientations.SOUTH);
        System.out.println("\nSHIFT SOUTH" + tetris);


        tetris.rotateRight();
        System.out.println("\nROTATE RIGHT" + tetris);

        tetris.rotateRight();
        System.out.println("\nROTATE RIGHT" + tetris);

        tetris.drop();
        System.out.println("\nDROP" + tetris);

        tetris.drop();
        System.out.println("\nDROP" + tetris);

        tetris.drop();
        System.out.println("\nDROP" + tetris);
    }
}
