package model;

import controller.LabyrinthObserver;
import controller.LabyrinthSolver;
import java.util.ArrayList;
import java.util.List;
import view.LabyrinthView;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public class LabyrinthMatrixImpl implements Labyrinth {

    private final int[][] matrix;
    private Cell startCell;
    private Cell finishCell;
    private Cell explorerPosition;
    private LabyrinthView attachedViewer;
    private LabyrinthSolver attachedSolver;
    List<LabyrinthObserver> labyrinthObservers = new ArrayList<>();
    List<Cell> path = new ArrayList<>();

    LabyrinthMatrixImpl(int rowCount, int columnCount) {
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
    public LabyrinthSolver getSolver() {
        return this.attachedSolver;
    }

    @Override
    public void setSolver(LabyrinthSolver solver) {
        this.attachedSolver = solver;
        this.attachedSolver.setLabyrinth(this);
    }

    @Override
    public void setExplorerPosition(int row, int column) {
        this.explorerPosition = new Cell(row, column);
    }

    @Override
    public Cell getExplorerPosition() {
        return this.explorerPosition;
    }

    /**
     * Sets a cell from matrix with a specific value
     * @param row
     * @param column
     * @param value
     */
    public void setCell(int row, int column, int value) {
        switch (value) {
            case -1: {
                this.setStartCell(row, column);
            }
            case 2: {
                this.setFinishCell(row, column);
            }
            default: {
                matrix[row][column] = value;
            }
        }
    }

    /**
     * Sets start cell
     * @param row
     * @param column
     */
    public void setStartCell(int row, int column) {
        this.startCell = new Cell(row, column);
        this.matrix[row][column] = -1;
    }

    /**
     * Sets finish cell
     * @param row
     * @param column
     */
    public void setFinishCell(int row, int column) {
        this.finishCell = new Cell(row, column);
        this.matrix[row][column] = 2;
    }

    @Override
    public void solve() {
        this.attachedSolver.solve();
    }

    @Override
    public void addObserver(LabyrinthObserver observer) {
        this.labyrinthObservers.add(observer);
        observer.setAttachedLabyrinth(this);
    }

    @Override
    public void notifyObservers() {
//        System.out.println("Notifying...");
        for (LabyrinthObserver observer : labyrinthObservers) {
            observer.processCell();
            observer.processSolution();
        }
    }
    
    @Override
    public void addPath(int row, int column){
        path.add(new Cell(row,column));
    }
            
    
    @Override
    public List<Cell> getPath(){
        return path;
    }
    

}
