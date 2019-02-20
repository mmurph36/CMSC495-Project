/* Author: Ari Ohsie & Esther Ho
 * CMSC 495
 * PIMS Project
 *
 * File Name: PatientGUI.java
 *
 *
 *
 * DISCLAIMER: EmployeeGUI & PatientGUI use code from the following project for the calendar
 *  https://github.com/LGoodDatePicker/LGoodDatePicker
 */
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

	String patientGUItitle = "Patient PIMS"; 

    public PatientGUI() {
        initialize();
    }


    private void initialize() {


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
        mainPanelConstraints.weighty = 0.2;
        mainPanelConstraints.anchor = GridBagConstraints.CENTER;
        mainPanelConstraints.insets = new Insets(40, 0, 0, 0);

        mainPanel.add(chooseLabel, mainPanelConstraints);


        mainPanelConstraints.gridy = 20;
        mainPanelConstraints.weighty = 1;
        mainPanelConstraints.ipady = 10;
        mainPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanelConstraints.insets = new Insets(30, 110, 0, 0);

        mainPanel.add(existingPatientButton, mainPanelConstraints);


        mainPanelConstraints.ipadx = 20;
        mainPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        mainPanelConstraints.insets = new Insets(30, 0, 0, 110);

        mainPanel.add(newPatientButton, mainPanelConstraints);


        add(mainPanel);



        // Login Panel

        JPanel loginPanel = new JPanel(new GridBagLayout());


        GridBagConstraints loginConstraints = new GridBagConstraints();

        JLabel logInLabel = new JLabel("Patient Login");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JTextField usernameTextField = new JTextField(12);
        JPasswordField passwordField = new JPasswordField(12);

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

        loginPanel.add(passwordField, loginConstraints);


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


        // createNewPatientInfo panel

        // create the patient info panel

        JPanel createNewPatientInfoPanel = new JPanel(new GridBagLayout());

        GridBagConstraints createNewPatientInfoPanelConstraints = new GridBagConstraints();

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

        // create combo box
        JComboBox<String> stateComboBox = new JComboBox<>(states);

        // create buttons
        JButton submitNewInfoButton = new JButton("Submit Information");
        JButton cancelButton_cnip = new JButton("Cancel");


        // set the constraints for each component and add
        // them to the patient info panel

        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 10;
        createNewPatientInfoPanelConstraints.weightx = 1;
        createNewPatientInfoPanelConstraints.weighty = 0.4;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        createNewPatientInfoPanel.add(lastNameLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 20;

        createNewPatientInfoPanel.add(firstNameLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 30;

        createNewPatientInfoPanel.add(middleNameLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 20;

        createNewPatientInfoPanel.add(SSNLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 30;

        createNewPatientInfoPanel.add(DOBLabel, createNewPatientInfoPanelConstraints);

        createNewPatientInfoPanelConstraints.gridy = 40;
        createNewPatientInfoPanelConstraints.weighty = 1;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);

        createNewPatientInfoPanel.add(phoneNumberLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 50;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);

        createNewPatientInfoPanel.add(streetLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 60;
        createNewPatientInfoPanelConstraints.weighty = 0.4;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        createNewPatientInfoPanel.add(cityLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 70;

        createNewPatientInfoPanel.add(stateLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 80;

        createNewPatientInfoPanel.add(zipCodeLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 10;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        createNewPatientInfoPanel.add(lastNameTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        createNewPatientInfoPanel.add(firstNameTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 30;

        createNewPatientInfoPanel.add(middleNameTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 20;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        createNewPatientInfoPanel.add(SSNTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 30;

        createNewPatientInfoPanel.add(DOBTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 40;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);

        createNewPatientInfoPanel.add(phoneNumberTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 50;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);

        createNewPatientInfoPanel.add(streetTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 60;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        createNewPatientInfoPanel.add(cityTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 70;

        createNewPatientInfoPanel.add(stateComboBox, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 80;

        createNewPatientInfoPanel.add(zipCodeTextField, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.ipady = 10;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;

        createNewPatientInfoPanel.add(submitNewInfoButton, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.ipadx = 45;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;

        createNewPatientInfoPanel.add(cancelButton_cnip, createNewPatientInfoPanelConstraints);



        // Tabbed Panel

        JTabbedPane tabbedPane = new JTabbedPane();

        // create the patient info panel

        JPanel patientInfoPanel = new JPanel(new GridBagLayout());

        GridBagConstraints patientInfoPanelConstraints = new GridBagConstraints();


        // create labels

        JLabel firstNameLabel_TBP = new JLabel("First Name:");
        JLabel middleNameLabel_TBP = new JLabel("Middle Name:");
        JLabel lastNameLabel_TBP = new JLabel("Last Name:");
        JLabel SSNLabel_TBP = new JLabel("Social Security #:");
        JLabel DOBLabel_TBP = new JLabel("Date of Birth:");
        JLabel phoneNumberLabel_TBP = new JLabel("Phone Number:");
        JLabel streetLabel_TBP = new JLabel("Street:");
        JLabel cityLabel_TBP = new JLabel("City:");
        JLabel stateLabel_TBP = new JLabel("State:");
        JLabel zipCodeLabel_TBP = new JLabel("Zip Code:");

        // create text fields

        JTextField firstNameTextField_TBP = new JTextField(12);
        JTextField middleNameTextField_TBP = new JTextField(12);
        JTextField lastNameTextField_TBP = new JTextField(12);
        JTextField SSNTextField_TBP = new JTextField(12);
        JTextField DOBTextField_TBP = new JTextField(12);
        JTextField phoneNumberTextField_TBP = new JTextField(12);
        JTextField streetTextField_TBP = new JTextField(12);
        JTextField cityTextField_TBP = new JTextField(12);
        JTextField zipCodeTextField_TBP = new JTextField(12);

        // create combo box

        JComboBox<String> stateComboBox_TBP = new JComboBox<>(states);

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

        patientInfoPanel.add(lastNameLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;

        patientInfoPanel.add(firstNameLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;

        patientInfoPanel.add(SSNLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 30;

        patientInfoPanel.add(DOBLabel_TBP, patientInfoPanelConstraints);

        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.weighty = 1;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        patientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);

        patientInfoPanel.add(phoneNumberLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);

        patientInfoPanel.add(streetLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        patientInfoPanel.add(cityLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeLabel_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(lastNameTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        patientInfoPanel.add(firstNameTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(SSNTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 30;

        patientInfoPanel.add(DOBTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        patientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);

        patientInfoPanel.add(phoneNumberTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);

        patientInfoPanel.add(streetTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(cityTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateComboBox_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.ipady = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.CENTER;

        patientInfoPanel.add(updateInfoButton, patientInfoPanelConstraints);


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

        // set the label font

        chooseDateAndTimeLabel.setFont(new Font("Serif", Font.PLAIN, 40));


        // set the constraints for each component and add
        // them to the calendar panel

        calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 10;
        calendarConstraints.weighty = 0.2;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(20, 0, 0, 0);

        calendarPanel.add(chooseDateAndTimeLabel, calendarConstraints);


        calendarConstraints.gridy = 20;
        calendarConstraints.weighty = 0;
        calendarConstraints.anchor = GridBagConstraints.CENTER;
        calendarConstraints.insets = new Insets(0, 0, 0, 100);

        calendarPanel.add(datePicker, calendarConstraints);


        calendarConstraints.insets = new Insets(0, 170, 0, 0);

        calendarPanel.add(timePicker, calendarConstraints);


        calendarConstraints.gridy = 30;
        calendarConstraints.weighty = 1;
        calendarConstraints.ipady = 10;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(30, 0, 0, 0);

        calendarPanel.add(requestAppointmentButton, calendarConstraints);



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


        loginButton.addActionListener(e -> {
            if (String.valueOf(usernameTextField.getText()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Username");
            else if (String.valueOf(passwordField.getPassword()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Password");
            else if (MainGUI.pimsSystem.patient_exists(usernameTextField.getText(), String.valueOf(passwordField.getPassword()))) {
                remove(loginPanel);
                add(tabbedPane);
                JOptionPane.showMessageDialog
                        (null, "Login Successful");
                repaint();
                revalidate();

                // reset username and password fields

                usernameTextField.setText("");
                passwordField.setText("");
            }
            else
                JOptionPane.showMessageDialog
                        (null, "Invalid Password or Username");
        });

        cancelButton.addActionListener(e -> {
            remove(loginPanel);
            add(mainPanel);
            repaint();
            revalidate();

            // reset username and password fields

            usernameTextField_cnp.setText("");
            passwordTextField_cnp.setText("");
        });


        submitButton.addActionListener(e -> {
            if (String.valueOf(usernameTextField_cnp.getText()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Username");
            else if (String.valueOf(passwordTextField_cnp.getText()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Password");
            else if (usernameTextField_cnp.getText().length() < 4)
                JOptionPane.showMessageDialog
                        (null, "Username Must Have At Least 4 Characters");
            else if (passwordTextField_cnp.getText().length() < 4)
                JOptionPane.showMessageDialog
                        (null, "Password Must Have At Least 4 Characters");
            else if (!MainGUI.pimsSystem.patient_exists(usernameTextField_cnp.getText(), passwordTextField_cnp.getText())){
                remove(createNewPatientPanel);
                add(createNewPatientInfoPanel);
                JOptionPane.showMessageDialog
                        (null, "Submission Successful");
                repaint();
                revalidate();
            } else
                JOptionPane.showMessageDialog
                        (null, "This Username and Password are Already Taken");

        });


        cancelButton_cnp.addActionListener(e -> {
            remove(createNewPatientPanel);
            add(mainPanel);
            repaint();
            revalidate();

            // reset username and password fields

            usernameTextField_cnp.setText("");
            passwordTextField_cnp.setText("");
        });


        // submits a new patient info into the system

        submitNewInfoButton.addActionListener(e -> {
            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
            String errorMessage = "Must Enter";
            if (String.valueOf(firstNameTextField.getText()).equals(""))
                errorMessage += " First Name,";
            if (String.valueOf(lastNameTextField.getText()).equals(""))
                errorMessage += " Last Name,";
            if (String.valueOf(middleNameTextField.getText()).equals(""))
                errorMessage += " Middle Name,";
            if (String.valueOf(SSNTextField.getText()).equals(""))
                errorMessage += " Social Security #,";
            if (String.valueOf(DOBTextField.getText()).equals(""))
                errorMessage += " Date of Birth,";
            if (String.valueOf(phoneNumberTextField.getText()).equals(""))
                errorMessage += " Phone Number,";
            if (String.valueOf(streetTextField).equals(""))
                errorMessage += " Street,";
            if (String.valueOf(cityTextField).equals(""))
                errorMessage += " City,";
            if (String.valueOf(zipCodeTextField).equals(""))
                errorMessage += " Zip Code,";
            if (String.valueOf(errorMessage).equals("Must Enter")){
                    if (!MainGUI.pimsSystem.add_patient(firstNameTextField.getText(),
                        lastNameTextField.getText(), middleNameTextField.getText(),
                        usernameTextField_cnp.getText(), passwordTextField_cnp.getText(),
                        DOBTextField.getText(), 30, Integer.parseInt(SSNTextField.getText()),
                        Integer.parseInt(zipCodeTextField.getText()), streetTextField.getText(),
                        phoneNumberTextField.getText()))
                    JOptionPane.showMessageDialog
                            (null, "This Patient Is Already In System");
                    else {
                        MainGUI.pimsSystem.add_patient(firstNameTextField.getText(),
                                lastNameTextField.getText(), middleNameTextField.getText(),
                                usernameTextField_cnp.getText(), passwordTextField_cnp.getText(),
                                DOBTextField.getText(), 30, Integer.parseInt(SSNTextField.getText()),
                                Integer.parseInt(zipCodeTextField.getText()), streetTextField.getText(),
                                phoneNumberTextField.getText());

                        // set the patient info panel in the tabbed pane to
                        // to info from the create new info patient panel

                        firstNameTextField_TBP.setText(firstNameTextField.getText());
                        middleNameTextField_TBP.setText(middleNameTextField.getText());
                        lastNameTextField_TBP.setText(lastNameTextField.getText());
                        SSNTextField_TBP.setText(SSNTextField.getText());
                        DOBTextField_TBP.setText(DOBTextField.getText());
                        phoneNumberTextField_TBP.setText(phoneNumberTextField.getText());
                        streetTextField_TBP.setText(streetTextField.getText());
                        cityTextField_TBP.setText(cityTextField.getText());
                        zipCodeTextField_TBP.setText(zipCodeTextField.getText());
                        stateComboBox_TBP.setSelectedItem(stateComboBox.getSelectedItem());
                        remove(createNewPatientInfoPanel);
                        add(tabbedPane);
                        JOptionPane.showMessageDialog
                                (null, "Submission Successful");
                        repaint();
                        revalidate();
                    }
            } else {
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        });


        cancelButton_cnip.addActionListener(e -> {
            remove(createNewPatientInfoPanel);
            add(mainPanel);
            repaint();
            revalidate();

            // reset username and password fields

            usernameTextField_cnp.setText("");
            passwordTextField_cnp.setText("");
        });


        updateInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog
                    (null, "Information Updated");
        });

        requestAppointmentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog
                    (null, "Appointment Saved");
        });
    }





    // method to create a date picker

    private DatePicker createDatePicker() {

        DatePickerSettings datePickerSettings = new DatePickerSettings();

        datePickerSettings.setAllowEmptyDates(false);

        datePickerSettings.setAllowKeyboardEditing(false);

        DatePicker datePicker = new DatePicker(datePickerSettings);

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
}