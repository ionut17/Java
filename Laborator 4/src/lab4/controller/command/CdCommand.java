package controller.command;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class CdCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidPathException, NullPointerException {
        if (args.length == 0) {
            throw new InvalidCommandException("cd: not enough arguments");
        }
        Path currentPath = parseArgs();
        if (currentPath.toFile().isDirectory()) {
//            System.out.println(currentPath.toString());
            attachedAudioManager.setCurrentDirectory(currentPath);
        }
        else {
            throw new InvalidCommandException("cd: invalid directory parameter");
        }
    }
    
    //parse args _ absolute path or not  
}
