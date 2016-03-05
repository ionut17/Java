package laborator.pkg3;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
