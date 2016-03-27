/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import lab5.controller.KeyMonitor;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Anca
 */
class ToolsPanel extends JPanel {

    private MyTree myTree= new MyTree();
    private DetailsPanel dp=new DetailsPanel();
    JTextArea commandLine;
    JButton exit = new JButton("Exit");

    public ToolsPanel() {
        this.setLayout(new BorderLayout());

        commandLine = new JTextArea(5, 70);
        commandLine.setBackground(new Color(222, 222, 222));
        commandLine = new JTextArea("Command> ");
        KeyMonitor km =new KeyMonitor(commandLine);
        km.setMyTree(getMyTree());
        km.setDp(getDp());
        commandLine.addKeyListener(km);
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        this.add(new JScrollPane(commandLine), BorderLayout.CENTER);
        this.add(exit, BorderLayout.EAST);
    }

    /**
     * @return the myTree
     */
    public MyTree getMyTree() {
        return myTree;
    }

    /**
     * @param myTree the myTree to set
     */
    public void setMyTree(MyTree myTree) {
        this.myTree = myTree;
    }

    /**
     * @return the dp
     */
    public DetailsPanel getDp() {
        return dp;
    }

    /**
     * @param dp the dp to set
     */
    public void setDp(DetailsPanel dp) {
        this.dp = dp;
    }
    

}
