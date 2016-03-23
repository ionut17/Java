package lab5.controller.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lab5.model.Song;
import org.apache.tika.metadata.Metadata;

/**
 *
 * @author Ionut
 */
class TablePanel extends JTable {
    
    DetailsPanel parent;
    JTable myTable = new JTable(5,3);
    
    FilenameFilter musicFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".mp3") || lowercaseName.endsWith(".wav") || lowercaseName.endsWith(".flac");
        }
    };

    public TablePanel(DetailsPanel source) {
        parent = source;
        setLayout(new BorderLayout());
        add(myTable.getTableHeader(), BorderLayout.PAGE_START);
        add(myTable, BorderLayout.CENTER);
    }
    
//    public void updateTable() {
//        String[] musicList = parent.currentLocation.list(musicFilter);
//        DefaultTableModel model = new DefaultTableModel(musicList, {"Filename"});
//        myTable.setModel(model);
//    }

}
