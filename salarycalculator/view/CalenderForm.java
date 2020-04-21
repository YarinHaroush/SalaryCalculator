package il.ac.hit.salarycalculator.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represent the calender form UI component
 */
class CalenderForm extends JFrame implements ActionListener {
    private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    private JLabel label = new JLabel("", JLabel.CENTER);
    private String day = "";
    private JDialog dialog = new JDialog();
    private JButton[] button = new JButton[49];
    private JPanel panel = new JPanel(new GridLayout(7, 7));
    private JPanel panel2 = new JPanel(new GridLayout(1, 3));
    private JButton previous = new JButton("<< Previous");
    private JButton next = new JButton("Next >>");

    /**
     * C'tor
     * @param parent
     */
    public CalenderForm(Container parent) {
        addActionEvent();
        dialog.setModal(true);
        panel.setPreferredSize(new Dimension(430, 120));
        initButton();
        panel2.add(previous);
        panel2.add(label);
        panel2.add(next);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
    }

    /**
     * Show dialog of the form
     */
    public void showForm() {
        displayDate();
        dialog.setVisible(true);
    }

    /**
     * initialize the button of calender
     */
    private void initButton() {
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6)
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        dialog.dispose();
                    }
                });
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            panel.add(button[x]);
        }
    }

    /**
     * display the date
     */
    private void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        label.setText(sdf.format(cal.getTime()));
        dialog.setTitle("Date Picker");
    }

    /**
     * @return String that represent the date that chosen
     */
    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd/MM/yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

    /**
     * add action event
     */
    private void addActionEvent() {
        previous.addActionListener(this);
        next.addActionListener(this);
    }

    /**
     * Handle of action event
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previous) {
            month--;
            displayDate();
        } else if (e.getSource() == next) {
            month++;
            displayDate();
        }
    }
}

