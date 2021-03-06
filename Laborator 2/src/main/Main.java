package main;

import control.Reader;
import control.*;

/**
 * Application that solves the Student-Project allocation problem
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Main {

    /**
     * Main function where we create a problem and attach it to a solver
     * @param args 
     */
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
        
        Solver mySolver = new Solver();
        mySolver.setProblem(exampleProblem);
        
        Matching matchingResult = mySolver.solve();
        System.out.println(matchingResult.toString());
    }

}

//Chestii de facut: documentatia de terminat, overwrite la equals si toString, cazuri de exceptie (input incorect), diagrama UML, incercat alte exemple