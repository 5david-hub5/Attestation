package ru.vsu.cs.agababyan.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.use.Use;

public abstract class Figure {



    private final boolean black;
    private int rowIndex;
    private int colIndex;


    public Figure(boolean black, int rowIndex, int colIndex) {
        this.black = black;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public boolean isBlack() {
        return black;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }


    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

//abstract methods
    public abstract boolean checkMove(Figure[][] figures,Figure figure, String position);
    public abstract boolean checkKill(Figure[][] figures,Figure figure, String position);
    public abstract void makeKill(Figure[][] figures, int indexStartRow, int indexStartCol, int indexEndRow, int indexEndCol, Player player);
}
