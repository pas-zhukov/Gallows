package ru.pas_zhukov;

import java.util.Arrays;
import java.util.Scanner;

import org.jetbrains.annotations.NotNull;
import ru.pas_zhukov.WordGame;
import ru.pas_zhukov.AttemptResult;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Начать новую игру? [Y/n]");
            String userInput = reader.next();
            if (userInput.trim().equals("n")) {
                break;
            }
            WordGame game = new WordGame();
            System.out.println("Пора отгадать слово!");
            Main.playGame(game);

        }

    }

    public static void playGame(@NotNull WordGame game) {
        while (true) {
            Scanner reader = new Scanner(System.in);
            if (game.isVictory()) {
                System.out.println("Поздравляю, ты победил!");
                break;
            }
            if (game.getGallows().getAttemptsCount() == 0) {
                System.out.println("Это поражение, брат...");
                break;
            }
            System.out.println(game.getGallows().toString());
            System.out.println(game.toString());
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