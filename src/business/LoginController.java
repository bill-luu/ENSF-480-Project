package business;
import java.util.*;

import data.Developer;
import data.Employee;
import data.Manager;

/**
 * LoginController is a class that authenicates user logins
 */
public class LoginController {

	/**
     * An arraylist of all logins and passwords in the form:
     *  	man<username:password> for Managers
     * 		dev<username:password> for Developers
     */
    private ArrayList<String> loginInfo_;

    /**
     * Default constructor
     */
    public LoginController() {
    	loginInfo_ = new ArrayList<String>();
    }
    
    /**
     *  Constructor that sets the login list
     */
    public LoginController(ArrayList<String> logins) {
    	loginInfo_ = logins;
    }

    /**
     * Attempts to login the user.
     * @param login The login information being authenticated. In the form: 'username:password'
     * @return An employee object (with only a username) if the login is valid (Developer or Manager). Null if not valid.
     */
    public Employee validateLogin(String login) {
    	// For-each login in the list
        for(String validLogin : loginInfo_){
        	if(validLogin.matches("dev-<" + login + ">")){
        		Developer dev = new Developer();
        		dev.setUsername(login.split(":")[0]);
        		return dev;
        	}
			else if (validLogin.matches("man-<" + login + ">")){
				Manager man = new Manager();
				man.setUsername(login.split(":")[0]);
        		return man;
			}
        }
        return null;
    }

    /**
	 * Get the arraylist of login information
	 */
	public ArrayList<String> getLoginInfo_() {
		return loginInfo_;
	}

	/**
	 * Set the arraylist of login information
	 * @param loginInfo_
	 */
	public void setLoginInfo_(ArrayList<String> loginInfo_) {
		this.loginInfo_ = loginInfo_;
	}

}