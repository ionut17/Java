package controller.command;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class PlayCommand extends AbstractCommand {

    @Override
    public void execute() throws InvalidCommandException {
        Desktop desktop = Desktop.getDesktop();
        StringBuilder sb = new StringBuilder("\\");
        for (int i = 0; i < args.length-1; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length-1]);
        
        String str = attachedAudioManager.getCurrentDirectory().toString() + sb.toString();
        System.out.println(str);
        
        String[] list = str.split("\\\\");
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < list.length-1; i++) {
            sb2.append(list[i]).append("\\\\");
        }
        sb2.append(list[list.length-1]);
//        System.out.println(sb2.toString());
        Path path = Paths.get(sb2.toString());
        System.out.println(path.toString());
        if(path.toFile().exists()) try {
            desktop.open(path.toFile());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        else {
            throw new InvalidCommandException("File doesn't exist!");
        }
    }

}
