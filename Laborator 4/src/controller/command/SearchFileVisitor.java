package controller.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import model.Song;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.SAXException;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String search = new String();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file.toString().matches(search.concat("\\.(mp3|flac|wav)"))) {
            System.out.println(file.toString());
        }
        Song currentSong = new Song();
        currentSong.setSongPath(file.toString());
        try{ 
            Metadata metadata = currentSong.getMetadata();
            String[] metadataNames = metadata.names();
            for (String name : metadataNames) {
                if (metadata.get(name).matches(search)) {
                    System.out.println(file.toString());
                    System.out.println(name + ": " + metadata.get(name));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        try (InputStream input = new FileInputStream(file.toFile());) {
//
//            String[] metadataNames = metadata.names();
//            for (String name : metadataNames) {
//              if(metadata.get(name).matches(search)){
//                System.out.println(file.toString());
//                System.out.println(name + ": " + metadata.get(name));
//              }
//            }
//            
//        } catch (SAXException | TikaException e) {
//            System.err.println("SAX|Tika Error...");
//        }
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
