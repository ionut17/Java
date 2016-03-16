package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Song {

    private Path songPath;

    public Song(String path) {
        songPath = Paths.get(path);
    }

    public Metadata getMetadata() throws InvalidCommandException, FileNotFoundException, IOException, SAXException, TikaException {
        InputStream input = new FileInputStream(songPath.toFile());
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        return metadata;
    }
    
    public void printMetadata() throws InvalidCommandException, IOException, FileNotFoundException, SAXException, TikaException {
        Metadata metadata = this.getMetadata();
        // List all metadata
        String[] metadataNames = metadata.names();
        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }

    /**
     * @return the songPath
     */
    public Path getSongPath() {
        return songPath;
    }

    /**
     * @param songPath the songPath to set
     */
    public void setSongPath(Path songPath) {
        this.songPath = songPath;
    }

}
