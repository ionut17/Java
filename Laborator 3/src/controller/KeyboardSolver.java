package controller;

import java.util.Scanner;
import model.Labyrinth;

/**
 *
 * @author Ionut
 */
public class KeyboardSolver implements LabyrinthSolver{

    Labyrinth labyrinth;
    Scanner sc = new Scanner(System.in);
    
    @Override
    public Labyrinth getLabyrinth() {
        return this.labyrinth;
    }

    @Override
    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void nextCellToExplore(int row, int column) {
        if (labyrinth.isFinishCell(row,column)){
            //TODO: something
        }
        else {
            char direction = sc.next().charAt(0);
            switch(direction){
                case 'w':
                    if (row>0 && !labyrinth.isWallAt(row,column)){
                        nextCellToExplore(row-1,column);
                    }
                    break;
                case 's':
                    if (row<(labyrinth.getRowCount()-1) && !labyrinth.isWallAt(row,column)){
                        nextCellToExplore(row+1,column);
                    }
                    break;
                case 'a':
                    if (column>0 && !labyrinth.isWallAt(row,column)){
                        nextCellToExplore(row,column-1);
                    }
                    break;
                case 'd':
                    if (column<(labyrinth.getColumnCount()-1) && !labyrinth.isWallAt(row,column)){
                        nextCellToExplore(row,column+1);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
    
}
