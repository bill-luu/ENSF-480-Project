package presentation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
     * 
     * @param uiController
     */
    public OrdinaryPanel(UiController uiController) {
    	System.out.println("Ctor Ordinary");
    	this.uiController_ = uiController;
    	panel_ = new JPanel();
    	panel_.setName("OrdinaryPanel");
    	
    	JLabel title = new JLabel("Ordinary Panel");
    	JButton demoDevButton = new JButton("Go to Dev Screen");
    	JButton demoManButton = new JButton("Go to Manager Screen");
    	
    	demoDevButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get components
				JPanel viewHolder = (JPanel)(uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout)viewHolder.getLayout();
				
				// Create new DeveloperPanel if it doesn't exist
				if(! uiController.checkPanelExists("DeveloperPanel", viewHolder)){
					System.out.println("Ctor Developer");
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
					System.out.println("Ctor Manager");
					viewHolder.add(new ManagerPanel(uiController).getPanel_(), "ManagerPanel");
				}
				
				// Change view to manager panel
				layout.show(viewHolder, "ManagerPanel");
			}
    	});
    	
    	panel_.add(title);
    	panel_.add(demoDevButton);
    	panel_.add(demoManButton);
    }

    /**
     * @param void 
     * @return
     */
    public void createLoginPopUp() {
        // TODO implement here
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