package lab9.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DatabaseManager dm = new DatabaseManager();
            dm.generateReport();
            ResultSet rs = dm.executeStatement("SELECT nr_matricol, nume FROM studenti");
        } catch (IOException ex) {
            System.err.println("I/O error: Can't read from properties file");
        } catch (ClassNotFoundException ex) {
            System.err.println("Connection driver class not found");
        } catch (SQLException ex) {
            System.err.println("SQL error");
            ex.printStackTrace();
        }
    }
    
}
