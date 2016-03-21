package lab5.controller.content;

import javax.swing.JScrollPane;
import lab5.controller.MyTree;

/**
 *
 * @author Ionut
 */
public class treePanel extends JScrollPane {
    
    public treePanel(){
        super(new MyTree(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
}
