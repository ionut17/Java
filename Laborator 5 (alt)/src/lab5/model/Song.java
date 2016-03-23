package lab5.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
@XmlRootElement
public class Song implements Serializable {

    private String songPath;
    private String songName;
    public Song() {

    }

    public Song(String path) {
        songPath = path;
    }

    public Metadata getMetadata() throws FileNotFoundException, IOException, SAXException, TikaException {
        InputStream input = new FileInputStream(Paths.get(songPath).toFile());
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        return metadata;
    }

    public void printMetadata() throws IOException, FileNotFoundException, SAXException, TikaException {
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
    public String getSongPath() {
        return songPath;
    }

    /**
     * @param songPath the songPath to set
     */
    
    @XmlElement
    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public boolean isValid(Path p) {
        if (Files.exists(p) == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return the songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @param songName the songName to set
     */
    
    @XmlElement
    public void setSongName(String songName) {
        this.songName = songName;
    }
}
