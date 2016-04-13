package lab7.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Ionut
 */
public class Player implements Runnable {

    private String playerName;
    private int score = 0;
    private final LetterPack attachedLetterPack;
    private final HashSet<String> dictionary;
    private ArrayList<Word> wordsFound=new ArrayList<>();
    
    public Player(LetterPack lp, HashSet<String> dictionary, String name) {
        this.playerName = name;
        this.attachedLetterPack = lp;
        this.dictionary = dictionary;
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
        System.out.println("Configuring words...");
        this.getWord(letters, "",0);
        int maxValue=0;
        Word bestWord= new Word();
        for(Word w: wordsFound){
            if(w.getValue()>maxValue){
                maxValue=w.getValue();
                bestWord=w;
            }
        }
        System.out.println("best word: "+bestWord.getWord()+" ("+bestWord.getValue()+")");
        System.out.println("Pack size: " + attachedLetterPack.getPackSize());
    }

    private void getWord(List<Tile> letters, String currentWord, int currentWordValue) {
        if(currentWord.length()>1 && dictionary.contains(currentWord)){
            Word w=new Word();
            w.setPlayer(this);
            w.setWord(currentWord);
            w.setValue(currentWordValue);
            wordsFound.add(w);
        }
        if(currentWord.length()<7){
            for(int i=0;i<letters.size();i++){
                List<Tile> letters2=new ArrayList<Tile>(letters);
                letters2.remove(letters2.get(i));
                getWord(letters2, currentWord+letters.get(i).getLetter(), currentWordValue+letters.get(i).getValue());
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
