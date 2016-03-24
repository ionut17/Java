package lab5.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "song")
public class Song implements Serializable {

    private String name;
    private String artist;
    private String songPath;
    private String album;
    private String year;
    private String genre;
    
    
    public Song() {
    }

    public Song(String path) {
        songPath = path;
    }

    public Metadata getMetadata() throws FileNotFoundException, IOException, SAXException, TikaException {
        InputStream input = new FileInputStream(Paths.get(this.getSongPath()).toFile());
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

    public boolean isValid(Path p) {
        if (Files.exists(p) == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the path
     */
    public String getSongPath() {
        return songPath;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.songPath = path;
    }

}
