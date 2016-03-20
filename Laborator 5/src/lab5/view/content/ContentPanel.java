package lab5.view.content;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import lab5.controller.FolderObserver;
import lab5.controller.MusicObserver;

/**
 *
 * @author Ionut
 */
public class ContentPanel extends JPanel{
    
    private MusicObserver obs = new FolderObserver();
    
    public ContentPanel(int width){
        //Make content panel
        //TreePane
        JScrollPane treePanel = new JScrollPane();
        treePanel.getViewport().add(new FileTree(this));

        //DetailPanel
        DetailPanel detailPanel = new DetailPanel();
        obs.setAttachedDetailPanel(detailPanel);
        
        //Make the content panel
        JSplitPane splitPanel = new JSplitPane(HORIZONTAL_SPLIT, treePanel, detailPanel);
        splitPanel.setDividerLocation(width/3);
        add(splitPanel);
        setLayout(new GridLayout());
    }
    
    /**
     * @return the observer
     */
    public MusicObserver getObserver() {
        return obs;
    }
    
}
