package presentation;

import javax.swing.JPanel;

/**
 * 
 */
public class ManagerPanel {

    /**
     * Default constructor
     */
    public ManagerPanel() {
    }

    /**
     * 
     */
    private JPanel panel_;

    /**
     * 
     */
    private UiController uiController_;


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