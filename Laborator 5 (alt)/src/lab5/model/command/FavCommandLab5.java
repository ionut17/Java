package lab5.model.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab5.controller.MyTree;
import lab5.model.Song;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.SAXException;

/**
 *
 * @author Anca
 */
public class FavCommandLab5 extends AbstractCommandLab5{

    @Override
    public void execute() throws Exception {
        System.out.println(getDp().currentLocation.toString());
        if (getDp().currentLocation.toString().matches("(.*)\\.(mp3|flac|wav)")) {
                    Song addedSong = new Song(getDp().currentLocation.toString());

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

                    if (addedSong.isValid(getDp().currentLocation.toPath()) == true) {
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
        }
    
}}
