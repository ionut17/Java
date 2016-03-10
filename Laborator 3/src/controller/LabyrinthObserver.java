package controller;

import model.Labyrinth;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public interface LabyrinthObserver {
    
    /**
     * Process the current cell
     */
    void processCell();
    
    /**
     * Process the current solution
     */
    void processSolution();
    
    /**
     * Get attached observer labyrinth
     * @return Labyrinth
     */
    public Labyrinth getAttachedLabyrinth();
    
    /**
     * Set attached observer labyrinth
     * @param attachedLabyrinth
     */
    public void setAttachedLabyrinth(Labyrinth attachedLabyrinth);
    
}
