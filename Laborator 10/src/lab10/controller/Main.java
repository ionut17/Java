package lab10.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ionut
 */
public class Main {

    //Variables
    private static Point mousePos = new Point();
    private static int[] compCounter = new int[10];
    private static ArrayList<Component> objectList = new ArrayList<>();
    private static ObjectsXML objectsXML = new ObjectsXML();

    //Main canvas
    private static JPanel canvas = new JPanel();
    //Properties
    private static JPanel properties = new JPanel();
    private static JTable propertiesTable = new JTable();
    //Menu items
    private static JTextField menuInput = new JTextField();
    private static JButton menuButton = new JButton("Add Component");
    private static JButton saveXMLButton = new JButton("Save as XML");
    private static JButton loadXMLButton = new JButton("Load XML");

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("InterfaceBuilder v.0.0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

//        JPanel canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setPreferredSize(new Dimension(700, 600));

        properties.setLayout(new BorderLayout());
        properties.setPreferredSize(new Dimension(300, 600));
//        properties.setBackground(new Color(0, 0, 255, 50)); //Setting light blue background
//        propertiesTable.setRowHeight(30);
//        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.removeAll();
                properties.removeAll();
                properties.revalidate();
                properties.repaint();
                canvas.revalidate();
                canvas.repaint();
            }
        });

        //Menu
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setPreferredSize(new Dimension(1000, 100));
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
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(clearButton);
        menu.add(saveXMLButton);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(loadXMLButton);

        //Display the window.
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(properties, BorderLayout.EAST);
        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addListeners() {
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

                    ObjectXML myObjectXML = new ObjectXML();
                    myObjectXML.setName(driverName);
                    myObjectXML.setX((int) mousePos.getX());
                    myObjectXML.setY((int) mousePos.getY());
                    objectsXML.addObject(myObjectXML);

                    switch (driverName.split("\\.")[driverName.split("\\.").length - 1]) {
                        case "JButton":
                            compCounter[0]++;
                            JButton button = (JButton) cmpt;
                            button.setText("Button " + compCounter[0]);
//                            button.setName("Button " + compCounter[0]);
                            button.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 30);
                            objectList.add(button);
                            addComponentListener(button, "javax.swing.JButton");
                            canvas.add(button);
                            break;
                        case "JLabel":
                            compCounter[1]++;
                            System.out.println("Added label");
                            JLabel label = (JLabel) cmpt;
                            label.setText("Label " + compCounter[1]);
//                            label.setName("Label " + compCounter[1]);
                            label.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 30);
                            label.setFont(new Font("Montseratt", Font.BOLD, 16));
                            objectList.add(label);
                            addComponentListener(label, "javax.swing.JLabel");
                            canvas.add(label);
                            break;
                        case "JTextField":
                            compCounter[2]++;
                            System.out.println("Added JTextField " + compCounter[2]);
                            JTextField textField = (JTextField) cmpt;
                            textField.setName("TextField " + compCounter[2]);
                            textField.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 25);
                            objectList.add(textField);
                            addComponentListener(textField, "javax.swing.JTextField");
                            canvas.add(textField);
                        default:
                            compCounter[3]++;
                            System.out.println("Added component " + compCounter[3]);
                            Component component = (Component) cmpt;
                            component.setName("Component " + compCounter[3]);
                            component.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 50, 50);
                            objectList.add(component);
                            addComponentListener(component, "javax.awt.Component");
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

        saveXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ac) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    int returnVal = chooser.showOpenDialog(loadXMLButton);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String[] f = chooser.getSelectedFile().toString().split("\\\\");
                        String g = new String();
                        for (int i = 0; i < f.length - 1; i++) {
                            g += f[i] + "\\\\";
                        }
                        g += f[f.length - 1];
                        File file = new File(g);
