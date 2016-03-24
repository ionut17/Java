package lab5.controller;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import javax.swing.JTextArea;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Ionut
 */
public class MainFrame extends JFrame {

    //Main panel - contains everything
    JPanel mainPanel = new JPanel();

    //Top panel
    DetailsPanel detailsPanel = new DetailsPanel();
    JScrollPane treeView;

    //Bottom panel
    ToolsPanel toolsPanel = new ToolsPanel();

    public MainFrame(int width, int height) throws IOException, FileNotFoundException, ClassNotFoundException {
        super("AudioManager");
        MyTree mt=new MyTree(detailsPanel);
        this.treeView = new JScrollPane(mt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setSize(width, height);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Building elements
        try {
            JScrollPane treeView = new JScrollPane(new MyTree(detailsPanel), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            JSplitPane contentPanel = new JSplitPane(HORIZONTAL_SPLIT, treeView, detailsPanel);
            //Adding elements to other frames
            //Building treePanel
            contentPanel.setDividerLocation(width / 3);

            //Building detailsPanel
            //Building ToolsPanel
            //Main Frame
            add(contentPanel, BorderLayout.CENTER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        add(toolsPanel, BorderLayout.SOUTH);
        setVisible(true);
        toolsPanel.setMyTree(mt);
        toolsPanel.setDp(detailsPanel);
    }

}
