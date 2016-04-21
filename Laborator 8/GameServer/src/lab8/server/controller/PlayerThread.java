package lab8.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
class PlayerThread extends Thread {

    //Network dependencies
    private Socket socket = null;
    private final int id;

    //Player dependencies
    private final GameManager gm;
    private String playerName;
    private int score = 0;
    private final LetterPack attachedLetterPack;
    private List<Tile> playerLetters = new ArrayList<>();
    private final TextArea statusArea;
    private final HashSet<String> dictionary;
    private ArrayList<Word> wordsFound = new ArrayList<>();
    private final TextArea packArea;
    private final ScoreObserver sc;
//    private boolean myTurn = false;

    public PlayerThread(Socket socket, int tid, GameManager tgm, LetterPack lp, HashSet<String> dictionary, String name, TextArea status, TextArea pack, ScoreObserver tsc) {
        this.socket = socket;
        this.id = tid;
        this.gm = tgm;
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.statusArea = status;
        this.dictionary = dictionary;
        this.packArea = pack;
        this.sc = tsc;
    }

    public void run() {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send the response to the oputput stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());

//            boolean isRunning = true;
            while (attachedLetterPack.getPackSize() > 0) {
                System.out.println("Reading a new command...");
                String request = in.readLine();
                switch (request) {
                    case "join":
                        System.out.println("join");
                        statusArea.appendText("Player " + id + " has joined...\n");
//                    Platform.runLater(new Runnable() {
//                        public void run() {
//                            statusArea.appendText("Player "+pcount+" has joined...\n");
//                        }
//                    });
                        break;
                    case "extract":
                        System.out.println("extracting...");
                        //Extract by turns
                        while (playerLetters.size() < 7 && attachedLetterPack.getPackSize() > 0) {
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
                        System.out.println(playerLetters.toString());
                        break;
                    case "word":
                        System.out.println("making word...");
                        wordsFound.clear();
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
                        String s = bestWord.getWord();
                        for (int pos = 0; pos < s.length(); pos++) {
                            for (int k = 0; k < playerLetters.size(); k++) {
                                if (playerLetters.get(k).getLetter() == s.charAt(pos)) {
                                    playerLetters.remove(k);
                                    break;
                                }
                            }
                        }
                        int wordPoints = bestWord.getValue();
                        this.addPoints(wordPoints);
                        String currentMove = playerName + " created " + bestWord.getWord() + " for " + wordPoints + " points\n";
                        Platform.runLater(new Runnable() {
                            public void run() {
                                statusArea.appendText(currentMove);
                            }
                        });
                        break;
                    case "exit":
//                        isRunning = false;
//                    String raspuns = "finish";
//                    out.println(raspuns);
//                    out.flush();
                        break;
                    default:
                        System.out.println("default");
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("next turn...");
                    out.println("next");
                    out.flush();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            String response = "finish";
            out.println(response);
            out.flush();
            statusArea.appendText("Player " + id + " exited...\n");
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private void getWord(List<Tile> letters, String currentWord, int currentWordValue) {
        if (currentWord.length() > 1) {
//            if (!currentWord.contains("@")) {
            if (dictionary.contains(currentWord)) {
                Word w = new Word();
                w.setPlayer(null);
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
}
