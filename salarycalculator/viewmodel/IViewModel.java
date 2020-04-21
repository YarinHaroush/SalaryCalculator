package il.ac.hit.salarycalculator.viewmodel;

import il.ac.hit.salarycalculator.model.IModel;
import il.ac.hit.salarycalculator.model.IShiftHandle;
import il.ac.hit.salarycalculator.model.IUserUtils;

/**
 * IviewModel extends from IShiftHandle ,IUserUtils
 */
public interface IViewModel extends IShiftHandle, IUserUtils
{
    /**
     * Get user name
     * @return user name
     */
    String getUserName();

    /**
     * Get salary per hour
     * @return salary
     */
    double getSalaryPerHour();

    /**
     * Show the first UI component
     */
    void startProgram();

    /**
     * Change the form by name
     * @param viewToChange
     */
    void changeForm(String viewToChange);

    /**
     * Set Model
     * @param model
     */
    void setModel(IModel model);
}
