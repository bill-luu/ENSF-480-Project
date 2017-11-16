package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Bug;
import data.Developer;
import data.Employee;
import data.Manager;
import javafx.scene.control.ListView;

/**
 * 
 */
public class OrdinaryPanel {

	/**
     * 
     */
    private JPanel panel_;

    /**
     * The UIController for the panel
     */
    private UiController uiController_;
    
    /**
     * The JList display for products
     */
    private JList<String> productlist;
    
    /**
     * The JList display for bugs
     */
    private JList<String> buglist;
    
    /**
     * Constructor for the Ordinary Panel
     * @param uiController The UIController objet that this panel will be added to.
     */
    public OrdinaryPanel(UiController uiController) {
    	this.uiController_ = uiController;
    	panel_ = new JPanel();
    	panel_.setLayout(new GridBagLayout());
    	panel_.setName("OrdinaryPanel");
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	JLabel title = new JLabel("Products");
    	JLabel title2 = new JLabel("Bugs");
    	JButton loginButton = new JButton("Login");
    	JButton submitBugButton = new JButton("Report Bug");
    	
    	// Temp Data until System/Database integrated in main branch
	    	ArrayList<String> products = new ArrayList<String>();
	    	products.add("prod1");
	    	products.add("prod2");
	    	products.add("prod3");
	    	products.add("prod4");
	    	products.add("prod5");
	    	products.add("prod1");
	    	products.add("prod2");
	    	products.add("prod3");
	    	products.add("prod4");
	    	products.add("prod5");
	    	products.add("prod1");
	    	products.add("prod2");
	    	products.add("prod3");
	    	products.add("prod4");
	    	products.add("prod5");
	    	
	    	ArrayList<String> bugs = new ArrayList<String>();
	    	bugs.add("bug1");
	    	bugs.add("bug2");
	    	bugs.add("bug3");
	    	bugs.add("bug4");
	    	bugs.add("bug5");
	    	bugs.add("bug1");
	    	bugs.add("bug2");
	    	bugs.add("bug3");
	    	bugs.add("bug4");
	    	bugs.add("bug5");
	    	bugs.add("bug1");
	    	bugs.add("bug2");
	    	bugs.add("bug3");
	    	bugs.add("bug4");
	    	bugs.add("bug5");
    	// End Temp
	    	
    	DefaultListModel<String> productModel = new DefaultListModel<String>();
    	DefaultListModel<String> bugModel = new DefaultListModel<String>();
    	
    	for(String p : products)
    		productModel.addElement(p);
    	
    	for(String b : bugs)
    		bugModel.addElement(b);
    	
    	productlist = new JList<String>(productModel);
    	buglist = new JList<String>(bugModel);
    	
    	JScrollPane bugScroller = new JScrollPane(buglist);
    	
    	
    	JScrollPane productScroller = new JScrollPane(productlist);
    	
    	
    	
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
    	// End temporary components
	    
	    // Open Popup when clicking on list
	    buglist.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				inspectBugPopUp();
			}
	    });
	    
	    // Open Popup when clicking on list
	    productlist.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO: Update bug list with bugs
			}
	    });
	    
	    // Open login popup when clicking login button
    	loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createLoginPopUp();
			}
    	});
    	
    	// Open submit bug popup when clicking submit bug button
    	submitBugButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitBugPopUp();
			}
    	});
    	gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 4; gbc.gridheight = 1;
    	panel_.add(title, gbc);
    	
    	gbc.gridx = 5; gbc.gridy = 0; gbc.gridwidth = 4; gbc.gridheight = 1;
    	panel_.add(title2, gbc);
    	
    	gbc.gridx = 10; gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridheight = 1;
    	panel_.add(demoDevButton, gbc);
    	
    	gbc.gridx = 10; gbc.gridy = 4; gbc.gridwidth = 1; gbc.gridheight = 1;
    	panel_.add(demoManButton, gbc);
    	
    	gbc.gridx = 10; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
    	panel_.add(loginButton, gbc);
    	
    	gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 4; gbc.gridheight = 6;
    	panel_.add(productScroller, gbc);
    	
    	gbc.gridx = 5; gbc.gridy = 3; gbc.gridwidth = 4; gbc.gridheight = 6;
    	panel_.add(bugScroller, gbc);
    	
    	gbc.gridx = 4; gbc.gridy = 9; gbc.gridwidth = 3; gbc.gridheight = 1;
    	panel_.add(submitBugButton, gbc);
    }

    /**
     * @param void 
     * @return
     */
    public void createLoginPopUp() {
        JPanel loginPanel = new JPanel();
        
        HintTextField usernameEntry = new HintTextField("Username");
        usernameEntry.setPreferredSize(new Dimension(150, 25));
        
        HintPasswordField passwordEntry = new HintPasswordField("Password");
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
        	Employee logged_in_result = uiController_.login(username + ":" + password);
        	if(logged_in_result == null){
        		// Show login failed message
        		JOptionPane.showMessageDialog(uiController_.getFrame(),
					        				"Unrecognized Login Credentials", 
					        				"Invalid Login", 
					        				JOptionPane.WARNING_MESSAGE);
        	}
        	else if(logged_in_result instanceof Manager){
        		// Get components
				JPanel viewHolder = (JPanel)(uiController_.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout)viewHolder.getLayout();
				
				// Create new ManagerPanel if it doesn't exist
				if(! uiController_.checkPanelExists("ManagerPanel", viewHolder)){
					viewHolder.add(new ManagerPanel(uiController_).getPanel_(), "ManagerPanel");
				}
				
				// Change view to manager panel
				layout.show(viewHolder, "ManagerPanel");
        	}
        	else if(logged_in_result instanceof Developer){
        		// Get components
				JPanel viewHolder = (JPanel)(uiController_.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout)viewHolder.getLayout();
				
				// Create new DeveloperPanel if it doesn't exist
				if(! uiController_.checkPanelExists("DeveloperPanel", viewHolder)){
					viewHolder.add(new DeveloperPanel(uiController_).getPanel_(), "DeveloperPanel");
				}
				
				// Change view to developer panel
				layout.show(viewHolder, "DeveloperPanel");
        	}
        }
    	
    }

    /**
     * Create a popup for users to submit a new bug in the system
     */
    public void submitBugPopUp() {
    	JPanel bugPanel = new JPanel();
    	
    	HintTextField title = new HintTextField("Enter a bug title here");
    	
    	JTextArea description = new JTextArea();
    	description.setPreferredSize(new Dimension(500, 250));
    	description.setBorder(BorderFactory.createEtchedBorder());
    	description.setLineWrap(true);
    	
    	// Fill a dropdown menu with all the products
    	DefaultComboBoxModel<String> productModel = new DefaultComboBoxModel<String>();
    	DefaultListModel<String> model = (DefaultListModel<String>) productlist.getModel();
    	for(int i = 0; i < model.size(); i++)
    		productModel.addElement(model.getElementAt(i));
    	JComboBox<String> products = new JComboBox<String>(productModel);
    	
    	Box vBox = Box.createVerticalBox();
    	vBox.add(products);
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
    	
    	if(selection == JOptionPane.OK_OPTION){
    		Bug b = new Bug(); // Populate with entered bug values
    		uiController_.SubmitBug(b);
    	}
    }
    
    /**
     * Create a new popup for users to inspect a bug
     */
    public void inspectBugPopUp() { // TODO: Add real bug object info
    	JPanel bugPanel = new JPanel();
    	String bug = buglist.getSelectedValue();
    	
    	JLabel bugTitle = new JLabel(bug);
    	JLabel bugProduct = new JLabel("Product");
    	JLabel bugDescription = new JLabel("Description");
    	
    	Box vBox = Box.createVerticalBox();
    	vBox.add(bugTitle);
    	vBox.add(Box.createVerticalStrut(15));
    	vBox.add(bugProduct);
    	vBox.add(Box.createVerticalStrut(15));
    	vBox.add(bugDescription);
    	bugPanel.add(vBox);
    	
    	Object options[] = {"OK"};
    	JOptionPane.showOptionDialog(null, bugPanel, 
        		"Inspect Bug", 
        		JOptionPane.OK_OPTION, 
        		JOptionPane.PLAIN_MESSAGE,
        		null,
        		options, 
        		options[0]);
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
	
	/**
	 * Anonymous inner class which places temporary background text in a JTextField 
	 */
	public class HintTextField extends JTextField {
		
		private static final long serialVersionUID = 1L;
		
		/**
		 * The hint text that appears in the component
		 */
		private final String hint_;
		
		/**
		 * Constructor for the component
		 * @param hint The hint text which appears in the component
		 */
	    public HintTextField(String hint) {
	        hint_ = hint;
	    }
	    
		/**
		 * Overwritten method that paints the hint text on the component if it is empty
		 */
	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        if (getText().length() == 0) {
	            int h = getHeight();
	            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
	 * Anonymous inner class which places temporary background text in a JPasswordField 
	 */
	public class HintPasswordField extends JPasswordField {
		
		private static final long serialVersionUID = 2L;
		
		/**
		 * The hint text that appears in the component
		 */
		private final String hint_;
		
		/**
		 * Constructor for the component
		 * @param hint The hint text which appears in the component
		 */
	    public HintPasswordField(String hint) {
	        hint_ = hint;
	    }
	    
	    /**
		 * Overwritten method that paints the hint text on the component if it is empty
		 */
	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        if (this.getPassword().length == 0) {
	            int h = getHeight();
	            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
}