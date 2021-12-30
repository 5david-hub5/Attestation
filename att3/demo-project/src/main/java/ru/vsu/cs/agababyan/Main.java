package ru.vsu.cs.agababyan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Object[][] mat = {{11, 23, 51, 21, 45, 52, 51},
                          {10, 32, 54, 71, 13, 15, 12},
                          {10, 31, 61, 91, 38, 42, 51},
                          {21, 31, 41, 33, 87, 52, 38},
                          {12, 31, 38, 28, 13, 25, 32},
                          {31, 24, 12, 42, 69, 43, 24},
                          {13, 12, 56, 23, 26, 23, 12}};
        Table<Object> table = new Table<>();
        table.setMatrix(mat);
        Console console = new Console();
        console.print(table);
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!line.equals("-end")) {
            System.out.println("Select an action:");
            System.out.println("-addRow - add row");
            System.out.println("-addCol - add column");
            System.out.println("-removeCol - remove column");
            System.out.println("-removeRow - remove row");
            System.out.println("-selectRow - select by row");
            System.out.println("-selectCol - select by column");
            System.out.println("-groupCol - grouping by columns");
            System.out.println("-groupRow - grouping by row");
            System.out.println("-end - end the program");
            line = scanner.nextLine();
            switch (line){
                case "-addRow" -> console.addRow(table);
                case "-addCol" -> console.addCol(table);
                case "-removeRow" -> console.removeRow(table);
                case "-removeCol" -> console.removeCol(table);
                case "-selectRow" -> console.selectRow(table);
                case "-selectCol" -> console.selectCol(table);
                case "-groupRow" -> console.groupRow(table);
                case "-groupCol" -> console.groupCol(table);
            }
        }
    }
}
