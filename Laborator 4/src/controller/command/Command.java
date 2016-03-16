package controller.command;

import controller.AudioManager;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public interface Command {
    
    /**
     * Gets the audio manager attached to the command
     * @return AudioManager
     */
    public AudioManager getAudioManager();
    
    /**
     * Gets the audio manager attached to the command
     * @param targetManager
     */
    public void setAudioManager(AudioManager targetManager);
    
    /**
     * Get command arguments
     * @return String[]
     */
    public String[] getArgs();
    
    /**
     * Set command arguments
     * @param args
     */
    public void setArgs(String[] args);
    
    /**
     * Execute the command with the given arguments
     */
    public void execute() throws Exception;
    
}
