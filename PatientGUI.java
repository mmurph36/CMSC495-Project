


import com.github.lgooddatepicker.components.DatePicker;

import com.github.lgooddatepicker.components.DatePickerSettings;

import com.github.lgooddatepicker.components.TimePicker;

import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.*;

import java.awt.*;

import java.time.DayOfWeek;

import java.time.LocalDate;

import java.time.LocalTime;


public class PatientGUI extends JPanel {


    patient patient;


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


        // set label font

        chooseLabel.setFont(new java.awt.Font(chooseLabel.getFont().getFontName(), Font.PLAIN, 40));


        // set constraints for components and add

        // to the main panel


        mainPanelConstraints.gridx = 10;

        mainPanelConstraints.gridy = 10;

        mainPanelConstraints.weighty = 0.2;

        mainPanelConstraints.anchor = GridBagConstraints.CENTER;
        mainPanelConstraints.insets = new Insets(40, 0, 0, 0);


        mainPanel.add(chooseLabel, mainPanelConstraints);


        // add existing patient button

        mainPanelConstraints.gridy = 20;
        mainPanelConstraints.weighty = 1;
        mainPanelConstraints.ipady = 15;
        mainPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanelConstraints.insets = new Insets(30, 150, 0, 0);

        mainPanel.add(existingPatientButton, mainPanelConstraints);


        // add new patient button

        mainPanelConstraints.ipadx = 20;
        mainPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        mainPanelConstraints.insets = new Insets(30, 0, 0, 150);

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


        logInLabel.setFont(new java.awt.Font(logInLabel.getFont().getFontName(), Font.PLAIN, 40));


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


        JPasswordField passwordField_cnp = new JPasswordField(12);


        JButton submitButton = new JButton("Submit");

        JButton cancelButton_cnp = new JButton("Cancel");


        createNewPatientLabel.setFont

                (new java.awt.Font(createNewPatientLabel.getFont().getFontName(), Font.PLAIN, 40));


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


        createNewPatientPanel.add(passwordField_cnp, createNewPatientConstraints);


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

        JLabel addressLabel = new JLabel("Address:");

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

        JTextField addressTextField = new JTextField(12);

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

        // add last name label

        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 10;
        createNewPatientInfoPanelConstraints.weightx = 1;
        createNewPatientInfoPanelConstraints.weighty = 0.4;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        createNewPatientInfoPanel.add(lastNameLabel, createNewPatientInfoPanelConstraints);


        // add first name label

        createNewPatientInfoPanelConstraints.gridx = 20;

        createNewPatientInfoPanel.add(firstNameLabel, createNewPatientInfoPanelConstraints);


        // add middle name label

        createNewPatientInfoPanelConstraints.gridx = 30;

        createNewPatientInfoPanel.add(middleNameLabel, createNewPatientInfoPanelConstraints);


        // add SSN label

        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 20;

        createNewPatientInfoPanel.add(SSNLabel, createNewPatientInfoPanelConstraints);


        // add DOB label

        createNewPatientInfoPanelConstraints.gridy = 30;

        createNewPatientInfoPanel.add(DOBLabel, createNewPatientInfoPanelConstraints);


        // add phone number label

