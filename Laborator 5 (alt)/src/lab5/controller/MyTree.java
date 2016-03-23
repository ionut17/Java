package lab5.controller;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;
import javax.swing.JMenuItem;
import java.nio.file.Paths;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Ionut
 */
public class MyTree extends JTree {

    DetailsPanel target;

    public MyTree(DetailsPanel details) {
        target = details;

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

        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
//         MouseListener popupListener = new PopupListener();
//         this.addMouseListener(popupListener);
       
//        Desktop desktop = Desktop.getDesktop();
//        Path path = this.parseArgs();
//        if (path.toFile().exists()) {
//            if (path.toString().matches("(.*)\\.(mp3|flac|wav)")) {
//                desktop.open(path.toFile());
//                System.out.println("Playing: " + path.toFile().getName());
//            }
//        this.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                if (SwingUtilities.isRightMouseButton(e)) {
//                    TreePath path = this.getPathForLocation(e.getX(), e.getY());
//                    Rectangle pathBounds = this.getUI().getPathBounds(this, path);
//                    if (pathBounds != null && pathBounds.contains(e.getX(), e.getY())) {
//                        JPopupMenu menu = new JPopupMenu();
//                        menu.add(new JMenuItem("Test"));
//                        menu.show(this, pathBounds.x, pathBounds.y + pathBounds.height);
//                    }
//                }
//            }
//        });
    }

    @Override
    public String convertValueToText(Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus
    ) {
        if (value instanceof File) {
            if (!"".equals(((File) value).getName())) {
                return ((File) value).getName();
            } else {
                return ((File) value).getAbsolutePath();
            }
        } else {
            return "My Computer";
        }
    }

}
