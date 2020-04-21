package il.ac.hit.salarycalculator.model;

import il.ac.hit.salarycalculator.exception.GetAllException;

import java.sql.ResultSet;

/**
 * Interface to shift handle
 */
public interface IShiftHandle
{
    /**
     * Add a new shift to the model
     * @param shiftToAdd a shift to add
     */
    void addShift(Shift shiftToAdd);

    /**
     *
     * @param userName the represent the all shifts for the specific user
     * @return ResultSet that represent all the shifts for the specific user
     * @throws GetAllException
     */
    ResultSet getAllShift(String userName) throws GetAllException;
}
