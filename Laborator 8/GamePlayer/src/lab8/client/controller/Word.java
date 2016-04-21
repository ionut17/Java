package lab8.client.controller;

/**
 *
 * @author Anca
 */
public class Word {
    private String word;
    private int value;
    private GamePlayer player;

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the player
     */
    public GamePlayer getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(GamePlayer player) {
        this.player = player;
    }
}
