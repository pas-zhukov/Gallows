package ru.pas_zhukov;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import ru.pas_zhukov.AttemptResult;


public class WordGame {

    private static final Set<Character> asciiLetters = Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    private final String word;
    private Gallows gallows;
    private final Set<Character> letters = new HashSet<>();
    private Set<Character> guessedLetters = new HashSet<>();
    private Set<Character> wrongGuessedLetters = new HashSet<>();

    public WordGame(String word) {
        this.word = word.toLowerCase();
        this.gallows = new Gallows();
        for (int i = 0; i < this.word.length(); i++) {
            Character ch = this.word.charAt(i);
            this.letters.add(ch);
        }
    }

    public AttemptResult newAttempt(Character letter) {
        if  (!asciiLetters.contains(letter)) {
            return AttemptResult.NOT_LETTER;
        }
        else if (guessedLetters.contains(letter) || wrongGuessedLetters.contains(letter)) {
            return AttemptResult.DUPLICATE;
        }
        else if (letters.contains(letter)) {
            guessedLetters.add(letter);
            return AttemptResult.SUCCESS;
        }
        else {
            gallows.nextState();
            wrongGuessedLetters.add(letter);
            return AttemptResult.WRONG_GUESS;
        }
    }

    public Gallows getGallows() {
        return gallows;
    }

    public String toString() {
        Character[] wordWithGuessed = new Character[this.word.length()];
        for (int i = 0; i < this.word.length(); i++) {
            Character ch = this.word.charAt(i);
            if (this.guessedLetters.contains(ch)) {
                wordWithGuessed[i] = ch;
            }
            else {
                wordWithGuessed[i] = '_';
            }
        }
        return Arrays.toString(wordWithGuessed);
    }

    boolean isVictory() {
        return guessedLetters.containsAll(letters);
    }

    boolean isDefeat() {
        return gallows.getAttemptsCount() == 0;
    }
}
