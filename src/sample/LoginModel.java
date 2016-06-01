package sample;

import java.sql.*;

public class LoginModel
{
    Connection conection;

    public LoginModel()
    {
        conection = SqliteConnection.Connector();
        if(conection == null) System.exit(1);
    }
    public boolean isDbConnected()
    {
        try {
            return !conection.isClosed(); //an i sindesi den einai klisti tote i vasi einai sindemeni
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
