package model;

import controller.LabyrinthObserver;
import controller.LabyrinthSolver;
import view.LabyrinthView;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public interface Labyrinth {

    /**
     * Returns labyrinth number of rows
     * @return number of rows
     */
    int getRowCount();

    /**
     * Returns labyrinth number of columns
     * @return number of columns
     */
    int getColumnCount();

    /**
     * Returns if the labyrinth is free at position
     * @param row
     * @param column
     * @return boolean
     */
    boolean isFreeAt(int row, int column);

    /**
     * Returns if the labyrinth has wall at position
     * @param row
     * @param column
     * @return boolean
     */
    boolean isWallAt(int row, int column);

    /**
     * Returns a pair with starting coordinates
     * @return Cell
     */
    Cell getStartCell();

    /**
     * Returns a pair with finishing coordinates
     * @return Cell
     */
    Cell getFinishCell();

    /**
     * Returns if the position is a start cell
     * @param row
     * @param column
     * @return boolean
     */
    boolean isStartCell(int row, int column);

    /**
     * Returns if the position is a finish cell
     * @param row
     * @param column
     * @return boolean
     */
    boolean isFinishCell(int row, int column);
    
    //Labyrinth person position

    /**
     * Sets the explorer current position
     * @param row
     * @param column
     */
    void setExplorerPosition(int row, int column);
    
    /**
     * Gets the explorer current position
     * @return Cell
     */
    Cell getExplorerPosition();
    
    /**
     * Get the labyrinth attached viewer
     * @return LabyrinthView
     */
    LabyrinthView getView();

    /**
     * Set the labyrinth attached viewer
     * @param view
     */
    public void setView(LabyrinthView view);
    
    /**
     * Set the labyrinth attached solver
     * @return LabyrinthSolver
     */
    LabyrinthSolver getSolver();

    /**
     * Set the labyrinth attached solver
     * @param solver
     */
    public void setSolver(LabyrinthSolver solver);
    
    /**
     * Adds observers to the labyrinth
     * @param observer
     */
    public void addObserver(LabyrinthObserver observer);
    
    /**
     * Notify observers function
     */
    public void notifyObservers(); 

    /**
     * Solve method which calls the attached solver
     */
    public void solve();
    
    
}
