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

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        final FileTreeModel myTreeModel = new FileTreeModel(rootPath);
        setModel(myTreeModel);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new DefaultMutableTreeNode(rootPath));
        root.add(node);

        for (File file : rootPath.listFiles()) {
            if (file.isDirectory()) {
                node.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode(file)));
            }
        }

    }

    @Override
    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;//get node from the value object
        System.err.println(node.getUserObject());
        File nodeObject = (File) node.getUserObject();// get your File object wrapped by this node
        return nodeObject.getName();
    }

}
