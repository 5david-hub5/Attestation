package ru.vsu.cs.agababyan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.models.Figure;
import ru.vsu.cs.agababyan.models.Player;
import ru.vsu.cs.agababyan.use.Use;

public class KingFigure extends Figure {



    public KingFigure(boolean black, int rowIndex, int colIndex) {
        super(black, rowIndex, colIndex);
    }

    @Override
    public boolean checkKill(Figure[][] figures, Figure figure, String position) {

        Use.log.info("Проверка на убийство");

        int indexRow = figure.getRowIndex();
        int indexCol = figure.getColIndex();
        boolean color = figure.isBlack();
        int currDistanceRow = 1;
        int currDistanceCol = 1;
        if (position == null) {
            while (currDistanceRow < 7 && currDistanceCol < 7) {
                if (indexRow - currDistanceRow - 1 > -1 && indexCol - currDistanceCol - 1 > -1 && //UpLeft
                        figures[indexRow - currDistanceRow][indexCol - currDistanceCol] != null &&
                        figures[indexRow - currDistanceRow - 1][indexCol - currDistanceCol - 1] == null &&
                        figures[indexRow - currDistanceRow][indexCol - currDistanceCol].isBlack() != color) {
                    return true;
                }
                if (indexRow + currDistanceRow + 1 < 8 && indexCol + currDistanceCol + 1 < 8 && //DownRight
                        figures[indexRow + currDistanceRow][indexCol + currDistanceCol] != null &&
                        figures[indexRow + currDistanceRow + 1][indexCol + currDistanceCol + 1] == null &&
                        figures[indexRow + currDistanceRow][indexCol + currDistanceCol].isBlack() != color) {
                    return true;
                }
                if (indexRow - currDistanceRow - 1 > -1 && indexCol + currDistanceCol + 1 < 8 && //UpRight
                        figures[indexRow - currDistanceRow][indexCol + currDistanceCol] != null &&
                        figures[indexRow - currDistanceRow - 1][indexCol + currDistanceCol + 1] == null &&
                        figures[indexRow - currDistanceRow][indexCol + currDistanceCol].isBlack() != color) {
                    return true;
                }
                if (indexRow + currDistanceRow + 1 < 8 && indexCol - currDistanceCol - 1 > -1 && //DownLeft
                        figures[indexRow + currDistanceRow][indexCol - currDistanceCol] != null &&
                        figures[indexRow + currDistanceRow + 1][indexCol - currDistanceCol - 1] == null &&
                        figures[indexRow + currDistanceRow][indexCol - currDistanceCol].isBlack() != color) {
                    return true;
                }
                currDistanceRow++;
                currDistanceCol++;
            }
            return false;
        } else {
            int indexCol2 = position.charAt(0) - 'A';
            int indexRow2 = Integer.parseInt(position.substring(1, 2)) - 1;
            boolean findEnemyFigure = false;
            if (indexRow2 > indexRow) { //down
                indexRow++;
                if (indexCol2 > indexCol) { //right
                    indexCol++;
                    while (indexRow + 1 <= indexRow2 && indexCol + 1 <= indexCol2) {
                        if(!findEnemyFigure && figures[indexRow][indexCol]!=null){
                            findEnemyFigure = true;
                        }
                        if (figures[indexRow][indexCol] != null && figures[indexRow + 1][indexCol + 1] != null) {
                            return false;
                        }
                        indexRow++;
                        indexCol++;
                    }
                } else { // left
                    indexCol--;
                    while (indexRow + 1 <= indexRow2 && indexCol - 1 >= indexCol2) {
                        if(!findEnemyFigure && figures[indexRow][indexCol]!=null){
                            findEnemyFigure = true;
                        }
                        if (figures[indexRow][indexCol] != null && figures[indexRow + 1][indexCol - 1] != null) {
                            return false;
                        }
                        indexRow++;
                        indexCol--;
                    }
                }
            } else { //up
                indexRow--;
                if (indexCol2 > indexCol) { //right
                    indexCol++;
                    while (indexRow - 1 >= indexRow2 && indexCol + 1 <= indexCol2) {
                        if(!findEnemyFigure && figures[indexRow][indexCol]!=null){
                            findEnemyFigure = true;
                        }
                        if (figures[indexRow][indexCol] != null && figures[indexRow - 1][indexCol + 1] != null) {
                            return false;
                        }
                        indexRow--;
                        indexCol++;
                    }
                } else { // left
                    indexCol--;
                    while (indexRow - 1 >= indexRow2 && indexCol - 1 >= indexCol2) {
                        if(!findEnemyFigure && figures[indexRow][indexCol]!=null){
                            findEnemyFigure = true;
                        }
                        if (figures[indexRow][indexCol] != null && figures[indexRow - 1][indexCol - 1] != null) {
                            return false;
                        }
                        indexRow--;
                        indexCol--;
                    }
                }
            }
            return indexRow == indexRow2 && indexCol == indexCol2 && findEnemyFigure;
        }
    }

