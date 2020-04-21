package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.viewmodel.IViewModel;
import il.ac.hit.salarycalculator.model.UserSetting;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represent the setting of the user UI component
 */
public class DetailsForm extends JFrame implements ActionListener, IView {

    private static Logger logger = Logger.getLogger(DetailsForm.class);

    private Container frame = getContentPane();
    private JTextField iDTextField = new JTextField();
    private JTextField userNameTextField = new JTextField();
    private JTextField ratePerHourTextField = new JTextField();
    private JLabel iDLabel = new JLabel("ID:");
    private JLabel userNameLabel = new JLabel("UserName:");
    private JLabel ratePerHourLabel = new JLabel("Rate Per Hour:");
    private JButton enterButton = new JButton("Enter");
    private JButton backButton = new JButton("Back");
    private JCheckBox travelExpensesCheckBox = new JCheckBox("Travel Expenses");
    private IViewModel viewModel;
    private double perHourSalary = 30;
    private String id = "";
    private String userName;

    /**
     * C'tor
     */
    public DetailsForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        logger.info("Create successfully..");
    }

    /**
     * Get the salary per hour
     * @return
     */
    public double getPerHour() {
        return perHourSalary;
    }

    public void setPerHour(double m_PerHour) {
        this.perHourSalary = m_PerHour;
        ratePerHourTextField.setText(Double.toString(m_PerHour));
    }

    /**
     * Get id
     * @return
     */
    public String getID() {
        return id;
    }

    /**
     * Set id
     * @param id
     */
    public void setID(String id) {
        this.id = id;
        iDTextField.setText(this.id);
    }

    /**
     * Show dialog of the form
     */
    public void showForm() {
        logger.info(DetailsForm.class +"was shown..");
        setSize(450, 600);
        setTitle("Calculate Salary");
        setVisible(true);
        logger.info("was shown..");
    }

    /**
     * Initialize the component from database,
     * If there is no setting of the user name create a new row.
     */
    public void initCom() {
        initUserName(viewModel.getUserName());
        if (viewModel.checkIfSettingExist(userName)) {
            loadSetting();
        } else {
            ratePerHourTextField.setText(Double.toString(perHourSalary));
            iDTextField.setText(id);
            viewModel.insertSettings(userName, perHourSalary, id);
        }
    }

    /**
     * This method load the user's setting
     */
    private void loadSetting() {
        logger.info("was load from db..");
        UserSetting user = viewModel.getSetting(userName);
        setPerHour(user.getSalary());
        setID(user.getId());
    }

    /**
     * Set viewmodel component
     * @param viewModel
     */
    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Hide the form
     */
    @Override
    public void visibleOffForm() {
        setVisible(false);
    }

    /**
     * Initialize the username
     * @param userName
     */
    private void initUserName(String userName) {
        this.userName = userName;
        userNameTextField.setText(this.userName);
        userNameTextField.setEnabled(false);
    }

    /**
     * Set the layout of the form
     */
    private void setLayoutManager() {
        frame.setLayout(null);
    }

    /**
     * Set the location and size  of the all component
     */
    private void setLocationAndSize() {
        iDLabel.setBounds(100, 100, 100, 30);
        userNameLabel.setBounds(54, 150, 100, 30);
        ratePerHourLabel.setBounds(33, 200, 100, 30);
        enterButton.setBounds(85, 450, 100, 30);
        backButton.setBounds(250, 450, 100, 30);
        iDTextField.setBounds(120, 100, 100, 30);
        userNameTextField.setBounds(120, 150, 100, 30);
        ratePerHourTextField.setBounds(120, 200, 100, 30);
        travelExpensesCheckBox.setBounds(120, 230, 150, 30);
    }

    /**
     * Add the components to the frame
     */
    private void addComponentsToContainer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(iDLabel);
        frame.add(userNameLabel);
        frame.add(ratePerHourLabel);
        frame.add(iDTextField);
        frame.add(userNameTextField);
        frame.add(ratePerHourTextField);
        frame.add(enterButton);
        frame.add(backButton);
        frame.add(travelExpensesCheckBox);
    }

    /**
     * Add action event
     */
    private void addActionEvent() {
        enterButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * change the details of the username
     */
    private void changeDetails() {
        logger.info("change details of user..");
        perHourSalary = Double.parseDouble(ratePerHourTextField.getText());
        id = iDTextField.getText() == null ? "" : iDTextField.getText();
    }

    /**
     * action performed handle
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            visibleOffForm();
            viewModel.changeForm("menu");
        } else if (e.getSource() == enterButton) {
            changeDetails();
            viewModel.changeSettings(userName, perHourSalary, id);
            JOptionPane.showMessageDialog(this, "Setting was Change");
            visibleOffForm();
            viewModel.changeForm("menu");
        }
    }
}

