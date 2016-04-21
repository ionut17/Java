package lab8.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Ionut
 */
public class GamePlayer {

    private HashSet<String> dictionary = null;
    private ArrayList<Word> wordsFound = new ArrayList<>();
    private List<Tile> playerLetters = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        GamePlayer gp = new GamePlayer();
    }

    public GamePlayer() throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        Dictionary d = Dictionary.getDictionary();
        this.dictionary = d.getWords();
        try (
                //Try resources
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send a join request to the server
            String request;

            // While the game is on play it
            String response = null;
            response = in.readLine();
            while (response == null || !response.equals("#finish")) {
                try {
                    //Do things here
                    request = "#extract";
                    out.println(request);
                    //Reading letters
                    response = in.readLine();
                    playerLetters.clear();
                    System.out.println(response);
                    if (response != null && !response.equals("#finish")) {
                        for (int i = 0; i < response.length(); i++) {
                            playerLetters.add(new Tile(response.charAt(i), distribution[(int) (response.charAt(i) - 'A')]));
                        }
                        Thread.sleep(100);
                        //Sending word back to server
                        request = this.makeWord().getWord();
                        out.println(request);
                        //Waiting next turn
                        response = in.readLine();
                    }
                    else {
                        break;
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
            //After the game ended make an exit request
            request = "exit";
            out.println(request);
        }
    }

    private Word makeWord() {
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
        return bestWord;
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

    int[] distribution = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
}
