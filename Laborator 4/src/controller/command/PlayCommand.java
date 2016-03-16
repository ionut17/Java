package controller.command;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class PlayCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException, IOException {
        Desktop desktop = Desktop.getDesktop();
        
//        String[] list = str.split("\\\\");
//        StringBuilder sb2 = new StringBuilder();
//        for (int i = 0; i < list.length-1; i++) {
//            sb2.append(list[i]).append("\\\\");
//        }
//        sb2.append(list[list.length-1]);
//        System.out.println(sb2.toString());
        Path path = this.parseArgs();
        if(path.toFile().exists()) {
            desktop.open(path.toFile());
        }
        else {
            throw new InvalidCommandException("File doesn't exist!");
        }
    }

}
