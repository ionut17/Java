package main;

import model.Labyrinth;
import model.LabyrinthFactory;
import view.TextView;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Labyrinth maze = new LabyrinthFactory().createRandom(10,10);
        maze.setView(new TextView());
        maze.getView().toString();        
        Labyrinth testMaze=new LabyrinthFactory().readFromFile("MazeExample.txt");
        testMaze.setView(new TextView());
        testMaze.getView().toString();
    }
}

/*

labirynth: interfata continut labirint

main():
Labyrinth maze = LabyrinthFactory.createRandom(10,10) (method factory ce ascunde numele) -clasa ce contine diverse metode de creare a labirinturilor
                                  .readFromFile("filename.txt")
                                  .exemplu()

maze.setView(new TextView()); (ce clasa face reprezentarea: una care face println sau face un grafic),(ce fel de reprezentare sa foloseasca)
               TextView() implement LabyrinthView() (afisare in mod text a labirintului)
maze.setSolver(new KeyboardSolver()); (in procesul de dezvoltare afisez labirintul actualizat - starea curenta; util pentru KeyboardSolver());
        -> pentru actualizare: callback sau observer
*Observer:
maze.addObserver(new PrintScreen()); (pentru afisare pe ecran actualizata la fiecare pas)
maze.addObserver(new StoreSolution()); (verifica la fiecare pas daca am solutie sau nu si salvam solutia)

maze.solve();

LabyrinthSolver: keyboardSolver, automatedSolver


*/
