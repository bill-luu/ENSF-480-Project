package data;

import java.util.ArrayList;

/**
 * 
 */
public class DatabaseController {

    /**
     * Default constructor
     */
    public DatabaseController() {
    }

    /**
     * The file containing the products
     */
    private final String PRODUCT_FILE = "Products.txt";

    /**
     * The file containing the Assignments 
     */
    private final String ASSIGNMENT_FILE = "Assignment.txt";

    /**
     * The file containing the Developers
     */
    private final String DEVELOPER_FILE = "Developers.txt";

    /**
     * The file containing the managers
     */
    private final String MANAGER_FILE = "Managers.txt";

    /**
     * The file containing the login info
     */
    private final String LOGIN_FILE = "Login.txt";

    /**
     * The file containing the Bugs
     */
    private final String BUG_FILE = "Bugs.txt";


    /**
     * @param void 
     * @return
     */
    public ArrayList<Product> readProductFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param void 
     * @return
     */
    public ArrayList<Assignment> readAssignmentFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param void 
     * @return
     */
    public ArrayList<Developer> readDeveloperFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param void 
     * @return
     */
    public ArrayList<Manager> readManagerFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param void 
     * @return
     */
    public ArrayList<String> readLoginInfoFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param void 
     * @return
     */
    public ArrayList<Bug> readBugFile() {
        // TODO implement here
        return null;
    }

    /**
     * @param reportToWrite 
     * @return
     */
//    public void writeReport(Report reportToWrite) {
//        // TODO implement here
//    }

    /**
     * @param ArrayList product 
     * @return
     */
    public void writeProductFile(ArrayList product) {
        // TODO implement here
    }

    /**
     * @param ArrayList Assignment 
     * @return
     */
    public void writeAssignmentFile(ArrayList Assignment) {
        // TODO implement here
    }

    /**
     * @param ArrayList developer 
     * @return
     */
    public void writeDeveloperFile(ArrayList developer) {
        // TODO implement here
    }

    /**
     * @param ArrayList manager 
     * @return
     */
    public void writeManagerFile(ArrayList manager) {
        // TODO implement here
    }

    /**
     * @param ArrayList login 
     * @return
     */
    public void writeLoginFile(ArrayList login) {
        // TODO implement here
    }

    /**
     * @param ArrayList bug 
     * @return
     */
    public void writeBugFile(ArrayList bug) {
        // TODO implement here
    }

}