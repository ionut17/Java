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
        Reader rd = new Reader();
        rd.setFileName("ExampleSource.txt");
        rd.read();
        
        Problem exampleProblem = new Problem();
        
        exampleProblem.setStudentList(rd.getStudentList());
        exampleProblem.setLecturerList(rd.getLecturerList());
        exampleProblem.setProjectList(rd.getProjectList());        
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
