package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Application {
    public static void manual() {
        String st = "\t Быки и коровы — логическая игра, в ходе которой за несколько попыток один  из  игроков \n" +
                "должен  определить,  что  задумал  другой  игрок.  Варианты  игры  могут  зависеть  от типа \n" +
                "отгадываемой  последовательности  —  это  могут  быть  числа, цвета, пиктограммы  или слова. \n" +
                "После  каждой  попытки задумавший игрок выставляет «оценку», указывая количество угаданного\n" +
                "без совпадения с их позициями (количество «коров») и полных совпадений (количество «быков»).\n" +
                "Роли  участников игры не равнозначны — угадывающий должен анализировать сделанные попытки и \n" +
                "полученные  оценки, то есть его роль активна. Его партнёр лишь сравнивает очередной вариант  \n" +
                "с  задуманным  и  выставляет  оценку  по  формальным  правилам,  то  есть его роль пассивна. \n" +
                "\tДля уравновешивания ролей одновременно играют две встречные партии.";
        System.out.println(st);
    }

    public static void main(String[] args) {
        ArrayList<Archive> archive = new ArrayList<>();
        Answer answer = new Answer("user", 2, 3, 1);

        Date date = new Date();
        String time = "";
//        String time = String.valueOf(date);
        Integer count = 0;


        Boolean fl = true;
        Boolean fl1;
        Boolean fl2 = true;
        Game game = null;
        int sizeBord = 4;
        int attempt = 50;
        String gameIn = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J"); // очистка консоли
        System.out.println("\n- - Быки и коровы — логическая игрА - -\n");

        do {
            fl1 = true;
            System.out.println("\tВыберите игру по ");
            System.out.println("   отгадываемым последовательностям:");
            System.out.println("\tЧисла  - - - - - - - нажмите '1'");
            System.out.println("\tСлова RU - - - - - - нажмите '2'");
            System.out.println("\tСлова EN - - - - - - нажмите '3'");
            System.out.println("\tПравила игры - - - - нажмите '4'");
            System.out.println("\tВыход из программы - нажмите '9'");
            System.out.print("\n Введите число -> ");
            int num = scanner.nextInt();

            switch (num) {
                case 1:
                    game = new NumberGame();
                    game.start(sizeBord, attempt);
                    gameIn = " - Игра по отгадываемым последовательностям - Числа -";
                    break;
                case 2:
                    game = new RuGame();
                    game.start(sizeBord, attempt);
                    gameIn = " - Игра по отгадываемым последовательностям - Слова RU -";
                    break;
                case 3:
                    game = new EnGame();
                    game.start(sizeBord, attempt);
                    gameIn = " - Игра по отгадываемым последовательностям - Слова EN -";
                    break;
                case 4:
                    System.out.println("Правило игры!!!");
                    game = new NumberGame();
                    manual();
                    fl = false;
                    break;
                case 9:
                    System.out.print("Завершение программы.\n\n");
                    game = new NumberGame();
                    game.end(fl);
                    fl2 = false;
                    break;
            }

            if (game.getGameStatus().equals(GameStatus.START)) {
                System.out.println(gameIn);
                System.out.println(" Компьютер загадал последовательность из " + "'" + sizeBord + "'" + " символов");
                System.out.print("Всего попыток:\t\t" + "'" + attempt + "'" + ". ");
            }
            while (game.getGameStatus().equals(GameStatus.START)) {
                fl1 = false;
                System.out.print("Ваш ход" + ": -> ");
                String answers = scanner.next();
                Answer answerGame = game.inputValue(answers);
                System.out.println("\tКоров - " + answerGame.getCow() + " , Быков - " + answerGame.getBull());
                if (answerGame.getNumAttempts() != 0
                        & !game.getGameStatus().equals(GameStatus.LOSE)
                        & !game.getGameStatus().equals(GameStatus.WIN)) {
                    System.out.print("Осталось попыток:\t'" + answerGame.getNumAttempts() + "'" + ". ");
                }

                switch (game.getGameStatus()) {
                    case LOSE -> System.out.println("\n\t;-(, Вы проиграли!\n");
                    case WIN -> System.out.println("\n\t:-), Вы выйграли!\n");
                }
                count++;
                time = date.toString();
                archive.add(new Archive(count, time, answers, answerGame));
            }
            if (fl2) {
                if (fl1 == false) {
                    System.out.println("\tВывести историю ходов?");
                    System.out.println("\tДА  - - - - нажмите '1'");
                    System.out.println("\tНЕТ - - - - нажмите '2'");
                    System.out.print(" Введите число -> ");
                    num = scanner.nextInt();
                    System.out.println();
                    switch (num) {
                        case 1:
                            for (Archive o : archive) {
                                System.out.println(o);
                            }
                            System.out.println();
                            archive.clear();
                            count = 0;
                            break;
                        case 2:
                            archive.clear();
                            count = 0;
                            break;
                    }
                }
                if (fl1) {
                    System.out.println("\tЖелаете сыграть?");
                } else {
                    System.out.println("\tЖелаете еще сыграть?");
                }
                System.out.println("\tДА  - - - - нажмите '1'");
                System.out.println("\tНЕТ - - - - нажмите '2'");
                System.out.print(" Введите число -> ");
                num = scanner.nextInt();
                System.out.println();
                switch (num) {
                    case 1:
                        fl = false;
                        break;
                    case 2:
                        fl = true;
                        System.out.print("Завершение программы.\n\n");
                        break;
                }
            }

        } while (!game.end(fl).equals(GameStatus.END));

        scanner.close();
    }
}
