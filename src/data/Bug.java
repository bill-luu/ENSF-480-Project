package data;

/**
 * 
 */
public class Bug {

	/**
	 * Enum to represent the current bug state
	 */
	
	public enum State {
		PENDING_APPROVAL, REJECTED, AWAITING_ASSIGNMENT, IN_PROGRESS, FIXED
	};

	/**
	 * Default constructor
	 */
	public Bug() {
	}

	/**
	 * The Unique ID of the Bug
	 */
	private int bugId_;

	/**
	 * The name of the bug
	 */
	private String bugTitle_;

	/**
	 * The product ID
	 */
	private int productId_;

	/**
	 * Enum for bug state. Can be PENDING_APPROVAL, REJECTED,
	 * AWAITING_ASSIGNMENT, IN_PROGRESS, or FIXED.
	 */
	private State state_;

	/**
	 * Description of the bug
	 */
	private String description_;

	/**
	 * @param void
	 * @return
	 */
	public String extractKeywords() {
		// TODO implement here
		return null;
	}

	public int getBugId_() {
		return bugId_;
	}

	public void setBugId_(int bugId_) {
		this.bugId_ = bugId_;
	}

	public String getBugTitle_() {
		return bugTitle_;
	}

	public void setBugTitle_(String bugTitle_) {
		this.bugTitle_ = bugTitle_;
	}

	public int getProductId_() {
		return productId_;
	}

	public void setProductId_(int productId_) {
		this.productId_ = productId_;
	}

	public State getState_() {
		return state_;
	}

	public void setState_(State state_) {
		this.state_ = state_;
	}

	public String getDescription_() {
		return description_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}
}