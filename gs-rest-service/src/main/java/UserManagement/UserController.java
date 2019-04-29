package UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    public User signUp(int age, String username, String name, String email, String password, String gender , ArrayList<String> Interests) throws SQLException {
        User u = new User(name,email,password,username,gender,age,Interests);
        return u.createAccount(u);
    }

    public User signIn(String username,String password) throws SQLException {
        User u = new User();
        return u.logIn(username,password);
    }
}
