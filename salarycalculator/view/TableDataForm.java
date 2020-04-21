package il.ac.hit.salarycalculator.view;

import il.ac.hit.salarycalculator.exception.GetAllException;
import il.ac.hit.salarycalculator.viewmodel.IViewModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represent all the shifts in of big table UI component
 */
public class TableDataForm extends JFrame implements ActionListener , IView {
    private static Logger logger = Logger.getLogger(TableDataForm.class);

    private Container frame =  getContentPane();
    private JPanel panel = new JPanel();
    private String[][] dataVector = new String[0][0];


    private String[] columnNames = {"UserName",
            "Date",
            "BeginHour",
            "EndHour",
            "Total"};

    private JTable dataTable;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultTableModel;
    private JButton back = new JButton("Back");
    private IViewModel viewModel;

    /**
     * C'tor
     */
    public TableDataForm() {
        setLayoutManager();
        initButton();
        setLocationAndSize();
        addActionEvent();
        logger.info("was create successfully..");
    }

    /**
     * initialize all the components
     */
    private void initButton()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        defaultTableModel =  new DefaultTableModel(dataVector, columnNames);
        dataTable = new JTable(defaultTableModel);
        panel.add(dataTable.getTableHeader());
        panel.add(dataTable);
        scrollPane = new JScrollPane(dataTable);
        scrollPane.add(panel);
        frame.add(panel);
        frame.add(scrollPane);
        frame.add(back);
    }

    /**
     * Set layout
     */
    private void setLayoutManager() {
        frame.setLayout(new FlowLayout());
    }

    /**
     * Get the all the shifts from IModel use another thread
     */
    private void getData() {
        //anonymous inner class implements runnable interface
        Runnable run = new Runnable() {
            @Override
            public void run() {
                logger.info("start to get data from db..");
                if(defaultTableModel.getRowCount()>=0) {
                    defaultTableModel.setRowCount(1);
                }
                try {
                ResultSet rs = viewModel.getAllShift(viewModel.getUserName());
                String UserName = null;  //User name fill from the database
                String Date = null;   // date fill from database
                String Begin = null;  //begin fill from database
                String End = null;   //end fill from database
                String Total = null;  //total salary fill from database

                    while (rs.next()) {
                        UserName = rs.getString("UserName");
                        Date = rs.getString("Date");
                        Begin = rs.getString("BeginHour");
                        End = rs.getString("EndHour");
                        Total = rs.getString("Total");


                        String finalUserName = UserName;    //final user use another thread (inner class)
                        String finalDate = Date;   //final date use another thread (inner class)
                        String finalBegin = Begin;  //final begin use another thread (inner class)
                        String finalEnd = End;   //final end use another thread (inner class)
                        String finalTotal = Total;  //final total use another thread (inner class)
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                defaultTableModel.insertRow(dataTable.getRowCount(), new Object[]
                                        {finalUserName, finalDate, finalBegin, finalEnd, finalTotal});
                            }
                            });

                    }
                    logger.info("fetch data was end  successfully..");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                catch (GetAllException g)
                {
                    g.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(run);
        thread.start();
    }

    /**
     * Show from dialog
     */
    public void showForm() {
        getData();
        setSize(500, 600);
        setVisible(true);
        logger.info("was shown..");
    }

    /**
     * Hide form
     */
    public void visibleOffForm()
    {
        setVisible(false);
    }

    /**
     * Set View-Model
     * @param viewModel
     */
    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Set location and size for the components
     */
    private void setLocationAndSize()
    {
        dataTable.setBounds(50, 60, 350, 400);
        back.setBounds(250, 450, 100, 30);
    }

    /**
     * Add action event
     */
    private void addActionEvent() {
        back.addActionListener(this);
    }

    /**
     * Action performed handle
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back)
        {
            visibleOffForm();
            viewModel.changeForm("menu");
        }
    }
}

