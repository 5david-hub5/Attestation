package ru.vsu.cs.agababyan.consoleinterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.agababyan.commandhelper.CommandHelper;
import ru.vsu.cs.agababyan.commandhelper.ConsoleCommandHelper;
import ru.vsu.cs.agababyan.commandhelper.DemoCommandHelper;
import ru.vsu.cs.agababyan.models.Player;
import ru.vsu.cs.agababyan.service.ConsolePainter;
import ru.vsu.cs.agababyan.service.Logic;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleInterface {

    private static Scanner sc = new Scanner(System.in);
    private CommandHelper cp;
    private static final Logger log = LoggerFactory.getLogger(ConsoleInterface.class);
    private Logic game = new Logic();

    public void begin() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Игра шашки. Это главное меню");
        System.out.println("Начать игру - \"game\"");
        System.out.println("Demo-version напишите \"demo\"");
        System.out.println("Загрузить сохранение - \"load\"");
        System.out.println("Выход - \"exit\"");
        System.out.println("--------------------------------------------------------------");

        parseBeginInput(sc.nextLine());
    }

    private void parseBeginInput(String message) {
        switch (message) {
            case "game" -> {
                cp = new ConsoleCommandHelper();
                //System.out.print("Введите имя 1-го игрока: ");
                String namePlayer1 = cp.getNextLine();
                //System.out.print("Введите имя 2-го игрока: ");
                String namePlayer2 = cp.getNextLine();
                Player player1 = new Player(namePlayer1);
                Player player2 = new Player(namePlayer2);
                game = new Logic();
                startGame(player1, player2);
                break;
            }
            case "demo" -> {
                cp = new DemoCommandHelper();
                //System.out.print("Введите имя 1-го игрока: ");
                String namePlayer1 = cp.getNextLine();
                //System.out.print("Введите имя 2-го игрока: ");
                String namePlayer2 = cp.getNextLine();
                Player player1 = new Player(namePlayer1);
                Player player2 = new Player(namePlayer2);
                game = new Logic();
                startGame(player1, player2);
                break;
            }
            case "load" -> {
                try {
                    cp = new ConsoleCommandHelper();
                    ObjectMapper mapper = new ObjectMapper();
                    game = mapper.readValue(Paths.get("saves\\checker.json").toFile(), Logic.class);
                    String namePlayer1 = cp.getNextLine();
                    String namePlayer2 = cp.getNextLine();
                    Player player1 = new Player(namePlayer1);
                    Player player2 = new Player(namePlayer2);
                    startGame(player1, player2);
                } catch (IOException e) {
                    log.info("Can not read save file. Error: " + e.getMessage());
                    begin();
                }
                break;
            }
            case "exit" -> {
                System.exit(0);
            }
            default -> {
                System.out.println("Unknown command.");
                begin();
                break;
            }
        }
    }

    private void startGame(Player player1, Player player2) {
        log.info("Игра началась");

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

            if (countStep != 0) {
                System.out.println("хотите ли вы сохраниться? Если да, пишите save. Если нет, то любой символ");
                String str = cp.getNextLine();
                if (str.equals("save")) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        mapper.writeValue(Paths.get("saves\\checker.json").toFile(), game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //System.out.print(currPlayer + ", выберите шашку: ");
            positionFrom = cp.getNextLine();

            if (game.checkSelectedFigure(positionFrom.toUpperCase(), countStep)) {
                indexHorizontalStart = Integer.parseInt(positionFrom.substring(1, 2)) - 1;
                indexVerticalStart = positionFrom.toUpperCase().charAt(0) - 'A';

                while (game.checkKillNow(indexHorizontalStart, indexVerticalStart, null)) {

                    killing = true;
                    //System.out.print(currPlayer + ", вам надо бить! Выберите куда хотите сходить: ");
                    positionTo = cp.getNextLine();
                    if (game.checkKillNow(indexHorizontalStart, indexVerticalStart, positionTo.toUpperCase())) {

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
                positionTo = cp.getNextLine();

                if (game.checkMove(positionTo.toUpperCase(), indexHorizontalStart, indexVerticalStart)) {
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

        System.out.println("----Игра завершилась----");
        log.info("Игра завершилась");
        System.out.println("Победил " + (player1.getCountFigures() > player2.getCountFigures() ? player1.getName() : player2.getName()
        ) + "\n" + "Кол-во сделанных ходов за партию: " + countStep);

    }
}
