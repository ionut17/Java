package controller.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String search = new String();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().matches(search.concat("\\.(mp3|flac|wav)"))) {
            System.out.println(file.toString());
        }
        try (InputStream input = new FileInputStream(file.toFile());) {

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            
            String[] metadataNames = metadata.names();
            for (String name : metadataNames) {
              if(metadata.get(name).matches(search)){
                System.out.println(file.toString());
                System.out.println(name + ": " + metadata.get(name));
              }
            }
            
        } catch (SAXException | TikaException e) {
            System.err.println("SAX|Tika Error...");
        }
        return CONTINUE;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
}