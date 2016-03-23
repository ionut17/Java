package lab4.view;

import lab4.controller.AudioManager;

public class PrintObserver implements ManagerObserver {
    
    private AudioManager attachedManager;

    /**
     * Printing status for observed objects
     */
    @Override
    public void update() {
        System.out.format("Current directory: %s\n",attachedManager.getCurrentDirectory().toString());
        System.out.println("Insert command: ");
    }

    /**
     * @return the attachedManager
     */
    @Override
    public AudioManager getAttachedManager() {
        return attachedManager;
    }

    /**
     * @param attachedManager the attachedManager to set
     */
    @Override
    public void setAttachedManager(AudioManager attachedManager) {
        this.attachedManager = attachedManager;
    }
    
}
