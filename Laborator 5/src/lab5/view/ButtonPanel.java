package lab5.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ionut
 */
public class ButtonPanel extends JPanel{
    
    public ButtonPanel(){
        add(new JButton("Nooo"));
        setBackground(Color.red);
        setLayout(new FlowLayout());
    }
    
}
