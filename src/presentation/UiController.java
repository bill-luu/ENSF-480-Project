package presentation;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.*;
import business.SystemController;

// UiController changes
// TODO: Add methods for Create/Update/Remove LoginInfo so developer/manager info can be changed when their objects change

// SystemController changes
// TODO: approveBug() & submitBug() methods are not in the SystemController class 
// TODO: Add/Remove/Update methods are not present in the SystemController class
// TODO: getAssignmentList_() in SystemController needs to take in "int userId_" as an arguement

/**
 * UiController is the controller class for the GUI of the BTS. It displays
 * different views dynamically using a CardLayout.
 */
public class UiController {

	/**
	 * The users account object recieved from logging into the system
	 */
	private Employee userLoggedIn_;

	/**
	 * The "back-end" controller for the entire system
	 */
	private SystemController system_;

	/**
	 * The display frame for the GUI
	 */
	private JFrame frame_;

	/**
	 * Default constructor
	 */
	public UiController() {
		// Init GUI components
		frame_ = new JFrame("Bug Tracking System");

		// Cardlayout keeps a hashmap of JPanels mapped to Strings
		CardLayout layout = new CardLayout();
		JPanel viewHolder = new JPanel();
		viewHolder.setLayout(layout);

		// Add JPanel to hashmap
		viewHolder.add(new OrdinaryPanel(this).getPanel_(), "OrdinaryPanel");

		// Display the ordinary panel by calling the String its mapped to
		layout.show(viewHolder, "OrdinaryPanel");

		frame_.add(viewHolder);
		frame_.setSize(700, 700);
		frame_.setVisible(true);
	}

	/**
	 * Check if the JPanel has already been added to the CardLayout
	 * 
	 * @param panel_name
	 *            The JPanel's name in the CardLayout
	 * @param viewHolder
	 *            The JPanel holding the CardLayout
	 * @return True if the there exists a panel with name 'panel_name', false
	 *         otherwise.
	 */
	public boolean checkPanelExists(String panel_name, JPanel viewHolder) {
		for (Component panel : viewHolder.getComponents()) {
			if (panel != null && panel.getName().equals(panel_name) && panel instanceof JPanel)
				return true;
		}
		return false;
	}

	/**
	 * Fetch the product list from the system
	 * 
	 * @return An arraylist of products
	 */
	public ArrayList<Product> BrowseProducts() {
		return system_.getProductList_();
	}

	/**
	 * Fetch the bug list from the system
	 * 
	 * @return An arraylist of Bugs
	 */
	public ArrayList<Bug> BrowseBugs() {
		return system_.getBugList_();
	}

	/**
	 * @param bug_
	 *            The new bug to be submitted to the system
	 */
	public void SubmitBug(Bug bug_) {
		// TODO implement here
	}

	/**
	 * @param bug_
	 *            The bug to be updated in the system
	 */
	public void UpdateBug(Bug bug_) {
		// TODO implement here
	}

	/**
	 * @param bug_
	 *            The bug to be approved in the system
	 */
	public void ApproveBug(Bug bug_) {
		// TODO implement here
	}

	/**
	 * Fetch the developer list from the system
	 * 
	 * @return An arraylist of Developers
	 */
	public ArrayList<Developer> BrowseDevelopers() {
		return system_.getDeveloperList_();
	}

	/**
	 * Add a developer account to the system
	 * 
	 * @param developer_
	 *            The new developer account to be added
	 */
	public void AddDeveloper(Employee developer_) {
		// TODO implement here
	}

	/**
	 * Update the developer account in the system
	 * 
	 * @param developer_
	 *            The developer account to be updated
	 */
	public void UpdateDeveloper(Employee developer_) {
		// TODO implement here
	}

	/**
	 * Remove the developer account from the system
	 * 
	 * @param developer_
	 *            The developer account to be removed
	 */
	public void RemoveDeveloper(Employee developer_) {
		// TODO implement here
	}

	/**
	 * Add a product to the system
	 * 
	 * @param product_
	 *            The new product to be added
	 */
	public void AddProduct(Product product_) {
		// TODO implement here
	}

	/**
	 * Remove a product from the system
	 * 
	 * @param product_
	 *            The product to be removed
	 */
	public void RemoveProduct(Product product_) {
		// TODO implement here
	}

	/**
	 * Fetch an employee's assignment list from the system using their user id
	 * 
	 * @param userId_
	 *            The employee's user ID
	 * @return An arraylist of assignments
	 */
	public ArrayList<Assignment> BrowseAssignments(int userId_) {
		return system_.getAssignmentList_(); // TODO: change args of
												// getAssignmentList_ in system
	}

	/**
	 * Remove an assignment from the system
	 * 
	 * @param assignment_
	 *            The assignment to be removed
	 */
	public void RemoveAssignment(Assignment assignment_) {
		// TODO implement here
	}

	/**
	 * Add an assignment to the system
	 * 
	 * @param assignment_
	 *            The new assignment to be added
	 */
	public void AddAssignment(Assignment assignment_) {
		// TODO implement here
	}

	/**
	 * Generates a report from a finished or in progress assignment
	 * 
	 * @param assignment_
	 *            The assignment which should be used in the report
	 * @return The report held in a string object
	 */
	public String GenerateReport(Assignment assignment_) {
		// TODO implement here
		return "";
	}

	/**
	 * Attempt to login the user to the system with their input login
	 * information
	 * 
	 * @param login
	 *            The username and password entered by the user in the form:
	 *            "username:password"
	 * @return Null if the login fails, the users Developer/Manager account
	 *         object if successful
	 */
	public Employee login(String login) {
		// Temporary
		if (system_ == null)
			return null;
		//

		userLoggedIn_ = system_.loginUser(login);
		return userLoggedIn_;
	}

	public Employee getUserLoggedIn() {
		return userLoggedIn_;
	}

	public void setUserLoggedIn(Employee userLoggedIn_) {
		this.userLoggedIn_ = userLoggedIn_;
	}

	public SystemController getSystem() {
		return system_;
	}

	public void setSystem(SystemController system_) {
		this.system_ = system_;
	}

	public JFrame getFrame() {
		return frame_;
	}

	public void setFrame(JFrame frame_) {
		this.frame_ = frame_;
	}

}