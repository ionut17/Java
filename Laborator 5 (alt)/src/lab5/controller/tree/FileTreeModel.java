package lab5.controller;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Ionut
 */
public class FileTreeModel implements TreeModel {

    DefaultMutableTreeNode root;

    FilenameFilter musicFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".mp3") || lowercaseName.endsWith(".wav") || lowercaseName.endsWith(".flac") || new File(dir,name).isDirectory();
        }
    };

    public FileTreeModel(DefaultMutableTreeNode root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return this.root;
    }

    @Override
    public Object getChild(Object parent, int i) {
        if (parent instanceof File) {
            String[] childs = ((File) parent).list(musicFilter);
            if (childs == null || i >= childs.length) {
                return null;
            }
            return new File((File) parent, childs[i]);
        } else if (parent==root){
            return ((DefaultMutableTreeNode) parent).getChildAt(i);
        }
        else {
            return new File(((DefaultMutableTreeNode) parent).getChildAt(i).toString());
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof File) {
            String[] childs = ((File) parent).list(musicFilter);
            if (childs == null) {
                return 0;
            }
            return childs.length;
        } else {
            return ((DefaultMutableTreeNode) parent).getChildCount();
        }
    }

    @Override
    public boolean isLeaf(Object node) {
//        return ((File) o).isFile() && ((File) o).toString().matches(".*\\.[mp3|wav|flac]");
        if (node instanceof File) {
            return ((File) node).isFile();
        }
        else {
            return false;
        }
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof File) {
            String[] childs = ((File) parent).list(musicFilter);
            if (childs == null) {
                return -1;
            }
            for (int index = 0; index < childs.length; index++) {
                if (childs[index].equals(((File) child).getName())) {
                    return index;
                }
            }
            return -1;
        }
        else {
            return ((DefaultMutableTreeNode) parent).getIndex((TreeNode) child);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
    }

}
