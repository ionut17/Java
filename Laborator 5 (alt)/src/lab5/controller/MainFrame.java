package lab5.controller;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import javax.swing.JTextArea;
import lab5.controller.content.treePanel;

/**
 *
 * @author Ionut
 */
public class MainFrame extends JFrame{
    
    //Main panel - contains everything
    JPanel mainPanel = new JPanel();
    
    //Top panel
    JScrollPane treeView = new treePanel();
    JPanel detailsPanel = new JPanel();
    JSplitPane contentPanel = new JSplitPane(HORIZONTAL_SPLIT, treeView, detailsPanel);
    
    //Bottom panel
    JPanel toolsPanel = new JPanel();
    
    public MainFrame(int width, int height){
        super("AudioManager");
        setSize(width,height);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Adding elements to other frames
        //Building treePanel
        contentPanel.setDividerLocation(width/3);
        
        //Building detailsPanel
        JTextArea text = new JTextArea(
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod"
                + "tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation"
                + "ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat."
        );
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        detailsPanel.add(text,BorderLayout.CENTER);
        
        //Building ToolsPanel
        toolsPanel.add(new JButton("Exit"),BorderLayout.EAST);
        
        //Main Frame
        add(contentPanel,BorderLayout.CENTER);
        add(toolsPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    
}
