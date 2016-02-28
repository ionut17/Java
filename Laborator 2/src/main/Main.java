package main;

import java.io.*;
import java.util.ArrayList;
import control.*;
import model.*;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Main {

    public static void main(String[] args) {

        Main app = new Main();
        app.read("ExampleSource.txt");
        //app.debug();

        // TODO code application logic here
    }

    void read(String fileName) {
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

    public void debug() {
        Project proj = new Project();
        ArrayList<Project> projectList = new ArrayList<>();
        proj.setName("P1");
        projectList.add(proj);

        Student stud = new Student();
        stud.setName("Marcel");
        stud.setEmail("gugustiuc@marcel.com");
        stud.setProjectPreferences(projectList);

        System.out.println(stud.isFree());

        stud.setAllocatedProject(proj);

        System.out.println(stud.isFree());

        Problem exampleProblem = new Problem();
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(stud);
        ArrayList<Lecturer> lecturerList = new ArrayList<>();
        Lecturer lect = new Lecturer();
        lect.setName("Gigel");
        lect.setEmail("gigel@gigelmail.com");
        lect.setStudentPreferences(studentList);
        lect.setAcceptedStudents(studentList);
        lect.setProjectProposals(projectList);
        lecturerList.add(lect);
        exampleProblem.setStudentList(studentList);
        exampleProblem.setProjectList(projectList);
        exampleProblem.setLecturerList(lecturerList);
        System.out.println(exampleProblem.toString());
    }

}

// Clasa ce rezolva problema pe care vreau sa o rezolv (SMProblem, get/set Studenti, get/set profesori si proiecte)
// ExampleProblem instanta a clasei Problem (setez st,prof,proiecte) si apelez toString
// Student,Profesor extends Person (Person-abstracta), Proiect
// Student nume,email,status(are proiect?)
// Clasa responsabila cu rezolvarea unei probleme SMProblem, abstractSolver(set,getter), new Solver, solver.setProblem, solver.execute()
// punem in solver o problema si executam un anumit cod, extind solver-ul abstract, in care o sa am mai multi solveri cu algoritm diferti
//ma plimb prin studenti si cu getProject aflu solutia
//Clasa Matching(Solution) unde cand termin alg iau din fiecare student proiectul si creeze o structura de date unde am toti stundetii pusi si
//usor de modificat
//Clasa Adresa, obiectul Persoana are referinta la o clasa tip adresa
//pachete: pachet Model (Persoana,Student,Profesor,Proiect), pachet Control(Solver) (alg. pentru rez problemei)
/*
    Legat de algoritm:
    S1;1,2,3
S2:1,2,3
s3:1,2

S[1].setPref(p[1],p[2],p[3])
L[1]=s2,s3,s1
c[l[1]]=2;
 */
