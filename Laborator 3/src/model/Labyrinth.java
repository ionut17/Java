package model;

import controller.LabyrinthSolver;
import java.util.Observable;
import view.LabyrinthView;

/**
 *
 * @author Ionut
 */
public interface Labyrinth{

    int getRowCount();

    int getColumnCount();

    boolean isFreeAt(int row, int column);

    boolean isWallAt(int row, int column);

    Cell getStartCell();

    Cell getFinishCell();

    boolean isStartCell(int row, int column);

    boolean isFinishCell(int row, int column);
    
    LabyrinthView getView();

    public void setView(LabyrinthView view);

    public void setSolver(LabyrinthSolver solver);

}
