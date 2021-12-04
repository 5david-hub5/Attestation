package ru.vsu.cs.agababyan.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.use.Use;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public Player() {

    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int countFigures = 12;

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


}