    @Override
    public boolean checkMove(Figure[][] figures, Figure figure, String position) {
        int indexCol = position.charAt(0) - 'A';
        int indexRow = Integer.parseInt(position.substring(1, 2)) - 1;
        if (indexCol < 0 || indexCol > 7 || indexRow < 0 || indexRow > 7 || figures[indexRow][indexCol] != null) {
            return false;
        }
        return Math.abs(indexRow - figure.getRowIndex()) == Math.abs(indexCol - figure.getColIndex());
    }

    @Override
    public void makeKill(Figure[][] figures, int indexStartRow, int indexStartCol, int indexEndRow, int indexEndCol, Player player) {
        int currIndexStartRow = indexStartRow;
        int currIndexStartCol = indexStartCol;
        if (indexEndRow > currIndexStartRow) { //down
            currIndexStartRow++;
            if (indexEndCol > currIndexStartCol) { //right
                currIndexStartCol++;
                while (currIndexStartRow < indexEndRow && currIndexStartCol < indexEndCol) {
                    if (figures[currIndexStartRow][currIndexStartCol] != null) {
                        figures[currIndexStartRow][currIndexStartCol] = null;
                        player.setCountFigures(player.getCountFigures() - 1);
                    }
                    currIndexStartRow++;
                    currIndexStartCol++;
                }
            } else { // left
                currIndexStartCol--;
                while (currIndexStartRow < indexEndRow && currIndexStartCol > indexEndCol) {
                    if (figures[currIndexStartRow][currIndexStartCol] != null) {
                        figures[currIndexStartRow][currIndexStartCol] = null;
                        player.setCountFigures(player.getCountFigures() - 1);
                    }
                    currIndexStartRow++;
                    currIndexStartCol--;
                }
            }
        } else { //up
            currIndexStartRow--;
            if (indexEndCol > currIndexStartCol) { //right
                currIndexStartCol++;
                while (currIndexStartRow > indexEndRow && currIndexStartCol < indexEndCol) {
                    if (figures[currIndexStartRow][currIndexStartCol] != null) {
                        figures[currIndexStartRow][currIndexStartCol] = null;
                        player.setCountFigures(player.getCountFigures() - 1);
                    }
                    currIndexStartRow--;
                    currIndexStartCol++;
                }
            } else { // left
                currIndexStartCol--;
                while (currIndexStartRow > indexEndRow && currIndexStartCol > indexEndCol) {
                    if (figures[currIndexStartRow][currIndexStartCol] != null) {
                        figures[currIndexStartRow][currIndexStartCol] = null;
                        player.setCountFigures(player.getCountFigures() - 1);
                    }
                    currIndexStartRow--;
                    currIndexStartCol--;
                }
            }
        }
        figures[indexEndRow][indexEndCol] = figures[indexStartRow][indexStartCol];
        figures[indexStartRow][indexStartCol] = null;
        figures[indexEndRow][indexEndCol].setRowIndex(indexEndRow);
        figures[indexEndRow][indexEndCol].setColIndex(indexEndCol);
    }

}