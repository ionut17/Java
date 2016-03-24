package lab5.controller;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lab5.controller.content.DetailsPanel;
import lab5.model.Song;

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
            JPanel sidePanel = new JPanel();
            JButton xmlButton = new JButton("Get XML");

            xmlButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                try {
                    Favorites favorites = new Favorites();

                    File f = new File(System.getProperty("user.dir") + "\\favorites.ser");
                    if (f.exists() == true && f.length() > 0) {

                        try {
                            FileInputStream fileIn;
                            fileIn = new FileInputStream("favorites.ser");
                            ObjectInputStream in;
                            in = new ObjectInputStream(fileIn);
                            favorites.setSongs((List<Song>) in.readObject());
                            in.close();
                            fileIn.close();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }

                        File file = new File("D:\\FavoriteSongs.xml");
                        JAXBContext jaxbContext = JAXBContext.newInstance(Favorites.class);
                        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                        // output pretty printed
                        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        jaxbMarshaller.marshal(favorites, file);
                        jaxbMarshaller.marshal(favorites, System.out);
                        Desktop desktop = Desktop.getDesktop();

                        Path path = file.toPath();
                        if (path.toFile().exists()) {
                            try {
                                desktop.open(path.toFile());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
            });

            sidePanel.setLayout(new BorderLayout());
            sidePanel.add(xmlButton, BorderLayout.SOUTH);
            sidePanel.add(treeView, BorderLayout.CENTER);
            JSplitPane contentPanel = new JSplitPane(HORIZONTAL_SPLIT, sidePanel, detailsPanel);
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
