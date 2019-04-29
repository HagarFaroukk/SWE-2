package UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyController {
    public Company signUp(String name, String username, String location, String password, int nOfEmployees, ArrayList<String> Interests) throws SQLException {
        Company u = new Company(name,username,location,password,nOfEmployees,Interests);
        return u.createAccount(u);
    }

    public Company signIn(String username,String password) throws SQLException {
        Company c = new Company();
        return c.logIn(username,password);
    }
}
