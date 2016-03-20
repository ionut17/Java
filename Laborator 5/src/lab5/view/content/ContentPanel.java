/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.view.content;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

/**
 *
 * @author Ionut
 */
public class ContentPanel extends JPanel{
    
    public ContentPanel(int width){
        JPanel treePanel = new TreePanel();
        JPanel detailPanel = new DetailPanel();
        
        JSplitPane contentPanel = new JSplitPane(HORIZONTAL_SPLIT,treePanel,detailPanel);
        contentPanel.setDividerLocation(width/4);
    }
    
}
