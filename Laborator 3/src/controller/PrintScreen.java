package controller;

import model.Labyrinth;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public class PrintScreen implements LabyrinthObserver {

    private Labyrinth attachedLabyrinth;

    @Override
    public void processCell() {
        System.out.println();
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
