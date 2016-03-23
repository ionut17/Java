package lab4.controller.command;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import lab4.view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class PlayCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, IOException {
        Desktop desktop = Desktop.getDesktop();
        Path path = this.parseArgs();
        if (path.toFile().exists()) {
            if (path.toString().matches("(.*)\\.(mp3|flac|wav)")) {
                desktop.open(path.toFile());
                System.out.println("Playing: "+path.toFile().getName());
            }
            else {
                throw new InvalidCommandException("Cannot play the selected file...");
            }
        } else {
            throw new InvalidCommandException("File doesn't exist!");
        }
    }

}
