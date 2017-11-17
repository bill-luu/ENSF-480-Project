package presentation;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Assignment;
import data.Bug;
import data.Product;

/**
 * 
 */
public class DeveloperPanel {

	/**
	* 
	*/
	private JPanel panel_;

	/**
	 * 
	 */
	private UiController uiController_;

	/**
	 * The JList display for products
	 */
	private JList<String> assignList;

	/**
	 * The JList display for bugs
	 */
	private JList<String> buglist;

	/**
	 * 
	 * @param uiController
	 */
	public DeveloperPanel(UiController uiController) {
		this.uiController_ = uiController;
    	panel_ = new JPanel();
    	panel_.setLayout(new GridBagLayout());
    	panel_.setName("DeveloperPanel");
    	
    	Font titlefont = new Font("Calibri", Font.BOLD, 40);
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	JLabel title = new JLabel("Assignments");
    	title.setFont(titlefont);
    	
    	JLabel title2 = new JLabel("Info");
    	title2.setFont(titlefont);
    	
    	JButton logoutButton = new JButton("Log Out");
    	// JButton submitBugButton = new JButton("Report Bug");
    	
    	String[] pages = {"Bugs", "Assignments"};
    	DefaultComboBoxModel<String> pageModel = new DefaultComboBoxModel<String>(pages);
    	JComboBox<String> pageSelector = new JComboBox<String>(pageModel);
    	pageSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pageSelector.getSelectedItem().equals("Bugs"))
				{
					
				}
				
			}
    		
    	});
    	
    	ArrayList<Assignment> assignments = uiController.BrowseAssignments(uiController_.getUserLoggedIn().getUserId_());
	    	
    	DefaultListModel<String> assignmentModel = new DefaultListModel<String>();
    	
    	for(Assignment a : assignments)
    		assignmentModel.addElement(a.getAssignmentId_() + "");
    	
    	
    	assignList = new JList<String>(assignmentModel);
    	    	
    	JScrollPane assignmentScoller = new JScrollPane(assignList);
    	assignmentScoller.setPreferredSize(new Dimension(200, 525));
    	
    	
    	// Temporary components for testing
	    	JButton demoDevButton = new JButton("Test Dev Screen");
	    	JButton demoManButton = new JButton("Test Manager Screen");
	    	
    	demoDevButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get components
				JPanel viewHolder = (JPanel)(uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout)viewHolder.getLayout();
				
				// Create new DeveloperPanel if it doesn't exist
				if(! uiController.checkPanelExists("DeveloperPanel", viewHolder)){
					viewHolder.add(new DeveloperPanel(uiController).getPanel_(), "DeveloperPanel");
				}
				
				// Change view to developer panel
				layout.show(viewHolder, "DeveloperPanel");
			}
		});

		demoManButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get components
				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();

				// Create new ManagerPanel if it doesn't exist
				if (!uiController.checkPanelExists("ManagerPanel", viewHolder)) {
					viewHolder.add(new ManagerPanel(uiController).getPanel_(), "ManagerPanel");
				}

				// Change view to manager panel
				layout.show(viewHolder, "ManagerPanel");
			}
	    	});
    	// End temporary components
	    
	    // Open Popup when clicking on list
	  //   buglist.addListSelectionListener(new ListSelectionListener(){

			// @Override
			// public void valueChanged(ListSelectionEvent e) {
			// 	inspectBugPopUp();
			// }

			// private void inspectBugPopUp() {
			// 	// TODO Auto-generated method stub
				
			// }
	  //   });
	    
	    // Open Popup when clicking on list
	    assignList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//bugModel.removeAllElements();
				String product = assignList.getSelectedValue();
				String products[] = product.split(" ");
				int productID = Integer.parseInt(products[0]);
				
		    	// Search for corresponding bugs
				// ArrayList<Bug> bugs = uiController.BrowseBugs();
		  //   	for(Bug b : bugs){
		  //   		if(b.getProductId_() == productID)
		  //   			bugModel.addElement(b.getBugId_() + " " + b.getBugTitle_() + " " + b.getState_());
		    	//}
			}
	    });
	    
	    // Open login popup when clicking login button
    	logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createLoginPopUp();
			}

			private void createLoginPopUp() {
				// TODO Auto-generated method stub
				
			}
    	});
    	
    	// Open submit bug popup when clicking submit bug button
    	// submitBugButton.addActionListener(new ActionListener(){

			// @Override
			// public void actionPerformed(ActionEvent arg0) {
			// 	submitBugPopUp();
			// }

			// private void submitBugPopUp() {
			// 	// TODO Auto-generated method stub
				
			// }
   //  	});
    	
    	// Temp Components
//	    	gbc.gridx = 10; gbc.gridy = 9; gbc.gridwidth = 1; gbc.gridheight = 1;
//	    	panel_.add(demoManButton, gbc);
//	    	
//    	gbc.gridx = 10; gbc.gridy = 10; gbc.gridwidth = 1; gbc.gridheight = 1;
	//    	panel_.add(demoDevButton, gbc);
    	//
    	
    	gbc.weighty = 1;
    	gbc.insets = new Insets(20,0,0,0);
    	gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4; gbc.gridheight = 1;
    	panel_.add(title, gbc);
    	
    	gbc.gridx = 5; gbc.gridy = 0; gbc.gridwidth = 4; gbc.gridheight = 1;
    	panel_.add(title2, gbc);
    	
    	gbc.insets = new Insets(0, 75, 0, 0);
    	gbc.weightx = 1;
    	gbc.gridx = 11; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
    	panel_.add(pageSelector, gbc);
    	
    	gbc.gridx = 11; gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridheight = 1;
    	panel_.add(logoutButton, gbc);
    	
    	gbc.insets = new Insets(0,0,0,0);
    	gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.gridheight = 6;
    	panel_.add(assignmentScoller, gbc);
    	
//    	gbc.gridx = 5; gbc.gridy = 3; gbc.gridwidth = 4; gbc.gridheight = 6;
//    	panel_.add(bugScroller, gbc);
    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 4; gbc.gridy = 9; gbc.gridwidth = 1; gbc.gridheight = 1;
    	// panel_.add(submitBugButton, gbc);
//		this.uiController_ = uiController;
//		this.panel_ = new JPanel();
//		panel_.setName("DeveloperPanel");
//
//		JButton demoBackButton = new JButton("Logout");
//
//		demoBackButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
//				CardLayout layout = (CardLayout) viewHolder.getLayout();
//				layout.show(viewHolder, "OrdinaryPanel");
//			}
//		});
//
//		panel_.add(new JLabel("Developer"));
//		panel_.add(demoBackButton);
	}

	/**
	 * @param void
	 * @return
	 */
	public void updateAssignmentPopUp() {
		// TODO implement here
	}

	/**
	 * @param void
	 * @return
	 */
	public void reportFixPopUp() {
		// TODO implement here
	}

	public JPanel getPanel_() {
		return panel_;
	}

	public void setPanel_(JPanel panel_) {
		this.panel_ = panel_;
	}

	public UiController getUiController_() {
		return uiController_;
	}

	public void setUiController_(UiController uiController_) {
		this.uiController_ = uiController_;
	}

}