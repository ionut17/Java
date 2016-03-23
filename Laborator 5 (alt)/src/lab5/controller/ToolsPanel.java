/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import lab4.controller.AudioManager;
/**
 *
 * @author Anca
 */
class ToolsPanel extends JPanel {

    JTextArea commandLine;
   
    
    public ToolsPanel() {
        this.setLayout(new BorderLayout());
        commandLine = new JTextArea(5, 70);
//        commandLine.setBackground(Color.black);
        this.add(commandLine,BorderLayout.CENTER);
        this.add(new JButton("Exit"),BorderLayout.EAST);
//        AudioManager spotify = new AudioManager();
//        spotify.start(commandLine);
        String[] linesRead=commandLine.getText().split("\\n");
        for(String s : linesRead){
            System.out.println("command "+s);
        }
    }
    
    
}
