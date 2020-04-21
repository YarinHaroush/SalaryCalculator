package il.ac.hit.salarycalculator.viewmodel;

import il.ac.hit.salarycalculator.exception.GetAllException;
import il.ac.hit.salarycalculator.model.*;
import il.ac.hit.salarycalculator.view.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;

/**
 * This Class Management all the program it implements from IViewModel
 */
public class ManagementViewModel implements IViewModel {
    private static Logger logger = Logger.getLogger(ManagementViewModel.class);
    private static ManagementViewModel instance = null;
    private static Object lock = new Object();

    private HoursForm hoursForm;
    private LoginForm loginForm;
    private MenuForm menuForm;
    private DetailsForm detailsForm;
    private TableDataForm tableData;
    private String userName;
    private ReportShifts reportShift;
    private UserUtils userUtils;
    private IModel model;

    /**
     * C'tor
     */
    private ManagementViewModel() {
        setUserUtils(new UserUtils());
        setReportShift(new ReportShifts());
        logger.info("was create successfully");
    }

    /**
     * Initialize the class
     */
    public void init() {
        loginForm.setViewModel(this);
        menuForm.setViewModel(this);
        detailsForm.setViewModel(this);
        tableData.setViewModel(this);
        hoursForm.setViewModel(this);
        userUtils.setModel(model);
        reportShift.setModel(model);
        userUtils.initialize();
        reportShift.initialize();
    }

