package controller;

import model.*;

/**
 *
 * @author Ionut
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
     * @return a cell with cell row and column
     */
    public Cell nextCellToExplore();
    
}
