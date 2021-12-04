package ru.vsu.cs.agababyan.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.use.Use;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Figure {

    public Figure() {

    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    private boolean black;
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




//abstract methods
    public abstract boolean checkMove(Figure[][] figures,Figure figure, String position);
    public abstract boolean checkKill(Figure[][] figures,Figure figure, String position);
    public abstract void makeKill(Figure[][] figures, int indexStartRow, int indexStartCol, int indexEndRow, int indexEndCol, Player player);

}
