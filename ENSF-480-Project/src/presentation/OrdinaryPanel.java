package presentation;

import javax.swing.JPanel;

/**
 * 
 */
public class OrdinaryPanel {

    /**
     * Default constructor
     */
    public OrdinaryPanel() {
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