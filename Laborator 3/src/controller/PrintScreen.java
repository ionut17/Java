/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.AWTException;
import java.awt.Robot;
import model.Labyrinth;

/**
 *
 * @author Ionut
 */
public class PrintScreen implements LabyrinthObserver {

    private Labyrinth attachedLabyrinth;

    @Override
    public void processCell() {
        System.out.format("\n");
        System.out.println(attachedLabyrinth.getView().toString());
    }

    @Override
    public void processSolution() {
    }

    /**
     * @return the attachedLabyrinth
     */
    public Labyrinth getAttachedLabyrinth() {
        return attachedLabyrinth;
    }

    /**
     * @param attachedLabyrinth the attachedLabyrinth to set
     */
    public void setAttachedLabyrinth(Labyrinth attachedLabyrinth) {
        this.attachedLabyrinth = attachedLabyrinth;
    }

}
