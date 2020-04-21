package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.viewmodel.IViewModel;
import il.ac.hit.salarycalculator.model.Shift;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 *  This class represent the new shift to added of the user UI component
 */
public class HoursForm extends JFrame implements ActionListener, IView {
    private static Logger logger = Logger.getLogger(HoursForm.class);

    private Container frame = getContentPane();
    private JLabel checkIn = new JLabel("Check In:");
    private Date dateCal = new Date();
    private SpinnerDateModel spinner1 = new SpinnerDateModel(dateCal, null, null, Calendar.HOUR_OF_DAY);
    private SpinnerDateModel spinner2 = new SpinnerDateModel(dateCal, null, null, Calendar.HOUR_OF_DAY);
    private JSpinner jSpinnerCheckIn = new JSpinner(spinner1);
    private JSpinner jSpinnerCheckout = new JSpinner(spinner2);
    private JSpinner.DateEditor dateEditorCheckIn = new JSpinner.DateEditor(jSpinnerCheckIn, "HH:mm");
    private JSpinner.DateEditor dateEditorCheckout = new JSpinner.DateEditor(jSpinnerCheckout, "HH:mm");
    private JLabel checkOut = new JLabel("Check Out:");
    private JLabel dateLabel = new JLabel("Date:");
    private JButton date = new JButton("Date");
    private JButton submitButton = new JButton("SUBMIT");
    private JTextField dateTextField = new JTextField();
    private JButton back = new JButton("Back");
    private CalenderForm cal = new CalenderForm(frame);
    private IViewModel viewModel;

    /**
     * C'tor
     */
    public HoursForm() {
        initCom();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        logger.info("was create successfully");
    }

    /**
     * Show dialog of the form
     */
    public void showForm() {
        logger.info(HoursForm.class+" was shown..");
        addComponentsToContainer();
        setSize(450, 600);
        setTitle("Hours Form");
        setVisible(true);
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
        checkIn.setBounds(80, 100, 100, 30);
        checkOut.setBounds(70, 150, 100, 30);
        dateLabel.setBounds(100, 200, 100, 30);
        date.setBounds(250, 200, 100, 30);
        submitButton.setBounds(118, 450, 100, 30);
        jSpinnerCheckIn.setBounds(140, 100, 100, 30);
        jSpinnerCheckout.setBounds(140, 150, 100, 30);
        dateTextField.setBounds(140, 200, 100, 30);
        back.setBounds(250, 450, 100, 30);

    }

    /**
     * Initialize components
     */
    private void initCom() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jSpinnerCheckIn.setEditor(dateEditorCheckIn);
        jSpinnerCheckout.setEditor(dateEditorCheckout);
    }

    /**
     * Add the components to the frame
     */
    private void addComponentsToContainer() {
        frame.add(jSpinnerCheckIn);
        frame.add(jSpinnerCheckout);
        frame.add(checkIn);
        frame.add(checkOut);
        frame.add(submitButton);
        frame.add(dateLabel);
        frame.add(date);
        frame.add(dateTextField);
        frame.add(back);
        frame.setVisible(true);
    }

    /**
     * Add action event handle
     */
    private void addActionEvent() {
        submitButton.addActionListener(this);
        date.addActionListener(this);
        back.addActionListener(this);
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm() {
        setVisible(false);
    }

    /**
     * Set the view-model
     * @param viewModel
     */
    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Create a new shift from the text-field from the form
     */
    private Shift getShift() {
        Shift shift = new Shift(viewModel.getUserName());

        Date dateCheckIn = (Date) jSpinnerCheckIn.getValue();
        Date dateCheckout = (Date) jSpinnerCheckout.getValue();
        shift.setDate(dateTextField.getText());
        shift.setBegin(String.format("%02d:%02d", dateCheckIn.getHours(), dateCheckIn.getMinutes()));
        shift.setEnd(String.format("%02d:%02d", dateCheckout.getHours(), dateCheckout.getMinutes()));
        shift.setSalaryPerHour(viewModel.getSalaryPerHour());
        logger.info("was create a new shift");
        return shift;
    }

    /**
     * Action performed handle
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == date) {
            cal.showForm();
            dateTextField.setText(cal.setPickedDate());
        } else if (e.getSource() == back) {
            visibleOffForm();
            viewModel.changeForm("menu");
        } else if (e.getSource() == submitButton) {
            Shift shift = getShift();
            viewModel.addShift(shift);
            JOptionPane.showMessageDialog(this, "Shift was added");
            visibleOffForm();
            viewModel.changeForm("menu");
        }
    }
}
