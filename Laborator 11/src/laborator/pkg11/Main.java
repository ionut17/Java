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
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.stage.FileChooser;
import javax.swing.JComboBox;
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
    //Language changing panel
    private static JPanel languages = new JPanel();
//    private static String path = "D:\\Dropbox\\Java (github)\\Laborator 11\\MyResourcesFolder\\";
    private static String path = "C:\\Dropbox\\Facultate\\An II\\Semestrul II\\4. Java\\Labs (github)\\Laborator 11\\MyResourcesFolder\\";
    private static String file = "MyResources_";
    private static JComboBox languagesList = new JComboBox();
    //Main canvas
    private static JPanel locales = new JPanel();
    //Properties
    private static JPanel info = new JPanel();
    private static JLabel languagesListLabel = new JLabel();
    private static JLabel localesLabel = new JLabel();
    private static JLabel informationLabel = new JLabel();
    private static JPanel informationPanel = new JPanel();

    private static Properties props = new Properties();
//    //Menu items
//    private static JTextField menuInput = new JTextField();
//    private static JButton menuButton = new JButton("Add Component");
//    private static JButton saveXMLButton = new JButton("Save as XML");
//    private static JButton loadXMLButton = new JButton("Load XML");

    private static void createAndShowGUI() throws IOException {

        //Create and set up the window.
        JFrame frame = new JFrame("InterfaceBuilder v.0.0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        props.load(new FileReader(path + file + "en_US.properties"));
        languagesListLabel.setText(props.getProperty("select"));
        localesLabel.setText(props.getProperty("locales"));
        informationLabel.setText(props.getProperty("information"));
        informationLabel.setPreferredSize(new Dimension(450, 50));
        informationLabel.setAlignmentY(0);
        informationPanel.setLayout(new FlowLayout());
        informationPanel.add(informationLabel);

        languages.setLayout(new FlowLayout());
        languages.setPreferredSize(new Dimension(1000, 50));
        Properties props = new Properties();
        props.load(new FileReader(path + "LanguagesList.properties"));
        for (int i = 0; i < props.size(); i++) {
            languagesList.addItem(props.getProperty("language" + i));
        }
        languagesList.setSelectedIndex(0);
        languages.add(languagesListLabel);
        languages.add(languagesList);

//        JPanel canvas = new JPanel();
        locales.setLayout(new BorderLayout());
        locales.setPreferredSize(new Dimension(350, 600));
        locales.setBackground(Color.white);
        localesLabel.setPreferredSize(new Dimension(350, 60));
        locales.add(localesLabel, BorderLayout.NORTH);

        info.setLayout(new BorderLayout());
        info.setPreferredSize(new Dimension(450, 600));
        informationPanel.setBackground(Color.white);
        info.add(informationPanel, BorderLayout.NORTH);
//        properties.setBackground(new Color(0, 0, 255, 50)); //Setting light blue background
//        propertiesTable.setRowHeight(30);
//        properties.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
        JButton clearButton = new JButton("Clear All");

//        //Menu
        //Display the window.
        frame.add(languages, BorderLayout.NORTH);
        frame.add(locales, BorderLayout.CENTER);
        frame.add(info, BorderLayout.EAST);
//        frame.add(menu, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void setData() throws IOException {
        languagesList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    props.load(new FileReader(path + file + languagesList.getSelectedItem() + ".properties"));
                    languagesListLabel.setText(props.getProperty("select"));
                    languagesListLabel.revalidate();
                    languagesListLabel.repaint();
                    localesLabel.setText(props.getProperty("locales"));
                    localesLabel.revalidate();
                    localesLabel.repaint();
                    informationLabel.setText(props.getProperty("information"));
                    informationLabel.revalidate();
                    informationLabel.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ArrayList<String> arrayData = new ArrayList<>();
        Locale available[] = Locale.getAvailableLocales();
        for (Locale locale : available) {
            if (locale.toString().length() > 2) {
                arrayData.add(locale.toString());
                System.out.println(locale.toString());
            }
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
                info.add(informationPanel, BorderLayout.NORTH);
                System.out.println("Entered listener");
                JTextArea text = new JTextArea();
                String target = myList.getSelectedValue();
                StringBuilder sb = new StringBuilder();
                for (Locale locale : available) {
                    if (target.equals(locale.toString())) {
                        System.out.println("Selected: " + locale.toString());
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
                        DateFormatSymbols dfs = new DateFormatSymbols(locale);
                        Currency cr = Currency.getInstance(locale);
                        sb.append(props.getProperty("country") + ": ").append(locale.getDisplayCountry()).append("\n");
                        sb.append(props.getProperty("language") + ": ").append(locale.getDisplayLanguage()).append("\n");
                        sb.append(props.getProperty("currency") + ": ").append(cr.getSymbol() + " (" + cr.getDisplayName() + ")").append("\n");
                        sb.append(props.getProperty("weekDays") + ": ");
                        String[] weekdays = dfs.getWeekdays();
                        for (int i = 1; i < weekdays.length - 1; i++) {
                            sb.append(weekdays[i]).append(" | ");
                        }
                        sb.append(weekdays[weekdays.length - 1]).append("\n");
                        sb.append(props.getProperty("months") + ": ");
                        String[] months = dfs.getMonths();
                        for (int i = 0; i < months.length - 2; i++) {
                            sb.append(months[i]).append(" | ");
                        }
                        sb.append(months[months.length - 2]).append("\n");
                        sb.append(props.getProperty("today") + ": ").append(today.format(formatter)).append("\n");
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
                try {
                    createAndShowGUI();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    setData();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
