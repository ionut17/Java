package model;

import controller.LabyrinthObserver;
import controller.LabyrinthSolver;
import java.util.*;
import view.LabyrinthView;

/**
 *
 * @author Ionut
 */
public class LabyrinthListImpl implements Labyrinth {

    private List<Cell> list;
    private int rowCount;
    private int columnCount;
    private Cell startCell;
    private Cell finishCell;
    private LabyrinthView attachedViewer;
    private LabyrinthSolver attachedSolver;

    public LabyrinthListImpl() {
        this.list = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public boolean isFreeAt(int row, int column) {
        for (Cell cell : getList()) {
            if (cell.getRow() == row && cell.getColumn() == column) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isWallAt(int row, int column) {
        for (Cell cell : getList()) {
            if (cell.getRow() == row && cell.getColumn() == column) {
                return true;
            }
        }
        return false;
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
        attachedSolver.setLabyrinth(this);
    }
    
    @Override
    public void setExplorerPosition(int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cell getExplorerPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the list
     */
    public List<Cell> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Cell> list) {
        this.list = list;
    }

    /**
     * Adding occupied cell to list
     *
     * @param cell
     */
    public void addCell(Cell cell) {
        list.add(cell);
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @param columnCount the columnCount to set
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
    
    /**
     * @param row
     * @param column
     */
    public void setStartCell(int row, int column) {
        this.startCell = new Cell(row,column);
    }

    /**
     * @param row
     * @param column
     */
    public void setFinishCell(int row, int column) {
        this.finishCell = new Cell(row,column);
    }

    @Override
    public void solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver(LabyrinthObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
