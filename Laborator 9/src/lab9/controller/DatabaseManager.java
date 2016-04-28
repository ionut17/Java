package lab9.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Ionut
 */
class DatabaseManager {

    Connection con = null;

    //Interface
    private static JTable rTable = new JTable();

    public DatabaseManager() throws IOException, ClassNotFoundException, SQLException {
        //Making a connection
        ConnectionManager cm = new ConnectionManager();
        con = cm.getConnection();
    }

    private static void createGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DatabaseManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        //Add the ubiquitous "Hello World" label.
        JPanel queries = new JPanel();
        queries.setLayout(new BoxLayout(queries, BoxLayout.PAGE_AXIS));

        queries.add(new JLabel("Table 1"));
        queries.add(new JLabel("Table 2"));
        queries.add(new JLabel("Table 3"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JTextField input = new JTextField();
        input.setSize(100, 50);
        input.setPreferredSize(new Dimension(400, 30));
        inputPanel.add(input);

        frame.add(queries, BorderLayout.LINE_START);
        frame.add(inputPanel, BorderLayout.PAGE_START);
        frame.add(rTable, BorderLayout.CENTER);

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

    public ResultSet executeStatement(String target) throws SQLException {
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println(target);
        ResultSet rs = stmt.executeQuery(target);
        ResultSetMetaData metadata = rs.getMetaData();

        ArrayList<String> colNames = new ArrayList<>();
        for (int i = 0; i < metadata.getColumnCount(); i++) {
            colNames.add(metadata.getColumnName(i + 1));
        }

        rs.last();
        int total = rs.getRow();
        rs.beforeFirst();

        String[][] values = new String[total][metadata.getColumnCount()];

        int rowcount = 0;
        while (rs.next()) {
            StringBuilder row = new StringBuilder();
            for (int i = 0; i < metadata.getColumnCount(); i++) {
                row.append(rs.getString(i + 1).trim());
                if (i + 1 < metadata.getColumnCount()) {
                    row.append(", ");
                }
                values[rowcount][i] = rs.getString(i + 1).trim(); 
            }
            rowcount++;
            System.out.println(row.toString());
        }

        rTable = new JTable(values, colNames.toArray());
        createGUI();
        return rs;
    }

}
