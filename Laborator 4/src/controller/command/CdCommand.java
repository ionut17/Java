package controller.command;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class CdCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidPathException, NullPointerException {
        if (args.length != 1) {
            throw new InvalidCommandException(args.length < 1 ? "cd: not enough arguments" : "cd: too many arguments");
        }
            Path currentPath = Paths.get(args[0]);
            String s = currentPath.toFile().list()[0];
            attachedAudioManager.setCurrentDirectory(currentPath);
        
    }
}
