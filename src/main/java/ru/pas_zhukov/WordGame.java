package ru.pas_zhukov;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;


public class WordGame {

    private final String word;
    private final Gallows gallows;
    private final Set<Character> letters = new HashSet<>();
    private final Set<Character> guessedLetters = new HashSet<>();
    private final Set<Character> wrongGuessedLetters = new HashSet<>();

    public WordGame(String word) {
        this.word = word.toLowerCase();
        this.gallows = new Gallows();
        for (int i = 0; i < this.word.length(); i++) {
            Character ch = this.word.charAt(i);
            this.letters.add(ch);
        }
    }

    public AttemptResult newAttempt(Character letter) {
        if (guessedLetters.contains(letter) || wrongGuessedLetters.contains(letter)) {
            return AttemptResult.DUPLICATE;
        } else if (letters.contains(letter)) {
            guessedLetters.add(letter);
            return AttemptResult.SUCCESS;
        } else {
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
        String wordStatus = Arrays.toString(wordWithGuessed);
        return getGallows().toString() + "\n" + wordStatus;
    }

    boolean isVictory() {
        return guessedLetters.containsAll(letters);
    }

    boolean isDefeat() {
        return gallows.getAttemptsCount() == 0;
    }
}
