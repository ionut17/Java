package lab5.controller;

import lab5.view.content.DetailPanel;

/**
 *
 * @author Ionut
 */
public interface MusicObserver {
    
    public void setAttachedDetailPanel(DetailPanel attachedDetailPanel);
    
    public void updateFolder(String path);
    
}
