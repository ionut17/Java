package view;

import model.Labyrinth;

/**
 *
 * @author Adascalitei Anca, Iacob Ionut
 */
public interface LabyrinthView {

    /**
     * @return the labyrinth
     */
    public Labyrinth getLabyrinth();

    /**
     * @param labyrinth
     */
    public void setLabyrinth(Labyrinth labyrinth);

    @Override
    public String toString();
    
}
