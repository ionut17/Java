package control;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Problem {
    
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Lecturer> lecturerList = new ArrayList<>();
    private ArrayList<Project> projectList = new ArrayList<>();

    /**
     * @return the studentList
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * @return the lecturerList
     */
    public ArrayList<Lecturer> getLecturerList() {
        return lecturerList;
    }

    /**
     * @param lecturerList the lecturerList to set
     */
    public void setLecturerList(ArrayList<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    /**
     * @return the projectList
     */
    public ArrayList<Project> getProjectList() {
        return projectList;
    }

    /**
     * @param projectList the projectList to set
     */
    public void setProjectList(ArrayList<Project> projectList) {
        this.projectList = projectList;
    }
    
    
}
