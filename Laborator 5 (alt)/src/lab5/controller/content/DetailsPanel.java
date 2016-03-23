package lab5.controller.content;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Ionut
 */
public class DetailsPanel extends JPanel {

    public File currentLocation;

    final static String TABLEPANEL = "Card with JTable";
    final static String LISTPANEL = "Card with JList";
    final static String EMPTYPANEL = "Card with nothing";

    JTable tablePanel = new TablePanel(this);
    ListPanel listPanel = new ListPanel(this);

    public DetailsPanel() {
        setLayout(new CardLayout());
        add(tablePanel, TABLEPANEL);
        add(listPanel, LISTPANEL);
        add(new JLabel("No selection made"), LISTPANEL);
    }

    public void setLocation(File target) {
        currentLocation = target;
        update();
    }

    private void update() {
        CardLayout cl = (CardLayout) (this.getLayout());
        if (currentLocation.isDirectory()) {
            cl.show(this, TABLEPANEL);
        } else if (currentLocation.isFile()) {
            listPanel.updateList();
            cl.show(this, LISTPANEL);
        } else {
            cl.show(this, EMPTYPANEL);
        }
    }

}
