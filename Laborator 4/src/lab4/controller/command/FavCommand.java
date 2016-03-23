/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4.controller.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lab4.model.Song;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import lab4.view.exception.InvalidCommandException;

public class FavCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, FileNotFoundException, IOException, SAXException, TikaException, ClassNotFoundException {
        Song addedSong = new Song();
        addedSong.setSongPath(parseArgs().toString());

        List<Song> songSer = new ArrayList<>();

        File f = new File(System.getProperty("user.dir") + "\\favorites.ser");
        if (f.exists() == true && f.length()>0) {
            FileInputStream fileIn = new FileInputStream("favorites.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            songSer = (List<Song>) in.readObject();
            in.close();
            fileIn.close();
        }

        if (addedSong.isValid(this.parseArgs()) == true) {
            addedSong.setSongName(Paths.get(addedSong.getSongPath()).getFileName().toString());
            songSer.add(addedSong);
            FileOutputStream fileOut = new FileOutputStream("favorites.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(songSer);
            out.close();
            fileOut.close();
        } else {
            throw new InvalidCommandException("Invalid song path..");
        }
    }

}
