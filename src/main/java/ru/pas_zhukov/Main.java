package ru.pas_zhukov;


import java.util.Scanner;

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
            Words words = new Words();
            WordGame game = new WordGame(words.getRandomWord());
            System.out.println("Пора отгадать слово!");
            Main.playGame(game);

        }

    }

    public static void playGame(@NotNull WordGame game) {
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (game.isVictory()) {
                System.out.println("Поздравляю, ты победил!");
                break;
            }
            if (game.isDefeat()) {
                System.out.println("Это поражение, брат...");
                break;
            }
            System.out.println(game.getGallows());
            System.out.println(game);
            System.out.println("Предложи букву для отгадывания:");
            String userInput = reader.next().toLowerCase().trim();

            AttemptResult result = game.newAttempt(userInput.charAt(0));
            if (result == AttemptResult.NOT_LETTER) {
                System.out.println("В задаче используются только буквы латинского алфавита! Попробуй еще разок.");
            }
            else if (result == AttemptResult.DUPLICATE) {
                System.out.println("Ты уже загадывал эту букву. Используй новую.");
            }
            else if (result == AttemptResult.WRONG_GUESS) {
                System.out.println("Нет такой буквы!");
            } else if (result == AttemptResult.SUCCESS) {
                System.out.println("Есть такая буква!");
            }

        }
    }


}