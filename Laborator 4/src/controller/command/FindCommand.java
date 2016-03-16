package controller.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class FindCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException {
        String[] targetPath;
        targetPath = attachedAudioManager.getCurrentDirectory().toFile().list();
        StringBuilder sb = new StringBuilder("(.*)");
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append("(.*)");
        }
        sb.append(args[args.length - 1]).append("(.*)");
        try {
            SearchFileVisitor fv= new SearchFileVisitor();
            fv.setSearch(sb.toString());
            Files.walkFileTree(attachedAudioManager.getCurrentDirectory(), fv);
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }

    }
}
