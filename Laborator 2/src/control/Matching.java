package control;

import java.util.ArrayList;
import model.*;

/**
 * Creates the solution by getting the student list with the assigned projects
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Matching {

    private ArrayList<Student> studentList;

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
     * Prints the table with the student and allocated project
     *
     * @return string with the solution data
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Students & Allocated Projects\n");
        for (Student stud : studentList) {
            sb.append(stud.getName()).append("->");
            if (stud.getAllocatedProject() != null) {
                sb.append(stud.getAllocatedProject().getName());
            } else {
                sb.append("null");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
