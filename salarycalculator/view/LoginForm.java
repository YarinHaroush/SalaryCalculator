package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.viewmodel.IViewModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  This class represent the new login form handle also with the sign user
 */
public class LoginForm extends JFrame implements ActionListener, IView {
    private static Logger logger = Logger.getLogger(LoginForm.class);

    private Container frame = getContentPane();
    private JLabel username = new JLabel("USERNAME");
    private JLabel passwordLabel = new JLabel("PASSWORD");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton signInButton = new JButton("Sign In");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private IViewModel viewModel;

    /**
     * C'tor
     */
    public LoginForm() {
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
        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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
     * Set layout
     */
    private void setLayoutManager() {
        frame.setLayout(null);
    }

    /**
     * Set location and size to all the components
     */
    private void setLocationAndSize() {
        username.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        signInButton.setBounds(200, 300, 100, 30);
    }

    /**
     * Add the components to the JFrame
     */
    private void addComponentsToContainer() {
        frame.add(username);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(showPassword);
        frame.add(loginButton);
        frame.add(signInButton);
    }

    /**
     * Add action event
     */
    public void addActionEvent() {
        loginButton.addActionListener(this);
        signInButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    /**
     * Action performed handle
     */
    public void actionPerformed(ActionEvent e) {

        String userText = userTextField.getText();
        String pwdText = passwordField.getText();

        if(userText.equals("") || pwdText.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please fill the text field!!!");
            return;
        }

        if (e.getSource() == loginButton) {
            if (viewModel.checkIfUserExist(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                visibleOffForm();
                viewModel.changeForm("menu");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == signInButton) {

            if (viewModel.signIn(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Sign Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Sign Failed Username Exist");
            }
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}