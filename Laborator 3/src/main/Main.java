package main;

import controller.*;
import model.*;
import view.*;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Labyrinth maze = new LabyrinthFactory().createRandom(10, 10);
        Labyrinth maze = new LabyrinthFactory().readFromFile("example3");
//        System.out.println(maze.getView().toString());
        maze.setView(new TextView());
        maze.setSolver(new AutomatedSolver());
//        maze.setSolver(new KeyboardSolver());
        maze.addObserver(new PrintScreen());
        maze.solve();
    }
}
