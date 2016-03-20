/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.view.content;

import java.awt.GridLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTree;
import lab5.controller.FolderObserver;
import lab5.controller.MusicObserver;

/**
 *
 * @author Ionut
 */
public class FileTree extends JPanel {
    
    public FileTree(ContentPanel target) {
        JTree tree = new JTree();
        
        // Create a TreeModel object to represent our tree of files
        MyTreeModel model = new MyTreeModel(new File("F:\\"));
        MyTreeSelectionListener listener = new MyTreeSelectionListener(tree,target);

        // Create a JTree and tell it to display our model
        tree.setModel(model);
        tree.addTreeSelectionListener(listener);

        add(tree);
        setLayout(new GridLayout());
    }


}
