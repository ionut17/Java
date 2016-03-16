/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import model.Song;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import view.exception.InvalidCommandException;

public class FavCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, FileNotFoundException, IOException, SAXException, TikaException{
        Song addedSong = new Song();
        addedSong.setSongPath(parseArgs());
        if (addedSong.isValid(this.parseArgs()) == true) {
            try {
                FileOutputStream fileOut = new FileOutputStream("/favorites.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                addedSong.setSongName(addedSong.getSongPath().getFileName().toString());
                out.writeObject(addedSong);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in favorites.ser");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
        else throw new InvalidCommandException("Invalid song path..");
    }

}
