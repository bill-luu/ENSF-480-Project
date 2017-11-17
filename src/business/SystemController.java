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
	 * A list of the next generated ID for new entities
	 */
	private HashMap<String, Integer> idList_;
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

	/**
	 * Attempt to login a user using a login string in the form "username:password"
	 * @param login The login credentials received
	 * @return An employee if successful, null if failed. 
	 */
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
	 */
	public void buildList() {
		managerList_ = databaseController_.readManagerFile();
		developerList_ = databaseController_.readDeveloperFile();

		bugList_ = databaseController_.readBugFile();
		assignmentList_ = databaseController_.readAssignmentFile();
		productList_ = databaseController_.readProductFile();

		loginInfoList_ = databaseController_.readLoginInfoFile();
		idList_ = databaseController_.readIDFile();
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
		Integer nextID = idList_.get("MANAGER");
		manToAdd.setUserId_(nextID);
		managerList_.add(manToAdd);
		idList_.put("MANAGER", nextID + 1);
	}

	public void addToBugList(Bug bugToAdd) {
		Integer nextID = idList_.get("BUG");
		bugToAdd.setBugId_(nextID);
		bugList_.add(bugToAdd);
		idList_.put("BUG", nextID + 1);
	}

	public void addToAssignmentList(Assignment assToAdd) {
		Integer nextID = idList_.get("ASSIGNMENT");
		assToAdd.setBugId_(nextID);
		assignmentList_.add(assToAdd);
		idList_.put("ASSIGNMENT", nextID + 1);
	}

	public void addToProductList(Product productToAdd) {
		Integer nextID = idList_.get("PRODUCT");
		productToAdd.setProductId_(nextID);
		productList_.add(productToAdd);
		idList_.put("PRODUCT", nextID + 1);
	}

	public void addToDeveloperList(Developer devToAdd, String loginInfoToAdd) {
		developerList_.add(devToAdd);
		loginInfoList_.add(loginInfoToAdd);
	}

	/**
	 * Remove the developer account from the system
	 * 
	 * @param developer_
	 *            The developer account to be removed
	 */
	public void RemoveDeveloper(Developer developer_) {
		developerList_.remove(developer_);
	}

	/**
	 * Remove a product from the system
	 * 
	 * @param product_
	 *            The product to be removed
	 */
	public void RemoveProduct(Product product_) {
		productList_.remove(product_);
	}

	/**
	 * Remove an assignment from the system
	 * 
	 * @param assignment_
	 *            The assignment to be removed
	 */
	public void RemoveAssignment(Assignment assignment_) {
		assignmentList_.remove(assignment_);
	}

	/**
	 * Method that saves all arraylist values to the database text files
	 */
	public void save() {
		databaseController_.writeAssignmentFile(assignmentList_);
		databaseController_.writeBugFile(bugList_);
		databaseController_.writeDeveloperFile(developerList_);
		databaseController_.writeManagerFile(managerList_);
		databaseController_.writeProductFile(productList_);
		databaseController_.writeLoginInfoFile(loginInfoList_);
		databaseController_.writeIDFile(idList_);
	}

	/**
	 * Method that saves all arraylist values to the database text files on
	 * shutdown
	 */
	public void shutdown() {
		save();
	}

	/**
	 * Update a bug in the buglist
	 * 
	 * @param toUpdate
	 *            The new values for the bug
	 */
	public void updateBug(Bug toUpdate) {
		for (Bug bug : bugList_) {
			if (toUpdate.getBugId_() == bug.getBugId_()) {
				int index = bugList_.indexOf(bug);
				bugList_.set(index, toUpdate);
			}
		}
	}

	/**
	 * Update an assignment in the assignemnt list with new values
	 * 
	 * @param toUpdate
	 *            The new values for the assignment
	 */
	public void updateAssignment(Assignment toUpdate) {
		for (Assignment assignment : assignmentList_) {
			if (toUpdate.getAssignmentId_() == assignment.getAssignmentId_()) {
				int index = assignmentList_.indexOf(assignment);
				assignmentList_.set(index, toUpdate);
			}
		}
	}

	/**
	 * Update a product in the product list with new values
	 * 
	 * @param toUpdate
	 *            The new values for the product
	 */
	public void updateProduct(Product toUpdate) {
		for (Product product : productList_) {
			if (toUpdate.getProductId_() == product.getProductId_()) {
				int index = productList_.indexOf(product);
				productList_.set(index, toUpdate);
			}
		}
	}

	/**
	 * Update a dev in the dev list with new values
	 * 
	 * @param toUpdate
	 *            The new values for the dev
	 */
	public void updateDeveloper(Developer toUpdate) {
		for (Developer dev : developerList_) {
			if (toUpdate.getUserId_() == dev.getUserId_()) {
				int index = developerList_.indexOf(dev);
				developerList_.set(index, toUpdate);
			}
		}
	}

	/**
	 * Update a login in the login list with new values
	 * 
	 * @param toUpdate
	 *            The new values for the login
	 */
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