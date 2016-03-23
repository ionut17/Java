package lab5.controller;

import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ionut
 */
public class MyTree extends JTree {

    public MyTree() {
        File rootPath = new File("C:\\");
        
//        DefaultMutableTreeNode rooter = new DefaultMutableTreeNode("FileSystem");
//        DefaultMutableTreeNode parent;
//        File [] roots = File.listRoots();
//        parent = new DefaultMutableTreeNode(roots);
//        rooter.add(parent);
//        

        setModel(new FileTreeModel(rootPath));

//        File[] roots = File.listRoots();
//        System.out.println("Root directories in your system are:");
//        for (int i = 0; i < roots.length; i++) {
//            System.out.println(roots[i].toString());
//        }

//        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
//        final FileTreeModel myTreeModel = new FileTreeModel(rootPath);
//        setModel(myTreeModel);

//        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new DefaultMutableTreeNode(rootPath));
//        root.add(node);

//        for (File file : rootPath.listFiles()) {
//            if (file.isDirectory()) {
//                node.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode(file)));
//            }
//        }

    }

    @Override
    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (value instanceof File) {
            return ((File) value).getName();
        } else {
            return "root";
        }
    }

}
