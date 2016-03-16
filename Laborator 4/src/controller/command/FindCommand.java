package controller.command;

import java.nio.file.Path;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        try {
            int ok=0;
            for (String path : targetPath) {
                // prints the matching file, if anything is found
                if (path.matches("(.*)" + sb.toString() + "(.*)"+"\\.(mp3|flac|wav)")) {
                    System.out.println(path);
                    ok=1;
                }
            }
            if(ok==0)
                System.out.println("No matches found!");
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }

    }
}
