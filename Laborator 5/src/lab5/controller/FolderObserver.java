package lab5.controller;

import lab5.view.content.DetailPanel;

public class FolderObserver implements MusicObserver {

    private DetailPanel attachedDetailPanel;
    
    /**
     * @param attachedDetailPanel the attachedDetailPanel to set
     */
    public void setAttachedDetailPanel(DetailPanel attachedDetailPanel) {
        this.attachedDetailPanel = attachedDetailPanel;
    }
    
    @Override
    public void updateFolder(String path) {
        this.attachedDetailPanel.updateContent(path);
    }
}
