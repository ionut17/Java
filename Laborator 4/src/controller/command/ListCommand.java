package controller.command;

import java.io.IOException;
import java.nio.file.Path;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class ListCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, IOException, NullPointerException {
        String[] targetPath;
        if (args.length == 0) {
            targetPath = attachedAudioManager.getCurrentDirectory().toFile().list();
        } else {
            Path myPath = this.parseArgs();
            System.out.println(myPath);
            targetPath = myPath.toFile().list();
        }
        if (targetPath != null) {
            for (String path : targetPath) {
                // prints filename and directory name
                if (path.matches("(.*)\\.(mp3|flac|wav)")) {
                    System.out.println(path);
                }
            }
        }
        else {
            throw new NullPointerException();
        }
    }

}
