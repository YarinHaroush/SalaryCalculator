package il.ac.hit.salarycalculator.model;

import il.ac.hit.salarycalculator.exception.DataBaseQueryException;
import il.ac.hit.salarycalculator.exception.InsertException;
import il.ac.hit.salarycalculator.exception.RemoveException;

import java.sql.ResultSet;

/**
 * IModel interface to implements IModel
 */
public interface IModel
{
    /**
     * This method create a new table in the database  if the table not exist
     * @param nameOfTable the name of the table nameOfTable
     * @param parameters the parameters of the table
     * @return true or false if it is success
     * @throw DataBaseQueryException
     * @throw Exception
     */
    boolean createTableIfNotExist(String nameOfTable, String... parameters) throws DataBaseQueryException,Exception;

    /**
     * Delete the table from the database
     * @param nameOfTable the name of the table to delete
     */
    void dropTable(String nameOfTable);

    /**
     * Insert a value into a table on the database
     * @param nameOfTable the name of the table on the database
     * @param parameters the parameters to insert into the table
     * @throws InsertException
     */
    void insertValue(String nameOfTable, String parameters) throws InsertException;

    /**
     * Make a query into the database
     * @param query a query string to make on the database
     * @return ResultSet represent the query
     * @throws DataBaseQueryException
     */
    ResultSet query(String query) throws DataBaseQueryException;

    /**
     * Remove a row from table on the database
     * @param nameOfTable the name of the table on the database
     * @param userName the username row
     * @throws RemoveException
     */
    void removeValue(String nameOfTable, String userName) throws RemoveException;
}
