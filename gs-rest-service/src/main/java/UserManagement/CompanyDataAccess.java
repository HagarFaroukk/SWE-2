package UserManagement;

import DBconnection.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDataAccess {

    /**********get Company by username********/
    public Company getCompanyByUsername(String username) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select username,location,nOfEmployees,name,Interests from company WHERE username = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, username);
        //preparedStmt.executeQuery();
        ResultSet rs;
        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            String uname = rs.getString("username");
            String name = rs.getString("name");
            int noofemployees = rs.getInt("nOfEmployees");
            String location = rs.getString("location");
            String Interests = rs.getString("Interests");
            String[] interets = Interests.split(",");
            ArrayList<String> arrayofInterests = new ArrayList<>();

            for(int i=0;i<interets.length;i++){
                arrayofInterests.add(interets[i]);
            }
            Company c = new Company(name,username, location,noofemployees,arrayofInterests);

            rs.close();
            preparedStmt.close();
            return c;

        }
        return null;

    }


    /**********get All companies********/
    public List<Company> getAllCompanies() throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select name,username,location,nOfEmployees,Interests,name from company";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        //preparedStmt.executeQuery();
        ResultSet rs;
        List<Company> allCompanies = new ArrayList<>();
        rs =preparedStmt.executeQuery();
        while(rs.next()){

            String username = rs.getString("username");
            String Interests = rs.getString("Interests");
            String name = rs.getString("name");
            int nOfEmployees = rs.getInt("nOfEmployees");
            String location = rs.getString("location");
            String[] interets = Interests.split(",");
            ArrayList<String> arrayofInterests = new ArrayList<>();

            for(int i=0;i<interets.length;i++){
                arrayofInterests.add(interets[i]);
            }
            Company c = new Company(name,username ,location,nOfEmployees, arrayofInterests);
            allCompanies.add(c);

        }
        rs.close();
        preparedStmt.close();
        return allCompanies;

    }
    public void insertNewCompany(Company c) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String interests="";
        for (int i=0;i<c.Interests.size();i++){
            interests+=c.Interests.get(i);
        }

        // insert the data
        String query = "INSERT INTO company(name, username, location, nOfEmployees, password,Interests) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, c.name);
        preparedStmt.setString(2, c.username);
        preparedStmt.setString(3, c.location);
        preparedStmt.setInt(4, c.nOfEmployees);
        preparedStmt.setString(5, c.password);
        preparedStmt.setString(6, interests);
        preparedStmt.execute();
        preparedStmt.close();
    }
    public boolean usernameIsAlreadyTaken(String username) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select username from company where username =?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, username);
        ResultSet rs;
        rs =preparedStmt.executeQuery();
        if(rs.next()){
            return true;
        }
        rs.close();
        preparedStmt.close();
        return false;

    }

    /***************Sign In****************/
    public String retrievePasswordByUsername(String username) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select password from company WHERE username = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, username);
        //preparedStmt.executeQuery();
        ResultSet rs;
        rs =preparedStmt.executeQuery();
        if(rs.next()){
            String pass = rs.getString("password");
            rs.close();
            preparedStmt.close();
            return pass;
        }
        return "null";

    }

    public boolean Check_Password_match(String username, String password) throws SQLException {
        if(retrievePasswordByUsername(username).equals(password))
        {
            return true; //match
        }
        return false; // not match

    }

}
