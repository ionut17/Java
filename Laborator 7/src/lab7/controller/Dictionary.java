package lab7.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Anca
 */
public class Dictionary {

    private final static Dictionary dictionary = new Dictionary();
    private static HashSet<String> words = new HashSet<>();

    /**
     * @return the words
     */
    public static HashSet<String> getWords() {
        return words;
    }

    private Dictionary() {
    }

    public static Dictionary getDictionary() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(line.toUpperCase()) && !line.contains("'")) {
                    getWords().add(line.toUpperCase());
//                    System.out.println(line);
                }
            }
        }
        return dictionary;
    }
}
