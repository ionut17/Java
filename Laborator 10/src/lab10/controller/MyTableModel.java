package lab10.controller;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ionut
 */
public class MyTableModel extends DefaultTableModel {

    MyTableModel(String[][] values, String[] cols) {
        super(values, cols);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return false;
        }
    }

}
