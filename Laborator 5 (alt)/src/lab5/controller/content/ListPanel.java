package lab5.controller.content;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import lab5.model.Song;
import org.apache.tika.metadata.Metadata;

/**
 *
 * @author Ionut
 */
class ListPanel extends JPanel {

    DetailsPanel parent;
    JList myList = new JList();

    public ListPanel(DetailsPanel source) {
        parent = source;
        setLayout(new BorderLayout());
        add(myList,BorderLayout.CENTER);
        setBackground(Color.yellow);
    }

    public void updateList() {
        Song mySong = new Song(parent.currentLocation.toString());
        DefaultListModel listModel = new DefaultListModel();
        try {
            Metadata mtd = mySong.getMetadata();
            for (String name : mtd.names()) {
                listModel.addElement(name + ": " + mtd.get(name));
            }
            myList.setModel(listModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
