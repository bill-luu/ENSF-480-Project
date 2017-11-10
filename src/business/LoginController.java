package business;
import java.util.*;
import data.Employee;

/**
 * LoginController is a class that authenicates user logins
 */
public class LoginController {

	/**
     * An arraylist of all logins and passwords
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
     * @param login The login information being authenticated
     * @return An employee object if the login is valid (Developer or Manager). Null if not valid.
     */
    public Employee validateLogin(String login) {
    	// For-each login in the list
        for(String validLogin : loginInfo_){
        	if(validLogin.matches(login))
			{
        		// TODO: Get Employee Object and Return it. How does LoginController access the manager/developer list?
			}
        }
        return null;
    }

    
	public ArrayList<String> getLoginInfo_() {
		return loginInfo_;
	}


	public void setLoginInfo_(ArrayList<String> loginInfo_) {
		this.loginInfo_ = loginInfo_;
	}

}