package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Student extends Person {

    private ArrayList<Project> projectPreferences = new ArrayList<>();
    private Project allocatedProject;

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

    /**
     * Checks if 2 students are equal
     * @param obj is a student object
     * @return boolean true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student stud = (Student) obj;
        return (stud.getName().equals(this.getName()) && stud.getEmail().equals(this.getEmail()));
    }

    /**
     * Converts the projectPreferences array to String 
     * @return string with the student's preferences in projects
     */
    public String toStringProjectPreferences() {
        String projectString;
        StringBuilder sb = new StringBuilder();
        for (Project proj : projectPreferences) {
            sb.append(proj.getName()).append(" ");
        }
        projectString = sb.toString();
        return this.getName() + " : " + projectString;
    }

}
