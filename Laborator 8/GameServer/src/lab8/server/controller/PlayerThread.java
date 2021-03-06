package lab8.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
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
    private final LetterPackGenerator lpg = new LetterPackGenerator();
    private final TextArea statusArea;
    private final TextArea packArea;
    private final ScoreObserver sc;
    private List<Tile> playerLetters = new ArrayList<>();
    private Player attachedPlayer;
    private HashSet<String> dictionary = null;
//    private boolean myTurn = false;

    public PlayerThread(Socket socket, int tid, GameManager tgm, LetterPack lp, String name, TextArea status, TextArea pack, ScoreObserver tsc) throws IOException {
        this.socket = socket;
        this.id = tid;
        this.gm = tgm;
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.statusArea = status;
        this.packArea = pack;
        this.sc = tsc;
        Dictionary d = Dictionary.getDictionary();
        this.dictionary = d.getWords();
        this.attachedPlayer = new Player(gm, attachedLetterPack, dictionary, String.valueOf("Player " + id), statusArea, packArea, sc);
        sc.observePlayer(attachedPlayer);
        Platform.runLater(new Runnable() {
            public void run() {
                sc.updateScores();
            }
        });
    }

    public void run() {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send the response to the oputput stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream());

//            try {
//                Thread.sleep(1000);
                System.out.println("next turn...");
                out.println("next");
                out.flush();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }

            String request;

//            boolean isRunning = true;
            while (attachedLetterPack.getPackSize() > 0) {
                System.out.println("Reading a new command...");
                request = in.readLine();
                switch (request) {
                    case "#extract":
                        System.out.println("extracting...");
                        //Extract by turns
                        StringBuilder letterString = new StringBuilder();
                        for (int i = 0; i < playerLetters.size(); i++) {
                            letterString.append(playerLetters.get(i).getLetter());
                        }
                        while (playerLetters.size() < 7 && attachedLetterPack.getPackSize() > 0) {
                            Tile extractedLetter = attachedLetterPack.extractLetter();
                            if (extractedLetter != null) {
                                playerLetters.add(extractedLetter);
                                letterString.append(extractedLetter.getLetter());
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        packArea.setText(attachedLetterPack.toString());
                                    }
                                });
                            }
                        }
                        out.println(letterString);
                        System.out.println(playerLetters.toString());
                        try {
                            Thread.sleep(1000);
                            System.out.println("next turn...");
                            out.println("next");
                            out.flush();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "#exit":
//                        isRunning = false;
//                    String raspuns = "finish";
//                    out.println(raspuns);
//                    out.flush();
                        break;
                    default:
                        if (dictionary.contains(request)) {
                            int wordPoints = 0;
                            //REMOVE BEST WORD LETTERS FROM PLAYER LETTERS
                            String s = request;
                            for (int pos = 0; pos < s.length(); pos++) {
                                for (int k = 0; k < playerLetters.size(); k++) {
                                    if (playerLetters.get(k).getLetter() == s.charAt(pos)) {
                                        playerLetters.remove(k);
                                        break;
                                    }
                                }
                            }
                            //Adding points
                            for (int i = 0; i < request.length(); i++) {
                                wordPoints += lpg.valueOf(request.charAt(i));
                            }
                            this.addPoints(wordPoints);
                            
                            //Definition
                            URL def = new URL(" http://www.dictionaryapi.com/api/v1/references/thesaurus/xml/" + request + "?key=fc40ba93-ec7d-4335-895d-a6a293543113");
                            URLConnection con = def.openConnection();
                            InputStream is = con.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                            String line;
                            String all_def = new String();
                            while ((line = br.readLine()) != null) {
                                all_def += line;
                            }
                            String[] pieces = all_def.split("<mc>");
                            String currentMove;
                            if (pieces.length > 1) {
                                String[] pieces2 = pieces[1].split("</mc>");
                                System.out.println("definition:" + pieces2[0]);
                                currentMove = playerName + " created " + request + " for " + wordPoints + " points\ndefinition: "+ pieces2[0]+"\n\n";
                            }
                            else {
                                currentMove = playerName + " created " + request + " for " + wordPoints + " points\n";
                            }
                            
                            //View
                            attachedPlayer.setScore(this.getScore());
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    statusArea.appendText(currentMove);
                                    sc.updateScores();
                                }
                            });
                        }
                }
            }
            String response = "#finish";
            out.println(response);
            out.flush();
//            statusArea.appendText("Player " + id + " exited...\n");
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
