package service;

import model.Item;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "databaseService", eager = true)
@ApplicationScoped
public class DatabaseService {

    private final Connection connection;

    public DatabaseService() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/laborator4";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","qwe123");
        connection = DriverManager.getConnection(url, props);
        int x = 5;
    }

    public Connection getConnection() {
        return connection;
    }

}
