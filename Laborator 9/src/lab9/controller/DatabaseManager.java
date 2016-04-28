package lab9.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ionut
 */
class DatabaseManager {

    Connection con = null;

    public DatabaseManager() throws IOException, ClassNotFoundException, SQLException {
        //Making a connection
        ConnectionManager cm = new ConnectionManager();
        con = cm.getConnection();
        createGUI();
    }

    private static void createGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DatabaseManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        //Add the ubiquitous "Hello World" label.
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(300, 600));
        left.setLayout(new BorderLayout());
        
        JPanel queries = new JPanel();
        queries.setLayout(new BoxLayout(queries,BoxLayout.PAGE_AXIS));
        
        queries.add(new JLabel("Table 1"));
        queries.add(new JLabel("Table 2"));
        queries.add(new JLabel("Table 3"));
                
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JTextField input = new JTextField();
        input.setText();
        inputPanel.add(input);
        
        left.add(queries, BorderLayout.PAGE_START);
        left.add(inputPanel, BorderLayout.CENTER);
        
        frame.add(left, BorderLayout.CENTER);
        frame.add(new JPanel(), BorderLayout.LINE_END);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void generateReport() throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nr_matricol, nume, prenume FROM studenti ");

        ArrayList<ArrayList<String>> values = new ArrayList<>();
        ArrayList<String> row = new ArrayList<>();

        int count = 0;
        while (rs.next()) {
            row.clear();
            String nr_matricol = rs.getString("nr_matricol");
            String nume = rs.getString("nume");
            String prenume = rs.getString("prenume");
            row.add(nr_matricol);
            row.add(nume);
            row.add(prenume);
            values.add(row);
//            System.out.println(nr_matricol+" "+nume+" "+prenume);
        }

        String[] cols = {"nr_matricol", "nume", "prenume"};

//        SimpleAdhocReport arp = new SimpleAdhocReport(cols, values);

    }

}
