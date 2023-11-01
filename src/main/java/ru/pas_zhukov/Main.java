package ru.pas_zhukov;


import java.util.Scanner;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;


public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Начать новую игру? [Y/n]");

            String userInput = reader.next();
            if (userInput.trim().equals("n")) {
                break;
            }

            System.out.println("\n===== ДА НАЧНЁТСЯ ИГРА! =====");

            Words words = new Words();
            WordGame game = new WordGame(words.getRandomWord());
            playGame(game);

        }

    }

    public static void playGame(@NotNull WordGame game) {
        Scanner reader = new Scanner(System.in);
        while (true) {

            if (game.isVictory()) {
                System.out.println("===== ПОБЕДА! =====");
                break;
            }
            if (game.isDefeat()) {
                System.out.println("----- Это поражение, брат... -----");
                break;
            }

            System.out.println(game);

            System.out.println("+++ Предложи букву для отгадывания: +++");
            String userInput = reader.next().toLowerCase().trim();
            System.out.print("\033[H\033[J");
            System.out.flush();
            if (!isLetter(userInput)) {
                System.out.println("!!! Для отгадывания используй только буквы латинского алфавита. Причем одну за раз! Давай еще разок.");
                continue;
            }

            AttemptResult result = game.newAttempt(userInput.charAt(0));

            if (result == AttemptResult.DUPLICATE) {
                System.out.println("--- Ты уже загадывал эту букву. Используй новую. ---");
            } else if (result == AttemptResult.WRONG_GUESS) {
                System.out.println("--- Нет такой буквы! ---");
            } else if (result == AttemptResult.SUCCESS) {
                System.out.println("+++ Есть такая буква! +++");
            }

        }
    }

    public static boolean isLetter(String potentialLetter) {
        String regex = "^[A-Za-z]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(potentialLetter).matches();
    }


}