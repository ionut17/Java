package controller.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
public class InfoCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, FileNotFoundException, IOException {
        try (InputStream input = new FileInputStream(parseArgs().toFile());) {

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            
            // List all metadata
            String[] metadataNames = metadata.names();
//            for (String name : metadataNames) {
//              System.out.println(name + ": " + metadata.get(name));
//            }
            System.out.println("Title: " + metadata.get("title"));
            System.out.println("Artists: " + metadata.get("xmpDM:artist"));
            System.out.println("Genre: " + metadata.get("xmpDM:genre"));

        } catch (SAXException | TikaException e) {
            System.err.println("SAX|Tika Error...");
        }
    }

}
