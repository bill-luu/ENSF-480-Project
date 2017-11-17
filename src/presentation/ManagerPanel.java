package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Assignment;
import data.Bug;
import data.Bug.State;
import presentation.OrdinaryPanel.HintTextField;
import data.Developer;
import data.Employee;
import data.Manager;
import data.Product;

/**
 * 
 */
public class ManagerPanel {	
	
	/**
	* 
	*/
	private JPanel panel_;
	private SpringLayout layout;
	
	private JList<String> bugJList;
	private JList<String> productJList;
	private JList<String> developerJList;
	private JList<String> assignmentJList;
	private JScrollPane bugScrollPane;
	private String bugList[];
	private String assignmentList[];
	private String developerList[];
	private String productList[];
	/**
	 * 
	 */
	private UiController uiController_;

	/**
	 * 
	 * @param uiController
	 */
	public ManagerPanel(UiController uiController) {
		this.uiController_ = uiController;
		panel_ = new JPanel();
		panel_.setName("ManagerPanel");
		layout = new SpringLayout();
		panel_.setLayout(layout);

		
		/*****BUG LIST PANEL*****/
		JPanel bugListPanel = new JPanel();
		bugListPanel.setPreferredSize(new Dimension(170, 650));
		JLabel bugListLabel = new JLabel("Bug List");
		bugListPanel.setLayout(new BoxLayout(bugListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Bug> bugs = this.uiController_.BrowseBugs();
		
		int count1 = 0;
    	for(int i = 0; i < bugs.size(); i++)
		{
			if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
			{
				count1++;
			}
		}

    	bugList = new String[count1];
    	int x = 0;
    	for(int i = 0; i < bugs.size(); i++)
		{
			if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
			{
				String temp = "";
				temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
				temp = temp.concat("    ");
				temp = temp.concat(bugs.get(i).getBugTitle_());
				temp = temp.concat("    ");
				temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
				bugList[x] = temp;
				x++;
			}
		}
		bugJList = new JList<String>();
		bugJList.setListData(bugList);
		
		bugScrollPane = new JScrollPane(bugJList);	
		
		bugJList.addListSelectionListener(new ListSelectionListener()
		{	
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if (bugJList.isSelectionEmpty())
				{
					return;
				}
				String s = (String) bugJList.getSelectedValue();
				StringTokenizer n = new StringTokenizer(s);
				String bugId = n.nextToken();
				
				int index = -1;
				
				for(int i = 0; i < bugs.size(); i++)
				{
					if (String.valueOf(bugs.get(i).getBugId_()).equals(bugId))
					{
						index = i;
						break;
					}
				}
				ManagerPanel.this.createBugInfoPanel(index);
			}
		});
				
		bugListPanel.add(bugListLabel);
		bugListPanel.add(bugScrollPane);
		
		
		/*****PRODUCT LIST PANEL*****/
		JPanel productListPanel = new JPanel();
		productListPanel.setPreferredSize(new Dimension(170, 650));

		JLabel productListLabel = new JLabel("Product List");
		productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Product> products = this.uiController_.BrowseProducts();
		
		productList = new String[products.size()];
		
		for(int i = 0; i < products.size(); i++)
		{
			String temp = "";
			temp = temp.concat(String.valueOf(products.get(i).getProductId_()));
			temp = temp.concat("    ");
			temp = temp.concat(products.get(i).getProductName_());
			temp = temp.concat("    ");
			temp = temp.concat(String.valueOf(products.get(i).getProductDescription()));
			productList[i] = temp;
		}
		productJList = new JList<String>(productList);
		
		JScrollPane productScrollPane = new JScrollPane(productJList);	
		
		productJList.addListSelectionListener(new ListSelectionListener()
		{	
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if (productJList.isSelectionEmpty())
				{
					return;
				}

		    	bugList = new String[bugs.size()];
				String product = productJList.getSelectedValue();
				String products[] = product.split(" ");
				int productID = Integer.parseInt(products[0]);
				
		    	// Search for corresponding bugs
				ArrayList<Bug> bugs = uiController.BrowseBugs();
				int count = 0;
				for(int i = 0; i < bugs.size(); i++)
		    	{
		    		if(bugs.get(i).getProductId_() == productID && !(bugs.get(i).getState_().equals(Bug.State.ARCHIVED)))
		    			count++;
		    	}
				bugList = new String[count];
				int x = 0;
				for(int i = 0; i < bugs.size(); i++)
		    	{
		    		if(bugs.get(i).getProductId_() == productID)
		    		{
		    			bugList[x] = (bugs.get(i).getBugId_() + " " + bugs.get(i).getBugTitle_() + " " + bugs.get(i).getState_());
		    			x++;
		    		}
		    	}
		    	bugJList.setListData(bugList);
			}
		});
				
		productListPanel.add(productListLabel);
		productListPanel.add(productScrollPane);
		
		
		/*****DEVELOPER LIST PANEL*****/
		JPanel developerListPanel = new JPanel();
		developerListPanel.setPreferredSize(new Dimension(150, 650));

		JLabel developerListLabel = new JLabel("Developer List");
		developerListPanel.setLayout(new BoxLayout(developerListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Developer> developers = this.uiController_.BrowseDevelopers();
		
		developerList = new String[developers.size()];
		
		for(int i = 0; i < developers.size(); i++)
		{
			String temp = "";
			temp = temp.concat(String.valueOf(developers.get(i).getUserId_()));
			temp = temp.concat("    ");
			temp = temp.concat(developers.get(i).getFirstName_());
			temp = temp.concat("    ");
			temp = temp.concat(String.valueOf(developers.get(i).getLastName_()));
			developerList[i] = temp;
		}
		developerJList = new JList<String>(developerList);
		
		JScrollPane developerScrollPane = new JScrollPane(developerJList);	
		
		developerJList.addListSelectionListener(new ListSelectionListener()
		{	
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if (developerJList.isSelectionEmpty())
				{
					return;
				}

				String developer = developerJList.getSelectedValue();
				String developers[] = developer.split(" ");
				int developerId = Integer.parseInt(developers[0]);
				
		    	// Search for corresponding bugs
				ArrayList<Assignment> assignments = uiController.BrowseAssignments();
				int count = 0;
		    	for(int i = 0; i < assignments.size(); i++)
		    	{
		    		if(assignments.get(i).getDeveloperId_()== developerId)
		    			count++;
		    	}
		    	int x = 0;
		    	assignmentList = new String[count];
		    	for(int i = 0; i < assignments.size(); i++)
		    	{
		    		if(assignments.get(i).getDeveloperId_()== developerId)
		    		{
		    			assignmentList[x] = (String.valueOf(assignments.get(i).getAssignmentId_()));
		    			x++;
		    		}
		    	}
		    	assignmentJList.setListData(assignmentList);
			}
		});
				
		developerListPanel.add(developerListLabel);
		developerListPanel.add(developerScrollPane);
		
		
		/*****ASSIGNMENT LIST PANEL*****/
		JPanel assignmentListPanel = new JPanel();
		assignmentListPanel.setPreferredSize(new Dimension(150, 650));

		JLabel assignmentListLabel = new JLabel("Assignment List");
		assignmentListPanel.setLayout(new BoxLayout(assignmentListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Assignment> assignment = this.uiController_.BrowseAssignments();
		
		assignmentList = new String[assignment.size()];
		
		for(int i = 0; i < assignment.size(); i++)
		{
			String temp = "";
			temp = temp.concat(String.valueOf(assignment.get(i).getAssignmentId_()));
			assignmentList[i] = temp;
		}
		assignmentJList = new JList<String>();
		assignmentJList.setListData(assignmentList);
		
		JScrollPane assignmentScrollPane = new JScrollPane(assignmentJList);	
		assignmentJList.addListSelectionListener(new ListSelectionListener()
		{	
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if (assignmentJList.isSelectionEmpty())
				{
					return;
				}
				String s = (String) assignmentJList.getSelectedValue();
				StringTokenizer n = new StringTokenizer(s);
				String assignmentId = n.nextToken();
				
				int index = -1;
				
				for(int i = 0; i < assignment.size(); i++)
				{
					if (String.valueOf(assignment.get(i).getAssignmentId_()).equals(assignmentId))
					{
						index = i;
						break;
					}
				}
				ManagerPanel.this.createAssignmentInfoPanel(index);
			}
		});
				
		assignmentListPanel.add(assignmentListLabel);
		assignmentListPanel.add(assignmentScrollPane);
		
		/*****ADD BUG BUTTON*****/
		JButton addBugButton = new JButton("Add Bug");
		addBugButton.setPreferredSize(new Dimension(50, 20));
		addBugButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerPanel.this.createBugPopUp();
			}
		});
		bugListPanel.add(addBugButton);

