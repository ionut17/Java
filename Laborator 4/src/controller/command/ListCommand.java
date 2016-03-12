package controller.command;

import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class ListCommand extends AbstractCommand {

    @Override
    public void execute() {
        if (args.length > 1) {
            throw new InvalidCommandException("list: too many arguments");
        }
        String[] paths;

        try {
            for (String path : attachedAudioManager.getCurrentDirectory().toFile().list()) {
                // prints filename and directory name
                if (path.matches("(.*)\\.(mp3|flac|wav)")) System.out.println(path);
            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }

}
