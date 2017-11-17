package presentation;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	 * 
	 * @param uiController
	 */
	public DeveloperPanel(UiController uiController) {
		this.uiController_ = uiController;
		this.panel_ = new JPanel();
		panel_.setName("DeveloperPanel");

		JButton demoBackButton = new JButton("Logout");

		demoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();
				layout.show(viewHolder, "OrdinaryPanel");
			}
		});

		panel_.add(new JLabel("Developer"));
		panel_.add(demoBackButton);
	}

	/**
	 * @param void
	 * @return
	 */
	public void updateAssignmentPopUp() {
		// TODO implement here
	}

	/**
	 * @param void
	 * @return
	 */
	public void reportFixPopUp() {
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