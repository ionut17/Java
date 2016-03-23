package lab5.controller.content;

import java.awt.BorderLayout;
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
    JTable myTable = new JTable();

    FilenameFilter musicFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".mp3") || lowercaseName.endsWith(".wav") || lowercaseName.endsWith(".flac");
        }
    };

    public TablePanel(DetailsPanel source) {
        parent = source;
        setLayout(new BorderLayout());
        myTable.setEnabled(false);
        add(myTable.getTableHeader(), BorderLayout.PAGE_START);
        add(myTable, BorderLayout.CENTER);
    }

    public void updateTable() {
        String[] nameList = parent.currentLocation.list(musicFilter);
        String[][] musicList = new String[nameList.length][4];
        //Adding filenames
        for (int i = 0; i < nameList.length; i++) {
            musicList[i][0] = nameList[i];
            Song mySong = new Song(new File(parent.currentLocation, nameList[i]).toString());
            //Adding metadata
            DefaultListModel listModel = new DefaultListModel();
            try {
                Metadata metadata = mySong.getMetadata();
                musicList[i][2] = metadata.get("xmpDM:artist");
                musicList[i][1] = metadata.get("title");
                musicList[i][3] = metadata.get("xmpDM:genre");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //Column names
        String[] colNames = {"Filename", "Artist", "Title", "Genre"};
        DefaultTableModel model = new DefaultTableModel(musicList, colNames);
        myTable.setModel(model);
    }

}
