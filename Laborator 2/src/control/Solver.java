package control;

import java.util.ArrayList;
import model.*;

/**
 * Solver class which solves a Problem with an algorithm
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Solver {

    private Problem problem;

    /**
     * @return the problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * @param problem the problem to set
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Solves the assigned problem with the current algorithm
     * @return Matching with the student-project pairs
     */
    public Matching solve() {
        ArrayList<Student> studentList = problem.getStudentList();
        //Parse all the students and assign them a project
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            //If the student doesn't have an asigned project
            if (stud.isFree()) {
                //Getting student's first available project preference
                Project preference = stud.getFirstAvailablePreference();
                while (preference != null) {
                    //If the preference has open spots assign the project to the student
                    if (preference.isFree()) {
                        stud.setAllocatedProject(preference);
                        preference.addStudent(stud);
                        break;
                    } else { //Search the least wanted student that got the wanted project
                        ArrayList<Student> tempStuds = preference.getProjectLecturer().getStudentPreferences();
                        int studIndex = tempStuds.indexOf(stud);
                        int maxIndex = 0;
                        ArrayList<Student> tempProjectStuds = preference.getAcceptedStudents();
                        for (Student projectStudent : tempProjectStuds) {
                            if (tempStuds.indexOf(projectStudent) > maxIndex) {
                                maxIndex = tempStuds.indexOf(projectStudent);
                            }
                        }
                        //If the current student is wanted more than the least wanted student that got a project, assign him the project and remove the other's project
                        if (maxIndex > studIndex) {
                            Student removedStudent = tempStuds.get(maxIndex);
                            //Jump back in for to check the user's preferences again and assign another project
                            i = studentList.indexOf(removedStudent) - 1;
                            preference.removeStudent(removedStudent);
                            removedStudent.setAllocatedProject(null);
                            preference.addStudent(stud);
                            stud.setAllocatedProject(preference);
                            break;
                        }
                    }
                    //If it wasn't break, it means we can't give the current student the current wanted project so we advance in the wanted project list
                    preference = stud.getFirstAvailablePreference();
                }
                //If no available project can be assigned to the student, then the student will have no project
            }
        }
        //Call the matching to get the student list with the assigned projects
        Matching result = new Matching();
        result.setStudentList(problem.getStudentList());
        return result;
    }
}
