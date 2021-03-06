package data;

import java.util.*;

/**
 * 
 */
public class Assignment {

	/**
	 * Default constructor
	 */
	public Assignment() {
	}

	/**
	 * 
	 */
	private int assignmentId_;

	/**
	 * The unique ID of the assignment
	 */
	private int developerId_;

	/**
	 * The ID of the developer assigned to the assignment
	 */
	private int bugId_;

	/**
	 * The ID of the bug that needs to be fixed
	 */
	private int managerId_;

	/**
	 * The ID of the manager that created the assignment
	 */
	private ArrayList<String> updateMessages_;

	public int getAssignmentId_() {
		return assignmentId_;
	}

	public void setAssignmentId_(int assignmentId_) {
		this.assignmentId_ = assignmentId_;
	}

	public int getDeveloperId_() {
		return developerId_;
	}

	public void setDeveloperId_(int developerId_) {
		this.developerId_ = developerId_;
	}

	public int getBugId_() {
		return bugId_;
	}

	public void setBugId_(int bugId_) {
		this.bugId_ = bugId_;
	}

	public int getManagerId_() {
		return managerId_;
	}

	public void setManagerId_(int managerId_) {
		this.managerId_ = managerId_;
	}

	public ArrayList<String> getUpdateMessages_() {
		return updateMessages_;
	}

	public void setUpdateMessages_(ArrayList<String> updateMessages_) {
		this.updateMessages_ = updateMessages_;
	}

}