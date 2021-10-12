package ru.vsu.cs.agababyan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.models.Figure;
import ru.vsu.cs.agababyan.models.Player;
import ru.vsu.cs.agababyan.use.Use;

public class Logic {


    private final Figure[][] figures = new Figure[8][8];

    public Figure[][] getFigures() {
        return figures;
    }

    public void createBoardWithFigures() {
        boolean color = true;
        for (int i = 0; i < 8; i++) {
            if (i > 4) {
                color = false;
            } else if (i == 3 || i == 4) {
                continue;
            }
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        figures[i][j] = null;
                    } else {
                        figures[i][j] = new SimpleFigure(color, i, j);
                    }
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        figures[i][j] = new SimpleFigure(color, i, j);
                    } else {
                        figures[i][j] = null;
                    }
                }
            }
        }
    }

    public boolean checkSelectedFigure(String position, int count){
        int indexCol = position.charAt(0) - 'A';
        int indexRow = Integer.parseInt(position.substring(1, 2)) - 1;
        if (indexCol < 0 || indexCol > 7 || indexRow < 0 || indexRow > 7 || figures[indexRow][indexCol] == null
                || !((count % 2 == 0 && !figures[indexRow][indexCol].isBlack()) || (count % 2 != 0 &&
                figures[indexRow][indexCol].isBlack()))) {
            return false;
        }
        return true;
    }

    public boolean checkMove(String position, int indexRow, int indexCol){
        return figures[indexRow][indexCol].checkMove(figures,figures[indexRow][indexCol],position);
    }
    public void makeMove(int indexHor1, int indexVer1, int indexHor2, int indexVer2){
        Figure temp = figures[indexHor1][indexVer1];
        figures[indexHor1][indexVer1] = figures[indexHor2][indexVer2];
        figures[indexHor2][indexVer2] = temp;
        temp.setColIndex(indexVer2);
        temp.setRowIndex(indexHor2);
        if (temp instanceof SimpleFigure && (indexHor2 == 0 || indexHor2 == 7)) {
            figures[indexHor2][indexVer2] = new KingFigure(temp.isBlack(),temp.getRowIndex(),temp.getColIndex());
        }
    }

    public boolean checkKillNow(int indexRow, int indexCol, String position){
        if(position !=null) {
            int indexColEnd = position.charAt(0) - 'A';
            int indexRowEnd = Integer.parseInt(position.substring(1, 2)) - 1;
            if (indexColEnd < 0 || indexColEnd > 7 || indexRowEnd < 0 || indexRowEnd > 7 || figures[indexRowEnd][indexColEnd] != null) {
                return false;
            }
        }
        return figures[indexRow][indexCol].checkKill(figures,figures[indexRow][indexCol],position);
    }

    public void makeKill(int indexHor1, int indexVer1, int indexHor2, int indexVer2, Player player){
        figures[indexHor1][indexVer1].makeKill(figures, indexHor1, indexVer1, indexHor2, indexVer2, player);
    }

    public boolean checkBlock(boolean color){
        for(int i = 0; i < figures.length; i++){
            for(int j = 0; j < figures[0].length; j++){
                if(figures[i][j] != null && figures[i][j].isBlack() == color){

                }
            }
        }
        return false;
    }
}
