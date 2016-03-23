package lab5.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Ionut
 */
public class MyTree extends JTree {
    
    DetailsPanel target;

    public MyTree(DetailsPanel details) {
        target = details;
        File rootPath = new File("C:\\");
        
        DefaultMutableTreeNode rooter = new DefaultMutableTreeNode("FileSystem");
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            rooter.add(new DefaultMutableTreeNode(roots[i]));
        }

        setModel(new FileTreeModel(rooter));
        this.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
//                System.out.println(e.getPath().toString());
                String[] splitedStrings = e.getPath().toString().split(", ");
                String pathString = splitedStrings[splitedStrings.length-1];
                Path path = Paths.get(pathString.substring(0,pathString.length()-1));
                target.setLocation(path.toFile());
            }
        });
    }

    @Override
    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (value instanceof File) {
            if ( !"".equals(((File)value).getName()) ){
                return ((File) value).getName();
            }
            else {
                return ((File) value).getAbsolutePath();
            }
        } else {
            return "My Computer";
        }
    }

}
