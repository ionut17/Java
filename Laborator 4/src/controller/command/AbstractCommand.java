package controller.command;

import controller.AudioManager;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
abstract public class AbstractCommand implements Command{
    
    protected AudioManager attachedAudioManager;
    protected String[] args;
    
    @Override
    public AudioManager getAudioManager(){
        return this.attachedAudioManager;
    }
    
    @Override
    public void setAudioManager(AudioManager targetManager){
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
    
}
