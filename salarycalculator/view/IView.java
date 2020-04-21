package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.viewmodel.IViewModel;

import javax.swing.*;

/**
 * this interface handle for all the view
 * @param <T> extends from JFrame
 */
public interface IView <T extends JFrame>
{
    /**
     * Show the form
     */
    public void showForm();

    /**
     * Hide the form
     */
    public void visibleOffForm();

    /**
     * Set the view-model
     * @param i_ViewModel
     */
    public void setViewModel(IViewModel i_ViewModel);
}
