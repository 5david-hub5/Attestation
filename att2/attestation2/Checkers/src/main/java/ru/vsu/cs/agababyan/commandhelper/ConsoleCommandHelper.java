package ru.vsu.cs.agababyan.commandhelper;

import java.util.Scanner;

public class ConsoleCommandHelper implements CommandHelper {

    private Scanner sc;

    public ConsoleCommandHelper() {
        sc = new Scanner(System.in);
    }

    @Override
    public String getNextLine() {
        return sc.nextLine();
    }
}