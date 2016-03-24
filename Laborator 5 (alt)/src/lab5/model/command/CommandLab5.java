package lab5.model.command;

import lab5.controller.MyTree;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Anca
 */

public interface CommandLab5 {
    
    /**
     *
     */
    MyTree myTree=new MyTree();
    DetailsPanel dp=new DetailsPanel();
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
    
    public void setMyTree(MyTree mt);
    
    public MyTree getMyTree();
    
    public void setDp(DetailsPanel dp);
    
    public DetailsPanel getDp();
    
}
