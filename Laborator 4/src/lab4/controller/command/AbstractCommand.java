package controller.command;

import controller.AudioManager;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
abstract public class AbstractCommand implements Command {

    protected AudioManager attachedAudioManager;
    protected String[] args;

    @Override
    public AudioManager getAudioManager() {
        return this.attachedAudioManager;
    }

    @Override
    public void setAudioManager(AudioManager targetManager) {
        this.attachedAudioManager = targetManager;
        
    }

    /**
     * @return the arguments
     */
    @Override
    public String[] getArgs() {
        return this.args;
    }

    /**
     * @param args the arguments to set
     */
    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Creates a Path object from the arguments
     * @return Path
     */
    public Path parseArgs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        //Checks if the link is absolute, if not it appends the current dir
        String initString;
        if (Paths.get(sb.toString()).isAbsolute()) {
            initString = "";
        }
        else {
            initString = attachedAudioManager.getCurrentDirectory().toString()+"\\";
        }
        //Replace the / with double / in the link
        String[] list = sb.toString().split("\\\\");
        StringBuilder sb2 = new StringBuilder(initString);
        for (int i = 0; i < list.length - 1; i++) {
            sb2.append(list[i]).append("\\\\");
        }
        sb2.append(list[list.length - 1]);
        Path p = Paths.get(sb2.toString());
        return p;
    }
}
