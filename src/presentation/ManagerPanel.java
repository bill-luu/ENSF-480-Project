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
public class ManagerPanel {

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
	public ManagerPanel(UiController uiController) {
		this.uiController_ = uiController;
		panel_ = new JPanel();
		panel_.setName("ManagerPanel");

		JButton demoBackButton = new JButton("Logout");

		demoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel viewHolder = (JPanel) (uiController.getFrame().getContentPane().getComponent(0));
				CardLayout layout = (CardLayout) viewHolder.getLayout();
				layout.show(viewHolder, "OrdinaryPanel");
			}
		});

		panel_.add(new JLabel("Manager"));
		panel_.add(demoBackButton);
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