    /**
     * return instance of the class implements singleton
     * @return ManagementViewModel single instance
     */
    public static ManagementViewModel getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ManagementViewModel();
                }
            }
        }
        return instance;
    }

    /**
     * Get hoursForm
     * @return HoursForm
     */
    public HoursForm getHoursForm() {
        return hoursForm;
    }

    /**
     * Set HoursForm
     * @param hoursForm
     */
    public void setHoursForm(HoursForm hoursForm) {
        this.hoursForm = hoursForm;
    }

    /**
     * get LoginForm
     * @return LoginForm
     */
    public LoginForm getLoginForm() {
        return loginForm;
    }

    /**
     * Set loginForm
     * @param loginForm
     */
    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    /**
     * Get MenuForm
     * @return MenuForm
     */
    public MenuForm getMenuForm() {
        return menuForm;
    }

    /**
     * Set MenuForm
     * @param menuForm
     */
    public void setMenuForm(MenuForm menuForm) {
        this.menuForm = menuForm;
    }

    /**
     * Get Details Form
     * @return
     */
    public DetailsForm getDetailsForm() {
        return detailsForm;
    }

    /**
     * Set DetailsForm
     * @param detailsForm
     */
    public void setDetailsForm(DetailsForm detailsForm) {
        this.detailsForm = detailsForm;
    }

    /**
     * Get TableForm
     * @return
     */
    public TableDataForm getTableData() {
        return tableData;
    }

    /**
     * Set table form
     * @param tableData
     */
    public void setTableData(TableDataForm tableData) {
        this.tableData = tableData;
    }

    /**
     * Set User name
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get report shift
     * @return  ReportShift
     */
    public ReportShifts getReportShift() {
        return reportShift;
    }

    /**
     * Set report-shift
     * @param reportShift
     */
    public void setReportShift(ReportShifts reportShift) {
        this.reportShift = reportShift;
    }

    /**
     * Get user utils
     * @return User utils
     */
    public UserUtils getUserUtils() {
        return userUtils;
    }

    /**
     * Set user Utils
     * @param userUtils user utils to set
     */
    public void setUserUtils(UserUtils userUtils) {
        this.userUtils = userUtils;
    }

    /**
     * Get IModel
     * @return IModel
     */
    public IModel getModel() {
        return model;
    }

    /**
     * Set Model
     * @param model
     */
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * Get User name
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set salary per hour
     * @return
     */
    public double getSalaryPerHour() {
        return detailsForm.getPerHour();
    }

    /**
     * Show the first form
     */
    public void startProgram() {
        logger.info(" start program method");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                loginForm.showForm();
            }
        });
    }

    /**
     *
     * @param viewToChange string that represent the form to change
     */
    @Override
    public void changeForm(String viewToChange) {
        if (viewToChange == null) {
            throw new NullPointerException();
        }

        switch (viewToChange) {
            case "menu":
                showMenuFrom();
                break;
            case "details":
                showDetailsForm();
                break;
            case "table":
                showTableData();
                break;
            case "shift":
                showHoursForm();
                break;
        }

    }

    /**
     * Show menu form
     */
    private void showMenuFrom() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                menuForm.showForm();
            }
        });
    }

    /**
     * Show Details form
     */
    private void showDetailsForm() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                detailsForm.showForm();
            }
        });
    }

    /**
     * Show Hours Form
     */
    private void showHoursForm() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hoursForm.showForm();
            }
        });
    }

    /**
     * Show Table Data
     */
    private void showTableData() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tableData.showForm();
            }
        });
    }

    /**
     *
     * @param userName user name that check if exist
     * @param password password of the user name
     * @return true or false if the user exist
     */
    public boolean checkIfUserExist(final String userName, final String password) {

        if (userName == null || password == null) {
            throw new NullPointerException();
        }

        final Boolean[] valueToReturn = {false};

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (userUtils.checkIfUserExist(userName, password)) {
                    valueToReturn[0] = true;
                    ManagementViewModel.this.userName = userName;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            detailsForm.initCom();
                        }
                    });
                } else {
                    System.out.println("Check");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return valueToReturn[0];
    }

    /**
     *
     * @param userName username that want ot add
     * @param password password want to add
     * @return true or false if operation is success
     */
    public boolean signIn(String userName, String password) {
        if (userName == null || password == null) {
            throw new NullPointerException();
        }


        logger.info(" was sign a new user..");
        if (!userUtils.checkIfUserExist(userName, password)) {
            return userUtils.signIn(userName, password);
        }
        return false;
    }

    /**
     * Add a new shift to IModel
     * @param shiftToAdd a shift to add
     */
    public void addShift(Shift shiftToAdd) {
        if (shiftToAdd == null) {
            throw new NullPointerException();
        }

        Runnable run = new Runnable() {
            @Override
            public void run() {
                logger.info(" was added a new shift..");
                reportShift.addShift(shiftToAdd);
            }
        };

        Thread thread = new Thread(run);
        thread.start();
    }

    /**
     * @param userName the user username, can't be null
     * @return Result set that represent the table
     * with all shifts to the specific user
     */
    public ResultSet getAllShift(String userName) {
        if (userName == null) {
            throw new NullPointerException();
        }

        try {
            return reportShift.getAllShift(userName);
        } catch (GetAllException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Change the setting in the data base
     *
     * @param userName user name to change
     * @param salary salary to change
     * @param iD to change
     * @return true or false if the operation success
     */
    public boolean changeSettings(String userName, double salary, String iD) {
        if (userName == null || iD == null) {
            throw new NullPointerException();
        }
        logger.info(" change setting was occurred..");
        return userUtils.changeSettings(userName, salary, iD);
    }

    /**
     * Check if there is setting in the data base for the specific username
     *
     * @param userName
     * @return true of false if the user exist
     */
    public boolean checkIfSettingExist(String userName) {
        if (userName == null) {
            throw new NullPointerException();
        }
        logger.info(ManagementViewModel.class + " check if setting exist occurred");
        return userUtils.checkIfSettingExist(userName);
    }

    /**
     * Return the setting for the specific username
     * @param userName user name setting
     * @return UserSetting class that contain all the setting of the user
     */
    public UserSetting getSetting(String userName) {
        if (userName == null) {
            throw new NullPointerException();
        }
        return userUtils.getSetting(userName);
    }

    /**
     * @param userName user name to insert into to database
     * @param salary salary to insert
     * @param iD id to insert
     * @return true or false if the operation success
     */
    public boolean insertSettings(String userName, double salary, String iD) {
        if (userName == null || iD == null) {
            throw new NullPointerException();
        }
        logger.info(" insert setting to db..");
        return userUtils.insertSettings(userName, salary, iD);
    }

}
