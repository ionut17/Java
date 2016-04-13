package lab7.controller;

import java.util.ArrayList;
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
    private TextArea packArea;

    public Player(LetterPack lp, String name, TextArea status, TextArea pack) {
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.statusArea = status;
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
                statusArea.appendText(currentSize);
            }
        });
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
