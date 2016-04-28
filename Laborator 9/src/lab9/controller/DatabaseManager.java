package lab9.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ionut
 */
class DatabaseManager {

    static Connection con = null;

    //Interface
    private static JTable rTable = new JTable();
    
    //Metadata
    private static String[] arrayMetadata;

    public DatabaseManager() throws IOException, ClassNotFoundException, SQLException {
        //Making a connection
        ConnectionManager cm = new ConnectionManager();
        con = cm.getConnection();
        arrayMetadata = this.getMetadata();
        createGUI();
    }

    private static void createGUI() throws SQLException {
        //Create and set up the window.
        JFrame frame = new JFrame("DatabaseManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        //Elements
        JList queries = new JList(arrayMetadata);
        ListSelectionListener queriesListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                String value = queries.getSelectedValue().toString();
                String tableName = value.split(",")[0];
                String type = value.split(",")[1];
                if (type.trim().toUpperCase().equals("TABLE")) {
                    String query = "SELECT * FROM " + tableName.trim();
                    try {
                        executeStatement(query);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        queries.addListSelectionListener(queriesListener);
//        queries.setLayout(new BoxLayout(queries, BoxLayout.PAGE_AXIS));
//        queries.add(new JLabel("Table 1"));
//        queries.add(new JLabel("Table 2"));
//        queries.add(new JLabel("Table 3"));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        JTextField input = new JTextField();
        input.setSize(100, 50);
        input.setPreferredSize(new Dimension(500, 40));
        input.setMaximumSize(new Dimension(1000,40));
        inputPanel.add(input,BorderLayout.CENTER);

        JButton executor = new JButton("Execute");
        executor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                // display/center the jdialog when the button is pressed
                String currentStmt = input.getText();
                try {
                    executeStatement(currentStmt);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        inputPanel.add(executor,BorderLayout.LINE_END);

        frame.add(new JScrollPane(queries), BorderLayout.LINE_START);
        frame.add(inputPanel, BorderLayout.PAGE_START);
        frame.add(new JScrollPane(rTable), BorderLayout.CENTER);

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

    public static ResultSet executeStatement(String target) throws SQLException {
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
//            System.out.println(row.toString());
        }

        rTable.setModel(new DefaultTableModel(values, colNames.toArray()));
//        createGUI();

        return rs;
    }

    private static String[] getMetadata() throws SQLException {
        //Metadata
        DatabaseMetaData databaseMetaData = con.getMetaData();
        String catalog = null;
        String schemaPattern = "STUDENT";
        String namePattern = null;
        String[] types = null;

        ArrayList<String> metadata = new ArrayList<>();

        String user;
        int ok = 0;
        ResultSet tables = databaseMetaData.getTables(catalog, schemaPattern, namePattern, types);
        while (tables.next()) {
            if (ok == 0) {
                ok = 1;
                user = tables.getString("TABLE_SCHEM");
                metadata.add(user);
            }
            metadata.add(/*tables.getString("TABLE_SCHEM") + ", " + */tables.getString("TABLE_NAME") + ", " + tables.getString("TABLE_TYPE"));
        }

        ResultSet functions = databaseMetaData.getFunctions(catalog, schemaPattern, namePattern);
        while (functions.next()) {
            metadata.add(/*functions.getString("FUNCTION_SCHEM") + ", " +*/functions.getString("FUNCTION_NAME") + ", " + functions.getString("FUNCTION_TYPE") + ", FUNCTION");
        }

        ResultSet procedures = databaseMetaData.getProcedures(catalog, schemaPattern, namePattern);
        while (procedures.next()) {
            metadata.add(/*procedures.getString("PROCEDURE_SCHEM") + ", " +*/procedures.getString("PROCEDURE_NAME") + ", " + procedures.getString("PROCEDURE_TYPE") + ", PROCEDURE");
        }

        String[] arrayMetadata = new String[metadata.size()];
        arrayMetadata = metadata.toArray(arrayMetadata);  // in array_metadata ai string-uri cu metadatele fiecarui obiect din baza de date :* 
        for (String s : arrayMetadata) {
            System.out.println("* " + s);
        }
        return arrayMetadata;
    }

}
