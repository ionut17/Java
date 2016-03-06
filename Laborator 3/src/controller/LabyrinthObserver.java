/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Labyrinth;

/**
 *
 * @author Ionut
 */
public interface LabyrinthObserver {
    
    void processCell();
    
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
