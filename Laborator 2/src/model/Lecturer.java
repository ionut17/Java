package model;

import java.util.ArrayList;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Lecturer extends Person {

    private int lecturerCapacity = 0;
    private ArrayList<Student> studentPreferences = new ArrayList<>();
    private ArrayList<Project> projectProposals = new ArrayList<>();
    private ArrayList<Student> acceptedStudents = new ArrayList<>();

    /**
     * Checks if the lecturer has any project openings
     *
     * @return true or false
     */
    @Override
    public boolean isFree() {
        return acceptedStudents.size() < lecturerCapacity;
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

    /**
     * @return the studentPreferences
     */
    public ArrayList<Student> getStudentPreferences() {
        return studentPreferences;
    }

    /**
     * @param studentPreferences the studentPreferences to set
     */
    public void setStudentPreferences(ArrayList<Student> studentPreferences) {
        this.studentPreferences = studentPreferences;
    }

    /**
     * @return the projectProposals
     */
    public ArrayList<Project> getProjectProposals() {
        return projectProposals;
    }

    /**
     * @param projectProposals the projectProposals to set
     */
    public void setProjectProposals(ArrayList<Project> projectProposals) {
        this.projectProposals = projectProposals;
    }

    /**
     * @return the acceptedStudents
     */
    public ArrayList<Student> getAcceptedStudents() {
        return acceptedStudents;
    }

    /**
     * @param acceptedStudent add the acceptedStudent to array
     */
    public void setAcceptedStudents(Student acceptedStudent) {
        this.acceptedStudents.add(acceptedStudent);
    }

    /**
     * @param acceptedStudents the acceptedStudents to set
     */
    public void setAcceptedStudents(ArrayList<Student> acceptedStudents) {
        this.acceptedStudents = acceptedStudents;
    }

}
