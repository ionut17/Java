package model;

import java.util.Random;

/**
 *
 * @author Ionut
 */
public class LabyrinthFactory {

    public Labyrinth createRandom(int rowCount, int columnCount) {
        Random rand = new Random();
        if (rowCount < 100 && columnCount < 100) {
            LabyrinthMatrixImpl labyrinth = new LabyrinthMatrixImpl(rowCount, columnCount);
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    labyrinth.setCell(i, j, rand.nextInt(2));
                }
            }
            labyrinth.setStartCell(new Cell(rand.nextInt(rowCount),rand.nextInt(columnCount),-1));
            labyrinth.setFinishCell(new Cell(rand.nextInt(rowCount),rand.nextInt(columnCount),2));
            return labyrinth;
        }
        else {
            LabyrinthListImpl labyrinth = new LabyrinthListImpl();
            labyrinth.setRowCount(rowCount);
            labyrinth.setColumnCount(columnCount);
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    labyrinth.addCell(new Cell(i,j,rand.nextInt(2)));
                }
            }
            labyrinth.setStartCell(new Cell(rand.nextInt(rowCount),rand.nextInt(columnCount),-1));
            labyrinth.setFinishCell(new Cell(rand.nextInt(rowCount),rand.nextInt(columnCount),2));
            return labyrinth;
        }
    }

}
