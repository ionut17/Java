package lab5.controller;

import java.io.File;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Ionut
 */
public class FileTreeModel implements TreeModel{

    File root;
    
    public FileTreeModel(File root){
        this.root=root;
    }
    
    @Override
    public Object getRoot() {
        return this.root;
    }

    @Override
    public Object getChild(Object parent, int i) {
        String[] childs = ((File)parent).list();
        if (childs==null || i>=childs.length){
            return null;
        }
        return new File((File)parent,childs[i]);
    }

    @Override
    public int getChildCount(Object o) {
        String[] childs = ((File)o).list();
        if (childs==null) return 0;
        return childs.length;
    }

    @Override
    public boolean isLeaf(Object o) {
        return ((File)o).isFile() && ((File)o).toString().matches(".*\\.[mp3|wav|flac]");
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        String[] childs = ( (File)parent ).list();
        if (childs==null) return -1;
        for (int index=0;index<childs.length;index++){
            if (childs[index].equals(((File)child).getName())){
                return  index;
            }
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
    }
    
}