package il.ac.hit.salarycalculator.model;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * This class create the database this class implements singleton
 */
public class DataBaseCreator {
    private static DataBaseCreator instance = null;
    private static Object lock = new Object();
    private static Logger logger = Logger.getLogger(DataBaseCreator.class);

    private final String drive = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String protocol = "jdbc:derby:SalaryCalculatorDB;create=true";
    private boolean isConnected = false;
    private Connection connection = null;
    private Statement statement = null;

    /**
     * Ctor
     */
    private DataBaseCreator() {
        BasicConfigurator.configure();
        init();
        logger.info("DataBase was create successfully..");
    }

    /**
     * Initialize the components
     * @return
     */
    private boolean init() {
        try {
            Class.forName(drive);
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            isConnected = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return isConnected;
        }
    }

    /**
     *  Get instance of DataBaseCreator implements singleton
     * @return DataBaseCreator
     */
    public static DataBaseCreator getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DataBaseCreator();
                }
            }
        }

        return instance;
    }

    /**
     * @return Statement from the db
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }
}
