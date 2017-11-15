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
    	startup();
    }

    /**
     * A list of managers in the company
     */
    private ArrayList<Manager> managerList_;

    /**
     *  A list of bugs
     */
    private ArrayList<Bug> bugList_;

	/**
     * The list of developers in the company
     */
    private ArrayList<Developer> developerList_;

    /**
     * The list of assignments
     */
    private ArrayList<Assignment> assignmentList_;

    /**
     * A list of products at the company
     */
    private ArrayList<Product> productList_;

    /**
     * The database controller
     */
    private DatabaseController databaseController_;

    /**
     * The Ui Controller
     */
    private UiController uiController;

    /**
     * The login controller
     */
    private LoginController loginController_;

    /**
     * The startup sequence of the program, creates the other controllers and builds the arraylists
     * @param void 
     * @return
     */
    public void startup() {
    	System.out.println("System is starting up");
    	
    	//Create each of the controllers & set them
    	setDatabaseController_(new DatabaseController());
    	setLoginController_(new LoginController());
    	setUiController(new UiController());
    	
    	buildList();
    }

    /**
     * Reads the databaseContoller for each of the lists
     * @param void 
     * @return
     */
    public void buildList() {
    	managerList_ = databaseController_.readManagerFile();
    	developerList_ = databaseController_.readDeveloperFile();
    	
    	bugList_ = databaseController_.readBugFile();
    	assignmentList_ = databaseController_.readAssignmentFile();
    	productList_ = databaseController_.readProductFile();
    }
    
    public ArrayList<Manager> getManagerList_() {
		return managerList_;
	}

	public void setManagerList_(ArrayList<Manager> managerList_) {
		this.managerList_ = managerList_;
	}

	public ArrayList<Bug> getBugList_() {
		return bugList_;
	}

	public void setBugList_(ArrayList<Bug> bugList_) {
		this.bugList_ = bugList_;
	}

	public ArrayList<Developer> getDeveloperList_() {
		return developerList_;
	}

	public void setDeveloperList_(ArrayList<Developer> developerList_) {
		this.developerList_ = developerList_;
	}

	public ArrayList<Assignment> getAssignmnetList_() {
		return assignmentList_;
	}

	public void setAssignmnetList_(ArrayList<Assignment> assignmnetList_) {
		this.assignmentList_ = assignmnetList_;
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
	
	public static void main(String[] args)
	{
		SystemController application = new SystemController();
	}
}