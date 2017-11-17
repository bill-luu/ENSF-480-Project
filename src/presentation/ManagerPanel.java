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

/**
 * 
 */
public class ManagerPanel {	
	
	/**
	* 
	*/
	private JPanel panel_;
	private SpringLayout layout;
	ArrayList<Bug> bugs;
	
	private JList<String> bugJList;

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
		bugListPanel.setLayout(new BoxLayout(bugListPanel, BoxLayout.Y_AXIS));
		
		
		/* remove this with the actual bug list*/
		bugs = new ArrayList<Bug>();
		bugs.add(new Bug());
		bugs.get(0).setBugId_(0);
		bugs.get(0).setBugTitle_("Bug0");
		bugs.get(0).setDescription_("This is Bug0");
		bugs.get(0).setProductId_(1);
		bugs.get(0).setState_(Bug.State.IN_PROGRESS);

		bugs.add(new Bug());
		bugs.get(1).setBugId_(1);
		bugs.get(1).setBugTitle_("Bug1");
		bugs.get(1).setDescription_("This is Bug1");
		bugs.get(1).setProductId_(1);
		bugs.get(1).setState_(Bug.State.IN_PROGRESS);
		
		bugs.add(new Bug());
		bugs.get(2).setBugId_(2);
		bugs.get(2).setBugTitle_("Bug2");
		bugs.get(2).setDescription_("This is Bug2");
		bugs.get(2).setProductId_(1);
		bugs.get(2).setState_(Bug.State.IN_PROGRESS);
		
		bugs.add(new Bug());
		bugs.get(3).setBugId_(3);
		bugs.get(3).setBugTitle_("Bug3");
		bugs.get(3).setDescription_("This is Bug3");
		bugs.get(3).setProductId_(1);
		bugs.get(3).setState_(Bug.State.IN_PROGRESS);
		
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
		
		
		
		
		
		
		
		
		
		
		
		
		panel_.add(new JLabel("Manager"));
		panel_.add(bugListPanel);
		panel_.add(demoBackButton);
		
		/*****CONSTRAINSTS*****/
		
		layout.putConstraint(SpringLayout.NORTH, bugListPanel, 10, SpringLayout.NORTH, panel_);		
		layout.putConstraint(SpringLayout.WEST, bugListPanel, 10, SpringLayout.WEST, panel_);		
		
	}
	
	public void createBugInfoPanel(int index)
	{
		Bug temp = bugs.get(index);
		SpringLayout layout = new SpringLayout();
		
		JPanel bugInfoPanel = new JPanel();
		
		JLabel BugIdLabel = new JLabel("ID: ");
		JLabel BugTitleLabel = new JLabel("Bug Title: ");
		JLabel BugDescriptionLabel = new JLabel("Description: ");
		
		
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