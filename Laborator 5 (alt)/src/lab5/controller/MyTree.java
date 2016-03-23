package lab5.controller;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import javax.swing.JMenuItem;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
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
import lab5.model.Song;

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
                String pathString = splitedStrings[splitedStrings.length - 1];
                Path path = Paths.get(pathString.substring(0, pathString.length() - 1));
                target.setLocation(path.toFile());
            }
        });

        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem(new AbstractAction("Open") {
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.getDesktop();
                Path path = target.currentLocation.toPath();
                if (path.toFile().exists()) {
                    try {
                        desktop.open(path.toFile());
                    } catch (IOException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );
        JMenuItem menuItem2 = new JMenuItem(new AbstractAction("Add to favorites") {
            public void actionPerformed(ActionEvent e) {
                if (target.currentLocation.toString().matches("(.*)\\.(mp3|flac|wav)")) {
                Song addedSong = new Song(target.currentLocation.toString());

                List<Song> songSer = new ArrayList<>();

                File f = new File(System.getProperty("user.dir") + "\\favorites.ser");
                if (f.exists() == true && f.length() > 0) {

                    try {
                        FileInputStream fileIn;
                        fileIn = new FileInputStream("favorites.ser");
                        ObjectInputStream in;
                        in = new ObjectInputStream(fileIn);
                        songSer = (List<Song>) in.readObject();
                        in.close();
                        fileIn.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                if (addedSong.isValid(target.currentLocation.toPath()) == true) {
                    addedSong.setSongName(Paths.get(addedSong.getSongPath()).getFileName().toString());
                    songSer.add(addedSong);
                    try {
                        FileOutputStream fileOut;
                        fileOut = new FileOutputStream("favorites.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(songSer);
                        out.close();
                        fileOut.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }}
        });
        menu.add(menuItem1);
        menu.add(menuItem2);
        this.setComponentPopupMenu(menu);
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