        createNewPatientInfoPanelConstraints.gridy = 40;
        createNewPatientInfoPanelConstraints.weighty = 1;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);

        createNewPatientInfoPanel.add(phoneNumberLabel, createNewPatientInfoPanelConstraints);


        // add address label

        createNewPatientInfoPanelConstraints.gridy = 50;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);

        createNewPatientInfoPanel.add(addressLabel, createNewPatientInfoPanelConstraints);


        // add city label

        createNewPatientInfoPanelConstraints.gridy = 60;
        createNewPatientInfoPanelConstraints.weighty = 0.4;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        createNewPatientInfoPanel.add(cityLabel, createNewPatientInfoPanelConstraints);


        // add state label

        createNewPatientInfoPanelConstraints.gridy = 70;

        createNewPatientInfoPanel.add(stateLabel, createNewPatientInfoPanelConstraints);


        // add zip code label

        createNewPatientInfoPanelConstraints.gridy = 80;

        createNewPatientInfoPanel.add(zipCodeLabel, createNewPatientInfoPanelConstraints);


        createNewPatientInfoPanelConstraints.gridy = 10;

        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;

        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);


        createNewPatientInfoPanel.add(lastNameTextField, createNewPatientInfoPanelConstraints);


        // add first name textfield

        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        createNewPatientInfoPanel.add(firstNameTextField, createNewPatientInfoPanelConstraints);


        // add middle name textfield

        createNewPatientInfoPanelConstraints.gridx = 30;

        createNewPatientInfoPanel.add(middleNameTextField, createNewPatientInfoPanelConstraints);


        // add SSN textfield

        createNewPatientInfoPanelConstraints.gridx = 10;
        createNewPatientInfoPanelConstraints.gridy = 20;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        createNewPatientInfoPanel.add(SSNTextField, createNewPatientInfoPanelConstraints);


        // add DOB textfield

        createNewPatientInfoPanelConstraints.gridy = 30;

        createNewPatientInfoPanel.add(DOBTextField, createNewPatientInfoPanelConstraints);


        // add phone number textfield

        createNewPatientInfoPanelConstraints.gridy = 40;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);

        createNewPatientInfoPanel.add(phoneNumberTextField, createNewPatientInfoPanelConstraints);


        // add address textfield

        createNewPatientInfoPanelConstraints.gridy = 50;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);

        createNewPatientInfoPanel.add(addressTextField, createNewPatientInfoPanelConstraints);


        // add city textfield

        createNewPatientInfoPanelConstraints.gridy = 60;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        createNewPatientInfoPanel.add(cityTextField, createNewPatientInfoPanelConstraints);


        // add state combobox

        createNewPatientInfoPanelConstraints.gridy = 70;

        createNewPatientInfoPanel.add(stateComboBox, createNewPatientInfoPanelConstraints);


        // add zip code textfield

        createNewPatientInfoPanelConstraints.gridy = 80;

        createNewPatientInfoPanel.add(zipCodeTextField, createNewPatientInfoPanelConstraints);


        // add submitNewInfo button

        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.ipady = 10;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 15, 0);

        createNewPatientInfoPanel.add(submitNewInfoButton, createNewPatientInfoPanelConstraints);


        // add cancel button

        createNewPatientInfoPanelConstraints.gridx = 20;
        createNewPatientInfoPanelConstraints.ipadx = 50;
        createNewPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        createNewPatientInfoPanelConstraints.insets = new Insets(0, 0, 15, 50);

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
        JLabel addressLabel_TBP = new JLabel("Address:");
        JLabel cityLabel_TBP = new JLabel("City:");
        JLabel stateLabel_TBP = new JLabel("State:");
        JLabel zipCodeLabel_TBP = new JLabel("Zip Code:");

        /* NEW JLabels */

        JLabel patient_userLabel_TBP = new JLabel("Username:");
        JLabel patient_pwLabel_TBP = new JLabel("Password:");


        // create text fields

        JTextField firstNameTextField_TBP = new JTextField(12);
        JTextField middleNameTextField_TBP = new JTextField(12);
        JTextField lastNameTextField_TBP = new JTextField(12);
        JTextField SSNTextField_TBP = new JTextField(12);
        JTextField DOBTextField_TBP = new JTextField(12);
        JTextField phoneNumberTextField_TBP = new JTextField(12);
        JTextField addressTextField_TBP = new JTextField(12);
        JTextField cityTextField_TBP = new JTextField(12);
        JTextField zipCodeTextField_TBP = new JTextField(12);

        /* NEW JTextField */

        JTextField patient_userField_TBP = new JTextField(12);
        JTextField patient_pwField_TBP = new JTextField(12);


        // create combo box

        JComboBox<String> stateComboBox_TBP = new JComboBox<>(states);

        // create buttons

        JButton updateInfoButton = new JButton("Update Information");


        // set the constraints for each component and add
        // them to the patient info panel

        // add last name label

        patientInfoPanelConstraints.gridx = 10;

        patientInfoPanelConstraints.gridy = 10;

        patientInfoPanelConstraints.weightx = 1;

        patientInfoPanelConstraints.weighty = 0.4;

        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;

        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);


        patientInfoPanel.add(lastNameLabel_TBP, patientInfoPanelConstraints);


        // add first name label

        patientInfoPanelConstraints.gridx = 20;

        patientInfoPanel.add(firstNameLabel_TBP, patientInfoPanelConstraints);


        // add middle name label

        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameLabel_TBP, patientInfoPanelConstraints);


        // add SSN label

        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;

        patientInfoPanel.add(SSNLabel_TBP, patientInfoPanelConstraints);


        // add username label

        patientInfoPanelConstraints.gridx = 20;

        patientInfoPanel.add(patient_userLabel_TBP, patientInfoPanelConstraints);


        // add password label

        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(patient_pwLabel_TBP, patientInfoPanelConstraints);


        // add DOB label

        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 30;

        patientInfoPanel.add(DOBLabel_TBP, patientInfoPanelConstraints);


        // add phone number label

        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.weighty = 1;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        patientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);

        patientInfoPanel.add(phoneNumberLabel_TBP, patientInfoPanelConstraints);


        // add address label

        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);

        patientInfoPanel.add(addressLabel_TBP, patientInfoPanelConstraints);


        // add city label

        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);

        patientInfoPanel.add(cityLabel_TBP, patientInfoPanelConstraints);


        // add state label

        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateLabel_TBP, patientInfoPanelConstraints);


        // add zip code label

        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeLabel_TBP, patientInfoPanelConstraints);


        // add last name textfield

        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(lastNameTextField_TBP, patientInfoPanelConstraints);


        // add first name textfield

        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        patientInfoPanel.add(firstNameTextField_TBP, patientInfoPanelConstraints);


        // add middle name textfield

        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(middleNameTextField_TBP, patientInfoPanelConstraints);


        // add SSN textfield

        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(SSNTextField_TBP, patientInfoPanelConstraints);


        // add username textfield

        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);

        patientInfoPanel.add(patient_userField_TBP, patientInfoPanelConstraints);


        // add password textfield

        patientInfoPanelConstraints.gridx = 30;

        patientInfoPanel.add(patient_pwField_TBP, patientInfoPanelConstraints);


        // add DOB textfield

        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 30;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(DOBTextField_TBP, patientInfoPanelConstraints);


        // add phone number textfield

        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        patientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);

        patientInfoPanel.add(phoneNumberTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);

        patientInfoPanel.add(addressTextField_TBP, patientInfoPanelConstraints);


        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);

        patientInfoPanel.add(cityTextField_TBP, patientInfoPanelConstraints);



        patientInfoPanelConstraints.gridy = 70;

        patientInfoPanel.add(stateComboBox_TBP, patientInfoPanelConstraints);


        // add zip code textfield

        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanel.add(zipCodeTextField_TBP, patientInfoPanelConstraints);


        // add updateinfo button

        patientInfoPanelConstraints.gridy = 80;

        patientInfoPanelConstraints.gridx = 20;

        patientInfoPanelConstraints.ipady = 10;

        patientInfoPanelConstraints.anchor = GridBagConstraints.CENTER;

        patientInfoPanelConstraints.insets = new Insets(0, 0, 13, 0);


        patientInfoPanel.add(updateInfoButton, patientInfoPanelConstraints);


        // create calendar panel


        JPanel calendarPanel = new JPanel(new GridBagLayout());


        GridBagConstraints calendarConstraints = new GridBagConstraints();


        // create label


        JLabel chooseDateAndTimeLabel =

                new JLabel("Select Date and Time For Appointment");

        JLabel lookUpAppointmentLabel =

                new JLabel("Look Up Existing Appointment");


        // create date picker


        DatePicker datePicker = createDatePicker();


        // create time picker


        TimePicker timePicker = createTimePicker();


        // create buttons


        JButton requestAppointmentButton = new JButton("Request Appointment");

        JButton cancelAppointmentButton = new JButton("Cancel Appointment");

        JButton lookUpAppointmentButton = new JButton("Look Up Appointment");


        // create text field


        JTextField lookUpAppointmentTextField = new JTextField(15);

        lookUpAppointmentTextField.setEditable(false);

        lookUpAppointmentTextField.setBackground(Color.white);


        // set the label font


        chooseDateAndTimeLabel.setFont(new java.awt.Font(chooseDateAndTimeLabel.getFont().getFontName(), Font.PLAIN, 40));

        lookUpAppointmentLabel.setFont(new java.awt.Font(lookUpAppointmentLabel.getFont().getFontName(), Font.PLAIN, 25));

        // set the constraints for each component and add

        // them to the calendar panel


        calendarConstraints.gridx = 10;

        calendarConstraints.gridy = 10;

        calendarConstraints.weighty = 0.2;

        calendarConstraints.anchor = GridBagConstraints.NORTH;

        calendarConstraints.insets = new Insets(20, 0, 0, 0);


        calendarPanel.add(chooseDateAndTimeLabel, calendarConstraints);


        calendarConstraints.gridy = 20;

        calendarConstraints.anchor = GridBagConstraints.CENTER;

        calendarConstraints.insets = new Insets(20, 0, 0, 110);


        calendarPanel.add(datePicker, calendarConstraints);


        calendarConstraints.insets = new Insets(20, 160, 0, 0);


        calendarPanel.add(timePicker, calendarConstraints);


        // add requestAppointment button

        calendarConstraints.gridy = 30;
        calendarConstraints.weighty = 1;
        calendarConstraints.ipady = 10;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(30, 0, 0, 170);

        calendarPanel.add(requestAppointmentButton, calendarConstraints);


        calendarConstraints.ipady = 10;

        calendarConstraints.insets = new Insets(30, 170, 0, 0);


        calendarPanel.add(cancelAppointmentButton, calendarConstraints);


        calendarConstraints.ipady = 0;

        calendarConstraints.gridy = 40;

        calendarConstraints.weighty = 0.2;

        calendarConstraints.insets = new Insets(0, 0, 0, 0);


        calendarPanel.add(lookUpAppointmentLabel, calendarConstraints);


        calendarConstraints.ipady = 10;

        calendarConstraints.weighty = 1;

        calendarConstraints.gridy = 50;

        calendarConstraints.insets = new Insets(0, 0, 0, 195);


        calendarPanel.add(lookUpAppointmentButton, calendarConstraints);



        calendarConstraints.ipady = 5;
        calendarConstraints.insets = new Insets(5, 180, 0, 0);


        calendarPanel.add(lookUpAppointmentTextField, calendarConstraints);


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

            else if (String.valueOf(String.valueOf(passwordField.getPassword())).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Password");

            else if (MainGUI.pimsSystem.patient_exists

                    (usernameTextField.getText(), String.valueOf(passwordField.getPassword()))) {

                remove(loginPanel);

                add(tabbedPane);

                JOptionPane.showMessageDialog

                        (null, "Login Successful");

                repaint();

                revalidate();


                // set info into patient info panel


                patient = MainGUI.pimsSystem.setPatientDetails

                        (usernameTextField.getText(), String.valueOf(passwordField.getPassword()));


                if (patient != null) {

                    firstNameTextField_TBP.setText(patient.f_name);

                    lastNameTextField_TBP.setText(patient.l_name);

                    middleNameTextField_TBP.setText(patient.m_name);

                    SSNTextField_TBP.setText(String.valueOf(patient.SSN));

                    DOBTextField_TBP.setText(patient.dob);

                    phoneNumberTextField_TBP.setText(patient.p_number);

                    addressTextField_TBP.setText(patient.address);

                    cityTextField_TBP.setText(patient.city);

                    stateComboBox_TBP.setSelectedItem(patient.state);

                    zipCodeTextField_TBP.setText(String.valueOf(patient.zip));

                    patient_userField_TBP.setText(patient.user_name);

                    patient_pwField_TBP.setText(patient.password);

                } else

                    JOptionPane.showMessageDialog(null, "Error");


                // reset username and password fields


                usernameTextField.setText("");

                passwordField.setText("");


            } else

                JOptionPane.showMessageDialog

                        (null, "Invalid Password or Username");

        });


        cancelButton.addActionListener(e -> {

            remove(loginPanel);

            add(mainPanel);

            repaint();

            revalidate();


            // reset username and password fields


            usernameTextField.setText("");

            passwordField.setText("");

        });


        submitButton.addActionListener(e -> {

            if (String.valueOf(usernameTextField_cnp.getText()).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Username");

            else if (String.valueOf(String.valueOf(passwordField_cnp.getPassword())).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Password");

            else if (!MainGUI.pimsSystem.patient_exists

                    (usernameTextField_cnp.getText(), String.valueOf(passwordField_cnp.getPassword()))) {

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

            passwordField_cnp.setText("");

        });


        // submits a new patient info into the system


        submitNewInfoButton.addActionListener(e -> {

            // values to test if there are no input errors

            boolean emptyFields = true, illegalFields = true;


            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));

            String errorMessage = "Must Enter";

            if (String.valueOf(firstNameTextField.getText()).equals("")) {

                errorMessage += " First Name,";

                emptyFields = false;

            }

            if (String.valueOf(lastNameTextField.getText()).equals("")) {

                errorMessage += " Last Name,";

                emptyFields = false;

            }

            if (String.valueOf(SSNTextField.getText()).equals("")) {

                errorMessage += " Social Security #,";

                emptyFields = false;

            }

            if (String.valueOf(DOBTextField.getText()).equals("")) {

                errorMessage += " Date of Birth,";

                emptyFields = false;

            }

            if (String.valueOf(phoneNumberTextField.getText()).equals("")) {

                errorMessage += " Phone Number,";

                emptyFields = false;

            }

            if (String.valueOf(addressTextField).equals("")) {

                errorMessage += " Street,";

                emptyFields = false;

            }

            if (String.valueOf(cityTextField).equals("")) {

                errorMessage += " City,";

                emptyFields = false;

            }

            if (String.valueOf(zipCodeTextField).equals("")) {

                errorMessage += " Zip Code,";

                emptyFields = false;

            }


            // if there's no middle name, the text field

            // is set to "N/A"


            String middleName;


            if (String.valueOf(middleNameTextField.getText()).equals(""))

                middleName = "N/A";

            else middleName = middleNameTextField.getText();


            // throws error if last name has characters other than letters


            if (lastNameTextField.getText().length() > 0) {

                for (int i = 0; i < lastNameTextField.getText().length(); i++) {

                    if (!Character.isLetter(lastNameTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Last Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if first name has characters other than letters


            if (firstNameTextField.getText().length() > 0) {

                for (int i = 0; i < firstNameTextField.getText().length(); i++) {

                    if (!Character.isLetter(firstNameTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "First Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if middle name has characters other than letters


            if (middleNameTextField.getText().length() > 0 &&

                    !String.valueOf(middleNameTextField.getText()).equals("N/A")) {

                for (int i = 0; i < middleNameTextField.getText().length(); i++) {

                    if (!Character.isLetter(middleNameTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Middle Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if SSN has characters other than numbers, or has less/more than 4 digits


            if (SSNTextField.getText().length() > 0 && SSNTextField.getText().length() != 4) {

                JOptionPane.showMessageDialog

                        (null, "Social Security # Must Have 4 Characters");

                illegalFields = false;

            } else if (SSNTextField.getText().length() == 4) {

                for (int i = 0; i < 4; i++) {

                    if (!Character.isDigit(SSNTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Social Security # Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"


            if (DOBTextField.getText().length() > 0 && DOBTextField.getText().length() != 10) {

                JOptionPane.showMessageDialog

                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                illegalFields = false;

            } else if (DOBTextField.getText().length() == 10) {

                if (!DOBparser(DOBTextField.getText())) {

                    JOptionPane.showMessageDialog

                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                    illegalFields = false;

                }

            }


            // throws error if phone number isn't formatted correctly - "###-###-####"


            if (phoneNumberTextField.getText().length() > 0 && phoneNumberTextField.getText().length() != 12) {

                JOptionPane.showMessageDialog

                        (null, "Phone Number Must be formatted \"###-###-####\"");

                illegalFields = false;

            } else if (phoneNumberTextField.getText().length() == 12) {

                if (!phoneNumberParser(phoneNumberTextField.getText())) {

                    JOptionPane.showMessageDialog

                            (null, "Phone Number Must be formatted \"###-###-####\"");

                    illegalFields = false;

                }

            }


            // throws error if address has characters other than letters and numbers


            if (addressTextField.getText().length() > 0) {

                for (int i = 0; i < addressTextField.getText().length(); i++) {

                    if (!Character.isLetter(addressTextField.getText().charAt(i)) &&

                            !Character.isDigit(addressTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Address Must Have Only Numbers and Letters");

                        illegalFields = false;

                    }

                }

            }


            // throws error if city has characters other than letters


            if (cityTextField.getText().length() > 0) {

                for (int i = 1; i < cityTextField.getText().length(); i++) {

                    if (!Character.isLetter(cityTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "City Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if zip code has characters other than numbers, or has less/more than 4 digits


            if (zipCodeTextField.getText().length() > 0 && zipCodeTextField.getText().length() != 5) {

                JOptionPane.showMessageDialog

                        (null, "Zip Code Must Have 5 Characters");

                illegalFields = false;

            } else if (zipCodeTextField.getText().length() == 5) {

                for (int i = 0; i < 5; i++) {

                    if (!Character.isDigit(zipCodeTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Zip Code Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // checks if there are no input errors


            if (emptyFields && illegalFields) {

                if (!MainGUI.pimsSystem.add_patient(firstNameTextField.getText(),

                        lastNameTextField.getText(), middleName, usernameTextField_cnp.getText(),

                        String.valueOf(passwordField_cnp.getPassword()), DOBTextField.getText(),

                        Integer.parseInt(SSNTextField.getText()), Integer.parseInt(zipCodeTextField.getText()),

                        addressTextField.getText(), cityTextField.getText(),

                        String.valueOf(stateComboBox.getSelectedItem()), phoneNumberTextField.getText()))

                    JOptionPane.showMessageDialog

                            (null, "This Patient Is Already In System");

                else {


                    // set the patient info panel in the tabbed pane to

                    // to info from the create new info patient panel


                    firstNameTextField_TBP.setText(firstNameTextField.getText());

                    middleNameTextField_TBP.setText(middleName);

                    lastNameTextField_TBP.setText(lastNameTextField.getText());

                    SSNTextField_TBP.setText(SSNTextField.getText());

                    DOBTextField_TBP.setText(DOBTextField.getText());

                    phoneNumberTextField_TBP.setText(phoneNumberTextField.getText());

                    addressTextField_TBP.setText(addressTextField.getText());

                    cityTextField_TBP.setText(cityTextField.getText());

                    zipCodeTextField_TBP.setText(zipCodeTextField.getText());

                    stateComboBox_TBP.setSelectedItem(stateComboBox.getSelectedItem());

                    patient_userField_TBP.setText(usernameTextField_cnp.getText());

                    patient_pwField_TBP.setText(String.valueOf(passwordField_cnp.getPassword()));

                    remove(createNewPatientInfoPanel);

                    add(tabbedPane);

                    JOptionPane.showMessageDialog

                            (null, "Submission Successful");

                    repaint();

                    revalidate();

                }

            } else if (!String.valueOf(errorMessage).equals("Must Enter")) {

                JOptionPane.showMessageDialog(null, errorMessage);

            }

        });


        // brings user back to main panel


        cancelButton_cnip.addActionListener(e -> {

            remove(createNewPatientInfoPanel);

            add(mainPanel);

            repaint();

            revalidate();


            // reset username and password fields


            usernameTextField_cnp.setText("");

            passwordField_cnp.setText("");

        });


        updateInfoButton.addActionListener(e -> {

            // values to test if there are no input errors

            boolean emptyFields = true, illegalFields = true;


            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));

            String errorMessage = "Must Enter";

            if (String.valueOf(firstNameTextField.getText()).equals("")) {

                errorMessage += " First Name,";

                emptyFields = false;

            }

            if (String.valueOf(lastNameTextField.getText()).equals("")) {

                errorMessage += " Last Name,";

                emptyFields = false;

            }

            if (String.valueOf(SSNTextField.getText()).equals("")) {

                errorMessage += " Social Security #,";

                emptyFields = false;

            }

            if (String.valueOf(DOBTextField.getText()).equals("")) {

                errorMessage += " Date of Birth,";

                emptyFields = false;

            }

            if (String.valueOf(phoneNumberTextField.getText()).equals("")) {

                errorMessage += " Phone Number,";

                emptyFields = false;

            }

            if (String.valueOf(addressTextField).equals("")) {

                errorMessage += " Street,";

                emptyFields = false;

            }

            if (String.valueOf(cityTextField).equals("")) {

                errorMessage += " City,";

                emptyFields = false;

            }

            if (String.valueOf(zipCodeTextField).equals("")) {

                errorMessage += " Zip Code,";

                emptyFields = false;

            }

            if (String.valueOf(patient_userField_TBP).equals("")) {

                errorMessage += " Username,";

                emptyFields = false;

            }

            if (String.valueOf(patient_pwField_TBP).equals("")) {

                errorMessage += " password,";

                emptyFields = false;

            }


            // if there's no middle name, the text field

            // is set to "N/A"


            String middleName;


            if (String.valueOf(middleNameTextField.getText()).equals(""))

                middleName = "N/A";

            else middleName = middleNameTextField.getText();


            // throws error if last name has characters other than letters


            if (lastNameTextField.getText().length() > 0) {

                for (int i = 0; i < lastNameTextField.getText().length(); i++) {

                    if (!Character.isLetter(lastNameTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Last Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if first name has characters other than letters


            if (firstNameTextField.getText().length() > 0) {

                for (int i = 0; i < firstNameTextField.getText().length(); i++) {

                    if (!Character.isLetter(firstNameTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "First Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if middle name has characters other than letters


            if (middleNameTextField.getText().length() > 0 &&
                    !String.valueOf(middleNameTextField.getText()).equals("N/A")) {
                for (int i = 0; i < middleName.length(); i++) {
                    if (!Character.isLetter(middleName.charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Middle Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }


            // throws error if SSN has characters other than numbers, or has less/more than 4 digits


            if (SSNTextField.getText().length() > 0 && SSNTextField.getText().length() != 4) {
                JOptionPane.showMessageDialog
                        (null, "Social Security # Must Have 4 Characters");
                illegalFields = false;
            } else if (SSNTextField.getText().length() == 4) {
                for (int i = 0; i < 4; i++) {
                    if (!Character.isDigit(SSNTextField.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Social Security # Must Have Only Numbers");
                        illegalFields = false;
                        break;
                    }
                }
            }


            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"

            if (DOBTextField.getText().length() > 0 && DOBTextField.getText().length() != 10) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            } else if (DOBTextField.getText().length() == 10) {
                if (!DOBparser(DOBTextField.getText())) {
                    JOptionPane.showMessageDialog
                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                    illegalFields = false;
                }
            }

            // throws error if phone number isn't formatted correctly - "###-###-####"

            if (phoneNumberTextField.getText().length() > 0 && phoneNumberTextField.getText().length() != 12) {

                JOptionPane.showMessageDialog

                        (null, "Phone Number Must be formatted \"###-###-####\"");

                illegalFields = false;

            } else if (phoneNumberTextField.getText().length() == 12) {

                if (!phoneNumberParser(phoneNumberTextField.getText())) {

                    JOptionPane.showMessageDialog

                            (null, "Phone Number Must be formatted \"###-###-####\"");

                    illegalFields = false;

                }

            }


            // throws error if address has characters other than letters and numbers


            if (addressTextField_TBP.getText().length() > 0) {

                for (int i = 0; i < addressTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(addressTextField_TBP.getText().charAt(i)) &&

                            !Character.isDigit(addressTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Address Must Have Only Numbers and Letters");

                        illegalFields = false;

                    }

                }

            }


            // throws error if city has characters other than letters


            if (cityTextField.getText().length() > 0) {

                for (int i = 1; i < cityTextField.getText().length(); i++) {

                    if (!Character.isLetter(cityTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "City Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // throws error if zip code has characters other than numbers, or has less/more than 4 digits


            if (zipCodeTextField.getText().length() > 0 && zipCodeTextField.getText().length() != 5) {

                JOptionPane.showMessageDialog

                        (null, "Zip Code Must Have 5 Characters");

                illegalFields = false;

            } else if (zipCodeTextField.getText().length() == 5) {

                for (int i = 0; i < 5; i++) {

                    if (!Character.isDigit(zipCodeTextField.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Zip Code Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }


            // checks if there are no input errors


            patient = MainGUI.pimsSystem.patient_details

                    (lastNameTextField.getText(), Integer.parseInt(SSNTextField.getText()));


            if (emptyFields && illegalFields && patient != null) {

                JOptionPane.showMessageDialog

                        (null, "Information Updated");

                patient.l_name = lastNameTextField_TBP.getText();

                patient.f_name = firstNameTextField_TBP.getText();

                patient.m_name = middleName;

                patient.SSN = Integer.parseInt(SSNTextField_TBP.getText());

                patient.dob = DOBTextField_TBP.getText();

                patient.p_number = phoneNumberTextField_TBP.getText();

                patient.address = addressTextField_TBP.getText();

                patient.city = cityTextField_TBP.getText();

                patient.state = String.valueOf(stateComboBox_TBP.getSelectedItem());

                patient.zip = Integer.parseInt(zipCodeTextField_TBP.getText());

                patient.user_name = patient_userField_TBP.getText();

                patient.password = patient_pwField_TBP.getText();


            } else if (!String.valueOf(errorMessage).equals("Must Enter"))

                JOptionPane.showMessageDialog(null, errorMessage);

            else if (patient == null)

                JOptionPane.showMessageDialog(null, "Error");

        });


        requestAppointmentButton.addActionListener(e -> {
            patient = MainGUI.pimsSystem.patient_details
                    (lastNameTextField_TBP.getText(), Integer.parseInt(SSNTextField_TBP.getText()));

            if (patient != null) {
                String message =
                        MainGUI.pimsSystem.add_date(datePicker.getText(), timePicker.getText(), patient);
                JOptionPane.showMessageDialog(null, message);
            } else JOptionPane.showMessageDialog(null, "Error");
        });


        lookUpAppointmentButton.addActionListener(e -> {
            patient = MainGUI.pimsSystem.patient_details
                    (lastNameTextField_TBP.getText(), Integer.parseInt(SSNTextField_TBP.getText()));

            String appointment = MainGUI.pimsSystem.lookUpAppointmentDate(patient);

            if (String.valueOf(appointment).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Sorry, You Have No Appointment Scheduled.");
            else lookUpAppointmentTextField.setText(appointment);
        });


        cancelAppointmentButton.addActionListener(e -> {
            patient = MainGUI.pimsSystem.patient_details
                    (lastNameTextField_TBP.getText(), Integer.parseInt(SSNTextField_TBP.getText()));

            if (!MainGUI.pimsSystem.patient_delete_date(patient))
                JOptionPane.showMessageDialog
                        (null, "Sorry, You Have No Appointment Scheduled.");
            else {
                JOptionPane.showMessageDialog(null, "Appointment Deleted");
                lookUpAppointmentTextField.setText("");
            }
        });

    }


    // method to parse the DOB and make

    // sure it's in the "MM/DD/YYYY" format


    private boolean DOBparser(String string) {

        if (!Character.isDigit(string.charAt(0)))

            return false;

        else if (!Character.isDigit(string.charAt(1)))

            return false;

        else if (string.charAt(2) != '/')

            return false;

        else if (!Character.isDigit(string.charAt(3)))

            return false;

        else if (!Character.isDigit(string.charAt(4)))

            return false;

        else if (string.charAt(5) != '/')

            return false;

        else if (!Character.isDigit(string.charAt(6)))

            return false;

        else if (!Character.isDigit(string.charAt(7)))

            return false;

        else if (!Character.isDigit(string.charAt(8)))

            return false;

        else if (!Character.isDigit(string.charAt(9)))

            return false;

        return true;

    }


    // method to parse the phone number and make

    // sure it's in the "###-###-####" format


    private boolean phoneNumberParser(String string) {

        if (!Character.isDigit(string.charAt(0)))

            return false;

        else if (!Character.isDigit(string.charAt(1)))

            return false;

        else if (!Character.isDigit(string.charAt(2)))

            return false;

        else if (string.charAt(3) != '-')

            return false;

        else if (!Character.isDigit(string.charAt(4)))

            return false;

        else if (!Character.isDigit(string.charAt(5)))

            return false;

        else if (!Character.isDigit(string.charAt(6)))

            return false;

        else if (string.charAt(7) != '-')

            return false;

        else if (!Character.isDigit(string.charAt(8)))

            return false;

        else if (!Character.isDigit(string.charAt(9)))

            return false;

        else if (!Character.isDigit(string.charAt(10)))

            return false;

        else if (!Character.isDigit(string.charAt(11)))

            return false;

        return true;

    }


    // method to create a date picker


    private DatePicker createDatePicker() {


        DatePickerSettings datePickerSettings = new DatePickerSettings();


        datePickerSettings.setAllowEmptyDates(false);


        datePickerSettings.setAllowKeyboardEditing(false);


        DatePicker datePicker = new DatePicker(datePickerSettings);


        // If tomorrow is Saturday or Sunday, this sets the default

        // to the following Monday


        if (LocalDate.now().plusDays(1).getDayOfWeek() == DayOfWeek.SATURDAY) {

            datePicker.setDate(LocalDate.now().plusDays(3));

        } else if (LocalDate.now().plusDays(1).getDayOfWeek() == DayOfWeek.SUNDAY) {

            datePicker.setDate(LocalDate.now().plusDays(2));

        } else datePicker.setDate(LocalDate.now().plusDays(1));


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

}