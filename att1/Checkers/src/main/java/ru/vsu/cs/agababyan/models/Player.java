package ru.vsu.cs.agababyan.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.use.Use;

import java.util.ArrayList;
import java.util.List;

public class Player {




    private final String name;
    private int countFigures = 12;
    private List<Figure> listFigures = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCountFigures() {
        return countFigures;
    }

    public void setCountFigures(int countFigures) {
        this.countFigures = countFigures;
    }

    public void setListFigures(List<Figure> listFigures) {
        this.listFigures = listFigures;
    }
}
