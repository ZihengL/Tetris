package io.github.zihengl.tetris;

import io.github.zihengl.tetris.models.enums.Orientations;
import io.github.zihengl.tetris.models.objects.Tetris;

public class Tester {

    public static void main(String[] args) {
        Tetris tetris = Tetris.instance;

        System.out.println(tetris.getTetro().getType());
        System.out.println("\nSPAWN" + tetris);

        for (int i = 0; i < 6; i++) {
            tetris.shift(Orientations.EAST);
        }
        System.out.println("\nSHIFT EAST" + tetris);

//        tetris.shift(Orientations.SOUTH);
//        System.out.println("\nSHIFT SOUTH" + tetris);

        tetris.rotateLeft();
        System.out.println("\nROTATE LEFT" + tetris);

//        tetris.rotateLeft();
//        System.out.println("\nROTATE LEFT" + tetris);

//        tetris.rotateRight();
//        System.out.println("\nROTATE RIGHT" + tetris);
//
//        while (!tetris.isGameover()) {
//            tetris.drop();
//            System.out.println("\nDROP" + tetris);
//        }

//        System.out.println(1 << 20);
//        System.out.println(Math.pow(2, 20));

    }
}
