package ru.pas_zhukov;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Words {

    private final List<String> words;

    public Words(String filePath) {
        this.words = readWordsList(filePath);
    }

    public Words() {
        this("words.txt");
    }

    private static List<String> readWordsList(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}
