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
/**
 *
 * @author Anca
 */
class ToolsPanel extends JPanel {

    JTextArea commandLine;
    JButton exit = new JButton("Exit");
    
    public ToolsPanel() {
        this.setLayout(new BorderLayout());
        
        commandLine = new JTextArea(5, 70);
        commandLine.setBackground(new Color(222,222,222));
        
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        this.add(new JScrollPane(commandLine),BorderLayout.CENTER);
        this.add(exit,BorderLayout.EAST);
        
//        String[] linesRead=commandLine.getText().split("\\n");
//        for(String s : linesRead){
//            System.out.println("command "+s);
//        }
    }
    
    
}
