package laborator.pkg11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        
        JButton clearButton = new JButton("Clear All");
        
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
//            if (locale.toString().length() > 2) {
            arrayData.add(locale.toString());
            System.out.println(locale.toString());
//            }
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
                String capital = new String();
                String phoneCode = new String();
                String continentCode = new String();
                String flag = new String();
                ArrayList<Pair<String, String>> continents = new ArrayList<>();

                try {
                    URL url;
                    url = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
                    URLConnection con = url.openConnection();
                    InputStream is = con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String l = new String();
                    String code = new String();
                    while ((l = br.readLine()) != null) {
                        if (l.contains("<sCode>")) {
                            code = l.split("<sCode>")[1].split("</sCode>")[0];
                        } else if (l.contains("<sName>")) {
                            Pair<String, String> p = new Pair<String, String>(code, l.split("<sName>")[1].split("</sName>")[0]);
                            continents.add(p);
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Locale locale : available) {
                    if (target.equals(locale.toString())) {
                        System.out.println("Selected: " + locale.toString());
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
                        DateFormatSymbols dfs = new DateFormatSymbols(locale);

                        try {
                            URL url1;
                            url1 = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CountryISOCode/JSON/debug?sCountryName=" + locale.getDisplayCountry().replaceAll(" ", "+"));
                            URLConnection con1 = url1.openConnection();
                            InputStream is1 = con1.getInputStream();
                            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
                            String countryCode = br1.readLine().substring(1, 3);

                            URL url2;
                            url2 = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/FullCountryInfo/JSON/debug?sCountryISOCode=" + countryCode);
                            URLConnection con2 = url2.openConnection();
                            InputStream is2 = con2.getInputStream();
                            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                            String lines = new String();
                            String line;
                            while ((line = br2.readLine()) != null) {
                                lines += line;
                            }
                            String[] forCapitalCity = lines.split("\",\"");
                            for (String s : forCapitalCity) {
                                if (s.contains("sCapitalCity")) {
                                    if (s.split("\":\"").length > 1) {
                                        capital = s.split("\":\"")[1];
                                    }
                                }
                                if (s.contains("sPhoneCode")) {
                                    if (s.split("\":\"").length > 1) {
                                        phoneCode = s.split("\":\"")[1];
                                    }
                                }
                                if (s.contains("sContinentCode")) {
                                    if (s.split("\":\"").length > 1) {
                                        continentCode = s.split("\":\"")[1];
                                    }
                                }
                                if (s.contains("sCountryFlag")) {
                                    if (s.split("\":\"").length > 1) {
                                        flag = s.split("\":\"")[1];
                                    }
                                }
                            }
                            System.out.println("capital: " + capital + " phoneCode: " + phoneCode + " continentCode: " + continentCode);
                            System.out.println("code 1: " + countryCode + " primit 2: " + lines);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        sb.append(props.getProperty("country") + ": ").append(locale.getDisplayCountry()).append(" ("+locale.getDisplayCountry(locale)+") ").append("\n");
                        sb.append(props.getProperty("language") + ": ").append(locale.getDisplayLanguage()).append(" ("+locale.getDisplayLanguage(locale)+") ").append("\n");
                        try {
                            Currency cr = Currency.getInstance(locale);
                            sb.append(props.getProperty("currency") + ": ").append(cr.getSymbol() + " (" + cr.getDisplayName() + ")").append("\n");
                        } catch (IllegalArgumentException i) {
                            System.err.println(i);
                        };
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
                        sb.append(props.getProperty("capital") + ": ").append(capital).append("\n");
                        sb.append(props.getProperty("phoneCode") + ": ").append(phoneCode).append("\n");
                        Image image = null;
                        try {
                            URL url_img = new URL(flag);
                            image = ImageIO.read(url_img);
                            JLabel imgLabel = new JLabel(new ImageIcon(url_img));
                            info.add(imgLabel, BorderLayout.NORTH);
                            info.revalidate();
                            info.repaint();   
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        String continentName = new String();
                        for (Pair<String, String> p : continents) {
                            if (continentCode.matches(p.getKey())) {
                                continentName = p.getValue();
                            }
                        }
                        sb.append(props.getProperty("continent") + ": ").append(continentName).append("\n");
                    }
                }
//                System.out.println(sb.toString());
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
