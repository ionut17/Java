package controller.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class FindCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, FileNotFoundException, IOException, SAXException, TikaException {
        if (args.length == 0) {
            throw new InvalidCommandException("find: not enough arguments..");
        }
        
        StringBuilder sb = new StringBuilder("(.*)");
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append("(.*)");
        }
        sb.append(args[args.length - 1]).append("(.*)");
        
        SearchFileVisitor fv = new SearchFileVisitor();
        fv.setSearch(sb.toString());
        Files.walkFileTree(attachedAudioManager.getCurrentDirectory(), fv);

    }
}
