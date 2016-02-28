package control;

import java.util.ArrayList;
import model.*;

/**
 *
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

    public void solve() {
        ArrayList<Student> studentList = problem.getStudentList();
        for (int i = 0; i < studentList.size(); i++) {
            Student stud = studentList.get(i);
            if (stud.isFree()) {
                Project preference = stud.getFirstAvailablePreference();
                while (preference != null) {
                    if (preference.isFree()) {
                        stud.setAllocatedProject(preference);
                        System.out.println(stud.getName() + " got the project " + preference.getName());
                        break;
                    } else {
                        ArrayList<Student> tempStuds = preference.getProjectLecturer().getStudentPreferences();
                        int studIndex = tempStuds.indexOf(stud);
                        int maxIndex = 0;
                        ArrayList<Student> tempProjectStuds = preference.getAcceptedStudents();
                        for (Student projectStudent : tempProjectStuds) {
                            if (tempStuds.indexOf(projectStudent) > maxIndex) {
                                maxIndex = tempStuds.indexOf(projectStudent);
                            }
                        }
                        if (maxIndex > studIndex) {
                            Student removedStudent = tempProjectStuds.get(maxIndex);
                            preference.removeStudent(removedStudent);
                            removedStudent.setAllocatedProject(null);
                            System.out.println(removedStudent.getName() + " lost the project " + preference.getName());

                            preference.addStudent(stud);
                            stud.setAllocatedProject(preference);
                            System.out.println(stud.getName() + " got the project " + preference.getName());

                            //Jump back in for
                            i = studentList.indexOf(removedStudent);
                            break;
                        }
                    }
                    preference = stud.getFirstAvailablePreference();
                }
            }
        }

    }
}
