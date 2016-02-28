package main;

import control.*;
import model.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Reader {
    
    private String fileName;
    
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    void read() {
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Lecturer> lecturerList = new ArrayList<>();
        ArrayList<Project> projectList = new ArrayList<>();
//      Reading
        String content = null;
        File file = new File(fileName);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Actual parsing
//        System.out.println(content);
        String[] lines = content.split("\n");
        String[] number = lines[0].split(" ");
        for (int numberId = 0; numberId < 3; numberId++) {
            switch (numberId) {
                case 0: {
                    //Creating students
                    for (int i = 0; i < Integer.valueOf(number[numberId]); i++) {
                        Student e = new Student();
                        e.setName("S" + String.valueOf(i + 1));
                        e.setEmail("S" + String.valueOf(i + 1) + "@mail.com");
                        studentList.add(e);
                    }
                    break;
                }
                case 1: {
                    //Creating lecturers
                    for (int i = 0; i < Integer.valueOf(number[numberId]); i++) {
                        Lecturer e = new Lecturer();
                        e.setName("L" + String.valueOf(i + 1));
                        e.setEmail("L" + String.valueOf(i + 1) + "@mail.com");
                        lecturerList.add(e);
                    }
                    break;
                }
                case 2: {
                    //Creating projects
                    for (int i = 0; i < Integer.valueOf(number[numberId]); i++) {
                        Project p = new Project();
                        p.setName("P" + String.valueOf(i + 1));
                        p.setProjectCapacity(1);
                        projectList.add(p);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        //Setting the project capacities
        String[] pCap = lines[1].split(" ");
        for (int i = 0; i < Integer.valueOf(number[2]); i++) {
            projectList.get(i).setProjectCapacity(Integer.valueOf(pCap[i]));
        }
        //Setting the lecturers capacities
        String[] lCap = lines[2].split(" ");
        for (int i = 0; i < Integer.valueOf(number[1]); i++) {
            lecturerList.get(i).setLecturerCapacity(Integer.valueOf(lCap[i]));
        }
        //Adding preferences
        int count = 0;
        int type = 0;
        for (int i = 3; i < lines.length; i++) {
            if (type == 0) count = Integer.valueOf(number[0]);
            else count = Integer.valueOf(number[1]);
            for (int j = 0; j < count; j++) {
                String[] values = lines[i + j].split(" ");
                switch (type) {
                    case 0: {
                        //Add wanted projects to students
                        ArrayList<Project> tempProject = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Project p : projectList) {
                                if (p.getName().equals(auxValues)) {
                                    tempProject.add(p);
                                }
                            }
                        }
                        studentList.get(j).setProjectPreferences(tempProject);
                        break;
                    }
                    case 1: {
                        //Add wanted students to lecturers
                        ArrayList<Student> tempStudent = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Student s : studentList) {
                                if (s.getName().equals(auxValues)) {
                                    tempStudent.add(s);
                                }
                            }
                        }
                        lecturerList.get(j).setStudentPreferences(tempStudent);
                        break;
                    }
                    case 2: {
                        //Add supervised projects to lecturers
                        ArrayList<Project> tempProject = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Project p : projectList) {
                                if (p.getName().equals(auxValues)) {
                                    tempProject.add(p);
                                }
                            }
                        }
                        lecturerList.get(j).setProjectProposals(tempProject);
                        break;
                    }
                    default:
                        break;
                }
            }
            i = i + count - 1;
            type++;
        }
        //Creating the problem
        Problem exampleProblem = new Problem();
        exampleProblem.setStudentList(studentList);
        exampleProblem.setLecturerList(lecturerList);
        exampleProblem.setProjectList(projectList);
        System.out.println(exampleProblem.toString());
    }
    
}
