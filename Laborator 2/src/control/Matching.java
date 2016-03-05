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
     * Checks if 2 matchings are equal
     *
     * @param obj is a matching object
     * @return boolean true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Matching)) {
            return false;
        }
        Matching match = (Matching) obj;
        return (match.getStudentList().equals(this.getStudentList()));
    }


    /**
     * Prints the table with the student and allocated project and their satisfaction
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
                sb.append(" ").append(stud.getStudentSatisfaction());
            } else {
                sb.append("null");
                sb.append(" 0");
            }
            sb.append("%\n");
        }
        return sb.toString();
    }
    
}
