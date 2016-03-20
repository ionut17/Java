/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.view.content;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import lab4.controller.command.ListCommand;

/**
 *
 * @author Ionut
 */
public class DetailPanel extends JPanel {

    List<String> content;

    public void updateContent(String path) {
        ListCommand list = new ListCommand();
        String[] s = new String[1];
        s[0] = path;
        list.setArgs(s);
        try {
            list.execute();
            content = list.getResults();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DetailPanel() {
        if (content != null) {
            System.out.println("Fired");
            StringBuilder sb = new StringBuilder();
            for (String s : content) {
                sb.append(s).append("\n");
            }

            JTextArea text = new JTextArea(sb.toString());
        }

        setLayout(new CardLayout());
    }

}
