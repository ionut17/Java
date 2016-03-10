package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cell;
import model.Labyrinth;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class AutomatedSolver implements LabyrinthSolver {

    Labyrinth labyrinth;
    int[][] matrix;

    @Override
    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    @Override
    public void setLabyrinth(Labyrinth labyrinth) {
        matrix = new int[labyrinth.getRowCount()][labyrinth.getColumnCount()];
        this.labyrinth = labyrinth;
    }

    @Override
    public void nextCellToExplore(int row, int column) {

    }

    @Override
    public void solve() {
        //Lee implementation
        Cell finish = labyrinth.getFinishCell();
        for (int i = 0; i < labyrinth.getRowCount(); i++) {
            for (int j = 0; j < labyrinth.getColumnCount(); j++) {
                if (labyrinth.isWallAt(i, j)) {
                    matrix[i][j] = -1;
                }
            }
        }
        //implement
        List<Cell> cobra = new ArrayList<>();
        cobra.add(labyrinth.getStartCell());
        int[] shiftRow = {-1, 0, 1, 0};
        int[] shiftColumn = {0, 1, 0, -1};
        int ok = 0;
        while (!cobra.isEmpty() && ok == 0) {
            Cell currentCell = cobra.get(0);
            cobra.remove(currentCell);
            for (int i = 0; i < 4; i++) {
                Cell newCell = new Cell(currentCell.getRow() + shiftRow[i], currentCell.getColumn() + shiftColumn[i]);
                //Check if the new cell is in the labyrinth
                if (newCell.getRow() >= 0 && newCell.getColumn() >= 0 && newCell.getRow() < labyrinth.getRowCount() && newCell.getColumn() < labyrinth.getColumnCount() && !newCell.equals(labyrinth.getStartCell())) {
                    //Check if the new cell is empty
                    if (labyrinth.isFinishCell(newCell.getRow(), newCell.getColumn())) {
                        //what we do with the solution
                        matrix[newCell.getRow()][newCell.getColumn()] = matrix[currentCell.getRow()][currentCell.getColumn()] + 1;
                        ok = 1;
                        break;
                    }
                    if (matrix[newCell.getRow()][newCell.getColumn()] == 0) {
                        cobra.add(newCell);
                        matrix[newCell.getRow()][newCell.getColumn()] = matrix[currentCell.getRow()][currentCell.getColumn()] + 1;
                    }
                }
            }
        }
        if (ok == 1) {
            
            Cell back = finish;
            System.out.println("Finish cell: "+finish.getRow()+finish.getColumn()+" value: "+matrix[finish.getRow()][finish.getColumn()]);
            ok=0;
            while (ok==0) {
                for (int i = 0; i < 4; i++) {
                    Cell backTry = new Cell(back.getRow() + shiftRow[i], back.getColumn() + shiftColumn[i]);
                    if (backTry.getRow() >= 0 && backTry.getColumn() >= 0 && backTry.getRow() < labyrinth.getRowCount() && backTry.getColumn() < labyrinth.getColumnCount()) {
//                        System.out.format("Comparing: %d == %d\n",matrix[backTry.getRow()][backTry.getColumn()],(matrix[back.getRow()][back.getColumn()] - 1));
                        if (matrix[backTry.getRow()][backTry.getColumn()] == (matrix[back.getRow()][back.getColumn()] - 1)) {
                            if (matrix[backTry.getRow()][backTry.getColumn()] == 0 && backTry.equals(labyrinth.getStartCell())) {
                                ok = 1;
                            }
                            labyrinth.addPath(backTry.getRow(), backTry.getColumn());
                            back = backTry;
                            break;
                        }
                    }
                }
            }
            labyrinth.notifyObservers();
        }
    }
    
}
