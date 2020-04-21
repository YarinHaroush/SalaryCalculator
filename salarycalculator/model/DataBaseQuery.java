package il.ac.hit.salarycalculator.model;

import il.ac.hit.salarycalculator.exception.DataBaseQueryException;
import il.ac.hit.salarycalculator.exception.InsertException;
import il.ac.hit.salarycalculator.exception.RemoveException;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * This Class implements IModel interface
 * the rule of this class is implements database queries
 */
public class DataBaseQuery implements IModel{
    private static DataBaseQuery instance;
    private static Object lock = new Object();
    private DataBaseCreator dataBaseCreator = DataBaseCreator.getInstance();
    private Statement statement = dataBaseCreator.getStatement();
    private DatabaseMetaData metaData = null;
    private static Logger logger = Logger.getLogger(DataBaseQuery.class);

    /**
     * Private C'tor implement singleton
     */
    private DataBaseQuery() {
        try {
            metaData = dataBaseCreator.getConnection().getMetaData();
            logger.info("DataBaseQuery was create successfully..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton implementation
     * @return DataBaseQuery instance
     */
    public static DataBaseQuery getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DataBaseQuery();
                }
            }
        }

        return instance;
    }

    /**
     * @param  nameOfTable of table name
     * @param  parameters for the table parameters
     * @throws Exception,DataBaseQueryException
     */
    public boolean createTableIfNotExist(String nameOfTable, String... parameters) throws DataBaseQueryException,Exception {
        if (nameOfTable == null || parameters == null)
        {
            throw new NullPointerException();
        }

        if (parameters.length % 2 != 0) {
            logger.info("Create Table was failed the number of parameters was odd..");
            throw new Exception
                ("Please enter the parameters as follows: Param1Name ,Param1Type, Param2Name ,Param2Type .... , ParamName ,ParamType");
        }

        String str = stringsConcat(parameters);
        try {
            ResultSet rs = metaData.getTables(null, "APP", nameOfTable.toUpperCase(), null);
            if (!rs.next()) {
                statement.execute("create table " + nameOfTable + str);
                logger.info("Create Table was Successes..");
                return true;
            }
        } catch (SQLException e) {
           throw new DataBaseQueryException("Create table failed",e);
        }

        logger.info("Create Table = "+nameOfTable+" already exist..");
        return false;
    }

    /**
     * This method delete table from database
     * @param nameOfTable the name of the table that want to delete
     */
    public void dropTable(String nameOfTable)
    {
        if (nameOfTable == null)
        {
            throw new NullPointerException();
        }

        try {
            statement.execute("DROP TABLE "+nameOfTable);
            logger.info("Drop Table was success..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param  nameOfTable name of table
     * @param  parameters for the table parameters
     * @throws InsertException
     */
    public void insertValue(String nameOfTable, String parameters) throws InsertException {
        if (nameOfTable == null || parameters == null)
        {
            throw new NullPointerException();
        }

        try {
            statement.executeUpdate("insert into " + nameOfTable.toUpperCase() + " values " + parameters);
            logger.info("Insert parameters to Table  = "+nameOfTable+" successes.");
        } catch (Exception e) {
            logger.info("Insert parameters to Table  = "+nameOfTable+" failed.");
            throw new InsertException("Insert value was failed",e);
        }
    }

    /**
     *
     * @param nameOfTable name of table
     * @param  userName user name that logged
     * @throws RemoveException
     */
    public void removeValue(String nameOfTable, String userName) throws RemoveException {
        if (nameOfTable == null || userName == null)
        {
            throw new NullPointerException();
        }

        try {
            statement.executeUpdate(String.format("DELETE  FROM %s WHERE %s.USERNAME = '%S'"
                    , nameOfTable, nameOfTable, userName));
            logger.info("Remove parameters to Table  = "+nameOfTable+" successes.");
        } catch (Exception e) {
            logger.warn("Remove parameters to Table  = "+nameOfTable+" failed.");
            throw new RemoveException("Remove value was failed",e);
        }
    }

    /**
     * query for the database
     * @param query String that represent the query
     * @return ResultSet
     * @throws DataBaseQueryException
     */
    public ResultSet query(String query) throws DataBaseQueryException {
        if (query == null)
        {
            throw new NullPointerException();
        }

        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DataBaseQueryException("The Query was failed",e);
        }

        return rs;
    }

    /**
     * @param stringToConcat string parameters
     * @return a string that concat the string array
     */
    public String stringsConcat(String... stringToConcat) {
        if (stringToConcat == null)
        {
            throw new NullPointerException();
        }

        StringBuffer stringBuffer = new StringBuffer();
        int length = stringToConcat.length - 1;
        stringBuffer.append("(");
        for (int i = 0; i < length; i += 2) {
            stringBuffer.append(stringToConcat[i]);
            stringBuffer.append(" ");
            stringBuffer.append(stringToConcat[i + 1]);
            if (i < length - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
