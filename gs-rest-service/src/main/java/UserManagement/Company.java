package UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class Company {
    public String name,username,location,password;
    public int nOfEmployees;
    public ArrayList<String> Interests;
    public Company(String name, String username, String location, String password, int nOfEmployees, ArrayList<String> Interests){
        this.name = name;
        this.nOfEmployees = nOfEmployees;
        this.password = password;
        this.location = location;
        this.username = username;
        this.Interests = Interests;
    }
    public Company(String name, String username, String location, int nOfEmployees, ArrayList<String> Interests){
        this.name = name;
        this.nOfEmployees = nOfEmployees;
        this.location = location;
        this.username = username;
        this.Interests = Interests;
    }
    public Company(String username,ArrayList<String> Interests){
        this.username = username;
        this.Interests = Interests;
    }
    public Company(){}
    public Company createAccount(Company c) throws SQLException {
        CompanyDataAccess cda = new CompanyDataAccess();
        if(cda.usernameIsAlreadyTaken(c.username)){
            return null;
        }
        cda.insertNewCompany(c);
        return c;
    }

    public Company logIn(String username, String password) throws SQLException {
        CompanyDataAccess cda= new CompanyDataAccess();
        if(cda.usernameIsAlreadyTaken(username)){
            if(cda.Check_Password_match(username,password)){
                return cda.getCompanyByUsername(username);
            }
        }
        return null;
    }


}
