package model;

import java.util.ArrayList;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Student extends Person {

    private ArrayList<Project> projectPreferences = new ArrayList<>();
    private Project allocatedProject = new Project();

    /**
     * Checks if the student has any preferences
     *
     * @return true or false
     */
    @Override
    public boolean isFree() {
        return allocatedProject == null;
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

    /**
     * @return the allocatedProject
     */
    public Project getAllocatedProject() {
        return allocatedProject;
    }

    /**
     * @param allocatedProject the allocatedProject to set
     */
    public void setAllocatedProject(Project allocatedProject) {
        this.allocatedProject = allocatedProject;
    }

}
