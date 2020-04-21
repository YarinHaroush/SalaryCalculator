package il.ac.hit.salarycalculator.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represent the shift
 */
public class Shift {
    private String userName;
    private Date date;
    private Date begin;
    private Date end;
    private double salaryPerHour = 30;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    /**
     * C'tor
     * @param userName
     */
    public Shift(String userName) {
        setUserName(userName);
    }

    /**
     *
     * @param userName the user name owner shift
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return User name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        if (date == null)
        {
            throw new NullPointerException();
        }

        try {
            this.date = dateFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reformat the time to hh::mm
     * @return String in format hh::mm that represent the time begin
     */
    public String getBegin() {
        return String.format("%02d:%02d", begin.getHours(), begin.getMinutes());
    }

    /**
     * Reformat the time to hh::mm
     * @return String in format hh::mm that represent the time end
     */
    public String getEnd() {
        return String.format("%02d:%02d", end.getHours(), end.getMinutes());
    }

    /**
     * Set time was the shift started hh:MM
     * @param begin the time was the shift started hh:MM
     */
    public void setBegin(String begin) {
        if (begin == null)
        {
            throw new NullPointerException();
        }

        try {
            this.begin = timeFormatter.parse(begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set time was the shift ended hh:MM
     * @param end time was the shift ended hh:MM
     */
    public void setEnd(String end) {
        if (end == null)
        {
            throw new NullPointerException();
        }
        try {
            this.end = timeFormatter.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the Salary per hour for a specific user
     * @param salaryPerHour the salary per hour
     */
    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    /**
     * this method calculate the total monet for a specific user that earn in this shift
     * @return Salary calculate
     */
    public double getTotalSalary() {
        double salary = 0;

        if (end != null) {
            double hourEnd = end.getHours();
            double hourBegin = begin.getHours();
            double minutesEnd = end.getMinutes();
            double minutesBegin = begin.getMinutes();

            if (hourEnd < hourBegin) {
                hourEnd %= 12;
                hourBegin %= 12;
                hourEnd += 12;
            }

            salary =
                    ((hourEnd - hourBegin) * salaryPerHour) +
                            ((minutesEnd - minutesBegin) / 60) * salaryPerHour;
        }
        return salary;
    }
}
