package business;
import java.util.*;
import data.*;
import presentation.UiController;

/**
 * 
 */
public class SystemController {

    /**
     * Default constructor
     */
    public SystemController() {
    }

    /**
     * 
     */
    private ArrayList<Employee> managerList_;

    /**
     * 
     */
    private ArrayList<Bug> bugList_;

	/**
     * 
     */
    private ArrayList<Employee> developerList_;

    /**
     * 
     */
    private ArrayList<Assignment> assignmnetList_;

    /**
     * 
     */
    private ArrayList<Product> productList_;

    /**
     * 
     */
    private DatabaseController databaseController_;

    /**
     * 
     */
    private UiController uiController;

    /**
     * 
     */
    private LoginController loginController_;




    /**
     * @param void 
     * @return
     */
    public void startup() {
        // TODO implement here
    }

    /**
     * @param void 
     * @return
     */
    public void buildList() {
        // TODO implement here
    }
    
    public Employee loginUser(String login){
    	Employee logged_in_user = loginController_.validateLogin(login);
    	if(logged_in_user == null)
    		return null;
    	else if(logged_in_user instanceof Manager){
    		return null;
    	}
    	else if(logged_in_user instanceof Developer){
    		return null;
    	}
    	else return null;
    }
    
    public ArrayList<Employee> getManagerList_() {
		return managerList_;
	}

	public void setManagerList_(ArrayList<Employee> managerList_) {
		this.managerList_ = managerList_;
	}

	public ArrayList<Bug> getBugList_() {
		return bugList_;
	}

	public void setBugList_(ArrayList<Bug> bugList_) {
		this.bugList_ = bugList_;
	}

	public ArrayList<Employee> getDeveloperList_() {
		return developerList_;
	}

	public void setDeveloperList_(ArrayList<Employee> developerList_) {
		this.developerList_ = developerList_;
	}

	public ArrayList<Assignment> getAssignmnetList_() {
		return assignmnetList_;
	}

	public void setAssignmnetList_(ArrayList<Assignment> assignmnetList_) {
		this.assignmnetList_ = assignmnetList_;
	}

	public ArrayList<Product> getProductList_() {
		return productList_;
	}

	public void setProductList_(ArrayList<Product> productList_) {
		this.productList_ = productList_;
	}

	public DatabaseController getDatabaseController_() {
		return databaseController_;
	}

	public void setDatabaseController_(DatabaseController databaseController_) {
		this.databaseController_ = databaseController_;
	}

	public UiController getUiController() {
		return uiController;
	}

	public void setUiController(UiController uiController) {
		this.uiController = uiController;
	}

	public LoginController getLoginController_() {
		return loginController_;
	}

	public void setLoginController_(LoginController loginController_) {
		this.loginController_ = loginController_;
	}

}