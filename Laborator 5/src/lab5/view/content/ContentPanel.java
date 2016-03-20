package lab5.view.content;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

/**
 *
 * @author Ionut
 */
public class ContentPanel extends JPanel{
    
    public ContentPanel(int width){
        //Make content panel
        //TreePane
        JScrollPane treePanel = new JScrollPane();
        JPanel p = new JPanel();
        p.add(new JButton("Chestie"));
        treePanel.getViewport().add(p);

        //DetailPanel
        DetailPanel detailPanel = new DetailPanel();
        
        //Make the content panel
        JSplitPane splitPanel = new JSplitPane(HORIZONTAL_SPLIT, treePanel, detailPanel);
        splitPanel.setDividerLocation(width/3);
        add(splitPanel);
        setLayout(new GridLayout());
    }
    
}
