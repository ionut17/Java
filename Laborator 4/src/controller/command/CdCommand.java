package controller.command;

import java.nio.file.Path;
import java.nio.file.Paths;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class CdCommand extends AbstractCommand {

    @Override
    public void execute() {
        if (args.length != 1) {
            throw new InvalidCommandException(args.length < 1 ? "cd: not enough arguments" : "cd: too many arguments");
        }
        attachedAudioManager.setCurrentDirectory(Paths.get(args[0]));
    }

}
