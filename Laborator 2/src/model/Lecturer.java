package model;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Lecturer extends Person {

    private int lecturerCapacity = 0;
    
    /**
     * Checks if the lecturer has any project openings
     * @return true or false
     */
    @Override
    public boolean isFree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the lecturerCapacity
     */
    public int getLecturerCapacity() {
        return lecturerCapacity;
    }

    /**
     * @param lecturerCapacity the lecturerCapacity to set
     */
    public void setLecturerCapacity(int lecturerCapacity) {
        this.lecturerCapacity = lecturerCapacity;
    }

}
