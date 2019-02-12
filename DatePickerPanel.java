import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import com.github.lgooddatepicker.components.DatePicker;

import com.github.lgooddatepicker.components.DatePickerSettings;

import com.github.lgooddatepicker.components.TimePicker;

import com.github.lgooddatepicker.components.TimePickerSettings;

import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.InternalUtilities;

import java.awt.Dimension;

import java.awt.FlowLayout;

import java.time.DayOfWeek;

import java.time.LocalDate;

import java.time.LocalTime;

import javax.swing.*;


/**
 * BasicDemo,
 *
 * This class demonstrates the most basic usage of the date and time picker components. More
 * specifically, this class only demonstrates how to create the components and add them to a form.
 * For a more extensive demonstration of all library components and their various optional settings,
 * see "FullDemo.java".
 */

public class DatePickerPanel extends JPanel {



    /**
     * main, This is the entry point for the basic demo.
     */

    public static void main(String[] args) {

        // Use the standard swing code to start this demo inside a swing thread.

        SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                // Create an instance of the demo.

                DatePickerPanel basicDemo = new DatePickerPanel();

                // Make the demo visible on the screen.

                basicDemo.setVisible(true);

            }

        });

    }



    /**
     * Default Constructor.
     */

    public DatePickerPanel() {

        initializeComponents();

    }



    /**
     * initializeComponents, This creates the user interface for the basic demo.
     */

    private void initializeComponents() {

        // Set up the form which holds the date picker components.

        //setTitle("LGoodDatePicker Basic Demo " + InternalUtilities.getProjectVersionString());

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        setSize(new Dimension(640, 480));

        //setLocationRelativeTo(null);




        // Create a date picker, and add it to the form.

        DatePickerSettings datePickerSettings = new DatePickerSettings();

        datePickerSettings.setAllowEmptyDates(false);

        datePickerSettings.setAllowKeyboardEditing(false);

        DatePicker datePicker1 = new DatePicker(datePickerSettings);

        System.out.println(LocalDate.now().getDayOfMonth());

        // If today is Saturday or Sunday, this sets the default
        // to the following Monday

        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            datePicker1.setDate(LocalDate.now().plusDays(2));
        } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            datePicker1.setDate(LocalDate.now().plusDays(1));
        } else datePicker1.setDate(LocalDate.now());

        // Veto Policy to disallow weekends

        datePickerSettings.setVetoPolicy(new VetoWeekends());

        add(datePicker1);




        // Create a time picker, and add it to the form.

        TimePickerSettings timeSettings = new TimePickerSettings();

        timeSettings.initialTime = LocalTime.of(9, 0);

        timeSettings.setAllowKeyboardEditing(false);

        timeSettings.generatePotentialMenuTimes
                (TimePickerSettings.TimeIncrement.OneHour,
                        null, null);


        TimePicker timePicker1 = new TimePicker(timeSettings);

        timeSettings.setVetoPolicy(new VetoTimes());



        add(timePicker1);


        // display the chosen date and time in a textfield

        JButton getDateButton = new JButton("Get date");

        JTextField getDateField = new JTextField(15);

        add(getDateButton);
        add(getDateField);

        getDateButton.addActionListener(e ->
            getDateField.setText(datePicker1.getText()));


        JButton getTimeButton = new JButton("Get time");

        JTextField getTimeField = new JTextField(15);

        getTimeButton.addActionListener(e ->
        getTimeField.setText(timePicker1.getText()));

        add(getTimeButton);
        add(getTimeField);
    }

    // class to disallow choosing Saturdays and Sundays

    private class VetoWeekends implements DateVetoPolicy {

        @Override
        public boolean isDateAllowed(LocalDate localDate) {
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                return false;
            return true;
        }
    }

    // Class to disallow choosing times before 9am
    // and after 5pm

    private class VetoTimes implements TimeVetoPolicy {

        @Override
        public boolean isTimeAllowed(LocalTime time) {

            // Only allow times from 9a to 5p, inclusive.

            return PickerUtilities.isLocalTimeInRange(

                    time, LocalTime.of(9, 0), LocalTime.of(16, 30), true);

        }
    }
}