package ru.vsu.cs.agababyan.use;

import ru.vsu.cs.agababyan.models.Player;
import ru.vsu.cs.agababyan.service.ConsolePainter;
import ru.vsu.cs.agababyan.service.Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Use {

    private static Scanner scanner = new Scanner(System.in);

    public static final Logger log = LoggerFactory.getLogger(Use.class);

    public static void main(String[] args) {

        log.info("Игра началась");

        Logic game = new Logic();
        game.createBoardWithFigures();

        System.out.print("Введите имя 1-го игрока: ");
        String namePlayer1 = scanner.nextLine();
        System.out.print("Введите имя 2-го игрока: ");
        String namePlayer2 = scanner.nextLine();
        Player player1 = new Player(namePlayer1);
        Player player2 = new Player(namePlayer2);

        int countStep = 0;
        String positionTo, currPlayer, positionFrom;
        int indexHorizontalStart, indexVerticalStart, indexHorizontalEnd, indexVerticalEnd;
        boolean killing = false;

        System.out.println("----Игра началась----");
        while (player1.getCountFigures() > 0 && player2.getCountFigures() > 0) {
            System.out.println("Убито белых: " + (12 - player1.getCountFigures()) + "   Убито черных: " + (12 - player2.getCountFigures()));
            ConsolePainter.drawBoardWithFigures(game.getFigures());
            if (countStep % 2 == 0) {
                currPlayer = player1.getName();
            } else {
                currPlayer = player2.getName();
            }

            System.out.print(currPlayer + ", выберите шашку: ");
            positionFrom = scanner.nextLine();

            if (game.checkSelectedFigure(positionFrom.toUpperCase(),countStep)) {
                indexHorizontalStart = Integer.parseInt(positionFrom.substring(1, 2)) - 1;
                indexVerticalStart = positionFrom.toUpperCase().charAt(0) - 'A';

                while (game.checkKillNow(indexHorizontalStart, indexVerticalStart, null)) {
                    killing = true;
                    System.out.print(currPlayer + ", вам надо бить! Выберите куда хотите сходить: ");
                    positionTo = scanner.nextLine();
                    if (game.checkKillNow(indexHorizontalStart,indexVerticalStart, positionTo.toUpperCase())) {
                        indexHorizontalEnd = Integer.parseInt(positionTo.substring(1, 2)) - 1;
                        indexVerticalEnd = positionTo.toUpperCase().charAt(0) - 'A';
                        game.makeKill(indexHorizontalStart, indexVerticalStart, indexHorizontalEnd, indexVerticalEnd,
                                countStep % 2 == 0 ? player2 : player1);
                        indexHorizontalStart = indexHorizontalEnd;
                        indexVerticalStart = indexVerticalEnd;
                        System.out.println("!Вы съели шашку противника!");
                        //ConsolePainter.drawBoardWithFigures(game.getFigures());
                    } else {
                        System.out.println("ход неверный");
                    }
                }

                if (killing) {
                    countStep++;
                    killing = false;
                    continue;
                }

                System.out.print(currPlayer + ", выберите куда хотите сходить: ");
                positionTo = scanner.nextLine();

                if (game.checkMove(positionTo.toUpperCase(), indexHorizontalStart,indexVerticalStart)) {
                    indexHorizontalEnd = Integer.parseInt(positionTo.substring(1, 2)) - 1;
                    indexVerticalEnd = positionTo.toUpperCase().charAt(0) - 'A';
                    game.makeMove(indexHorizontalStart, indexVerticalStart, indexHorizontalEnd, indexVerticalEnd);
                } else {
                    System.out.println("Данный ход невозможен");
                    continue;
                }
            } else {
                System.out.println("Ошибка при выборе фигуры");
                continue;
            }
            countStep++;
        }
//C:\Users\David\Desktop\Checkers
        System.out.println("----Игра завершилась----");
        log.info("Игра завершилась");
        System.out.println("Победил " + (player1.getCountFigures() > player2.getCountFigures() ? player1.getName() : player2.getName()
        ) + "\n" + "Кол-во сделанных ходов за партию: " + countStep);
    }
}