//                    File file = new File("D:\\Dropbox\\Java (github)\\Laborator 10\\file.xml");
                        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectsXML.class);

                        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                        // output pretty printed
                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                        jaxbMarshaller.marshal(objectsXML, file);
                        jaxbMarshaller.marshal(objectsXML, System.out);
                    }
                } catch (JAXBException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        loadXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ac) {
                try {

                    JFileChooser chooser = new JFileChooser();
                    int returnVal = chooser.showOpenDialog(loadXMLButton);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String[] f = chooser.getSelectedFile().toString().split("\\\\");
                        String g = new String();
                        for (int i = 0; i < f.length - 1; i++) {
                            g += f[i] + "\\\\";
                        }
                        g += f[f.length - 1];
                        File file = new File(g);
                        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectsXML.class);

                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        ObjectsXML loadedObjectsXML = (ObjectsXML) jaxbUnmarshaller.unmarshal(file);
                        System.out.println(loadedObjectsXML);
                        ArrayList<ObjectXML> loadedObjects = loadedObjectsXML.getObj();
                        canvas.removeAll();
                        for (int i = 0; i < compCounter.length; i++) {
                            compCounter[i] = 0;
                        }
                        for (ObjectXML obj : loadedObjects) {

                            Class clazz = Class.forName(obj.getName());
                            Component cmpt = (Component) clazz.newInstance();

                            switch (obj.getName().split("\\.")[obj.getName().split("\\.").length - 1]) {
                                case "JButton":
                                    compCounter[0]++;
                                    JButton button = (JButton) cmpt;
                                    button.setText("Button " + compCounter[0]);
                                    button.setName("Button " + compCounter[0]);
                                    button.setBounds(obj.getX(), obj.getY(), 100, 30);
                                    objectList.add(button);
                                    addComponentListener(button, "JButton");
                                    canvas.add(button);
                                    break;
                                case "JLabel":
                                    compCounter[1]++;
                                    System.out.println("Added label");
                                    JLabel label = (JLabel) cmpt;
                                    label.setText("Label " + compCounter[1]);
                                    label.setName("Label " + compCounter[1]);
                                    label.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 50, 30);
                                    objectList.add(label);
                                    addComponentListener(label, "JLabel");
                                    canvas.add(label);
                                    break;
                                case "JTextField":
                                    compCounter[2]++;
                                    System.out.println("Added JTextField " + compCounter[2]);
                                    JTextField textField = (JTextField) cmpt;
                                    textField.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 100, 25);
                                    objectList.add(textField);
                                    addComponentListener(textField, "JTextField");
                                    canvas.add(textField);
                                default:
                                    compCounter[3]++;
                                    System.out.println("Added component " + compCounter[3]);
                                    Component component = (Component) cmpt;
                                    component.setBounds((int) mousePos.getX(), (int) mousePos.getY(), 50, 50);
                                    objectList.add(component);
                                    addComponentListener(component, "Component");
                                    canvas.add(component);
                                    break;
                            }
                            canvas.repaint();
                            canvas.revalidate();
                        }
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private static void addComponentListener(Component cmpt, String toCast) throws ClassNotFoundException {
        String[] pieces = toCast.split("\\.");
        String name = pieces[pieces.length - 1];

//        System.out.println("Class: " + cmpt.getClass() + " Name: " + name);
        String[] cols = {"Property", "Value"};

        //Remove button
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.remove(cmpt);
                properties.removeAll();
                properties.revalidate();
                properties.repaint();
                canvas.revalidate();
                canvas.repaint();
            }
        });

        switch (name) {
            case "JButton":
                JButton button = (JButton) cmpt;
                button.addMouseListener(new MouseAdapter() {// provides empty implementation of all
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        System.out.println("Entered " + button.getText());

                        //Data
                        String[][] values = new String[5][2];
                        values[0][0] = "Name";
                        values[0][1] = button.getText();
                        values[1][0] = "X";
                        values[1][1] = String.valueOf((int) button.getBounds().getX());
                        values[2][0] = "Y";
                        values[2][1] = String.valueOf((int) button.getBounds().getY());
                        values[3][0] = "Width";
                        values[3][1] = String.valueOf((int) button.getWidth());
                        values[4][0] = "Height";
                        values[4][1] = String.valueOf((int) button.getHeight());
                        MyTableModel model = new MyTableModel(values, cols);

                        //Table listener
                        model.addTableModelListener(new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent tme) {
                                //Setting new coordinates and size
                                int newX, newY, newWidth, newHeight;
                                newX = Integer.valueOf((String) model.getValueAt(1, 1));
                                newY = Integer.valueOf((String) model.getValueAt(2, 1));
                                newWidth = Integer.valueOf((String) model.getValueAt(3, 1));
                                newHeight = Integer.valueOf((String) model.getValueAt(4, 1));
                                button.setBounds(newX, newY, newWidth, newHeight);
                                //Setting new name
                                String newName;
                                newName = (String) model.getValueAt(0, 1);
                                button.setText(newName);
                            }
                        });

                        properties.removeAll();
                        propertiesTable = new JTable(model);
                        propertiesTable.setRowHeight(30);
                        properties.add(removeBtn, BorderLayout.SOUTH);
                        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
                        properties.revalidate();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        System.out.println("Exited " + button.getText());
//                        properties.removeAll();
//                        properties.add(new JScrollPane(new JTable()));
                        properties.revalidate();
                    }
                });
                break;
            case "JLabel":
                JLabel label = (JLabel) cmpt;
                label.addMouseListener(new MouseAdapter() {// provides empty implementation of all
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        System.out.println("Entered " + label.getText());

                        //Data
                        String[][] values = new String[5][2];
                        values[0][0] = "Name";
                        values[0][1] = label.getText();
                        values[1][0] = "X";
                        values[1][1] = String.valueOf((int) label.getBounds().getX());
                        values[2][0] = "Y";
                        values[2][1] = String.valueOf((int) label.getBounds().getY());
                        values[3][0] = "Width";
                        values[3][1] = String.valueOf((int) label.getWidth());
                        values[4][0] = "Height";
                        values[4][1] = String.valueOf((int) label.getHeight());
                        MyTableModel model = new MyTableModel(values, cols);

                        //Table listener
                        model.addTableModelListener(new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent tme) {
                                //Setting new coordinates and size
                                int newX, newY, newWidth, newHeight;
                                newX = Integer.valueOf((String) model.getValueAt(1, 1));
                                newY = Integer.valueOf((String) model.getValueAt(2, 1));
                                newWidth = Integer.valueOf((String) model.getValueAt(3, 1));
                                newHeight = Integer.valueOf((String) model.getValueAt(4, 1));
                                label.setBounds(newX, newY, newWidth, newHeight);
                                //Setting new name
                                String newName;
                                newName = (String) model.getValueAt(0, 1);
                                label.setText(newName);
                            }
                        });

                        properties.removeAll();
                        propertiesTable = new JTable(model);
                        propertiesTable.setRowHeight(30);
                        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
                        properties.revalidate();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        System.out.println("Exited " + label.getText());
