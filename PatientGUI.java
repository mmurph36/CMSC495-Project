import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class PatientGUI extends JPanel {

    public PatientGUI() {

        setLayout(new BorderLayout());


        // Main panel

        JPanel mainPanel = new JPanel(new GridBagLayout());


        GridBagConstraints mainPanelConstraints = new GridBagConstraints();

        // create label

        JLabel chooseLabel = new JLabel("Choose New or Existing Patient");

        // create buttons

        JButton existingPatientButton = new JButton("Existing Patient");
        JButton newPatientButton = new JButton("New Patient");
        JButton backButton = new JButton("Back");

        // set label font

        chooseLabel.setFont(new Font("Serif", Font.PLAIN, 40));

        // set constraints for components and add
        // to the main panel
        mainPanelConstraints.gridx = 10;
        mainPanelConstraints.gridy = 10;
        mainPanelConstraints.weighty = 1;
        mainPanelConstraints.anchor = GridBagConstraints.NORTH;
        mainPanelConstraints.insets = new Insets(60, 0, 0, 0);

        mainPanel.add(chooseLabel, mainPanelConstraints);


        mainPanelConstraints.weighty = 0;
        mainPanelConstraints.ipady = 10;
        mainPanelConstraints.anchor = GridBagConstraints.WEST;
        mainPanelConstraints.insets = new Insets(30, 110, 0, 0);

        mainPanel.add(existingPatientButton, mainPanelConstraints);


        mainPanelConstraints.ipadx = 20;
        mainPanelConstraints.anchor = GridBagConstraints.EAST;
        mainPanelConstraints.insets = new Insets(30, 0, 0, 110);

        mainPanel.add(newPatientButton, mainPanelConstraints);


        mainPanelConstraints.gridy = 20;
        mainPanelConstraints.weighty = 1;
        mainPanelConstraints.ipadx = 45;
        mainPanelConstraints.ipady = 5;
        mainPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        mainPanelConstraints.insets = new Insets(0, 0, 10, 0);

        mainPanel.add(backButton, mainPanelConstraints);


        add(mainPanel);



        // Login Panel

        JPanel loginPanel = new JPanel(new GridBagLayout());


        GridBagConstraints loginConstraints = new GridBagConstraints();

        JLabel logInLabel = new JLabel("Patient Login");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameTextField = new JTextField(12);
        JTextField passwordTextField = new JTextField(12);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        logInLabel.setFont(new Font("Serif", Font.PLAIN, 40));


        loginConstraints.gridx = 10;
        loginConstraints.gridy = 10;
        loginConstraints.weighty = 0.2;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(50, 5, 0, 30);

        loginPanel.add(logInLabel, loginConstraints);


        loginConstraints.gridy = 20;
        loginConstraints.weighty = 0;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(40, 0, 0, 10);

        loginPanel.add(usernameLabel, loginConstraints);


        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(10, 0, 0, 10);

        loginPanel.add(passwordLabel, loginConstraints);

        loginConstraints.gridx = 20;
        loginConstraints.gridy = 20;
        loginConstraints.gridwidth = 20;
        loginConstraints.insets = new Insets(40, 0, 0, 10);

        loginPanel.add(usernameTextField, loginConstraints);

        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(12, 0, 0, 10);

        loginPanel.add(passwordTextField, loginConstraints);


        loginConstraints.gridx = 10;
        loginConstraints.gridy = 40;
        loginConstraints.weighty = 1;
        loginConstraints.ipadx = 15;
        loginConstraints.ipady = 5;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTHWEST;
        loginConstraints.insets = new Insets(30, 15, 0, 0);

        loginPanel.add(loginButton, loginConstraints);

        loginConstraints.gridx = 20;
        loginConstraints.gridy = 40;
        loginConstraints.ipadx = 10;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(30, 35, 0, 0);

        loginPanel.add(cancelButton, loginConstraints);



        // Create new patient panel

        JPanel createNewPatientPanel = new JPanel(new GridBagLayout());


        GridBagConstraints createNewPatientConstraints = new GridBagConstraints();

        JLabel createNewPatientLabel = new JLabel("New Patient");
        JLabel usernameLabel_cnp = new JLabel("Username:");
        JLabel passwordLabel_cnp = new JLabel("Password:");

        JTextField usernameTextField_cnp = new JTextField(12);
        JTextField passwordTextField_cnp = new JTextField(12);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton_cnp = new JButton("Cancel");

        createNewPatientLabel.setFont
                (new Font("Serif", Font.PLAIN, 40));

        createNewPatientConstraints.gridx = 10;
        createNewPatientConstraints.gridy = 0;
        createNewPatientConstraints.weighty = 0.2;
        createNewPatientConstraints.gridwidth = 20;
        createNewPatientConstraints.anchor = GridBagConstraints.NORTH;
        createNewPatientConstraints.insets = new Insets(50, 10, 0, 30);

        createNewPatientPanel.add(createNewPatientLabel, createNewPatientConstraints);


        createNewPatientConstraints.gridy = 10;
        createNewPatientConstraints.weighty = 0;
        createNewPatientConstraints.gridwidth = 10;
        createNewPatientConstraints.anchor = GridBagConstraints.CENTER;
        createNewPatientConstraints.insets = new Insets(40, 0, 0, 10);

        createNewPatientPanel.add(usernameLabel_cnp, createNewPatientConstraints);


        createNewPatientConstraints.gridy = 20;
        createNewPatientConstraints.gridwidth = 10;
        createNewPatientConstraints.insets = new Insets(10, 0, 0, 10);

        createNewPatientPanel.add(passwordLabel_cnp, createNewPatientConstraints);


        createNewPatientConstraints.gridx = 20;
        createNewPatientConstraints.gridy = 10;
        createNewPatientConstraints.gridwidth = 20;
        createNewPatientConstraints.insets = new Insets(40, 0, 0, 10);

        createNewPatientPanel.add(usernameTextField_cnp, createNewPatientConstraints);


        createNewPatientConstraints.gridy = 20;
        createNewPatientConstraints.gridwidth = 10;
        createNewPatientConstraints.insets = new Insets(12, 0, 0, 10);

        createNewPatientPanel.add(passwordTextField_cnp, createNewPatientConstraints);


        createNewPatientConstraints.gridx = 10;
        createNewPatientConstraints.gridy = 30;
        createNewPatientConstraints.weighty = 1;
        createNewPatientConstraints.ipadx = 15;
        createNewPatientConstraints.ipady = 5;
        createNewPatientConstraints.gridwidth = 20;
        createNewPatientConstraints.anchor = GridBagConstraints.NORTHWEST;
        createNewPatientConstraints.insets = new Insets(30, 10, 0, 0);

        createNewPatientPanel.add(submitButton, createNewPatientConstraints);


        createNewPatientConstraints.gridx = 20;
        createNewPatientConstraints.ipadx = 10;
        createNewPatientConstraints.gridwidth = 10;
        createNewPatientConstraints.insets = new Insets(30, 45, 0, 0);

        createNewPatientPanel.add(cancelButton_cnp, createNewPatientConstraints);



        // Tabbed Panel

        JTabbedPane tabbedPane = new JTabbedPane();

        String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",

                "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida",

                "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",

                "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",

                "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",

                "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",

                "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",

                "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",

                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",

                "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin",

                "Wyoming"};



        // create the patient info panel

        JPanel patientInfoPanel = new JPanel(new GridBagLayout());

        GridBagConstraints patientInfoPanelConstraints = new GridBagConstraints();

        // create labels

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel middleNameLabel = new JLabel("Middle Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel SSNLabel = new JLabel("Social Security #:");
        JLabel DOBLabel = new JLabel("Date of Birth:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel cityLabel = new JLabel("City:");
        JLabel stateLabel = new JLabel("State:");
        JLabel zipCodeLabel = new JLabel("Zip Code:");

        // create text fields

        JTextField firstNameTextField = new JTextField(12);
        JTextField middleNameTextField = new JTextField(12);
        JTextField lastNameTextField = new JTextField(12);
        JTextField SSNTextField = new JTextField(12);
        JTextField DOBTextField = new JTextField(12);
        JTextField phoneNumberTextField = new JTextField(12);
        JTextField streetTextField = new JTextField(12);
        JTextField cityTextField = new JTextField(12);
        JTextField zipCodeTextField = new JTextField(12);

        // create combo box
        JComboBox<String> stateComboBox = new JComboBox<>(states);

        // create buttons
        JButton updateInfoButton = new JButton("Update Information");
        JButton logoutButton_PI = new JButton("Log Out");


        // set the constraints for each component and add
        // them to the patient info panel

        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.weightx = 1;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        patientInfoPanel.add(lastNameLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;

        patientInfoPanel.add(firstNameLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;

        patientInfoPanel.add(SSNLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 30;

        patientInfoPanel.add(DOBLabel, patientInfoPanelConstraints);

        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.weighty = 1;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        patientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);

        patientInfoPanel.add(phoneNumberLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);

        patientInfoPanel.add(streetLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        patientInfoPanel.add(cityLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeLabel, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(lastNameTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        patientInfoPanel.add(firstNameTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(SSNTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 30;

        patientInfoPanel.add(DOBTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        patientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);

        patientInfoPanel.add(phoneNumberTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);

        patientInfoPanel.add(streetTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(cityTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateComboBox, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeTextField, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.ipady = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.CENTER;

        patientInfoPanel.add(updateInfoButton, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 30;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;

        patientInfoPanel.add(logoutButton_PI, patientInfoPanelConstraints);


        // create calendar panel

        JPanel calendarPanel = new JPanel(new GridBagLayout());

        GridBagConstraints calendarConstraints = new GridBagConstraints();

        // create label

        JLabel chooseDateAndTimeLabel =
                new JLabel("Select Date and Time For Appointment");

        // create date picker

        DatePicker datePicker = createDatePicker();

        // create time picker

        TimePicker timePicker = createTimePicker();

        // create buttons

        JButton requestAppointmentButton = new JButton("Request Appointment");
        JButton logoutButton_calendar = new JButton("Log Out");


        // set the label font

        chooseDateAndTimeLabel.setFont(new Font("Serif", Font.PLAIN, 25));


        // set the constraints for each component and add
        // them to the calendar panel

        calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 10;
        calendarConstraints.weighty = 1;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(20, 0, 0, 0);

        calendarPanel.add(chooseDateAndTimeLabel, calendarConstraints);


        calendarConstraints.weighty = 0;
        calendarConstraints.anchor = GridBagConstraints.WEST;
        calendarConstraints.insets = new Insets(0, 70, 0, 0);

        calendarPanel.add(datePicker, calendarConstraints);


        calendarConstraints.anchor = GridBagConstraints.EAST;
        calendarConstraints.insets = new Insets(0, 0, 0, 75);

        calendarPanel.add(timePicker, calendarConstraints);


        calendarConstraints.ipady = 10;
        calendarConstraints.anchor = GridBagConstraints.SOUTH;
        calendarConstraints.insets = new Insets(0, 0, 60, 0);

        calendarPanel.add(requestAppointmentButton, calendarConstraints);


        calendarConstraints.gridy = 20;
        calendarConstraints.weighty = 1;
        calendarConstraints.anchor = GridBagConstraints.NORTHEAST;

        calendarPanel.add(logoutButton_calendar, calendarConstraints);



        // add patient and calendar panels to tabbed pane

        tabbedPane.add("Patient Information", patientInfoPanel);
        tabbedPane.add("Calendar", calendarPanel);



        // create ActionListeners for all the buttons

        existingPatientButton.addActionListener(e -> {
            remove(mainPanel);
            add(loginPanel);
            repaint();
            revalidate();
        });

        newPatientButton.addActionListener(e -> {
            remove(mainPanel);
            add(createNewPatientPanel);
            repaint();
            revalidate();
        });

        backButton.addActionListener(e -> {
        });


        loginButton.addActionListener(e -> {
            remove(loginPanel);
            add(tabbedPane);
            JOptionPane.showMessageDialog
                    (null, "Login Successful");
            repaint();
            revalidate();

        });

        cancelButton.addActionListener(e -> {
            remove(loginPanel);
            add(mainPanel);
            repaint();
            revalidate();
        });


        submitButton.addActionListener(e -> {
            remove(createNewPatientPanel);
            add(tabbedPane);
            JOptionPane.showMessageDialog
                    (null, "Submission Successful");
            repaint();
            revalidate();
        });


        cancelButton_cnp.addActionListener(e -> {
            remove(createNewPatientPanel);
            add(mainPanel);
            repaint();
            revalidate();
        });


        updateInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog
                    (null, "Information Updated");
        });

        requestAppointmentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog
                    (null, "Appointment Saved");
        });

        logoutButton_PI.addActionListener(e -> {
            remove(tabbedPane);
            add(mainPanel);
            JOptionPane.showMessageDialog
                    (null, "Logout Successful");
            repaint();
            revalidate();
        });


        logoutButton_calendar.addActionListener(e -> {
            remove(tabbedPane);
            add(mainPanel);
            JOptionPane.showMessageDialog
                    (null, "Logout Successful");
            repaint();
            revalidate();
        });
    }




    // method to create a date picker

    private DatePicker createDatePicker() {

        DatePickerSettings datePickerSettings = new DatePickerSettings();

        datePickerSettings.setAllowEmptyDates(false);

        datePickerSettings.setAllowKeyboardEditing(false);

        DatePicker datePicker = new DatePicker(datePickerSettings);

        System.out.println(LocalDate.now().getDayOfMonth());

        // If today is Saturday or Sunday, this sets the default
        // to the following Monday

        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            datePicker.setDate(LocalDate.now().plusDays(2));
        } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            datePicker.setDate(LocalDate.now().plusDays(1));
        } else datePicker.setDate(LocalDate.now());

        // Veto Policy to disallow weekends

        datePickerSettings.setVetoPolicy(new VetoWeekends());

        return datePicker;
    }




    // method to create a time picker

    private TimePicker createTimePicker() {

        TimePickerSettings timeSettings = new TimePickerSettings();

        timeSettings.initialTime = LocalTime.of(9, 0);

        timeSettings.setAllowKeyboardEditing(false);

        timeSettings.generatePotentialMenuTimes
                (TimePickerSettings.TimeIncrement.OneHour,
                        null, null);


        TimePicker timePicker = new TimePicker(timeSettings);

        timeSettings.setVetoPolicy(new VetoTimes());

        return timePicker;
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

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setTitle("Patient Panel");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new PatientGUI());

        frame.setVisible(true);
    }
}
