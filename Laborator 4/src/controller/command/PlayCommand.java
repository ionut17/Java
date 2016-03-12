package controller.command;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import view.exception.InvalidCommandException;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class PlayCommand extends AbstractCommand{

    @Override
    public void execute() throws InvalidCommandException {
        Desktop desktop = Desktop.getDesktop();
        StringBuilder sb = new StringBuilder("\\");
        for (String s:args){
            sb.append(s).append(" ");
        }
        Path path = Paths.get(attachedAudioManager.getCurrentDirectory().toString()+sb.toString());
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
