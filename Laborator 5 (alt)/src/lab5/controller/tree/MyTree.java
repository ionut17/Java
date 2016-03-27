package lab5.controller;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Path;
import javax.swing.JMenuItem;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lab5.controller.content.DetailsPanel;
import lab5.model.Song;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.SAXException;

/**
 *
 * @author Ionut
 */
public class MyTree extends JTree {

    public DetailsPanel target;

    public MyTree(){};
    
    public MyTree(DetailsPanel details) throws FileNotFoundException, IOException, ClassNotFoundException {
        target = details;

        DefaultMutableTreeNode rooter = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode favorites = new DefaultMutableTreeNode("Favorites");
        DefaultMutableTreeNode computer = new DefaultMutableTreeNode("My Computer");
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            computer.add(new DefaultMutableTreeNode(roots[i]));
        }
        List<Song> songSer = new ArrayList<>();

        File f = new File(System.getProperty("user.dir") + "\\favorites.ser");
        if (f.exists() == true && f.length() > 0) {
            FileInputStream fileIn = new FileInputStream("favorites.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            songSer = (List<Song>) in.readObject();
            in.close();
            fileIn.close();
        }
        for (Song s : songSer) {
            Path aux = Paths.get(s.getSongPath());
            favorites.add(new DefaultMutableTreeNode(aux.toFile()));
        }
        rooter.add(favorites);
        rooter.add(computer);

        FileTreeModel myModel = new FileTreeModel(rooter);
        setModel(myModel);
        setRootVisible(false);
        Icon leafIcon = new ImageIcon("src/music_icon.gif");
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) this.getCellRenderer();
        renderer.setLeafIcon(leafIcon);

        //Selection listener
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
            @Override
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
        JMenuItem menuItem2 = new JMenuItem(new AddToFavorites(myModel,favorites));

        JMenuItem menuItem3 = new JMenuItem(new AbstractAction("Search Google") {
            public void actionPerformed(ActionEvent e) {
                FunnyCrawler obj = new FunnyCrawler();
                String[] name = target.currentLocation.toString().split("\\\\");
                String[] split = name[name.length - 1].split(" ");
                String toSearch = new String();
                int count = 0;
                for (String s : split) {
                    if (count < split.length - 1) {
                        toSearch = toSearch + s + '+';
                    }
                    count++;
                }
                toSearch = toSearch + split[split.length - 1];
                Set<String> result = obj.getDataFromGoogle(toSearch);
                int openWindows = 0;
                for (String temp : result) {
                    openWindows++;
                    if (openWindows < 7) {
                        try {
                            Desktop.getDesktop().browse(new URI(temp));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                System.out.println(result.size());
            }
        }
        );
        JMenuItem menuItem4;
        menuItem4 = new JMenuItem(new AbstractAction("Get XML") {
            public void actionPerformed(ActionEvent e) {
                try {
                    Favorites favorites = new Favorites();

                    File f = new File(System.getProperty("user.dir") + "\\favorites.ser");
                    if (f.exists() == true && f.length() > 0) {

                        try {
                            FileInputStream fileIn;
                            fileIn = new FileInputStream("favorites.ser");
                            ObjectInputStream in;
                            in = new ObjectInputStream(fileIn);
                            favorites.setSongs((List<Song>) in.readObject());
                            in.close();
                            fileIn.close();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        File file = new File("D:\\Dropbox\\FavoriteSongs.xml");
                        JAXBContext jaxbContext = JAXBContext.newInstance(Favorites.class);
                        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                        // output pretty printed
                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        jaxbMarshaller.marshal(favorites, file);
                        jaxbMarshaller.marshal(favorites, System.out);
                        Desktop desktop = Desktop.getDesktop();

                        Path path = file.toPath();
                        if (path.toFile().exists()) {
                            try {
                                desktop.open(path.toFile());
                            } catch (IOException ex) {
                                Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        );
        //Spotify integration :D
        Api api = Api.DEFAULT_API;
        JMenuItem menuItem5 = new JMenuItem(new AbstractAction("Open with Spotify") {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("Spotify api fired..");
                if (target.currentLocation.isFile()) {
                    String songNameSource = target.currentLocation.getName();
                    String songName = songNameSource.substring(0, songNameSource.lastIndexOf('.'));
                    songName = songName.replaceAll("-", " ");
//                    System.out.println(songName);

                    final TrackSearchRequest request = api.searchTracks(songName).market("US").build();

                    try {
                        final Page<Track> trackSearchResult = request.get();
                        String link = trackSearchResult.getItems().get(0).getUri();
                        Desktop.getDesktop().browse(new URI(link));
//                        System.out.println("I got " + trackSearchResult.getTotal() + " results!");
                    } catch (Exception ex) {
                        System.out.println("Something went wrong!" + ex.getMessage());
                    }
                }
            }
        }
        );

        //Adding items to pop-up menu
        menu.add(menuItem1);
        menu.add(menuItem5);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
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
            return value.toString();
        }
    }
    
    class AddToFavorites extends AbstractAction{
        
        FileTreeModel myModel;
        DefaultMutableTreeNode favorites;
        
        public AddToFavorites(FileTreeModel sourceModel, DefaultMutableTreeNode sourceFavorites){
            super("Add to favorites");
            myModel = sourceModel;
            favorites = sourceFavorites;
        }

        @Override
            public void actionPerformed(ActionEvent e) {
                if (target.currentLocation.toString().matches("(.*)\\.(mp3|flac|wav)")) {
                    Song addedSong = new Song(target.currentLocation.toString());

                    Path aux = Paths.get(addedSong.getSongPath());
                    DefaultMutableTreeNode newSong = new DefaultMutableTreeNode(aux.toFile());
                    favorites.add(newSong);
//                    myModel.nodeStructureChanged(favorites);
                    
//                myModel.nodeStructureChanged((TreeNode) favorites);

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
                        try {
                            Metadata metadata;
                            metadata = addedSong.getMetadata();
                            // List all metadata
                            String[] metadataNames = metadata.names();
                            for (String name : metadataNames) {
//                                System.out.println(name + ": " + metadata.get(name));
                                switch (name) {
                                    case "creator":
                                        addedSong.setArtist(metadata.get(name));
                                        break;
                                    case "xmpDM:album":
                                        addedSong.setAlbum(metadata.get(name));
                                        break;
                                    case "xmpDM:releaseDate":
                                        addedSong.setYear(metadata.get(name));
                                        break;
                                    case "dc:title":
                                        addedSong.setName(metadata.get(name));
                                        break;
                                    case "xmpDM:genre":
                                        addedSong.setGenre(metadata.get(name));
                                        break;
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (TikaException ex) {
                            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
//                    DefaultTreeModel model = (DefaultTreeModel) getModel();
//                    DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//                    root.add(new DefaultMutableTreeNode("another_child"));
//                    model.reload(root);
                }
            }
        
    }

}
