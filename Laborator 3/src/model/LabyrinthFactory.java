package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            labyrinth.setStartCell(new Cell(rand.nextInt(rowCount), rand.nextInt(columnCount), -1));
            labyrinth.setFinishCell(new Cell(rand.nextInt(rowCount), rand.nextInt(columnCount), 2));
            return labyrinth;
        } else {
            LabyrinthListImpl labyrinth = new LabyrinthListImpl();
            labyrinth.setRowCount(rowCount);
            labyrinth.setColumnCount(columnCount);
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    labyrinth.addCell(new Cell(i, j, rand.nextInt(2)));
                }
            }
            labyrinth.setStartCell(new Cell(rand.nextInt(rowCount), rand.nextInt(columnCount), -1));
            labyrinth.setFinishCell(new Cell(rand.nextInt(rowCount), rand.nextInt(columnCount), 2));
            return labyrinth;
        }
    }

    public Labyrinth readFromFile(String fileName) {
        String content = null;
        File file = new File(fileName);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parse(content);
    }

    private Labyrinth parse(String content) {
        String[] lines = content.split("\n");
        int rowCount = lines.length;
        String[] aux = lines[0].split("\\|");
        int columnCount = aux.length;
        LabyrinthMatrixImpl labyrinth = new LabyrinthMatrixImpl(rowCount, columnCount - 1);
        int i = 0;
        for (String row : content.split("\n")) {
            int j = 0;
            for (String column : row.split("\\|")) {
                if (j > 0 && j < columnCount) {
                    labyrinth.setCell(i, j - 1, Integer.valueOf(column));
                }
                j++;
            }
            i++;
        }
        return labyrinth;
    }
}
