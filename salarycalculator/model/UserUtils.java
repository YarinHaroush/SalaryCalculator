package il.ac.hit.salarycalculator.model;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class User utils create a tables that handle all the User things
 */
public class UserUtils implements IUserUtils {
    private static Logger logger = Logger.getLogger(UserSetting.class);

    private IModel model;
    private final String nameOfTable = "User1";
    private final String nameOFTableSetting = "UserSetting";

    /**
     * Initialize the class
     */
    public void initialize() {
        try {
            model.createTableIfNotExist(
                    nameOfTable,
                    "UserName",
                    "varchar(300)",
                    "Password",
                    "varchar(300)");
            model.createTableIfNotExist(
                    nameOFTableSetting,
                    "UserName",
                    "varchar(300)",
                    "ID",
                    "varchar(300)",
                    "SalaryPerHour",
                    "double");
            logger.info("was create successfully..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method add a new user and password to the IModel
     * @param userName the user name
     * @param password the password
     * @return true or false if it success
     */
    public boolean signIn(String userName, String password) {
        if (userName == null || password == null) {
            throw new NullPointerException();
        }

        logger.info("signin method..");
        boolean isSuccess = false;
        try {

            String parameters =
                    "(" + "'" + userName + "'" +
                            "," + "'" + password + "')";

            model.insertValue(nameOfTable, parameters);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isSuccess;
        }
    }

    /**
     * Check if the user already exist in the IModel
     * @param userName the user that need to check
     * @param password the password of the username
     * @return true or false if the user and the password are exists
     */
    public boolean checkIfUserExist(String userName, String password) {
        if (userName == null || password == null) {
            throw new NullPointerException();
        }
        boolean valToReturn = false;

        try {
            logger.info("check if exist method..");
            ResultSet rs = model.query("select * from " + nameOfTable + " where " + nameOfTable + ".USERNAME ='" + userName + "' and " +
                    nameOfTable + ".PASSWORD ='" + password + "'");
            if (rs.next()) {
                return valToReturn = rs.getString("USERNAME").equals(userName) &&
                        rs.getString("PASSWORD").equals(password);
            }
        } catch (SQLException e) {
            logger.info("check if exist method was crashed");
            e.printStackTrace();
        } finally {
            return valToReturn;
        }
    }

    /**
     * Check if the user name has already setting
     * @param userName
     * @return true or false if the user has setting
     */
    public boolean checkIfSettingExist(String userName) {
        if (userName == null) {
            throw new NullPointerException();
        }

        boolean valToReturn = false;
        try {
            logger.info("check setting if exist method..");
            ResultSet rs = model.query
                    ("select * from " + nameOFTableSetting + " where " + nameOFTableSetting + ".USERNAME ='" + userName + "'");
            if (rs.next()) {
                valToReturn = true;
            }
        } catch (SQLException e) {
            logger.info("check setting if exist method was crashed");
            e.printStackTrace();
        } finally {
            return valToReturn;
        }
    }

    /**
     * insert an new setting to IModel
     * @param userName the user name to insert
     * @param salary the salary to insert
     * @param id the id to insert
     * @return true or false if the operation success
     */
    public boolean insertSettings(String userName, double salary, String id) {
        if (userName == null || id == null) {
            throw new NullPointerException();
        }

        boolean isSuccess = false;
        try {
            logger.info("insert setting method..");
            String parameters =
                    "(" + "'" + userName + "'" +
                            "," + "'" + id + "'" +
                            "," + salary + ")";

            model.insertValue(nameOFTableSetting, parameters);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isSuccess;
        }
    }

    /**
     * Change the setting for a specific user
     * @param userName the user name that need to change
     * @param salary the salary
     * @param id the id
     * @return true or false if the operation is success
     */
    public boolean changeSettings(String userName, double salary, String id) {
        if (userName == null || id == null) {
            throw new NullPointerException();
        }

        boolean isSuccess = false;
        try {
            logger.info("change setting method..");
            model.removeValue(nameOFTableSetting, userName);
            insertSettings(userName, salary, id);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isSuccess;
        }
    }

    /**
     * Get the user setting
     * @param userName user name specific to get user settings
     * @return
     */
    public UserSetting getSetting(String userName) {
        if (userName == null) {
            throw new NullPointerException();
        }

        UserSetting userToReturn = new UserSetting();
        try {
            ResultSet rs = model.query
                    ("select * from " + nameOFTableSetting + " where " + nameOFTableSetting + ".USERNAME ='" + userName + "'");
            while (rs.next()) {
                userToReturn.setUserName(rs.getString("UserName"));
                userToReturn.setId(rs.getString("ID"));
                userToReturn.setSalary(rs.getDouble("SalaryPerHour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return userToReturn;
        }
    }

    /**
     * Set the model
     * @param model
     */
    public void setModel(IModel model) {
        this.model = model;
    }
}

