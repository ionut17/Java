package lab7.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ionut
 */
public class Player implements Runnable {

    private String playerName;
    private int score = 0;
    private final LetterPack attachedLetterPack;

    public Player(LetterPack lp, String name) {
        this.playerName = name;
        this.attachedLetterPack = lp;
    }

    @Override
    public void run() {
        System.out.println("Player " + playerName + " is running...");
        List<Tile> letters = new ArrayList<>();
        System.out.println("Extracting...");
        for (int i = 0; i < 7; i++) {
            letters.add(attachedLetterPack.extractLetter());
        }
        for (Tile letter : letters) {
            System.out.println(playerName + ": " + letter.toString());
        }
        System.out.println("Pack size: "+attachedLetterPack.getPackSize());
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