		/*****ADD DEVELOPER BUTTON*****/
		JButton addDevButton = new JButton("Add Developer");
		addDevButton.setPreferredSize(new Dimension(50, 20));

		addDevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerPanel.this.createDeveloperPopUp();
			}
		});
		developerListPanel.add(addDevButton);
		
		/*****ADD ASSIGNMENT BUTTON*****/
		JButton addAssButton = new JButton("Add Assignment");
		addAssButton.setPreferredSize(new Dimension(120, 20));

		addAssButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerPanel.this.createAssignmentPopUp();
			}
		});
		assignmentListPanel.add(addAssButton);
		
		/*****ADD PRODUCT BUTTON*****/
		JButton addProButton = new JButton("Add Product");
		addProButton.setPreferredSize(new Dimension(50, 20));

		addProButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerPanel.this.createProductPopUp();
			}
		});
		productListPanel.add(addProButton);
		
		
		
		panel_.add(bugListPanel);
		panel_.add(productListPanel);
		panel_.add(developerListPanel);
		panel_.add(assignmentListPanel);

		/*****CONSTRAINSTS*****/
		//BUG LIST PANEL
		layout.putConstraint(SpringLayout.NORTH, bugListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, bugListPanel, 10, SpringLayout.WEST, panel_);		

		//PRODUCT LIST PANEL
		layout.putConstraint(SpringLayout.NORTH, productListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, productListPanel, 10, SpringLayout.EAST, bugListPanel);		

		//DEVELOPER LIST PANEL
		layout.putConstraint(SpringLayout.NORTH, developerListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, developerListPanel, 10, SpringLayout.EAST, productListPanel);		

		//ASSIGNMENT LIST PANEL
		layout.putConstraint(SpringLayout.NORTH, assignmentListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, assignmentListPanel, 10, SpringLayout.EAST, developerListPanel);		

	}
	
	public void createBugInfoPanel(int index)
	{
		JPanel bugPanel = new JPanel();

		ArrayList<Bug> bugs = uiController_.BrowseBugs();
		
		JLabel bugLabel = new JLabel("<HTML><b>Bug");
		JLabel bugTitle = new JLabel("Bug Title: " + bugs.get(index).getBugTitle_());
    	JLabel bugProduct = new JLabel("Product:" + bugs.get(index).getProductId_());
    	JLabel bugDescription = new JLabel("Description: " + bugs.get(index).getDescription_());
    	JLabel bugState = new JLabel("State: " + bugs.get(index).getState_().toString());
    	
    	ArrayList<Product> productlist = uiController_.BrowseProducts();
    	for(Product p : productlist){
    		if(p.getProductId_() == bugs.get(index).getProductId_()){
    	    	JLabel productTitle = new JLabel("<HTML><b>Product");
    	    	JLabel productID = new JLabel("Product ID: " + p.getProductId_());
    	    	JLabel productName = new JLabel("Name: " + p.getProductName_());
    	    	JLabel productDescription = new JLabel("Description: " + p.getProductDescription());
    	    	
    	    	Box vBox = Box.createVerticalBox();
    	    	vBox.add(bugLabel);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(bugTitle);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(bugProduct);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(bugDescription);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(bugState);
    	    	vBox.add(Box.createVerticalStrut(30));
    	    	vBox.add(productTitle);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(productID);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(productName);
    	    	vBox.add(Box.createVerticalStrut(15));
    	    	vBox.add(productDescription);
    	    	
    	    	if(bugs.get(index).getState_().equals(Bug.State.FIXED))
    	    	{
    	    		JButton archieve = new JButton("Archieve");
    	    		vBox.add(archieve);
    	    		archieve.addActionListener(new ActionListener() {
    	    			@Override
    	    			public void actionPerformed(ActionEvent arg0) {
    	    				Bug temp1 = bugs.get(index);
    	    				temp1.setState_(State.ARCHIVED);
    	    				ManagerPanel.this.uiController_.UpdateBug(temp1);
    	    		    	bugState.setText(bugs.get(index).getState_().toString());
    	    		    	vBox.remove(archieve);
    	    		    	
    	    		    	int count1 = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
    	    						count1++;
    	    					}
    	    				}

    	    		    	bugList = new String[count1];
    	    		    	int x = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
	    	    					String temp = "";
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(bugs.get(i).getBugTitle_());
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
	    	    					bugList[x] = temp;
	    	    					x++;
    	    					}
    	    				}
    	    		    	bugJList.setListData(bugList);
    	    		    	ArrayList<Assignment> asL = ManagerPanel.this.uiController_.BrowseAssignments();
    	    		    	Assignment temp = new Assignment();
    	    		    	for(int i = 0; i < asL.size(); i++)
    	    		    	{
    	    		    		if(asL.get(i).getBugId_() == bugs.get(index).getBugId_())
    	    		    		{
    	    		    			System.out.println("here");
    	    		    			temp.setAssignmentId_(asL.get(i).getAssignmentId_());
    	    		    			temp.setBugId_(asL.get(i).getBugId_());
    	    		    			temp.setDeveloperId_(asL.get(i).getDeveloperId_());
    	    		    			temp.setManagerId_(asL.get(i).getManagerId_());
    	    		    			temp.setUpdateMessages_(new ArrayList<String>(asL.get(i).getUpdateMessages_()));
    	    		    		}
    	    		    	}
    	    		    	System.out.println(temp.getBugId_());
    	    		    	String report = ManagerPanel.this.uiController_.GenerateReport(temp);
    	    		    	JOptionPane.showMessageDialog(uiController_.getFrame(), report, "Report", JOptionPane.INFORMATION_MESSAGE);
    	    			}
    	    		});

    	    	}

    	    	
    	    	if(bugs.get(index).getState_().equals(Bug.State.PENDING_APPROVAL))
    	    	{
    	    		JButton approve = new JButton("Approve");
    	    		JButton reject = new JButton("Reject");
    	    		vBox.add(approve);
    	    		vBox.add(reject);
    	    		approve.addActionListener(new ActionListener() {
    	    			@Override
    	    			public void actionPerformed(ActionEvent arg0) {
    	    				Bug temp1 = bugs.get(index);
    	    				temp1.setState_(State.AWAITING_ASSIGNMENT);
    	    				ManagerPanel.this.uiController_.UpdateBug(temp1);
    	    		    	bugState.setText(bugs.get(index).getState_().toString());
    	    		    	vBox.remove(approve);
    	    		    	vBox.remove(reject);
    	    		    	
    	    		    	int count1 = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
    	    						count1++;
    	    					}
    	    				}

    	    		    	bugList = new String[count1];
    	    		    	int x = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
	    	    					String temp = "";
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(bugs.get(i).getBugTitle_());
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
	    	    					bugList[x] = temp;
	    	    					x++;
    	    					}
    	    				}
    	    		    	bugJList.setListData(bugList);
    	    			}
    	    		});
    	    		reject.addActionListener(new ActionListener() {
    	    			@Override
    	    			public void actionPerformed(ActionEvent arg0) {
    	    				Bug temp1 = bugs.get(index);
    	    				temp1.setState_(State.REJECTED);
    	    				ManagerPanel.this.uiController_.UpdateBug(temp1);
    	    				bugState.setText(bugs.get(index).getState_().toString());
    	    		    	vBox.remove(approve);
    	    		    	vBox.remove(reject);
    	    		    	int count1 = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
    	    						count1++;
    	    					}
    	    				}

    	    		    	bugList = new String[count1];
    	    		    	int x = 0;
    	    		    	for(int i = 0; i < bugs.size(); i++)
    	    				{
    	    					if(!bugs.get(i).getState_().equals(Bug.State.ARCHIVED))
    	    					{
	    	    					String temp = "";
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(bugs.get(i).getBugTitle_());
	    	    					temp = temp.concat("    ");
	    	    					temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
	    	    					bugList[x] = temp;
	    	    					x++;
    	    					}
    	    				}
    	    		    	bugJList.setListData(bugList);
    	    			}
    	    		});
    	    		
    	    	}
    	    	bugPanel.add(vBox);
    	    	Object options[] = {"OK"};
    	    	JOptionPane.showOptionDialog(null, bugPanel, "Inspect Bug", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    	    	break;
    		}
    	}
	}
	
	public void createAssignmentInfoPanel(int index)
	{
		JPanel assignmentPanel = new JPanel();
    	Box vBox = Box.createVerticalBox();

		ArrayList<Assignment> assignments = uiController_.BrowseAssignments();
	
		JLabel assignmentId = new JLabel("Assignment ID: " + String.valueOf(assignments.get(index).getAssignmentId_()));
		vBox.add(assignmentId);
    	JLabel developerId = new JLabel("Developer ID: " + String.valueOf(assignments.get(index).getDeveloperId_()));
    	vBox.add(developerId);
    	for (Developer d : uiController_.BrowseDevelopers())
		{
			if (d.getUserId_() == assignments.get(index).getDeveloperId_()) 
			{
				JLabel developerName = new JLabel("Developer Name: " + d.getFirstName_() + " " + d.getLastName_());
				vBox.add(developerName);
			}
		}
    	
    	JLabel managerId = new JLabel("Manager ID: " + String.valueOf(assignments.get(index).getManagerId_()));
		vBox.add(managerId);

    	for (Manager b : uiController_.getSystem().getManagerList_())
		{
			if (b.getUserId_() == assignments.get(index).getManagerId_()) 
			{
				JLabel managerName = new JLabel("Manager Name: " + b.getFirstName_() + " " + b.getLastName_());
				vBox.add(managerName);
			}
		}

		for (Bug b : uiController_.getSystem().getBugList_()) 
		{
			if (b.getBugId_() == assignments.get(index).getBugId_()) 
			{	
				int i = 0;
				for (Product p : uiController_.getSystem().getProductList_()) 
				{
					if (b.getProductId_() == p.getProductId_()) 
					{
						JLabel productName = new JLabel("Product" + String.valueOf(i) + ": " + p.getProductName_());
						vBox.add(productName);
					}
					i++;
				}
				JLabel bugId = new JLabel("Bug ID: " + b.getBugId_());
				vBox.add(bugId);
				JLabel bugDescription = new JLabel("Bug Description: " + b.getDescription_());
				vBox.add(bugDescription);
			}
		}
    	
    	assignmentPanel.add(vBox);
    	Object options[] = {"OK"};
    	JOptionPane.showOptionDialog(null, assignmentPanel, "Inspect Assignment", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}

	/**
	 * @param void
	 * @return
	 */
	public void createProductPopUp() 
	{
		JPanel productPanel = new JPanel();
    	
    	HintTextFieldCopy name = new HintTextFieldCopy("Enter a product name here");
    	
    	JTextArea description = new JTextArea();
    	description.setPreferredSize(new Dimension(500, 250));
    	description.setBorder(BorderFactory.createEtchedBorder());
    	description.setLineWrap(true);
    	
    	// Fill a dropdown menu with all the products    	
    	Box vBox = Box.createVerticalBox();
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(name);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(description);
    	vBox.add(Box.createVerticalStrut(20));
    	
    	productPanel.add(vBox);
    	
    	Object options[] = {"Create", "Cancel"};
    	int selection = JOptionPane.showOptionDialog(null, productPanel, 
        		"New Product", 
        		JOptionPane.OK_CANCEL_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
    	
    	if(selection == JOptionPane.OK_OPTION)
    	{
    		Product p = new Product(); 
    		p.setProductId_(ManagerPanel.this.uiController_.BrowseProducts().size() + 1);
    		p.setProductName_(name.getText());
    		p.setProductDescription(description.getText());
    		
    		uiController_.AddProduct(p);
    		
    		ArrayList<Product> products = uiController_.BrowseProducts();

    		productList = new String[products.size()];
    		for(int i = 0; i < products.size(); i++)
    		{
    			String temp = "";
    			temp = temp.concat(String.valueOf(products.get(i).getProductId_()));
    			temp = temp.concat("    ");
    			temp = temp.concat(products.get(i).getProductName_());
    			temp = temp.concat("    ");
    			temp = temp.concat(String.valueOf(products.get(i).getProductDescription()));
    			productList[i] = temp;
    		}
	    	productJList.setListData(productList);
    	}
	}

	public void createDeveloperPopUp()
	{
		JPanel devPanel = new JPanel();
    	devPanel.setPreferredSize(new Dimension(250, 400));
    	HintTextFieldCopy firstName = new HintTextFieldCopy("Enter first name here");
    	HintTextFieldCopy lastName = new HintTextFieldCopy("Enter last name here");
    	HintTextFieldCopy userName = new HintTextFieldCopy("Enter username here");
    	HintTextFieldCopy password = new HintTextFieldCopy("Enter password here");
        firstName.setPreferredSize(new Dimension(150, 25));
        lastName.setPreferredSize(new Dimension(150, 25));
        userName.setPreferredSize(new Dimension(150, 25));
        password.setPreferredSize(new Dimension(150, 25));

    	
    	Box vBox = Box.createVerticalBox();
    	vBox.add(firstName);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(lastName);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(userName);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(password);
    	devPanel.add(vBox);
    	
    	Object options[] = {"Create", "Cancel"};
    	int selection = JOptionPane.showOptionDialog(null, devPanel, 
        		"New Developer", 
        		JOptionPane.OK_CANCEL_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
    	
    	if(selection == JOptionPane.OK_OPTION)
    	{
    		ArrayList<String> loginInfo = ManagerPanel.this.uiController_.getSystem().getLoginInfoList_();
    		
    		for(int i = 0; i < loginInfo.size(); i++)
    		{
    			String uEntered = "dev-<" + userName.getText().split(" ")[0];
    			String username = loginInfo.get(i).split(":")[0];
    			if(uEntered.equals(username))
    			{
    				JOptionPane.showMessageDialog(null, "Sorry, this username already exists.", "Error Message", JOptionPane.PLAIN_MESSAGE);
    				return;
    			}
    		}
    		
    		Developer d = new Developer(); 
    		d.setUserId_(ManagerPanel.this.uiController_.BrowseDevelopers().size() + 1);
    		d.setFirstName_(firstName.getText().split(" ")[0]);
    		d.setLastName_(lastName.getText().split(" ")[0]);
    		d.setUsername_(userName.getText());
    		
    		String lInfo = "dev-<" + userName.getText() + ":" + password.getText() + ">";
    		
    		ManagerPanel.this.uiController_.getSystem().addToDeveloperList(d, lInfo);
    		
    		ArrayList<Developer> developers = this.uiController_.BrowseDevelopers();
    		
    		developerList = new String[developers.size()];

    		for(int i = 0; i < developers.size(); i++)
    		{
    			String temp = "";
    			temp = temp.concat(String.valueOf(developers.get(i).getUserId_()));
    			temp = temp.concat("    ");
    			temp = temp.concat(developers.get(i).getFirstName_());
    			temp = temp.concat("    ");
    			temp = temp.concat(String.valueOf(developers.get(i).getLastName_()));
    			developerList[i] = temp;
    		}
    		developerJList.setListData(developerList);
    	}

	}
	public void createBugPopUp() 
	{
		JPanel bugPanel = new JPanel();
    	
    	HintTextFieldCopy title = new HintTextFieldCopy("Enter a bug title here");
    	
    	JTextArea description = new JTextArea();
    	description.setPreferredSize(new Dimension(500, 250));
    	description.setBorder(BorderFactory.createEtchedBorder());
    	description.setLineWrap(true);
    	
    	// Fill a dropdown menu with all the products
    	DefaultComboBoxModel<String> productModel = new DefaultComboBoxModel<String>();
    	ArrayList<Product> proL = ManagerPanel.this.uiController_.BrowseProducts();
    	for(int i = 0; i < proL.size(); i++)
    		productModel.addElement(proL.get(i).getProductId_()+" "+ proL.get(i).getProductName_()); // Fill combobox with only product names
    	JComboBox<String> products2 = new JComboBox<String>(productModel);
    	
    	Box vBox = Box.createVerticalBox();
    	vBox.add(products2);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(title);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(description);
    	vBox.add(Box.createVerticalStrut(20));
    	
    	bugPanel.add(vBox);
    	
    	Object options[] = {"Submit Bug", "Cancel"};
    	int selection = JOptionPane.showOptionDialog(null, bugPanel, 
        		"New Bug", 
        		JOptionPane.OK_CANCEL_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
    	
    	if(selection == JOptionPane.OK_OPTION)
    	{
    		Bug b = new Bug(); // Populate with entered bug values
    		b.setBugId_(ManagerPanel.this.uiController_.BrowseBugs().size() + 1);
    		b.setBugTitle_(title.getText());
    		b.setProductId_(Integer.parseInt(productModel.getElementAt(products2.getSelectedIndex()).split(" ")[0]));
    		b.setState_(Bug.State.PENDING_APPROVAL);
    		b.setDescription_(description.getText());
    		uiController_.SubmitBug(b);
    		ArrayList<Bug> bugs = uiController_.BrowseBugs();

    		bugList = new String[bugs.size()];
	    	for(int i = 0; i < bugs.size(); i++)
			{
				String temp = "";
				temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
				temp = temp.concat("    ");
				temp = temp.concat(bugs.get(i).getBugTitle_());
				temp = temp.concat("    ");
				temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
				bugList[i] = temp;
			}
	    	bugJList.setListData(bugList);
    	}
	}
	
	public class HintTextFieldCopy extends JTextField {

		private static final long serialVersionUID = 1L;

		/**
		 * The hint text that appears in the component
		 */
		private final String hint_;

		/**
		 * Constructor for the component
		 * 
		 * @param hint
		 *            The hint text which appears in the component
		 */
		public HintTextFieldCopy(String hint) {
			hint_ = hint;
		}

		/**
		 * Overwritten method that paints the hint text on the component if it
		 * is empty
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (getText().length() == 0) {
				int h = getHeight();
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				Insets ins = getInsets();
				FontMetrics fm = g.getFontMetrics();
				int c0 = getBackground().getRGB();
				int c1 = getForeground().getRGB();
				int m = 0xfefefefe;
				int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
				g.setColor(new Color(c2, true));
				g.drawString(hint_, ins.left, h / 2 + fm.getAscent() / 2 - 2);
			}
		}
	}
	
	/**
	 * @param void
	 * @return
	 */
	public void createAssignmentPopUp() 
	{
		JPanel assignmentPanel = new JPanel();
    	
    	// Fill a dropdown menu with all the products    	
    	DefaultComboBoxModel<String> bugsBox = new DefaultComboBoxModel<String>();
    	ArrayList<Bug> bugL = ManagerPanel.this.uiController_.BrowseBugs();
    	for(int i = 0; i < bugL.size(); i++)
    		bugsBox.addElement(bugL.get(i).getBugId_() +" " + bugL.get(i).getBugTitle_()); // Fill combobox with only product names
    	JComboBox<String> bugs = new JComboBox<String>(bugsBox);
    	
    	DefaultComboBoxModel<String> devBox = new DefaultComboBoxModel<String>();
    	ArrayList<Developer> devL = ManagerPanel.this.uiController_.BrowseDevelopers();
    	for(int i = 0; i < devL.size(); i++)
    		devBox.addElement(devL.get(i).getUserId_() +" "+ devL.get(i).getFirstName_()); // Fill combobox with only product names
    	JComboBox<String> devs = new JComboBox<String>(devBox);

    	Box vBox = Box.createVerticalBox();
    	vBox.add(bugs);
    	vBox.add(Box.createVerticalStrut(20));
    	vBox.add(devs);
    	
    	assignmentPanel.add(vBox);
    	
    	Object options[] = {"Create", "Cancel"};
    	int selection = JOptionPane.showOptionDialog(null, assignmentPanel, 
        		"New Assignment", 
        		JOptionPane.OK_CANCEL_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
    	
    	if(selection == JOptionPane.OK_OPTION)
    	{
    		Assignment a = new Assignment(); 
    		a.setAssignmentId_(ManagerPanel.this.uiController_.BrowseAssignments().size() + 1);
    		a.setBugId_(Integer.parseInt(bugsBox.getElementAt(bugs.getSelectedIndex()).split(" ")[0]));
    		a.setDeveloperId_(Integer.parseInt(devBox.getElementAt(devs.getSelectedIndex()).split(" ")[0]));
    		a.setManagerId_(ManagerPanel.this.uiController_.getUserLoggedIn().getUserId_());
    		a.setUpdateMessages_(new ArrayList<String>());
    		uiController_.AddAssignment(a);
    		ArrayList<Assignment> assignments = uiController_.BrowseAssignments();

    		assignmentList = new String[assignments.size()];
	    	for(int i = 0; i < assignments.size(); i++)
			{
				String temp = "";
				temp = temp.concat(String.valueOf(assignments.get(i).getAssignmentId_()));
				assignmentList[i] = temp;
			}
	    	assignmentJList.setListData(assignmentList);
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