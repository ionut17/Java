package lab5.view;

import java.awt.Color;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JFrame;
import lab5.view.content.ContentPanel;

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

        //Adding the items
        add(new ContentPanel(width));
        add(new ToolsPanel());

        //Setting layout
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        setVisible(true);
    }
}