//                        properties.removeAll();
//                        properties.add(new JScrollPane(new JTable()));
                        properties.revalidate();
                    }
                });
                break;
            case "JTextField":
                JTextField input = (JTextField) cmpt;
                input.addMouseListener(new MouseAdapter() {// provides empty implementation of all
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        System.out.println("Entered " + input.getText());

                        //Data
                        String[][] values = new String[5][2];
                        values[0][0] = "Name";
                        values[0][1] = input.getText();
                        values[1][0] = "X";
                        values[1][1] = String.valueOf((int) input.getBounds().getX());
                        values[2][0] = "Y";
                        values[2][1] = String.valueOf((int) input.getBounds().getY());
                        values[3][0] = "Width";
                        values[3][1] = String.valueOf((int) input.getWidth());
                        values[4][0] = "Height";
                        values[4][1] = String.valueOf((int) input.getHeight());
                        MyTableModel model = new MyTableModel(values, cols);

                        //Table listener
                        model.addTableModelListener(new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent tme) {
                                //Setting new coordinates and size
                                int newX, newY, newWidth, newHeight;
                                newX = Integer.valueOf((String) model.getValueAt(1, 1));
                                newY = Integer.valueOf((String) model.getValueAt(2, 1));
                                newWidth = Integer.valueOf((String) model.getValueAt(3, 1));
                                newHeight = Integer.valueOf((String) model.getValueAt(4, 1));
                                input.setBounds(newX, newY, newWidth, newHeight);
                                //Setting new name
                                String newName;
                                newName = (String) model.getValueAt(0, 1);
                                input.setText(newName);
                            }
                        });

                        properties.removeAll();
                        propertiesTable = new JTable(model);
                        propertiesTable.setRowHeight(30);
                        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
                        properties.revalidate();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        System.out.println("Exited " + input.getText());
//                        properties.removeAll();
//                        properties.add(new JScrollPane(new JTable()));
                        properties.revalidate();
                    }
                });
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                addListeners();
            }
        });
    }

}
