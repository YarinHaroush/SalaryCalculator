package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.viewmodel.IViewModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  This class represent the main menu of the form
 */
public class MenuForm extends JFrame implements ActionListener , IView {
    private static Logger logger = Logger.getLogger(MenuForm.class);
    private Container frame = getContentPane();
    private JButton settings = new JButton("Settings");
    private JButton workingDay = new JButton("Add New Shift");
    private JButton totalDays = new JButton("Total Shifts");
    private IViewModel viewModel;

    /**
     * C'tor
     */
    public MenuForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        logger.info("was create successfully..");
    }

    /**
     * Show dialog of the form
     */
    public void showForm() {
        addComponentsToContainer();
        setSize(450, 600);
        setTitle("Menu Form");
        setVisible(true);
        logger.info("was shown..");
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm() {
        setVisible(false);
    }

    /**
     * Set view -model handle
     * @param viewModel
     */
    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Set layout
     */
    private void setLayoutManager() {
        frame.setLayout(null);
    }

    /**
     * Set location and size for the components
     */
    private void setLocationAndSize() {
        settings.setBounds(150, 100, 150, 60);
        workingDay.setBounds(150, 250, 150, 60);
        totalDays.setBounds(150, 400, 150, 60);

    }

    /**
     * Add the components to the frame
     */
    private void addComponentsToContainer()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(settings);
        frame.add(workingDay);
        frame.add(totalDays);
    }

    /**
     * Add action event
     */
    private void addActionEvent() {
        settings.addActionListener(this);
        workingDay.addActionListener(this);
        totalDays.addActionListener(this);
    }


    /**
     * action performed handle
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        visibleOffForm();
        if (e.getSource() == settings) {
            viewModel.changeForm("details");
        }
        else if (e.getSource() == workingDay)
        {
            viewModel.changeForm("shift");
        }
        else if(e.getSource() == totalDays)
        {
            viewModel.changeForm("table");
        }
    }
}

