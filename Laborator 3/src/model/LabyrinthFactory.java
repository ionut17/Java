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
            //Generating start/finish cells truly random
            if (rand.nextInt(2) == 0) {
                labyrinth.setStartCell(rand.nextInt(2) * (rowCount - 1), rand.nextInt(columnCount));
            } else {
                labyrinth.setStartCell(rand.nextInt(rowCount), rand.nextInt(2) * (columnCount - 1));
            }
            if (rand.nextInt(2) == 0) {
                labyrinth.setFinishCell(rand.nextInt(2) * (rowCount - 1), rand.nextInt(columnCount));
            } else {
                labyrinth.setFinishCell(rand.nextInt(rowCount), rand.nextInt(2) * (columnCount - 1));
            }
            return labyrinth;
        } else {
            //Vezi implementarea prin liste, fara valori
            LabyrinthListImpl labyrinth = new LabyrinthListImpl();
            labyrinth.setRowCount(rowCount);
            labyrinth.setColumnCount(columnCount);
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    labyrinth.addCell(new Cell(i, j));
                }
            }
            //Generating start/finish cells truly random
            if (rand.nextInt(2) == 0) {
                labyrinth.setStartCell(rand.nextInt(2) * (rowCount - 1), rand.nextInt(columnCount));
            } else {
                labyrinth.setStartCell(rand.nextInt(rowCount), rand.nextInt(2) * (columnCount - 1));
            }
            if (rand.nextInt(2) == 0) {
                labyrinth.setFinishCell(rand.nextInt(2) * (rowCount - 1), rand.nextInt(columnCount));
            } else {
                labyrinth.setFinishCell(rand.nextInt(rowCount), rand.nextInt(2) * (columnCount - 1));
            }
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
        String[] aux = lines[0].split(" ");
        int columnCount = aux.length;
        LabyrinthMatrixImpl labyrinth = new LabyrinthMatrixImpl(rowCount, columnCount);
        int i = 0;
        for (String row : content.split("\n")) {
            int j = 0;
            for (String column : row.split(" ")) {
                if (j < columnCount) {
                    labyrinth.setCell(i, j, Integer.valueOf(column));
                }
                j++;
            }
            i++;
        }
        return labyrinth;
    }
}
