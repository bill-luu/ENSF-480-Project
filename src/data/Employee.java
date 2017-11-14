package data;
/**
 * 
 */
public class Employee {

    /**
     * Default constructor
     */
    public Employee() {
    }

    /**
     * The first name of the employee
     */
    private String firstName_;

    /**
     * The last name of the employee
     */
    private String lastName_;

    /**
     * The username of the employee
     */
    private String username_;
    
    /**
     * The user id of the employee
     */
    private int userId_;

	public String getFirstName_() {
		return firstName_;
	}

	public void setFirstName_(String firstName_) {
		this.firstName_ = firstName_;
	}

	public String getLastName_() {
		return lastName_;
	}

	public void setLastName_(String lastName_) {
		this.lastName_ = lastName_;
	}

	public int getUserId_() {
		return userId_;
	}

	public void setUserId_(int userId_) {
		this.userId_ = userId_;
	}
	
	public String getUsername_() {
		return username_;
	}

	public void setUsername_(String username_) {
		this.username_ = username_;
	}
	

    
}