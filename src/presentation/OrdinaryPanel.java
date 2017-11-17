package presentation;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
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
import data.Product;

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
	 * Combobox for selecting pages to view
	 */
	JComboBox<String> pageSelector;
	
	/**
	 * Button for logging into the system
	 */
	private JButton loginButton;
	
	/**
	 * Constructor for the Ordinary Panel
	 * 
	 * @param uiController
	 *            The UIController objet that this panel will be added to.
	 */
	public OrdinaryPanel(UiController uiController) {
		this.uiController_ = uiController;
		panel_ = new JPanel();
		panel_.setLayout(new GridBagLayout());
		panel_.setName("OrdinaryPanel");

		Font titlefont = new Font("Calibri", Font.BOLD, 40);

		GridBagConstraints gbc = new GridBagConstraints();
		JLabel title = new JLabel("Products");
		title.setFont(titlefont);

		JLabel title2 = new JLabel("Bugs");
		title2.setFont(titlefont);

		JButton loginButton = new JButton("Login");
		JButton submitBugButton = new JButton("Report Bug");

		String[] pages = { "Bugs" };
		DefaultComboBoxModel<String> pageModel = new DefaultComboBoxModel<String>(pages);
		pageSelector = new JComboBox<String>(pageModel);

		ArrayList<Product> products = uiController.BrowseProducts();
		ArrayList<Bug> bugs = uiController.BrowseBugs();

		DefaultListModel<String> productModel = new DefaultListModel<String>();
		DefaultListModel<String> bugModel = new DefaultListModel<String>();

		for (Product p : products)
			productModel.addElement(p.getProductId_() + " " + p.getProductName_() + " " + p.getProductDescription());

		for (Bug b : bugs)
			bugModel.addElement(b.getBugId_() + " " + b.getBugTitle_() + " " + b.getState_());

		productlist = new JList<String>(productModel);
		buglist = new JList<String>(bugModel);

		JScrollPane bugScroller = new JScrollPane(buglist);
		bugScroller.setPreferredSize(new Dimension(200, 525));

		JScrollPane productScroller = new JScrollPane(productlist);
		productScroller.setPreferredSize(new Dimension(200, 525));

		// Temporary components for testing
		JButton demoDevButton = new JButton("D");
		JButton demoManButton = new JButton("M");

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
		buglist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				inspectBugPopUp();
			}
		});

		// Open Popup when clicking on list
		productlist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				bugModel.removeAllElements();
				String product = productlist.getSelectedValue();
				String products[] = product.split(" ");
				int productID = Integer.parseInt(products[0]);

				// Search for corresponding bugs
				ArrayList<Bug> bugs = uiController.BrowseBugs();
				for (Bug b : bugs) {
					if (b.getProductId_() == productID)
						bugModel.addElement(b.getBugId_() + " " + b.getBugTitle_() + " " + b.getState_());
				}
			}
		});

		// Open login popup when clicking login button
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createLoginPopUp();
			}
		});

		// Open submit bug popup when clicking submit bug button
		submitBugButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitBugPopUp();
			}
		});

		// Temp Components
		gbc.gridx = 10;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel_.add(demoManButton, gbc);

		gbc.gridx = 10;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel_.add(demoDevButton, gbc);
		//

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
		panel_.add(loginButton, gbc);

		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 6;
		panel_.add(productScroller, gbc);

		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 6;
		panel_.add(bugScroller, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
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
		Object options[] = { "Login", "Cancel" };

		int selection = JOptionPane.showOptionDialog(null, loginPanel, "BTS Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selection == JOptionPane.OK_OPTION) {
			String username = usernameEntry.getText();
			String password = String.valueOf(passwordEntry.getPassword());
			// Call Login method in uicontroller and take action based on result
			Employee logged_in_result = uiController_.login(username + ":" + password);
			if (logged_in_result == null) {
				// Show login failed message
				JOptionPane.showMessageDialog(uiController_.getFrame(), "Unrecognized Login Credentials",
						"Invalid Login", JOptionPane.WARNING_MESSAGE);
			} else if (logged_in_result instanceof Manager) {
				// Get components
				((DefaultComboBoxModel<String>)pageSelector.getModel()).addElement("Assignment");
				loginButton.setVisible(false);
				JPanel viewHolder = (JPanel) (uiController_.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();
				
				// Create new ManagerPanel if it doesn't exist
				if (!uiController_.checkPanelExists("ManagerPanel", viewHolder)) {
					viewHolder.add(new ManagerPanel(uiController_).getPanel_(), "ManagerPanel");
				}

				// Change view to manager panel
				layout.show(viewHolder, "ManagerPanel");
			} else if (logged_in_result instanceof Developer) {
				((DefaultComboBoxModel<String>)pageSelector.getModel()).addElement("Assignment");
				loginButton.setVisible(false);
				// Get components
				JPanel viewHolder = (JPanel) (uiController_.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();

				// Create new DeveloperPanel if it doesn't exist
				if (!uiController_.checkPanelExists("DeveloperPanel", viewHolder)) {
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
		
		// Fill combobox with only product names
		for (int i = 0; i < model.size(); i++)
			productModel.addElement(model.getElementAt(i).split(" ")[1]); 
		JComboBox<String> products = new JComboBox<String>(productModel);

		Box vBox = Box.createVerticalBox();
		vBox.add(products);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(title);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(description);
		vBox.add(Box.createVerticalStrut(20));

		bugPanel.add(vBox);

		Object options[] = { "Submit Bug", "Cancel" };
		int selection = JOptionPane.showOptionDialog(null, bugPanel, "New Bug", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selection == JOptionPane.OK_OPTION) {
			// Get selected product ID
			String selectedProduct = model.getElementAt(products.getSelectedIndex());
			int productID = Integer.parseInt(selectedProduct.split(" ")[0]);
			
			// Create the new bug
			Bug b = new Bug();
			b.setBugId_(uiController_.BrowseBugs().size() + 101);
			b.setBugTitle_(title.getText());
			b.setDescription_(description.getText());
			b.setProductId_(productID);
			b.setState_(Bug.State.PENDING_APPROVAL);
			uiController_.SubmitBug(b);
		}
	}

	/**
	 * Create a new popup for users to inspect a bug
	 */
	public void inspectBugPopUp() {
		JPanel bugPanel = new JPanel();
		if (buglist.isSelectionEmpty())
			return;
		
		// Separate bug id from the rest of the string
		int bugID = Integer.parseInt(buglist.getSelectedValue().split(" ")[0]);
		
		ArrayList<Bug> bugs = uiController_.BrowseBugs();
		for (Bug b : bugs) {
			if (b.getBugId_() == bugID) {
				JLabel bugTitle = new JLabel("Bug:" + b.getBugTitle_());
				JLabel bugDescription = new JLabel("Description:" + b.getDescription_());
				JLabel bugState = new JLabel("Status:" + b.getState_().toString());
				ArrayList<Product> productlist = uiController_.BrowseProducts();
				
				for (Product p : productlist) {
					if (p.getProductId_() == b.getProductId_()) {
						JLabel productTitle = new JLabel("<HTML><U>Product Information</U></HTML>");
						JLabel productID = new JLabel("" + p.getProductId_());
						JLabel productName = new JLabel(p.getProductName_());
						JLabel productDescription = new JLabel(p.getProductDescription());
						
						Font font = new Font("Calibri", Font.PLAIN, 20);
						bugTitle.setFont(font); 		productTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
						productID.setFont(font);		productName.setFont(font);
						bugDescription.setFont(font); 	productDescription.setFont(font);
						bugState.setFont(font); 		
						
						Box vBox = Box.createVerticalBox();
						vBox.add(bugTitle);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(bugDescription);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(bugState);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(productTitle);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(productID);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(productName);
						vBox.add(Box.createVerticalStrut(15));
						vBox.add(productDescription);
						bugPanel.add(vBox);

						Object options[] = { "OK" };
						JOptionPane.showOptionDialog(null, bugPanel, "Inspect Bug", JOptionPane.OK_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						break;
					}
				}
				break;
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

	/**
	 * Anonymous inner class which places temporary background text in a
	 * JTextField
	 */
	public class HintTextField extends JTextField {

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
		public HintTextField(String hint) {
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
	 * Anonymous inner class which places temporary background text in a
	 * JPasswordField
	 */
	public class HintPasswordField extends JPasswordField {

		private static final long serialVersionUID = 2L;

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
		public HintPasswordField(String hint) {
			hint_ = hint;
		}

		/**
		 * Overwritten method that paints the hint text on the component if it
		 * is empty
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (this.getPassword().length == 0) {
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
}