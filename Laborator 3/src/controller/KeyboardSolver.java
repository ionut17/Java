package controller;

import java.util.*;
import model.Labyrinth;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class KeyboardSolver implements LabyrinthSolver {

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
        if (labyrinth.isFinishCell(row, column)) {
            System.out.println("Finished! The explorer has escaped!");
            labyrinth.notifyObservers();
        } else {
//            System.out.format("Current position: %d|%d\n", row, column);
            labyrinth.addPath(row, column);
            labyrinth.notifyObservers();
            System.out.print("Enter command: ");
            char direction = sc.next().charAt(0);
            System.out.println();
            switch (direction) {
                case 'w':
                    if (row > 0 && !labyrinth.isWallAt(row - 1, column)) {
//                        System.out.format("Going UP to: %d|%d\n", row - 1, column);
                        labyrinth.setExplorerPosition(row - 1, column);
                        nextCellToExplore(row - 1, column);
                    } else {
                        System.out.format("Invalid move!\n");
                        labyrinth.setExplorerPosition(row, column);
                        nextCellToExplore(row, column);
                    }
                    break;
                case 's':
                    if (row < (labyrinth.getRowCount() - 1) && !labyrinth.isWallAt(row + 1, column)) {
//                        System.out.format("Going DOWN to: %d|%d\n", row + 1, column);
                        labyrinth.setExplorerPosition(row + 1, column);
                        nextCellToExplore(row + 1, column);
                    } else {
                        System.out.format("Invalid move!\n");
                        labyrinth.setExplorerPosition(row, column);
                        nextCellToExplore(row, column);
                    }
                    break;
                case 'a':
                    if (column > 0 && !labyrinth.isWallAt(row, column - 1)) {
//                        System.out.format("Going LEFT to: %d|%d\n", row, column - 1);
                        labyrinth.setExplorerPosition(row, column - 1);
                        nextCellToExplore(row, column - 1);
                    } else {
                        System.out.format("Invalid move!\n");
                        labyrinth.setExplorerPosition(row, column);
                        nextCellToExplore(row, column);
                    }
                    break;
                case 'd':
                    if (column < (labyrinth.getColumnCount() - 1) && !labyrinth.isWallAt(row, column + 1)) {
//                        System.out.format("Going RIGHT to: %d|%d\n", row, column + 1);
                        labyrinth.setExplorerPosition(row, column + 1);
                        nextCellToExplore(row, column + 1);
                    } else {
                        System.out.format("Invalid move!\n");
                        labyrinth.setExplorerPosition(row, column);
                        nextCellToExplore(row, column);
                    }
                    break;
                default:
                    System.out.format("Invalid move!\n");
                    labyrinth.setExplorerPosition(row, column);
                    nextCellToExplore(row, column);
                    break;
            }
        }
    }

    @Override
    public void solve() {
        labyrinth.setExplorerPosition(labyrinth.getStartCell().getRow(),labyrinth.getStartCell().getColumn());
        nextCellToExplore(this.labyrinth.getStartCell().getRow(), this.labyrinth.getStartCell().getColumn());
    }

}
