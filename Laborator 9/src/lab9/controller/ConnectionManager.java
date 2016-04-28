package lab9.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ionut
 */
public class ConnectionManager {

    private Connection con = null;
    private final File properties = new File("user.properties");

    private String driver;
    private String url;
    private String username;
    private String password;

    public ConnectionManager() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(properties));

        String buffer;
        while ((buffer = reader.readLine()) != null) {
            String[] values = buffer.split("#");
            switch (values[0]) {
                case "DRIVER":
                    driver = values[1].trim();
                    break;
                case "URL":
                    url = values[1].trim();
                    break;
                case "USERNAME":
                    username = values[1].trim();
                    break;
                case "PASSWORD":
                    password = values[1].trim();
                    break;
                default:
                    System.err.println("Invalid properties file!");
                    break;
            }
        }
        System.out.println("Properties file imported succesfully!");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Succesfully connected to the database!");
        }
        return con;
    }

}
