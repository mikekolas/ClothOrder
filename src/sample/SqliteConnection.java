package sample;

import java.sql.*;
import java.sql.DriverManager;

public class SqliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBcloth.sqlite"); //i vasi dedomenwn
            return con;
        } catch (Exception e){
            return null;
        }
    }
}
