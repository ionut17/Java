package lab5.controller.content;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Ionut
 */
public class DetailsPanel extends JPanel {
    
    JTextArea myText = new JTextArea("empty");

    public DetailsPanel() {
        myText.setEditable(false);
        myText.setLineWrap(true);
        myText.setWrapStyleWord(true);
        add(myText, BorderLayout.CENTER);
    }
    
    public void setContent(String target){
        myText.setText(target);
    }

}
