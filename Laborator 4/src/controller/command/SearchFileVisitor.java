package controller.command;

import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import model.Song;
import org.apache.tika.metadata.Metadata;

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
