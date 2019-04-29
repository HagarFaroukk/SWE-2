package DBconnection;

import java.sql.*;

public class DBconnection {
    private static DBconnection instance;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private DBconnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "" );
            st = con.createStatement();

        }
        catch (Exception ex){
            System.out.println("Error : " +ex);
        }
    }
    public Connection getConnection(){
        return con;
    }
    public static synchronized DBconnection getInstance() {

        if (instance == null)
            instance = new DBconnection();
        return instance;
    }
}

