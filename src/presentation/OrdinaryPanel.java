package presentation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.SystemController;
import data.Developer;
import data.Employee;
import data.Manager;

/**
 * 
 */
public class OrdinaryPanel {

	/**
     * 
     */
    private JPanel panel_;

    /**
     * 
     */
    private UiController uiController_;
    
    /**
     * Constructor for the Ordinary Panel
     * @param uiController The UIController objet that this panel will be added to.
     */
    public OrdinaryPanel(UiController uiController) {
    	this.uiController_ = uiController;
    	panel_ = new JPanel();
    	panel_.setName("OrdinaryPanel");
    	
    	JLabel title = new JLabel("Ordinary Panel");
    	JButton demoDevButton = new JButton("Test Dev Screen");
    	JButton demoManButton = new JButton("Test Manager Screen");
    	JButton loginButton = new JButton("Login");
    	
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
    	
    	demoManButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get components
				JPanel viewHolder = (JPanel)(uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout)viewHolder.getLayout();
				
				// Create new ManagerPanel if it doesn't exist
				if(! uiController.checkPanelExists("ManagerPanel", viewHolder)){
					viewHolder.add(new ManagerPanel(uiController).getPanel_(), "ManagerPanel");
				}
				
				// Change view to manager panel
				layout.show(viewHolder, "ManagerPanel");
			}
    	});
    	
    	loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createLoginPopUp();
			}
    	});
    	
    	panel_.add(title);
    	panel_.add(demoDevButton);
    	panel_.add(demoManButton);
    	panel_.add(loginButton);
    }

    /**
     * @param void 
     * @return
     */
    public void createLoginPopUp() {
        JPanel loginPanel = new JPanel();
        
        JTextField usernameEntry = new JTextField();
        usernameEntry.setPreferredSize(new Dimension(150, 25));
        
        JPasswordField passwordEntry = new JPasswordField();
        passwordEntry.setPreferredSize(new Dimension(150, 25));
        
        Box vBox = Box.createVerticalBox(); // Align components in one column
        vBox.add(usernameEntry);
        vBox.add(passwordEntry);
        
        loginPanel.add(vBox);
        Object options[] = {"Login", "Cancel"};
        
        int selection = JOptionPane.showOptionDialog(null, loginPanel, 
        		"BTS Login", 
        		JOptionPane.OK_CANCEL_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
        
        if(selection == JOptionPane.OK_OPTION){
        	String username = usernameEntry.getText();
        	String password = String.valueOf(passwordEntry.getPassword());
        	// Call Login method in uicontroller and take action based on result
        	Employee logged_in_result = new Employee();
        	if(logged_in_result == null){
        		// 
        	}
        	else if(logged_in_result instanceof Manager){
        		
        	}
        	else if(logged_in_result instanceof Developer){
        		
        	}
        }
    	
    }

    /**
     * @param void 
     * @return
     */
    public void submitBugPopUp() {
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