package model;

import java.util.ArrayList;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Project {

    private String name;
    private int projectCapacity;
    private Lecturer projectLecturer;
    private ArrayList<Student> acceptedStudents = new ArrayList<>();

    /**
     * Checks if the project still has open spots
     *
     * @return boolean true or false
     */
    public boolean isFree() {
        return acceptedStudents.size() < projectCapacity;
    }

    /**
     * Adds a student to the project's accepted list
     *
     * @param stud
     */
    public void addStudent(Student stud) {
        acceptedStudents.add(stud);
    }

    /**
     * Removes a student from the project's accepted list
     *
     * @param stud
     */
    public void removeStudent(Student stud) {
        acceptedStudents.remove(stud);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the projectCapacity
     */
    public int getProjectCapacity() {
        return projectCapacity;
    }

    /**
     * @param projectCapacity the projectCapacity to set
     */
    public void setProjectCapacity(int projectCapacity) {
        this.projectCapacity = projectCapacity;
    }

    /**
     * @return the acceptedStudents
     */
    public ArrayList<Student> getAcceptedStudents() {
        return acceptedStudents;
    }

    /**
     * @param acceptedStudents the acceptedStudents to set
     */
    public void setAcceptedStudents(ArrayList<Student> acceptedStudents) {
        this.acceptedStudents = acceptedStudents;
    }

    /**
     * @return the projectLecturer
     */
    public Lecturer getProjectLecturer() {
        return projectLecturer;
    }

    /**
     * @param projectLecturer the projectLecturer to set
     */
    public void setProjectLecturer(Lecturer projectLecturer) {
        this.projectLecturer = projectLecturer;
    }

    /**
     * Checks if 2 projects are equal
     *
     * @param obj is a project object
     * @return boolean true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Project)) {
            return false;
        }
        Project proj = (Project) obj;
        return (proj.getName().equals(this.getName()) && proj.getProjectCapacity() == this.getProjectCapacity() && proj.getProjectLecturer().equals(this.getProjectLecturer()));
    }

}
