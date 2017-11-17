package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import data.*;
import data.Bug.State;

public class DatabaseController {

	final String PRODUCT_FILE = "./SampleFiles/Products.txt";
	final String ASSIGNMENT_FILE = "./SampleFiles/Assignments.txt";
	final String DEVELOPER_FILE = "./SampleFiles/Developers.txt";
	final String MANAGER_FILE = "./SampleFiles/Managers.txt";
	final String LOGIN_FILE = "./SampleFiles/Login.txt";
	final String BUG_FILE = "./SampleFiles/Bugs.txt";

	public ArrayList<Product> readProductFile() {
		ArrayList<Product> tempProductList = new ArrayList<Product>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(PRODUCT_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				Product tempProduct = new Product();
				StringTokenizer tokens = new StringTokenizer(tempString);
				int i = 0;
				String description = "";
				while (tokens.hasMoreTokens()) {
					if (i == 0) {
						tempProduct.setProductId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 1) {
						tempProduct.setProductName_(tokens.nextToken());
					} else if (i == 2) {
						description = description.concat(tokens.nextToken());
					} else {
						description = description.concat(" ");
						description = description.concat(tokens.nextToken());
					}
					i++;
				}
				tempProduct.setProductDescription(description);
				tempProductList.add(tempProduct);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the products file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempProductList;
	}

	public ArrayList<Assignment> readAssignmentFile() {
		ArrayList<Assignment> tempAssignmentList = new ArrayList<Assignment>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(ASSIGNMENT_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				Assignment tempAssignment = new Assignment();
				StringTokenizer tokens = new StringTokenizer(tempString);
				int i = 0;
				ArrayList<String> messages = new ArrayList<String>();
				while (tokens.hasMoreTokens()) {
					if (i == 0) {
						tempAssignment.setAssignmentId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 1) {
						tempAssignment.setDeveloperId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 2) {
						tempAssignment.setBugId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 3) {
						tempAssignment.setManagerId_(Integer.parseInt(tokens.nextToken()));
					} else {
						messages.add(tokens.nextToken(";"));
					}
					i++;
				}
				tempAssignment.setUpdateMessages_(messages);
				tempAssignmentList.add(tempAssignment);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the assignment file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempAssignmentList;
	}

	public ArrayList<Developer> readDeveloperFile() {
		ArrayList<Developer> tempDeveloperList = new ArrayList<Developer>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(DEVELOPER_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				Developer tempDeveloper = new Developer();
				StringTokenizer tokens = new StringTokenizer(tempString);
				int i = 0;
				while (tokens.hasMoreTokens()) {
					if (i == 0) {
						tempDeveloper.setUserId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 1) {
						tempDeveloper.setFirstName_(tokens.nextToken());
					} else if (i == 2) {
						tempDeveloper.setLastName_(tokens.nextToken());
					} else if (i == 3) {
						tempDeveloper.setUsername_(tokens.nextToken());
					}
					i++;
				}
				tempDeveloperList.add(tempDeveloper);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the developer file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempDeveloperList;
	}

	public ArrayList<Manager> readManagerFile() {
		ArrayList<Manager> tempManagerList = new ArrayList<Manager>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(MANAGER_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				Manager tempManager = new Manager();
				StringTokenizer tokens = new StringTokenizer(tempString);
				int i = 0;
				while (tokens.hasMoreTokens()) {
					if (i == 0) {
						tempManager.setUserId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 1) {
						tempManager.setFirstName_(tokens.nextToken());
					} else if (i == 2) {
						tempManager.setLastName_(tokens.nextToken());
					} else if (i == 3) {
						tempManager.setUsername_(tokens.nextToken());
					}
					i++;
				}
				tempManagerList.add(tempManager);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the manager file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempManagerList;
	}

	public ArrayList<String> readLoginInfoFile() {
		ArrayList<String> tempLoginList = new ArrayList<String>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(LOGIN_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				tempLoginList.add(tempString);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the login file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempLoginList;
	}

	public ArrayList<Bug> readBugFile() {
		ArrayList<Bug> tempBugsList = new ArrayList<Bug>();
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(BUG_FILE));
			String tempString = "";
			while ((tempString = input.readLine()) != null && tempString.length() != 0) {
				Bug tempBug = new Bug();
				StringTokenizer tokens = new StringTokenizer(tempString);
				int i = 0;
				String description = "";
				while (tokens.hasMoreTokens()) {
					if (i == 0) {
						tempBug.setBugId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 1) {
						tempBug.setProductId_(Integer.parseInt(tokens.nextToken()));
					} else if (i == 2) {
						tempBug.setBugTitle_(tokens.nextToken());
					} else if (i == 3) {
						String stateString = tokens.nextToken();
						Bug.State state = State.PENDING_APPROVAL;
						if (stateString.equals("PENDING_APPROVAL"))
							state = State.PENDING_APPROVAL;
						else if (stateString.equals("REJECTED"))
							state = State.REJECTED;
						else if (stateString.equals("AWAITING_ASSIGNMENT"))
							state = State.AWAITING_ASSIGNMENT;
						else if (stateString.equals("IN_PROGRESS"))
							state = State.IN_PROGRESS;
						else if (stateString.equals("FIXED"))
							state = State.FIXED;

						tempBug.setState_(state);
					} else if (i == 4) {
						description = description.concat(tokens.nextToken());
					} else {
						description = description.concat(" ");
						description = description.concat(tokens.nextToken());
					}
					i++;
				}
				tempBug.setDescription_(description);
				tempBugsList.add(tempBug);
			}
			input.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error reading the bugs file.");
			System.out.println(e.getMessage());
			return null;
		}
		return tempBugsList;
	}

	public void writeReport(String report) {
		BufferedWriter output;
		Random rand = new Random();
		int temp = rand.nextInt() % 20000;
		String reportName = "Report";
		reportName = reportName.concat(String.valueOf(temp));
		try {
			output = new BufferedWriter(new FileWriter(reportName));
			output.write(report);
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the report file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeProductFile(ArrayList<Product> products) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(PRODUCT_FILE));
			for (int i = 0; i < products.size(); i++) {
				String tempString = "";
				tempString = tempString.concat(String.valueOf(products.get(i).getProductId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(products.get(i).getProductName_());
				tempString = tempString.concat(" ");
				tempString = tempString.concat(products.get(i).getProductDescription());

				output.write(tempString);
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the products file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeAssignmentFile(ArrayList<Assignment> assignments) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(ASSIGNMENT_FILE));
			for (int i = 0; i < assignments.size(); i++) {
				String tempString = "";
				tempString = tempString.concat(String.valueOf(assignments.get(i).getAssignmentId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(String.valueOf(assignments.get(i).getDeveloperId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(String.valueOf(assignments.get(i).getBugId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(String.valueOf(assignments.get(i).getManagerId_()));
				for (int j = 0; j < assignments.get(i).getUpdateMessages_().size(); j++) {
					tempString = tempString.concat(" ");
					tempString = tempString.concat(assignments.get(i).getUpdateMessages_().get(j));
				}

				output.write(tempString);
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the assignment file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeDeveloperFile(ArrayList<Developer> developers) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(DEVELOPER_FILE));
			for (int i = 0; i < developers.size(); i++) {
				String tempString = "";
				tempString = tempString.concat(String.valueOf(developers.get(i).getUserId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(developers.get(i).getFirstName_());
				tempString = tempString.concat(" ");
				tempString = tempString.concat(developers.get(i).getLastName_());
				tempString = tempString.concat(" ");
				tempString = tempString.concat(developers.get(i).getUsername_());

				output.write(tempString);
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the developer file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeManagerFile(ArrayList<Manager> managers) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(MANAGER_FILE));
			for (int i = 0; i < managers.size(); i++) {
				String tempString = "";
				tempString = tempString.concat(String.valueOf(managers.get(i).getUserId_()));
				tempString = tempString.concat(" ");
				tempString = tempString.concat(managers.get(i).getFirstName_());
				tempString = tempString.concat(" ");
				tempString = tempString.concat(managers.get(i).getLastName_());
				tempString = tempString.concat(" ");
				tempString = tempString.concat(managers.get(i).getUsername_());

				output.write(tempString);
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the developer file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeLoginInfoFile(ArrayList<String> loginInfo) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(LOGIN_FILE));
			for (int i = 0; i < loginInfo.size(); i++) {
				output.write(loginInfo.get(i));
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the login file.");
			System.out.println(e.getMessage());
		}
	}

	public void writeBugFile(ArrayList<Bug> bugs) {
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(BUG_FILE));
			for (int i = 0; i < bugs.size(); i++) {
				String tempString = "";
				tempString.concat(String.valueOf(bugs.get(i).getBugId_()));
				tempString.concat(" ");
				tempString.concat(String.valueOf(bugs.get(i).getProductId_()));
				tempString.concat(" ");
				tempString.concat(bugs.get(i).getBugTitle_());
				tempString.concat(" ");
				tempString.concat(bugs.get(i).getDescription_());
				tempString.concat(" ");

				output.write(tempString);
				output.newLine();
			}
			output.close();
		} catch (IOException | NullPointerException e) {
			System.out.println("Error writing the bugs file.");
			System.out.println(e.getMessage());
		}
	}

}
