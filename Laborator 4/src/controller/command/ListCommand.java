package controller.command;

import java.nio.file.Path;


/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class ListCommand extends AbstractCommand {

    @Override
    public void execute() {
        String[] targetPath;
        if (args.length == 0) {
            targetPath = attachedAudioManager.getCurrentDirectory().toFile().list();
        } else {
            Path myPath = this.parseArgs();
            System.out.println(myPath.toString());
            targetPath = myPath.toFile().list();
        }
        try {
            for (String path : targetPath) {
                // prints filename and directory name
                if (path.matches("(.*)\\.(mp3|flac|wav)")) {
                    System.out.println(path);
                }
            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }

}
