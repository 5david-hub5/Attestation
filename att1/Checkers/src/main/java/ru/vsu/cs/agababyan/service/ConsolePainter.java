package ru.vsu.cs.agababyan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.models.Figure;
import ru.vsu.cs.agababyan.use.Use;

public class ConsolePainter {



    public static void drawBoardWithFigures(Figure[][] figures){

        Use.log.info("Рисование доски");

        System.out.println("--------------------------------");
        char ch = 'A';
        for (int i = -1; i <8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == -1){
                    System.out.print(" " + (ch++) + " ");
                    continue;
                }
                if (figures[i][j] == null) {
                    System.out.print(" - ");
                } else {
                    if (figures[i][j] instanceof SimpleFigure){
                        System.out.print(" " + (figures[i][j].isBlack() ? "*" : "o") + " ");
                    } else {
                        System.out.print(" " + (figures[i][j].isBlack() ? "0" : "@") + " ");
                    }
                }
                if(j == 7){
                    System.out.print(" " + (i + 1));
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }
}
