package model;

import java.util.ArrayList;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Student extends Person {
    
    private ArrayList<Project> projectPreferences = new ArrayList<>();

    /**
     * Checks if the student has any preferences
     * @return true or false
     */
    @Override
    public boolean isFree() {
        return projectPreferences.isEmpty();
    }

    /**
     * @return the projectPreferences
     */
    public ArrayList<Project> getProjectPreferences() {
        return projectPreferences;
    }

    /**
     * @param projectPreferences the projectPreferences to set
     */
    public void setProjectPreferences(ArrayList<Project> projectPreferences) {
        this.projectPreferences = projectPreferences;
    }

}
