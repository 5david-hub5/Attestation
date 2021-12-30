package ru.vsu.cs.agababyan;

import java.util.Arrays;
import java.util.Scanner;

public class Console {

    public void print(TableInterface<Object> table) {
        if (table.getAll() != null) {
            for (int i = 0; i < table.getAll().length; i++) {
                System.out.println(Arrays.toString(table.getRow(i)));
            }
        } else {
            System.out.println("The table is empty");
        }
    }

    public void addRow(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string values separated by space: ");
        String line = scanner.nextLine();
        Object[] objects = line.split(" ");
        table.addRow(objects);
        print(table);
    }

    public void addCol(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string values separated by space: ");
        String line = scanner.nextLine();
        Object[] objects = line.split(" ");
        table.addCol(objects);
        print(table);
    }


    public void removeRow(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the line number you want to delete: ");
        int n = scanner.nextInt();
        table.removeRow(n);
        print(table);
    }

    public void removeCol(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the column you want to delete: ");
        int n = scanner.nextInt();
        table.removeCol(n);
        print(table);
    }

    public void selectRow(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value for the sample: ");
        Object obj = scanner.nextLine();
        System.out.println("Enter a range of strings (value 'from' and value 'to' separated by a space): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        System.out.println("Select the number of the column by which the selection will occur");
        int a = scanner.nextInt();
        table.selectRow(Integer.parseInt(zone[0]) - 1, Integer.parseInt(zone[1]), a - 1, obj);
        print(table);
    }

    public void selectCol(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value for the sample: ");
        Object obj = scanner.nextLine();
        System.out.println("Enter a range of strings (value 'from' and value 'to' separated by a space): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        System.out.println("Select the line number by which the selection will occur");
        int a = scanner.nextInt();
        table.selectCol(Integer.parseInt(zone[0]) - 1, Integer.parseInt(zone[1]), a - 1, obj);
        print(table);
    }

    public void groupRow(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the lines to group (value 'from' and value 'to' separated by a space): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        int a = Integer.parseInt(zone[0]);
        int b = Integer.parseInt(zone[1]);
        TableInterface<Object> newTable = table.groupRows(a - 1, b - 1);
        for (int i = a - 1; i < b; i++){
            table.removeRow(a - 1);
        }
        for (int i = 0; i < table.getAll().length; i++) {
            System.out.println(Arrays.toString(table.getRow(i)));
            if (i == a - 2){
                System.out.println(newTable);
            }
        }
    }

    public void groupCol(TableInterface<Object> table) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the lines to group (value 'from' and value 'to' separated by a space): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        int a = Integer.parseInt(zone[0]);
        int b = Integer.parseInt(zone[1]);
        TableInterface<Object> newTable = table.groupCol(a - 1, b - 1);
        for (int i = a - 1; i < b; i++){
            table.removeCol(a - 1);
        }
        for (int i = 0; i < table.getAll().length; i++) {
            System.out.print(Arrays.toString(table.getRow(i)) + " ");
            System.out.println(Arrays.toString(newTable.getRow(i)));
        }

    }

}
