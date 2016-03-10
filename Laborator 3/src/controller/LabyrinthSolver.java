package controller;

import model.*;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public interface LabyrinthSolver {
    
    /**
     * @return the labyrinth
     */
    public Labyrinth getLabyrinth();

    /**
     * @param labyrinth
     */
    public void setLabyrinth(Labyrinth labyrinth);
    
    /**
     * Return the next cell that has to be explored
     * @param row
     * @param column
     */
    public void nextCellToExplore(int row, int column);
    
    /**
     * Solver method which solves the labyrinth
     */
    public void solve();
    
}
