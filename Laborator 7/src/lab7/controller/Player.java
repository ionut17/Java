package lab7.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Ionut
 */
public class Player implements Runnable {

    private String playerName;
    private int score = 0;
    private final LetterPack attachedLetterPack;
    private List<Tile> playerLetters = new ArrayList<>();
    private TextArea statusArea;
    private final HashSet<String> dictionary;
    private ArrayList<Word> wordsFound = new ArrayList<>();
    private TextArea packArea;

    public Player(LetterPack lp, HashSet<String> dictionary, String name, TextArea status, TextArea pack) {
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.statusArea = status;
        this.dictionary = dictionary;
        this.packArea = pack;
    }

    @Override
    public void run() {

        Platform.runLater(new Runnable() {
            public void run() {
                statusArea.appendText("Player " + playerName + " started...\n");
            }
        });

        //Extract by turns
        for (int i = 0; i < 7; i++) {
            Tile extractedLetter = attachedLetterPack.extractLetter();
            if (extractedLetter != null) {
                playerLetters.add(extractedLetter);
                Platform.runLater(new Runnable() {
                    public void run() {
                        packArea.setText(attachedLetterPack.toString());
                    }
                });
            }
        }
        for (Tile letter : playerLetters) {
            String currentMove = playerName + ": " + letter.toString() + '\n';
            Platform.runLater(new Runnable() {
                public void run() {
                    statusArea.appendText(currentMove);
                }
            });
        }
        String currentSize = "###" + playerName + " - Pack size: " + attachedLetterPack.getPackSize() + '\n';
        Platform.runLater(new Runnable() {
            public void run() {
                System.out.println("Pack size: " + attachedLetterPack.getPackSize());
                statusArea.appendText(currentSize);
            }
        });

        //Make words
        System.out.println("Configuring words...");
        this.getWord(playerLetters, "", 0);
        int maxValue = 0;
        Word bestWord = new Word();
        for (Word w : wordsFound) {
            if (w.getValue() > maxValue) {
                maxValue = w.getValue();
                bestWord = w;
            }
        }
        System.out.println("best word: " + bestWord.getWord() + " (" + bestWord.getValue() + ")");
    }

    private void getWord(List<Tile> letters, String currentWord, int currentWordValue) {
        if (currentWord.length() > 1 && dictionary.contains(currentWord)) {
            Word w = new Word();
            w.setPlayer(this);
            w.setWord(currentWord);
            w.setValue(currentWordValue);
            wordsFound.add(w);
        }
        if (currentWord.length() < 7) {
            for (int i = 0; i < letters.size(); i++) {
                List<Tile> letters2 = new ArrayList<Tile>(letters);
                letters2.remove(letters2.get(i));
                getWord(letters2, currentWord + letters.get(i).getLetter(), currentWordValue + letters.get(i).getValue());
            }
        }
//        if (letters.size() > 0) {
//            if (currentWord != "" && dictionary.contains(currentWord)) {
//                wordsFound.add(currentWord);
//            }
//            if (position < letters.size()) {
//                currentWord += letters.get(position).getLetter();
//                letters.remove(letters.get(position));
//            }
//
//            getWord(letters, currentWord, position + 1, wordsFound);
//        } else {
//            int maxLength = 0;
//            String longestWord = new String();
//            for (String s : wordsFound) {
//                if (s.length() > maxLength) {
//                    maxLength = s.length();
//                    longestWord = s;
//                }
//            }
//            System.out.println("longest word: " + longestWord);
//        }
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void addToScore(int value) {
        this.score += value;
    }

}
