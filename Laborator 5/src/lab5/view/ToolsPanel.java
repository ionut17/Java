package lab5.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ionut
 */
public class ToolsPanel extends JPanel{
    
    public ToolsPanel(){
        add(new JButton("Nooo"));
        setBackground(new Color(147,165,237));
        setLayout(new FlowLayout());
        //Generare width automata
        int wSize = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        setPreferredSize(new Dimension(wSize,100));
        setMaximumSize(new Dimension(wSize,200));
    }
    
}
