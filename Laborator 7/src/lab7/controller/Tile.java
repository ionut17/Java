package lab7.controller;

/**
 *
 * @author Ionut
 */
class Tile {
    
    private char letter;
    private int value;
    
    Tile(char srcLetter, int srcValue){
        this.letter = srcLetter;
        this.value = srcValue;
    }

    /**
     * @return the letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(char letter) {
        this.letter = letter;
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

    @Override
    public String toString() {
        return letter+" "+value+" points";
    }
    
}
