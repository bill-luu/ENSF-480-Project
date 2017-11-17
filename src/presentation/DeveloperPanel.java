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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Assignment;
import data.Bug;
import data.Bug.State;
import data.Manager;
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
	 * The JList display for products
	 */
	private JList<String> assignInfoList;

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
		JButton updateAssignmentButton = new JButton("Update");

		String[] pages = { "Assignments", "Bugs" };
		DefaultComboBoxModel<String> pageModel = new DefaultComboBoxModel<String>(pages);
		JComboBox<String> pageSelector = new JComboBox<String>(pageModel);
		pageSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pageSelector.getSelectedItem().equals("Bugs")) {
					// Switch to Ordinary panel
					JPanel viewHolder = (JPanel) (uiController_.getFrame().getContentPane().getComponent(0));
					CardLayout layout = (CardLayout) viewHolder.getLayout();
					layout.show(viewHolder, "OrdinaryPanel");
				}

			}

		});

		ArrayList<Assignment> assignments = uiController
				.BrowseAssignments(uiController_.getUserLoggedIn().getUserId_());

		DefaultListModel<String> assignmentModel = new DefaultListModel<String>();

		for (Assignment a : assignments)
			assignmentModel.addElement(a.getAssignmentId_() + "");

		assignList = new JList<String>(assignmentModel);

		JScrollPane assignmentScoller = new JScrollPane(assignList);
		assignmentScoller.setPreferredSize(new Dimension(200, 525));

		DefaultListModel<String> assignmentInfoModel = new DefaultListModel<String>();

		assignInfoList = new JList<String>(assignmentInfoModel);

		JScrollPane assignmentInfoScroller = new JScrollPane(assignInfoList);
		assignmentInfoScroller.setPreferredSize(new Dimension(200, 525));

		// Temporary components for testing
		JButton demoDevButton = new JButton("Test Dev Screen");
		JButton demoManButton = new JButton("Test Manager Screen");

		demoDevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get components
				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();

				// Create new DeveloperPanel if it doesn't exist
				if (!uiController.checkPanelExists("DeveloperPanel", viewHolder)) {
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
		assignList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				assignmentInfoModel.removeAllElements();
				String assign = assignList.getSelectedValue();
				String assigns[] = assign.split(" ");
				int assignID = Integer.parseInt(assigns[0]);
				ArrayList<Assignment> assignments = uiController
						.BrowseAssignments(uiController_.getUserLoggedIn().getUserId_());
				for (Assignment a : assignments) {
					if (a.getAssignmentId_() == assignID) {
						assignmentInfoModel.addElement("Assignment ID: " + a.getAssignmentId_());
						for (Manager b : uiController_.getSystem().getManagerList_()) {
							if (b.getUserId_() == a.getManagerId_()) {
								assignmentInfoModel.addElement("Manager: " + b.getUsername_());
							}
						}

						for (Bug b : uiController_.getSystem().getBugList_()) {
							if (b.getBugId_() == a.getBugId_()) {
								for (Product p : uiController_.getSystem().getProductList_()) {
									if (b.getProductId_() == p.getProductId_()) {
										assignmentInfoModel.addElement("Product: " + p.getProductName_());
										assignmentInfoModel.addElement("Bug ID: " + a.getBugId_());
									}
								}
								assignmentInfoModel.addElement("Bug Description: " + b.getDescription_());
								assignmentInfoModel.addElement("Bug State: " + b.getState_().toString());
							}
						}
					}
				}
			}
		});

		// Open login popup when clicking login button
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createLoginPopUp();
			}

			private void createLoginPopUp() {
				JPanel viewHolder = (JPanel) (uiController_.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();
				layout.show(viewHolder, "OrdinaryPanel");

			}
		});

		// Open submit bug popup when clicking submit bug button
		updateAssignmentButton.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent arg0) {
				updateAssignmentPopUp();
			}
		});

		gbc.weighty = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		panel_.add(title, gbc);

		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		panel_.add(title2, gbc);

		gbc.insets = new Insets(0, 75, 0, 0);
		gbc.weightx = 1;
		gbc.gridx = 11;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel_.add(pageSelector, gbc);

		gbc.gridx = 11;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel_.add(logoutButton, gbc);

		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 6;
		panel_.add(assignmentScoller, gbc);

		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 6;
		panel_.add(assignmentInfoScroller, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel_.add(updateAssignmentButton, gbc);
	}

	/**
	 * @param void
	 * @return
	 */
	public void updateAssignmentPopUp() {
		JPanel updatePanel = new JPanel();

		JTextField title = new JTextField("New Message");

		JTextArea description = new JTextArea();
		description.setPreferredSize(new Dimension(500, 250));
		description.setBorder(BorderFactory.createEtchedBorder());
		description.setLineWrap(true);

		// Fill a dropdown menu with all the assignment
		DefaultComboBoxModel<String> assignModel = new DefaultComboBoxModel<String>();
		DefaultListModel<String> model = (DefaultListModel<String>) assignList.getModel();

		// // Fill combobox with only assignment names
		for (int i = 0; i < model.size(); i++)
			assignModel.addElement("Assignment: " + model.getElementAt(i));
		JComboBox<String> assignmentComboBox = new JComboBox<String>(assignModel);

		// Fill a dropdown menu with all the states
		State[] states = { State.PENDING_APPROVAL, State.AWAITING_ASSIGNMENT, State.FIXED, State.IN_PROGRESS,
				State.REJECTED };
		DefaultComboBoxModel<State> assignStateModel = new DefaultComboBoxModel<State>(states);
		assignStateModel.setSelectedItem(State.PENDING_APPROVAL);
		JComboBox<State> stateComboBox = new JComboBox<State>(assignStateModel);

		DefaultListModel<String> assignmentDescriptionModel = new DefaultListModel<String>();
		JList assignDescriptionList = new JList<String>(assignmentDescriptionModel);

		JScrollPane assignmentDesciptionScroller = new JScrollPane(assignDescriptionList);
		assignmentDesciptionScroller.setPreferredSize(new Dimension(500, 250));

		assignmentComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				assignmentDescriptionModel.clear();
				String s = (String) assignmentComboBox.getSelectedItem();
				int id = Integer.parseInt(s.split(" ")[1]);
				// Fill with all assignment info

				for (Assignment a : getUiController_().getSystem().getAssignmentList_()) {
					if (a.getAssignmentId_() == id) {
						for (Bug b : getUiController_().getSystem().getBugList_()) {
							if (b.getBugId_() == a.getBugId_())
								assignStateModel.setSelectedItem(b.getState_());
						}
						for (String message : a.getUpdateMessages_()) {
							assignmentDescriptionModel.addElement(message);
						}
					}
				}
			}

		});

		Box vBox = Box.createVerticalBox();
		vBox.add(assignmentComboBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(stateComboBox);
		vBox.add(title);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(assignmentDesciptionScroller);
		vBox.add(Box.createVerticalStrut(20));

		updatePanel.add(vBox);

		Object options[] = { "Submit Update", "Cancel" };
		int selection = JOptionPane.showOptionDialog(null, updatePanel, "Update Assignment",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selection == JOptionPane.OK_OPTION) {
			Assignment a = new Assignment();

			String s = (String) assignmentComboBox.getSelectedItem();
			a.setAssignmentId_(Integer.parseInt(s.split(" ")[1]));
			for (Assignment assign : uiController_.getSystem().getAssignmentList_()) {
				if (assign.getAssignmentId_() == a.getAssignmentId_()) {
					a.setBugId_(assign.getBugId_());
					ArrayList<String> updates = new ArrayList<String>();
					updates = assign.getUpdateMessages_();
					updates.add(title.getText());
					a.setUpdateMessages_(updates);
					a.setDeveloperId_(assign.getDeveloperId_());
					a.setManagerId_(assign.getManagerId_());
					uiController_.updateAssignment(a);
				}
			}
		}

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