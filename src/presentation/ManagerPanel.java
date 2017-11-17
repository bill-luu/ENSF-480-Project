package presentation;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Bug;
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

		JButton demoBackButton = new JButton("Logout");

		demoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();
				layout.show(viewHolder, "OrdinaryPanel");
			}
		});

		
		/*****BUG LIST PANEL*****/
		JPanel bugListPanel = new JPanel();
		JLabel bugListLabel = new JLabel("Bug List");
		JButton addBugButton = new JButton("Add Bug");
		bugListPanel.setLayout(new BoxLayout(bugListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Bug> bugs = this.uiController_.BrowseBugs();
		
		DefaultListModel<String> bugList = new DefaultListModel<String>();
		
		for(int i = 0; i < bugs.size(); i++)
		{
			String temp = "";
			temp = temp.concat(String.valueOf(bugs.get(i).getBugId_()));
			temp = temp.concat("    ");
			temp = temp.concat(bugs.get(i).getBugTitle_());
			temp = temp.concat("    ");
			temp = temp.concat(String.valueOf(bugs.get(i).getState_()));
			bugList.addElement(temp);
		}
		bugJList = new JList<String>(bugList);
		
		JScrollPane bugScrollPane = new JScrollPane(bugJList);	
		bugScrollPane.setPreferredSize(new Dimension(150, 600));
		
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
		JLabel productListLabel = new JLabel("Product List");
		productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
		
		ArrayList<Product> products = this.uiController_.BrowseProducts();
		
		DefaultListModel<String> productList = new DefaultListModel<String>();
		
		for(int i = 0; i < products.size(); i++)
		{
			String temp = "";
			temp = temp.concat(String.valueOf(products.get(i).getProductId_()));
			temp = temp.concat("    ");
			temp = temp.concat(products.get(i).getProductName_());
			temp = temp.concat("    ");
			temp = temp.concat(String.valueOf(products.get(i).getProductDescription()));
			bugList.addElement(temp);
		}
		productJList = new JList<String>(productList);
		
		JScrollPane productScrollPane = new JScrollPane(productJList);	
		productScrollPane.setPreferredSize(new Dimension(150, 600));
		
		productJList.addListSelectionListener(new ListSelectionListener()
		{	
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if (productJList.isSelectionEmpty())
				{
					return;
				}
				String s = (String) productJList.getSelectedValue();
				StringTokenizer n = new StringTokenizer(s);
				String productId = n.nextToken();
				int index = -1;
				
				for(int i = 0; i < products.size(); i++)
				{
					if (String.valueOf(products.get(i).getProductId_()).equals(productId))
					{
						index = i;
						break;
					}
				}
				ManagerPanel.this.createProductInfoPanel(index);
			}
		});
				
		bugListPanel.add(productListLabel);
		bugListPanel.add(productScrollPane);
		
		
		
		
		
		
		
		
		
		panel_.add(new JLabel("Manager"));
		panel_.add(bugListPanel);
		panel_.add(demoBackButton);
		panel_.add(productListPanel);
		
		/*****CONSTRAINSTS*****/
		layout.putConstraint(SpringLayout.NORTH, bugListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, bugListPanel, 10, SpringLayout.WEST, panel_);		
		
	}
	
	public void createBugInfoPanel(int index)
	{
			
	}

	public void createProductInfoPanel(int index)
	{
			
	}

	/**
	 * @param void
	 * @return
	 */
	public void createProductPopUp() {
		// TODO implement here
	}

	/**
	 * @param void
	 * @return
	 */
	public void createDeveloperPopUp() {
		// TODO implement here
	}

	/**
	 * @param void
	 * @return
	 */
	public void createAssignmentPopUp() {
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