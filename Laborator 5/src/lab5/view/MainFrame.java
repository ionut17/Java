package lab5.view;


import java.awt.Color;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import lab5.view.content.DetailPanel;
import lab5.view.content.TreePanel;

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

        //Make content panel
        JPanel treePanel = new TreePanel();
        JPanel detailPanel = new DetailPanel();
        getContentPane().setBackground(Color.yellow);
        
//        JSplitPane contentPanel = new JSplitPane(HORIZONTAL_SPLIT,treePanel,detailPanel);
//        contentPanel.setDividerLocation(width/3);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color (0,0,255));
        
        //Make button panel
        JPanel buttonPanel = new ButtonPanel();
        buttonPanel.setBackground(Color.red);
        
        add(contentPanel);
        add(buttonPanel);
        
        //Setting layout
        setLayout(new BoxLayout(getContentPane(),Y_AXIS));
        setVisible(true);
    }

}
