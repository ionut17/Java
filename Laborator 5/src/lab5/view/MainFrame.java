package lab5.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import lab5.view.content.ContentPanel;
import lab5.view.content.DetailPanel;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class MainFrame extends JFrame {

    public MainFrame(int width, int height) {
        super("phiMusic Manager - 0.0.1");

        //Set size
        setSize(width, height);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.yellow);

        //Make button panel
        ButtonPanel buttonPanel = new ButtonPanel();

        //Adding the items
        add(new ContentPanel(width));
        add(buttonPanel);

        //Setting layout
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        setVisible(true);
    }

}
