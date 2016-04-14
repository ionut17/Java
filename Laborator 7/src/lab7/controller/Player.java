package lab7.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

/**
 *
 * @author Ionut
 */
public class Player implements Runnable {

    private GameManager gm;
    private String playerName;
    private int score = 0;
    private final LetterPack attachedLetterPack;
    private List<Tile> playerLetters = new ArrayList<>();
    private TextArea statusArea;
    private final HashSet<String> dictionary;
    private ArrayList<Word> wordsFound = new ArrayList<>();
    private TextArea packArea;
    private ScoreObserver sc;
    private boolean myTurn = false;

    public Player(GameManager tgm, LetterPack lp, HashSet<String> dictionary, String name, TextArea status, TextArea pack, ScoreObserver tsc) {
        this.gm = tgm;
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.statusArea = status;
        this.dictionary = dictionary;
        this.packArea = pack;
        this.sc = tsc;
    }

    @Override
    public void run() {

        Platform.runLater(new Runnable() {
            public void run() {
                statusArea.appendText("Player " + playerName + " started...\n");
            }
        });

        //Extract by turns
        while (playerLetters.size() < 7 && attachedLetterPack.getPackSize() > 0) {
            Tile extractedLetter = attachedLetterPack.extractLetter();
            if (extractedLetter != null) {
                playerLetters.add(extractedLetter);
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        packArea.setText(attachedLetterPack.toString());
//                    }
//                });
                } else {
                    count--;
                }
            }

            StringBuilder sb0 = new StringBuilder(playerName + " first has letters: ");
            for (Tile t : playerLetters) {
                sb0.append(t.getLetter());
            }
            sb0.append("\n");
            Platform.runLater(new Runnable() {
                public void run() {
                    statusArea.appendText(sb0.toString());
        }
            });

        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                //Make words
                this.getWord(playerLetters, "", 0);
                int maxValue = 0;
                Word bestWord = new Word();
                for (Word w : wordsFound) {
                    if (w.getValue() > maxValue) {
                        maxValue = w.getValue();
                        bestWord = w;
                    }
                }

                //REMOVE BEST WORD LETTERS FROM PLAYER LETTERS
                int wordPoints = bestWord.getValue();
                this.addPoints(wordPoints);
                String currentMove = playerName + " created " + bestWord.getWord() + " for " + wordPoints + " points\n";
                Platform.runLater(new Runnable() {
                    public void run() {
                        statusArea.appendText(currentMove);
                    StringBuilder ab = new StringBuilder();
                    ab.append("now: ").append(attachedLetterPack.toString());
                    packArea.appendText(ab.toString());
                }
            });

            //REMOVE BEST WORD LETTERS FROM PLAYER LETTERS
//        System.out.println("best word: " + bestWord.getWord() + " (" + bestWord.getValue() + ")");
            String s = bestWord.getWord();
            for (int i = 0; i < s.length(); i++) {

                for (int k = 0; k < playerLetters.size(); k++) {
                    if (playerLetters.get(k).getLetter() == s.charAt(i)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(playerName).append(" removed: ").append(playerLetters.get(k).getLetter()).append("\n");
                        Platform.runLater(new Runnable() {
                            public void run() {
                                statusArea.appendText(sb.toString());
                        packArea.setText(attachedLetterPack.toString());
                    }
                });
                        playerLetters.remove(k);
                        StringBuilder sb1 = new StringBuilder(playerName + " has letters: ");
                        for (Tile t : playerLetters) {
                            sb1.append(t.getLetter());
                        }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                        sb1.append("\n");
                        Platform.runLater(new Runnable() {
                            public void run() {
                                statusArea.appendText(sb1.toString());
                            }
                        });

                    }
                }
            }

                }
            }

        }

    }

    private void getWord(List<Tile> letters, String currentWord, int currentWordValue) {
        if (currentWord.length() > 1) {
//            if (!currentWord.contains("@")) {
                if (dictionary.contains(currentWord)) {
                    Word w = new Word();
                    w.setPlayer(this);
                    w.setWord(currentWord);
                    w.setValue(currentWordValue);
                    wordsFound.add(w);
                }
//            } 
//            else {
//                String[] wordPieces = currentWord.split("@");
//                for (Object key : dictionary.toArray()) {
//                    if (key.toString().matches(wordPieces[0] + "[a-zA-Z]")) {
//                        Word w1 = new Word();
//                        w1.setPlayer(this);
//                        w1.setWord(key.toString());
//                        w1.setValue(currentWordValue);
//                        wordsFound.add(w1);
//                    }
//                }
//            }
        }

        if (currentWord.length() < 7) {
            for (int i = 0; i < letters.size(); i++) {
                List<Tile> letters2 = new ArrayList<Tile>(letters);
                letters2.remove(letters2.get(i));
                getWord(letters2, currentWord + letters.get(i).getLetter(), currentWordValue + letters.get(i).getValue());
            }
        }
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

    public void addPoints(int value) {
        this.score += value;
        Platform.runLater(new Runnable() {
            public void run() {
                sc.updateScores();
            }
        });
    }

    /**
     * @return the myTurn
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * @param myTurn the myTurn to set
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

}
