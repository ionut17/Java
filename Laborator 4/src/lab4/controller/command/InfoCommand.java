package controller.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.Song;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.SAXException;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class InfoCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, FileNotFoundException, IOException, SAXException, TikaException {
        Song mySong = new Song(parseArgs().toString());
        Metadata metadata = mySong.getMetadata();
        System.out.println("Title: " + metadata.get("title"));
        System.out.println("Artists: " + metadata.get("xmpDM:artist"));
        System.out.println("Genre: " + metadata.get("xmpDM:genre"));
    }

}
