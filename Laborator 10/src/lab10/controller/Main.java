package lab10.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ionut
 */
public class Main {

    //Variables
    private static Point mousePos = new Point();
    private static int[] compCounter = new int[10];

    //Main canvas
    private static JPanel canvas = new JPanel();
    //Properties
    private static JPanel properties = new JPanel();
    //Menu items
    private static JTextField menuInput = new JTextField();
    private static JButton menuButton = new JButton("Add Component");

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("InterfaceBuilder v.0.0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

//        JPanel canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setPreferredSize(new Dimension(650, 600));

        properties.setLayout(new FlowLayout());
        properties.setPreferredSize(new Dimension(250, 600));
        properties.setBackground(new Color(0,0,255,50)); //Setting light blue background

        //Menu
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(900, 100));
        menu.setBackground(new Color((float) 0, (float) 0, (float) 0, (float) 0.1)); //Setting light grey background
        //Menu Items
        JLabel menuLabel = new JLabel("Component name: ");
//        JTextField menuInput = new JTextField();
        menuInput.setPreferredSize(new Dimension(200, 30));
//        JButton menuButton = new JButton("Add Component");
        //Assembling parts
        menu.add(menuLabel);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(menuInput);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(menuButton);

        //Display the window.
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(properties, BorderLayout.EAST);
        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addHandlers() {
        canvas.addMouseListener(new MouseAdapter() {// provides empty implementation of all
            @Override
            public void mousePressed(MouseEvent e) {
                mousePos.setLocation(e.getX(), e.getY());
                System.out.println("Set new coords at: " + e.getX() + ' ' + e.getY());
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String driverName = menuInput.getText();

                    Class clazz = Class.forName(driverName);
                    Component cmpt = (Component) clazz.newInstance();
                    switch (driverName.split("\\.")[driverName.split("\\.").length - 1]) {
                        case "JButton":
                            compCounter[0]++;
                            JButton button = (JButton) cmpt;
                            button.setText("Button " + compCounter[0]);
                            button.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 30);
                            canvas.add(button);
                            break;
                        case "JLabel":
                            compCounter[1]++;
                            System.out.println("Added label");
                            JLabel label = (JLabel) cmpt;
                            label.setText("Label " + compCounter[1]);
                            label.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 50, 30);
                            canvas.add(label);
                            break;
                        case "JTextField":
                            compCounter[2]++;
                            System.out.println("Added JTextField " + compCounter[2]);
                            JTextField textField = (JTextField) cmpt;
                            textField.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 25);
                            canvas.add(textField);
                        default:
                            compCounter[3]++;
                            System.out.println("Added component " + compCounter[3]);
                            Component component = (Component) cmpt;
                            component.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 50, 50);
                            canvas.add(component);
                            break;
                    }
                    canvas.repaint();
                    canvas.revalidate();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void getClick() {

    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                addHandlers();
            }
        });
    }

}
