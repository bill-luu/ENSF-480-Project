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
	 * A list of bugs
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
	 * A list of usernames and passwords for the company
	 */
	private ArrayList<String> loginInfoList_;

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
	 * The startup sequence of the program, creates the other controllers and
	 * builds the arraylists
	 * 
	 * @param void
	 * @return
	 */
	public void startup() {
		System.out.println("System is starting up");

		// Create each of the controllers & set them
		setDatabaseController_(new DatabaseController());
		buildList();

		setLoginController_(new LoginController(loginInfoList_));
		
		
		setLoginController_(new LoginController(loginInfoList_));
		setUiController(new UiController(this));
	}

	public Employee loginUser(String login) {
		Employee logged_in_user = loginController_.validateLogin(login);
		if (logged_in_user instanceof Manager) {
			for (Manager m : managerList_) {
				if (logged_in_user.getUsername_().equals(m.getUsername_())) {
					logged_in_user = m;
				}
			}
		} else if (logged_in_user instanceof Developer) {
			for (Developer d : developerList_) {
				if (logged_in_user.getUsername_().equals(d.getUsername_())) {
					logged_in_user = d;
				}
			}
		} else {
			logged_in_user = null;
		}
		return logged_in_user;
	}

	/**
	 * Reads the databaseController for each of the lists
	 * 
	 * @param void
	 * 
	 * @return
	 */
	public void buildList() {
		managerList_ = databaseController_.readManagerFile();
		developerList_ = databaseController_.readDeveloperFile();

		bugList_ = databaseController_.readBugFile();
		assignmentList_ = databaseController_.readAssignmentFile();
		productList_ = databaseController_.readProductFile();

		loginInfoList_ = databaseController_.readLoginInfoFile();
	}

	public ArrayList<String> getLoginInfoList_() {
		return loginInfoList_;
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

	public void setLoginInfoList_(ArrayList<String> loginInfoList_) {
		this.loginInfoList_ = loginInfoList_;
	}

	public ArrayList<Assignment> getAssignmentList_() {
		return assignmentList_;
	}

	public void setAssignmentList_(ArrayList<Assignment> assignmentList_) {
		this.assignmentList_ = assignmentList_;
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

	public void addToManagerList(Manager manToAdd) {
		managerList_.add(manToAdd);
	}

	public void addToBugList(Bug bugToAdd) {
		bugList_.add(bugToAdd);
	}

	public void addToAssignmentList(Assignment assToAdd) {
		assignmentList_.add(assToAdd);
	}

	public void addToProductList(Product productToAdd) {
		productList_.add(productToAdd);
	}

	public void addToDeveloperList(Developer devToAdd, String loginInfoToAdd) {
		developerList_.add(devToAdd);
		loginInfoList_.add(loginInfoToAdd);
	}

	public void save() {
		databaseController_.writeAssignmentFile(assignmentList_);
		databaseController_.writeBugFile(bugList_);
		databaseController_.writeDeveloperFile(developerList_);
		databaseController_.writeManagerFile(managerList_);
		databaseController_.writeProductFile(productList_);
		databaseController_.writeLoginInfoFile(loginInfoList_);
	}

	public void shutdown() {
		save();
	}

	public void updateBug(Bug toUpdate) {
		for (Bug bug : bugList_) {
			if (toUpdate.getBugId_() == bug.getBugId_()) {
				int index = bugList_.indexOf(bug);
				bugList_.set(index, toUpdate);
			}
		}
	}

	public void updateAssignment(Assignment toUpdate) {
		for (Assignment assignment : assignmentList_) {
			if (toUpdate.getAssignmentId_() == assignment.getAssignmentId_()) {
				int index = assignmentList_.indexOf(assignment);
				assignmentList_.set(index, toUpdate);
			}
		}
	}

	public void updateProduct(Product toUpdate) {
		for (Product product : productList_) {
			if (toUpdate.getProductId_() == product.getProductId_()) {
				int index = productList_.indexOf(product);
				productList_.set(index, toUpdate);
			}
		}
	}

	public void updateDeveloper(Developer toUpdate) {
		for (Developer dev : developerList_) {
			if (toUpdate.getUserId_() == dev.getUserId_()) {
				int index = developerList_.indexOf(dev);
				developerList_.set(index, toUpdate);
			}
		}
	}

	public void updateLoginInfo(String userName, String password) {
		for (String info : loginInfoList_) {
			if (info.contains(userName + ":")) {
				int index = loginInfoList_.indexOf(info);
				String[] parts = info.split(":");
				String newLogin = parts[0] + ":" + password + ">";
				System.out.println(newLogin);
				loginInfoList_.set(index, newLogin);
			}
		}
	}

	public static void main(String[] args) {
		SystemController application = new SystemController();
	}
}