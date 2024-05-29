package group8.database;

import lombok.Getter;
import lombok.Setter;

/**
 * The Authenticator class is responsible for handling user authentication and
 * registration.
 * It interacts with the DBMS class to perform login, registration, and username
 * availability checks;
 * Thus it adds as a mediator class between DBMS and the rest of the program,
 * enabling secure access.
 * 
 * @author Maksat Sanim - Orginial Class, Edits for Integration
 * @author Bedran Yilmaz Bakay - Edits for Integration, Javadocs
 */
@Getter @Setter
public class Authenticator {

    /**
     * The database management system used for authentication.
     */
    private DBMS dbms;

    /**
     * Constructs a new Authenticator object and initializes the DBMS.
     */
    public Authenticator(){
        dbms = new DBMS();
    }

    /**
     * Attempts to log in a user with the given username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the login is successful, false otherwise.
     */
    public boolean login (String username, String password){
        return dbms.login(username, password);
    }

    /**
     * Attempts to register a new user with the given username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the registration is successful, false otherwise.
     */
    public boolean register (String username, String password){
        return dbms.register(username, password);
    }

    /**
     * Checks if a username is already taken.
     * 
     * @param username The username to check.
     * @return true if the username is taken, false otherwise.
     */
    public boolean usernameTaken(String username){
        return dbms.usernameTaken(username);
    }
    
}
