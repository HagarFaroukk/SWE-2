package UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    public String name, email, password, username, gender;
    public int score, age;
    public ArrayList<String> Interests;

    public User(String name, String email, String password, String username, String gender, int age, ArrayList<String> Interests) {
        this.score = 0;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.Interests = Interests;
    }
    public User(String name, String email,String username, String gender, int age, ArrayList<String> Interests) {
        this.score = 0;
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.Interests = Interests;
    }

    public User(String username, ArrayList<String> Interests) {
        this.username = username;
        this.Interests = Interests;
    }

    public User() {
    }
    public User createAccount(User u) throws SQLException {
        UserDataAccess uda = new UserDataAccess();
        if(uda.usernameIsAlreadyTaken(u.username)){
            return null;
        }
        uda.insertNewUser(u);
        return u;
    }
    public User logIn(String username, String password) throws SQLException {
        UserDataAccess uda= new UserDataAccess();
        if(uda.usernameIsAlreadyTaken(username)){
            if(uda.Check_Password_match(username,password)){
                return uda.getUserByUsername(username);
            }
        }
        return null;
    }


}