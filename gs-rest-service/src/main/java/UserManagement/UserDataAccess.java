package UserManagement;

import DBconnection.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDataAccess {

    /**********get User by username********/
    public User getUserByUsername(String username) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select username,age,gender,email,name,Interests from user WHERE username = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, username);
        //preparedStmt.executeQuery();
        ResultSet rs;
        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            String uname = rs.getString("username");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String Interests = rs.getString("Interests");
            String[] interets = Interests.split(",");
            ArrayList<String> arrayofInterests = new ArrayList<>();

            for(int i=0;i<interets.length;i++){
                arrayofInterests.add(interets[i]);
            }
            User u = new User(name,email,username,gender,age,arrayofInterests);

            rs.close();
            preparedStmt.close();
            return u;

        }
        return null;

    }


    /**********get All users********/
    public List<User> getAllUsers() throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select username,age,email,gender,Interests,name from user";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        //preparedStmt.executeQuery();
        ResultSet rs;
        List<User> allUsers = new ArrayList<>();
        rs =preparedStmt.executeQuery();
        while(rs.next()){

            String username = rs.getString("username");
            String Interests = rs.getString("Interests");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");
            String email = rs.getString("email");
            String[] interets = Interests.split(",");
            ArrayList<String> arrayofInterests = new ArrayList<>();

            for(int i=0;i<interets.length;i++){
                arrayofInterests.add(interets[i]);
            }
            User u = new User(name, email, username, gender,age, arrayofInterests);
            allUsers.add(u);

        }
        rs.close();
        preparedStmt.close();
        return allUsers;

    }
    /*********************Sign Up***********************/
    public void insertNewUser(User u) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        // create a Statement from the connection
        Statement statement = db.getConnection().createStatement();
        String interests="";
        for (int i=0;i<u.Interests.size();i++){
            interests+=u.Interests.get(i);
        }

        // insert the data
        String query = "INSERT INTO user(age, gender, email, username, password, name,Interests) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, u.age);
        preparedStmt.setString(2, u.gender);
        preparedStmt.setString(3, u.email);
        preparedStmt.setString(4, u.username);
        preparedStmt.setString(5, u.password);
        preparedStmt.setString(6, u.name);
        preparedStmt.setString(7, interests);
        preparedStmt.execute();
        preparedStmt.close();
    }
    public boolean usernameIsAlreadyTaken(String username) throws SQLException {
        DBconnection db = DBconnection.getInstance();
        Statement statement = db.getConnection().createStatement();
        String query = "select username from user where username =?";
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
        String query = "select password from user WHERE username = ?";
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
