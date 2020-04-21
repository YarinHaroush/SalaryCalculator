package il.ac.hit.salarycalculator.model;

/**
 * Interface for User utils
 */
public interface IUserUtils
{
    /**
     * Check if the user exist on the data base
     * @param userName user name that check if exist
     * @param password password of the user name
     * @return true or false if the user name is exist
     */
    boolean checkIfUserExist(String userName, String password);

    /**
     * add the user name and the password to the database
     * @param userName username that want ot add
     * @param password password want to add
     * @return true or false if it success
     */
    boolean signIn(String userName, String password);

    /**
     * Check if the setting fro specific username are exist
     * @param userName the username that check if the settings are exists
     * @return true or false if the settings are exists
     */
    boolean checkIfSettingExist(String userName);

    /**
     * The method return the settings for a specific user name
     * @param userName user name specific to get user settings
     * @return true of false if it success
     */
    UserSetting getSetting(String userName);

    /**
     * Insert a new user settings to the database
     * @param userName user name specific to add user's setting to the database
     * @param salary the salary for username
     * @param id the id can be string empty("")
     * @return true of false if the operation success
     */
    boolean insertSettings(String userName, double salary, String id);

    /**
     * Change setting for a specific user name
     * @param userName the user name to change the setting on the database
     * @param salary the salary to change
     * @param id the id to change
     * @return true or false if the operation success
     */
    boolean changeSettings(String userName, double salary, String id);
}
