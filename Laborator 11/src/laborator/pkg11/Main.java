package laborator.pkg11;

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
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    //Main canvas
    private static JPanel locales = new JPanel();
    //Properties
    private static JPanel info = new JPanel();
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
        locales.setLayout(new BorderLayout());
        locales.setPreferredSize(new Dimension(350, 600));
        locales.setBackground(Color.white);

        info.setLayout(new BorderLayout());
        info.setPreferredSize(new Dimension(650, 600));
//        properties.setBackground(new Color(0, 0, 255, 50)); //Setting light blue background
//        propertiesTable.setRowHeight(30);
//        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
        JButton clearButton = new JButton("Clear All");

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
        menu.add(saveXMLButton);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(loadXMLButton);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(clearButton);

        //Display the window.
        frame.add(locales, BorderLayout.CENTER);
        frame.add(info, BorderLayout.EAST);
        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void setData() {
        ArrayList<String> arrayData = new ArrayList<>();
        Locale available[] = Locale.getAvailableLocales();
        for (Locale locale : available) {
            arrayData.add(locale.toString());
            System.out.println(locale.toString());
        }
        String[] data = new String[arrayData.size()];
        data = arrayData.toArray(data);
        JList<String> myList = new JList<String>(data);
        locales.add(new JScrollPane(myList), BorderLayout.CENTER);
        locales.repaint();
        //Add listeners
        myList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                info.removeAll();
                System.out.println("Entered listener");
                JTextArea text = new JTextArea();
                String target = myList.getSelectedValue();
                StringBuilder sb = new StringBuilder();
                for (Locale locale : available) {
                    if (target.equals(locale.toString())) {
                        System.out.println("Selected: "+locale.toString());
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
                        DateFormatSymbols dfs = new DateFormatSymbols(locale);
                        Currency cr = Currency.getInstance(locale);
                        sb.append("Country: ").append(locale.getDisplayCountry()).append("\n");
                        sb.append("Language: ").append(locale.getDisplayLanguage()).append("\n");
                        sb.append("Currency: ").append(cr.getSymbol()+" ("+cr.getDisplayName()+")").append("\n");
                        sb.append("Week days: ");
                        String[] weekdays = dfs.getWeekdays();
                        for (int i=0;i<weekdays.length;i++){
                            sb.append(weekdays[i]).append(" | ");
                        }
                        sb.append("\n");
                        sb.append("Months: ");
                        String[] months = dfs.getMonths();
                        for (int i=0;i<months.length;i++){
                            sb.append(months[i]).append(" | ");
                        }
                        sb.append("\n");
                        sb.append("Today: ").append(today.format(formatter)).append("\n");
                    }
                }
                System.out.println(sb.toString());
                text.setText(sb.toString());
                info.add(new JScrollPane(text), BorderLayout.CENTER);
                info.revalidate();
                info.repaint();
            }
        });
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                setData();
            }
        });
    }

}
