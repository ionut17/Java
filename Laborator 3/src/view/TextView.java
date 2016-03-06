package view;

import model.Cell;
import model.Labyrinth;

/**
 *
 * @author Ionut
 */
public class TextView implements LabyrinthView {

    private Labyrinth labyrinth;

    /**
     * @return the labyrinth
     */
    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    /**
     * @param labyrinth
     */
    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public void printLabyrinth() {
        System.out.println(labyrinth.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Cell explorer = labyrinth.getExplorerPosition();
        for (int i = 0; i < labyrinth.getRowCount(); i++) {
            sb.append("|");
            for (int j = 0; j < labyrinth.getColumnCount(); j++) {
                if (explorer.getRow() == i && explorer.getColumn() == j) {
                    sb.append("E");
                } else {
                    if (labyrinth.isStartCell(i, j)) {
                        sb.append("S");
                    }
                    if (labyrinth.isFinishCell(i, j)) {
                        sb.append("F");
                    }
                    if (labyrinth.isFreeAt(i, j)) {
                        sb.append(" ");
                    }
                    if (labyrinth.isWallAt(i, j)) {
                        sb.append("*");
                    }
                }
                sb.append("|");
            }
            sb.append("\n");

        }
        return sb.toString();
    }

}
