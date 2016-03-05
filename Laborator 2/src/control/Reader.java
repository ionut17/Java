package control;

import control.*;
import model.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Reader that reads from a file and saves the arrays
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Reader {

    
    private String fileName;
    private final ArrayList<Student> studentList;
    private final ArrayList<Lecturer> lecturerList;
    private final ArrayList<Project> projectList;

    /**
     * Reader constructor with array initializations
     */
    public Reader() {
        this.projectList = new ArrayList<>();
        this.lecturerList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

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

    /**
     * Read from the selected file
     */
    public void read() {
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
        parse(content);
    }

    /**
     * Parse the content and insert the data
     * @param content
     */
    private void parse(String content) {
//        Actual parsing
//        System.out.println(content);
        String[] lines = content.split("\n");
        String[] entitiesCount = lines[0].split(" ");
//        Set the student, lecturer, projects names
        for (int numberId = 0; numberId < 3; numberId++) {
            switch (numberId) {
                case 0: {
//                    Creating students
                    for (int i = 0; i < Integer.valueOf(entitiesCount[numberId]); i++) {
                        Student e = new Student();
                        e.setName("S" + String.valueOf(i + 1));
                        e.setEmail("S" + String.valueOf(i + 1) + "@mail.com");
                        getStudentList().add(e);
                    }
                    break;
                }
                case 1: {
//                    Creating lecturers
                    for (int i = 0; i < Integer.valueOf(entitiesCount[numberId]); i++) {
                        Lecturer e = new Lecturer();
                        e.setName("L" + String.valueOf(i + 1));
                        e.setEmail("L" + String.valueOf(i + 1) + "@mail.com");
                        getLecturerList().add(e);
                    }
                    break;
                }
                case 2: {
                    //Creating projects
                    for (int i = 0; i < Integer.valueOf(entitiesCount[numberId]); i++) {
                        Project p = new Project();
                        p.setName("P" + String.valueOf(i + 1));
                        p.setProjectCapacity(1);
                        getProjectList().add(p);
                    }
                    break;
                }
                default:
                    break;
            }
        }
        //Setting the project capacities
        String[] pCap = lines[1].split(" ");
        for (int i = 0; i < Integer.valueOf(entitiesCount[2]); i++) {
            getProjectList().get(i).setProjectCapacity(Integer.valueOf(pCap[i]));
        }
        //Setting the lecturers capacities
        String[] lCap = lines[2].split(" ");
        for (int i = 0; i < Integer.valueOf(entitiesCount[1]); i++) {
            getLecturerList().get(i).setLecturerCapacity(Integer.valueOf(lCap[i]));
        }
        //Adding preferences
        int count;
        int type = 0;
        for (int i = 3; i < lines.length; i++) {
            if (type == 0) {
                count = Integer.valueOf(entitiesCount[0]);
            } else {
                count = Integer.valueOf(entitiesCount[1]);
            }
            for (int j = 0; j < count; j++) {
                String[] values = lines[i + j].split(" ");
                switch (type) {
                    case 0: {
                        //Add wanted projects to students
                        ArrayList<Project> tempProject = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Project p : getProjectList()) {
                                if (p.getName().equals(auxValues)) {
                                    tempProject.add(p);
                                }
                            }
                        }
                        getStudentList().get(j).setProjectPreferences(tempProject);
                        break;
                    }
                    case 1: {
                        //Add wanted students to lecturers
                        ArrayList<Student> tempStudent = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Student s : getStudentList()) {
                                if (s.getName().equals(auxValues)) {
                                    tempStudent.add(s);
                                }
                            }
                        }
                        getLecturerList().get(j).setStudentPreferences(tempStudent);
                        break;
                    }
                    case 2: {
                        //Add supervised projects to lecturers
                        ArrayList<Project> tempProject = new ArrayList<>();
                        for (String auxValues : values) {
                            for (Project p : getProjectList()) {
                                if (p.getName().equals(auxValues)) {
                                    tempProject.add(p);
                                    p.setProjectLecturer(lecturerList.get(j));
                                }
                            }
                        }
                        getLecturerList().get(j).setProjectProposals(tempProject);
                        break;
                    }
                    default:
                        break;
                }
            }
            i = i + count - 1;
            type++;
        }
    }

    /**
     * @return the studentList
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * @return the lecturerList
     */
    public ArrayList<Lecturer> getLecturerList() {
        return lecturerList;
    }

    /**
     * @return the projectList
     */
    public ArrayList<Project> getProjectList() {
        return projectList;
    }

}
