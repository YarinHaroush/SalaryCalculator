package il.ac.hit.salarycalculator.model;

import il.ac.hit.salarycalculator.exception.DataBaseQueryException;
import il.ac.hit.salarycalculator.exception.GetAllException;
import il.ac.hit.salarycalculator.exception.InsertException;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * This class report create a tables that handle all the shift things
 */
public class ReportShifts implements IShiftHandle {
    private static Logger logger = Logger.getLogger(ReportShifts.class);
    private IModel model;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private final String nameOfTable = "Shifts1";

    /**
     * Initialize the class create the tables
     */
    public void initialize() {
        logger.info("was create successfully..");
        try {
            model.createTableIfNotExist(
                    nameOfTable,
                    "UserName",
                    "varchar(300)",
                    "Date",
                    "varchar(300)",
                    "BeginHour",
                    "varchar(300)",
                    "EndHour",
                    "varchar(300)",
                    "Total",
                    "double");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Add a new shift to database
     * @param shiftToAdd shift to add into the database
     */
    public void addShift(Shift shiftToAdd) {
        if (shiftToAdd == null) {
            throw new NullPointerException();
        }

        String parameters =
                "(" + "'" + shiftToAdd.getUserName() + "'" +
                        "," + "'" + format.format(shiftToAdd.getDate()).toString() + "'" + ","
                        + "'" + shiftToAdd.getBegin() + "'" + "," + "'" + shiftToAdd.getEnd() + "'" + ","
                        + shiftToAdd.getTotalSalary() + ")";

        try {
            model.insertValue(nameOfTable, parameters);
            logger.info("add shift was successfully..");
        } catch (InsertException idbe) {
            idbe.printStackTrace();
        }
    }

    /**
     * @param userName to return all the shift for the specific user name
     * @return ResultSet that represent all the shifts
     * @throws GetAllException
     */
    public ResultSet getAllShift(String userName) throws GetAllException {
        if (userName == null) {
            throw new NullPointerException();
        }

        try {
            return model.query
                    ("select * from " + nameOfTable + " where " + nameOfTable + ".USERNAME ='" + userName + "'");
        } catch (DataBaseQueryException e) {
            throw new GetAllException("Get all method was failed because Data base query as failed", e);
        }

    }

    /**
     * Set the model that represent the database
     * @param model
     */
    public void setModel(IModel model) {
        this.model = model;
    }
}
