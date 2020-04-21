package il.ac.hit.salarycalculator.model;

/**
 * This class is a container for a user setting
 */
public class UserSetting {
    private String userName;
    private String id = "";
    private double salary;

    /**
     * Get the user name
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     * @param id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the salary
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Set the salary per hour
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
