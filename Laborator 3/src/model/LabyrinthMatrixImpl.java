package model;

import controller.LabyrinthSolver;
import view.LabyrinthView;

/**
 *
 * @author Ionut
 */
public class LabyrinthMatrixImpl implements Labyrinth {

    private int[][] matrix;
    private Cell startCell;
    private Cell finishCell;
    private LabyrinthView attachedViewer;
    private LabyrinthSolver attachedSolver;
    
    LabyrinthMatrixImpl(int rowCount, int columnCount){
        matrix = new int[rowCount][columnCount];
    }

    @Override
    public int getRowCount() {
        return matrix.length;
    }

    @Override
    public int getColumnCount() {
        return matrix[0].length;
    }

    @Override
    public boolean isFreeAt(int row, int column) {
        return matrix[row][column] == 0;
    }

    @Override
    public boolean isWallAt(int row, int column) {
        return matrix[row][column] == 1;
    }

    @Override
    public Cell getStartCell() {
        return startCell;
    }

    @Override
    public Cell getFinishCell() {
        return finishCell;
    }

    @Override
    public boolean isStartCell(int row, int column) {
        return (startCell.getRow() == row && startCell.getColumn() == column);
    }

    @Override
    public boolean isFinishCell(int row, int column) {
        return (finishCell.getRow() == row && finishCell.getColumn() == column);
    }
    
    @Override
    public LabyrinthView getView() {
        return this.attachedViewer;
    }
    
    
    @Override
    public void setView(LabyrinthView view) {
        this.attachedViewer = view;
        this.attachedViewer.setLabyrinth(this);
    }

    @Override
    public void setSolver(LabyrinthSolver solver) {
        this.attachedSolver = solver;
    }
    
    public void setCell(int row, int column, int value){
        matrix[row][column] = value;
    }

    /**
     * @param startCell the startCell to set
     */
    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    /**
     * @param finishCell the finishCell to set
     */
    public void setFinishCell(Cell finishCell) {
        this.finishCell = finishCell;
    }

}
