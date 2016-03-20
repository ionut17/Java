package lab5.view.content;

import java.io.File;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import lab5.controller.FolderObserver;
import lab5.controller.MusicObserver;

/**
 *
 * @author Ionut
 */
class MyTreeSelectionListener implements TreeSelectionListener{
    
    JTree tree;
    ContentPanel panel;
    
    MyTreeSelectionListener(JTree target, ContentPanel targetPanel){
        tree = target;
        panel = targetPanel;
    }
    
    

    public void valueChanged(TreeSelectionEvent e) {
        File node = (File) tree.getLastSelectedPathComponent();

        /* if nothing is selected */
        if (node == null) {
            return;
        }
        
        panel.getObserver().updateFolder(node.getAbsolutePath());
    }

}
