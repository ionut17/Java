package lab4.view;

import lab4.controller.AudioManager;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public interface ManagerObserver {
    
    public void update();
    
    public AudioManager getAttachedManager();
    
    public void setAttachedManager(AudioManager attachedManager);
    
}
