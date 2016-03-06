package model;

/**
 *
 * @author Ionut
 */
public class Cell {

    private int row;
    private int column;

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) obj;
        return (cell.row == row && cell.column == column);
    }

}
