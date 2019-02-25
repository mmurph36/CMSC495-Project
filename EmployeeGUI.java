/* Author: Esther Ho & Ari Ohsie
 * CMSC 495
 * PIMS Project
 *
 * File Name: EmployeeGUI.java
 *
 *
 *
 * DISCLAIMER: EmployeeGUI & PatientGUI use code from the following project for the calendar
 *  https://github.com/LGoodDatePicker/LGoodDatePicker
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;


@SuppressWarnings("serial")


public class EmployeeGUI extends JPanel {

    // Patient Information - store info when searched

    private patient patient; // for search

    // EmployeeGUI title

    String employeeGUItitle;


    /* Login panel  */

    JPanel loginPanel;

    GridBagConstraints loginConstraints;

    JLabel logInLabel, usernameLabel, passwordLabel;

    JTextField usernameTextField;

    JPasswordField passwordField;

    JButton loginButton;

    /* Employee Window (Tabbed Pane) */
    // 4 Tabs: Calendar, Patient Information, Billing, Search


    JTabbedPane tabbedPane;

    JPanel billingTab, searchTab;



    /* TAB 1: Calendar */

    JPanel calTab;

    JLabel chooseDateAndTimeLabel, currentAppointmentLabel,
    cal_patientSSNLabel, cal_patientNameLabel, lookUpAppointmentLabel,
    currentPatientLabel;

    JTextField currentAppointmentTextField, cal_SSNTextField,
    cal_patientNameTextField, lookUpAppointmentTextField,
    currentPatientTextField;

    DatePicker datePicker;

    TimePicker timePicker;

    JButton requestAppointmentButton, cancelAppointmentButton, lookUpAppointmentButton;


    /* TAB 2: Patient Information */

    JPanel patientTab;

    GridBagConstraints patientTabConstraints;

    JLabel lastNameLabel_PInfo, firstNameLabel_PInfo, middleNameLabel_PInfo,
    ssnLabel_PInfo, dobLabel_PInfo, phoneNumberLabel_PInfo,
    streetLabel_PInfo, cityLabel_PInfo, stateLabel_PInfo, zipCodeLabel_PInfo,
    userLabel_PInfo, pwLabel_PInfo;

    JTextField lastNameTextField_PInfo, firstNameTextField_PInfo, middleNameTextField_PInfo,
    ssnTextField_PInfo, dobTextField_PInfo, phoneNumberTextField_PInfo,
    addressTextField_PInfo, cityTextField_PInfo, zipCodeTextField_PInfo,
    userField_PInfo, pwField_PInfo;

    JComboBox<String> stateComboBox_PInfo;

    static String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
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

    JButton updateInfoButton_PInfo, submitNewInfoButton_PInfo;


    /* TAB 3: Billing */

    JLabel patientBillingLabel, lNameBillLabel, ssnBillLabel, billingCodeLabel, policyLabel, amtDueLabel;

    JTextField lNameBillField, ssnBillField, amtDueField;

    JComboBox<String> codeCB, policyCB;

    JButton calculateButton_Billing; // NEW & TO-ADD


    /* TAB 4: Search */

    JLabel lNameSearchLabel, fNameSearchLabel,
            searchDirectionLabel, searchResultLabel;

    JTextField lNameSearchField, fNameSearchField;

    JPanel searchPanel, lNameSearchPanel, ssnSearchPanel, searchButtonPanel;

    JButton searchButton, selectPatientFoundButton;

    JComboBox<String> choosePatientCB;

    // Constructor

    public EmployeeGUI() {
        initialize();
    }

	/*
	 * initialize()
	 *
	 * - sets up EmployeeGUI panel
	 */

    private void initialize() {

        /* set up EmployeeGUI JPanel */

        setLayout(new BorderLayout());

        /* set up Login Panel */

        loginPanel = new JPanel(new GridBagLayout());

        loginConstraints = new GridBagConstraints();


        logInLabel = new JLabel("Employee Login");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameTextField = new JTextField(12);
        passwordField = new JPasswordField(12);

        loginButton = new JButton("Login");

        logInLabel.setFont(new java.awt.Font(logInLabel.getFont().getFontName(), Font.PLAIN, 40));


        // add login label

        loginConstraints.gridx = 10;
        loginConstraints.gridy = 10;
        loginConstraints.weighty = 0.2;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(50, 5, 0, 30);

        loginPanel.add(logInLabel, loginConstraints);


        // add username label

        loginConstraints.gridy = 20;
        loginConstraints.weighty = 0;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(40, 25, 0, 10);

        loginPanel.add(usernameLabel, loginConstraints);


        // add password label

        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(10, 25, 0, 10);

        loginPanel.add(passwordLabel, loginConstraints);


        // add username textfield

        loginConstraints.gridx = 20;
        loginConstraints.gridy = 20;
        loginConstraints.gridwidth = 20;
        loginConstraints.insets = new Insets(40, 0, 0, 25);

        loginPanel.add(usernameTextField, loginConstraints);


        // add password textfield

        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(12, 0, 0, 25);

        loginPanel.add(passwordField, loginConstraints);


        // add login button

        loginConstraints.gridx = 10;
        loginConstraints.gridy = 40;
        loginConstraints.weighty = 1;
        loginConstraints.ipadx = 15;
        loginConstraints.ipady = 5;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(30, 0, 0, 20);

        loginPanel.add(loginButton, loginConstraints);


        // ** initialize JTabbedPane **

        tabbedPane = new JTabbedPane();

        // TAB 1: Calendar

        calTab = new JPanel(new GridBagLayout());

        GridBagConstraints calendarConstraints = new GridBagConstraints();

        chooseDateAndTimeLabel = new JLabel("Select Date and Time For Appointment");

        /* NEW Labels */

        //  currentAppointmentLabel = new JLabel("Current Appointment:");
        cal_patientNameLabel = new JLabel("Patient Name:");
        // cal_patientSSNLabel = new JLabel("Last 4 SSN:");
        lookUpAppointmentLabel = new JLabel("Look Up Patient's Existing Appointment");
        //  currentPatientLabel = new JLabel("Current Patient:");

        datePicker = createDatePicker();

        timePicker = createTimePicker();

        /* NEW text fields*/

        //  currentAppointmentTextField = new JTextField(12);
        //   currentAppointmentTextField.setEditable(false);
//        cal_SSNTextField = new JTextField(12);
        cal_patientNameTextField = new JTextField(12);
        cal_patientNameTextField.setEditable(false);
        lookUpAppointmentTextField = new JTextField(15);
        lookUpAppointmentTextField.setEditable(false);
        lookUpAppointmentTextField.setBackground(Color.white);
        // currentPatientTextField = new JTextField(12);
        //currentPatientTextField.setEditable(false);
        //currentPatientTextField.setBackground(Color.white);

        requestAppointmentButton = new JButton("Request Appointment");
        cancelAppointmentButton = new JButton("Cancel Appointment");


        lookUpAppointmentButton = new JButton("Look Up Appointment");


        chooseDateAndTimeLabel.setFont(new java.awt.Font(chooseDateAndTimeLabel.getFont().getFontName(), Font.PLAIN, 30));

        lookUpAppointmentLabel.setFont(new java.awt.Font(lookUpAppointmentLabel.getFont().getFontName(), Font.PLAIN, 25));


        // set the constraints for each component and add

        // them to the calendar panel


        calendarConstraints.gridx = 10;

        calendarConstraints.gridy = 10;

        calendarConstraints.weightx = 1;

        calendarConstraints.weighty = 0.2;

        calendarConstraints.anchor = GridBagConstraints.NORTH;

        calendarConstraints.insets = new Insets(20, 0, 0, 0);


        calTab.add(chooseDateAndTimeLabel, calendarConstraints);


        // add patient name label

        //calendarConstraints.gridwidth = 20;
        calendarConstraints.anchor = GridBagConstraints.NORTHEAST;
        calendarConstraints.insets = new Insets(10, 0, 0, 40);

        calTab.add(cal_patientNameLabel, calendarConstraints);


        // add patient name textfield

        calendarConstraints.insets = new Insets(35, 0, 0, 10);

        calTab.add(cal_patientNameTextField, calendarConstraints);



        calendarConstraints.gridy = 20;
        calendarConstraints.weightx = 0;
        calendarConstraints.weighty = 0.1;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(10, 0, 0, 110);


        calTab.add(datePicker, calendarConstraints);


        calendarConstraints.insets = new Insets(10, 160, 0, 0);


        calTab.add(timePicker, calendarConstraints);


        calendarConstraints.gridy = 30;

        calendarConstraints.weighty = 0.4;

        calendarConstraints.ipady = 10;

        calendarConstraints.insets = new Insets(10, 0, 0, 170);


        calTab.add(requestAppointmentButton, calendarConstraints);


        calendarConstraints.ipady = 10;

        calendarConstraints.insets = new Insets(10, 170, 0, 0);


        calTab.add(cancelAppointmentButton, calendarConstraints);


        calendarConstraints.ipady = 0;

        calendarConstraints.gridy = 40;

        calendarConstraints.weighty = 0.2;

        calendarConstraints.insets = new Insets(0, 0, 0, 0);


        calTab.add(lookUpAppointmentLabel, calendarConstraints);


        calendarConstraints.ipady = 10;

        calendarConstraints.weighty = 1;

        calendarConstraints.gridy = 50;

        calendarConstraints.insets = new Insets(0, 0, 0, 195);


        calTab.add(lookUpAppointmentButton, calendarConstraints);


        calendarConstraints.ipady = 5;

        calendarConstraints.insets = new Insets(5, 180, 0, 0);


        calTab.add(lookUpAppointmentTextField, calendarConstraints);



/*

        calendarConstraints.gridy = 30;

        calendarConstraints.ipady = 0;

        calendarConstraints.weighty = 0.2;

        calendarConstraints.insets = new Insets(0, 0, 0, 100);



        calTab.add(cal_patientSSNLabel, calendarConstraints);





        calendarConstraints.insets = new Insets(0, 180, 0, 20);



        calTab.add(cal_SSNTextField, calendarConstraints);

*/


        // TAB 2: Patient Information


        patientTab = new JPanel(new GridBagLayout());

        patientTabConstraints = new GridBagConstraints();


        // create labels

        firstNameLabel_PInfo = new JLabel("First Name:");

        middleNameLabel_PInfo = new JLabel("Middle Name:");

        lastNameLabel_PInfo = new JLabel("Last Name:");

        ssnLabel_PInfo = new JLabel("Social Security #:");

        dobLabel_PInfo = new JLabel("Date of Birth:");

        phoneNumberLabel_PInfo = new JLabel("Phone Number:");

        streetLabel_PInfo = new JLabel("Street:");

        cityLabel_PInfo = new JLabel("City:");

        stateLabel_PInfo = new JLabel("State:");

        zipCodeLabel_PInfo = new JLabel("Zip Code:");

        userLabel_PInfo = new JLabel("Username:");

        pwLabel_PInfo = new JLabel("Password:");


        // create text fields

        firstNameTextField_PInfo = new JTextField(12);

        middleNameTextField_PInfo = new JTextField(12);

        lastNameTextField_PInfo = new JTextField(12);

        ssnTextField_PInfo = new JTextField(12);

        dobTextField_PInfo = new JTextField(12);

        phoneNumberTextField_PInfo = new JTextField(12);

        addressTextField_PInfo = new JTextField(12);

        cityTextField_PInfo = new JTextField(12);

        zipCodeTextField_PInfo = new JTextField(12);

        userField_PInfo = new JTextField(12);

        pwField_PInfo = new JTextField(12);


        // combo box

        stateComboBox_PInfo = new JComboBox<>(states);


        // create buttons

        updateInfoButton_PInfo = new JButton("Update Existing Patient");

        submitNewInfoButton_PInfo = new JButton("Create New Patient File");


        // set the constraints for each component and add

        // them to the patient info panel


        patientTabConstraints.gridx = 10;

        patientTabConstraints.gridy = 10;

        patientTabConstraints.weightx = 1;

        patientTabConstraints.weighty = 0.4;

        patientTabConstraints.anchor = GridBagConstraints.WEST;

        patientTabConstraints.insets = new Insets(0, 20, 0, 0);

        patientTab.add(lastNameLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 20;

        patientTab.add(firstNameLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 30;

        patientTab.add(middleNameLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 10;

        patientTabConstraints.gridy = 20;

        patientTab.add(ssnLabel_PInfo, patientTabConstraints);


        // add username label

        patientTabConstraints.gridx = 20;

        patientTab.add(userLabel_PInfo, patientTabConstraints);


        // add password label

        patientTabConstraints.gridx = 30;

        patientTab.add(pwLabel_PInfo, patientTabConstraints);


        // add DOB label

        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 30;

        patientTab.add(dobLabel_PInfo, patientTabConstraints);



        patientTabConstraints.gridy = 40;


        patientTabConstraints.weighty = 1;


        patientTabConstraints.anchor = GridBagConstraints.NORTHWEST;


        patientTabConstraints.insets = new Insets(10, 20, 0, 0);


        patientTab.add(phoneNumberLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 50;


        patientTabConstraints.anchor = GridBagConstraints.SOUTHWEST;


        patientTabConstraints.insets = new Insets(0, 20, 10, 0);


        patientTab.add(streetLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 60;


        patientTabConstraints.weighty = 0.4;


        patientTabConstraints.anchor = GridBagConstraints.WEST;


        patientTabConstraints.insets = new Insets(0, 20, 0, 0);


        patientTab.add(cityLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 70;


        patientTab.add(stateLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 80;


        patientTab.add(zipCodeLabel_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 10;


        patientTabConstraints.anchor = GridBagConstraints.EAST;


        patientTabConstraints.insets = new Insets(0, 0, 0, 40);


        patientTab.add(lastNameTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 20;


        patientTabConstraints.insets = new Insets(0, 0, 0, 60);


        patientTab.add(firstNameTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 30;


        patientTab.add(middleNameTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridx = 10;


        patientTabConstraints.gridy = 20;


        patientTabConstraints.insets = new Insets(0, 0, 0, 40);


        patientTab.add(ssnTextField_PInfo, patientTabConstraints);


        // add username textfield

        patientTabConstraints.gridx = 20;
        patientTabConstraints.insets = new Insets(0, 0, 0, 60);

        patientTab.add(userField_PInfo, patientTabConstraints);


        // add password textfield

        patientTabConstraints.gridx = 30;

        patientTab.add(pwField_PInfo, patientTabConstraints);


        // add DOB textfield

        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 30;
        patientTabConstraints.insets = new Insets(0, 0, 0, 40);

        patientTab.add(dobTextField_PInfo, patientTabConstraints);



        patientTabConstraints.gridy = 40;


        patientTabConstraints.anchor = GridBagConstraints.NORTHEAST;


        patientTabConstraints.insets = new Insets(10, 0, 0, 40);


        patientTab.add(phoneNumberTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 50;


        patientTabConstraints.anchor = GridBagConstraints.SOUTHEAST;


        patientTabConstraints.insets = new Insets(0, 0, 10, 40);


        patientTab.add(addressTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 60;


        patientTabConstraints.anchor = GridBagConstraints.EAST;


        patientTabConstraints.insets = new Insets(0, 0, 0, 40);


        patientTab.add(cityTextField_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 70;


        patientTab.add(stateComboBox_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 80;


        patientTab.add(zipCodeTextField_PInfo, patientTabConstraints);







        /* ADJUSTED -"update existing patient" and "create new patient file" buttons */


        patientTabConstraints.gridx = 20;


        patientTabConstraints.gridy = 70;


        patientTabConstraints.ipady = 10;


        patientTabConstraints.anchor = GridBagConstraints.CENTER;


        patientTab.add(updateInfoButton_PInfo, patientTabConstraints);


        patientTabConstraints.gridy = 80;


        patientTab.add(submitNewInfoButton_PInfo, patientTabConstraints);


        // TAB 3: Billing


        billingTab = new JPanel(new GridBagLayout());


        GridBagConstraints billingTabConstraints = new GridBagConstraints();


        patientBillingLabel = new JLabel("Patient Billing");


        lNameBillLabel = new JLabel("Patient Last Name:");


        ssnBillLabel = new JLabel("SSN:");


        billingCodeLabel = new JLabel("Billing Code:");


        policyLabel = new JLabel("Policy:");


        amtDueLabel = new JLabel("Amount Due");


        lNameBillField = new JTextField(12);


        ssnBillField = new JTextField(12);


        amtDueField = new JTextField(12);


        amtDueField.setEditable(false);


        patientBillingLabel.setFont(new java.awt.Font(patientBillingLabel.getFont().getFontName(), Font.PLAIN, 40));


        String[] billingCodeOptions = {"1110", "2110", "3110"};


        String[] policyOptions = {"Yes", "No"};


        codeCB = new JComboBox<String>(billingCodeOptions);


        policyCB = new JComboBox<String>(policyOptions);


        // add contents to Billing tab


        billingTabConstraints.gridx = 10;


        billingTabConstraints.gridy = 0;


        billingTabConstraints.gridwidth = 40;


        billingTabConstraints.weighty = 0.2;


        billingTabConstraints.anchor = GridBagConstraints.NORTH;


        billingTabConstraints.insets = new Insets(20, 0, 0, 0);


        billingTab.add(patientBillingLabel, billingTabConstraints);


        billingTabConstraints.gridy = 10;


        billingTabConstraints.gridwidth = 10;


        billingTabConstraints.weightx = 0.2;


        billingTabConstraints.anchor = GridBagConstraints.WEST;


        billingTabConstraints.insets = new Insets(0, 20, 0, 0);


        billingTab.add(lNameBillLabel, billingTabConstraints);


        billingTabConstraints.gridx = 30;


        billingTab.add(ssnBillLabel, billingTabConstraints);


        billingTabConstraints.gridx = 20;


        billingTabConstraints.weightx = 1;


        billingTab.add(lNameBillField, billingTabConstraints);


        billingTabConstraints.gridx = 40;


        billingTab.add(ssnBillField, billingTabConstraints);


        billingTabConstraints.gridx = 10;


        billingTabConstraints.gridy = 20;


        billingTabConstraints.weightx = 0.2;


        billingTab.add(billingCodeLabel, billingTabConstraints);


        billingTabConstraints.gridx = 20;


        billingTab.add(codeCB, billingTabConstraints);


        billingTabConstraints.gridx = 30;


        billingTab.add(policyLabel, billingTabConstraints);


        billingTabConstraints.gridx = 40;


        billingTabConstraints.weightx = 1;


        billingTab.add(policyCB, billingTabConstraints);


        billingTabConstraints.gridx = 10;


        billingTabConstraints.gridy = 30;


        billingTabConstraints.weightx = 0.2;


        billingTab.add(amtDueLabel, billingTabConstraints);


        billingTabConstraints.gridx = 20;


        billingTabConstraints.weightx = 1;


        billingTab.add(amtDueField, billingTabConstraints);


        // ADD CALCULATE BUTTON

        // TAB 4: Search

        searchTab = new JPanel(new GridBagLayout());

        GridBagConstraints searchTabConstraints = new GridBagConstraints();

        searchButton = new JButton("Search");
        searchDirectionLabel = new JLabel("Search for Patient using Last Name & First Name");
        lNameSearchLabel = new JLabel("Last Name:");
        lNameSearchField = new JTextField(12);
        fNameSearchLabel = new JLabel("First Name:");
        fNameSearchField = new JTextField(12);
        searchDirectionLabel.setFont(new java.awt.Font(searchDirectionLabel.getFont().getFontName(), Font.PLAIN, 30));


        // add components to Search tab

        // add search directions label

        searchTabConstraints.gridx = 10;
        searchTabConstraints.gridy = 10;
        searchTabConstraints.weighty = 0.2;
        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.insets = new Insets(30, 0, 0, 0);

        searchTab.add(searchDirectionLabel, searchTabConstraints);


        // add last name search label

        searchTabConstraints.gridx = 10;
        searchTabConstraints.gridy = 20;
        searchTabConstraints.anchor = GridBagConstraints.CENTER;
        searchTabConstraints.insets = new Insets(0, 0, 0, 120);

        searchTab.add(lNameSearchLabel, searchTabConstraints);


        // add last name search textfield

        searchTabConstraints.insets = new Insets(0, 110, 0, 0);

        searchTab.add(lNameSearchField, searchTabConstraints);


        // add first name search label
        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.gridy = 30;
        searchTabConstraints.insets = new Insets(0, 0, 0, 150);

        searchTab.add(fNameSearchLabel, searchTabConstraints);


        // add first name search textfield
        searchTabConstraints.insets = new Insets(0, 110, 0, 0);

        searchTab.add(fNameSearchField, searchTabConstraints);

        // add search button

        searchTabConstraints.gridy = 40;
        searchTabConstraints.ipadx = 30;
        searchTabConstraints.weighty = 0.5;
        searchTabConstraints.ipady = 10;
        searchTabConstraints.insets = new Insets(0, 0, 0, 0);

        searchTab.add(searchButton, searchTabConstraints);


        searchResultLabel = new JLabel("Result of Search");
        choosePatientCB = new JComboBox<String>();
        selectPatientFoundButton = new JButton("Select Patient");


        // add search result label

        searchTabConstraints.gridy = 50;
        searchTabConstraints.weighty = 0.2;
        searchTabConstraints.insets = new Insets(0, 0, 0, 60);

        searchTab.add(searchResultLabel, searchTabConstraints);


        // add choose patient combobox

        searchTabConstraints.weighty = 1;
        searchTabConstraints.insets = new Insets(50, 0, 0, 175);

        searchTab.add(choosePatientCB, searchTabConstraints);


        // add select patientfound button

        searchTabConstraints.ipady = 10;
        searchTabConstraints.insets = new Insets(50, 225, 0, 0);

        searchTab.add(selectPatientFoundButton, searchTabConstraints);


        // add panels to tabbed pane

        tabbedPane.add("Calendar", calTab);
        tabbedPane.add("Patient Information", patientTab);
        tabbedPane.add("Billing", billingTab);
        tabbedPane.add("Search", searchTab);

        // set up login panel - what is shown first to Employee

        add(loginPanel, BorderLayout.CENTER);

        validate();

        // ALL ACTION LISTENERS

        // LOGIN BUTTON LISTENER

        loginButton.addActionListener(e -> {
            if (String.valueOf(usernameTextField.getText()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Username");
            else if (String.valueOf(passwordField.getPassword()).equals(""))
                JOptionPane.showMessageDialog
                        (null, "Must Enter A Password");
            else if (MainGUI.pimsSystem.staff_exists(usernameTextField.getText(), String.valueOf(passwordField.getPassword()))) {
                remove(loginPanel);
                add(tabbedPane);
                JOptionPane.showMessageDialog
                        (null, "Login Successful");
                repaint();
                revalidate();

                // reset username and password fields

                usernameTextField.setText("");
                passwordField.setText("");
            } else
                JOptionPane.showMessageDialog
                        (null, "Invalid Password or Username");
        });

        // CALENDAR TAB LISTENERS

        requestAppointmentButton.addActionListener(e -> {
            if (!cal_patientNameTextField.getText().equals((""))) {
                patient = MainGUI.pimsSystem.patient_details
                        (cal_patientNameTextField.getText(), Integer.parseInt(ssnTextField_PInfo.getText()));
                if (patient == null)
                    JOptionPane.showMessageDialog(null, "No patient with that Last Name and SSN exists in our system");
                else if (!MainGUI.pimsSystem.lookUpAppointmentDate(patient).equals(""))
                    JOptionPane.showMessageDialog(null, "This patient already has an appointment");
                else {
                    if (MainGUI.pimsSystem.add_date(datePicker.getText(), timePicker.getText(), patient)) {
                        JOptionPane.showMessageDialog
                                (null, "Appointment Saved");
                        lookUpAppointmentTextField.setText(MainGUI.pimsSystem.lookUpAppointmentDate(patient));
                        validate();
                    } else JOptionPane.showMessageDialog
                            (null, "Sorry. This Time Slot Is Taken. Select Another Date or Time");
                } //else JOptionPane.showMessageDialog(null, "Error");
            } else {
                JOptionPane.showMessageDialog(null, "Must Search a Patient First");
            }
        });

        lookUpAppointmentButton.addActionListener(e -> {
            if (!cal_patientNameTextField.getText().equals((""))) {
                patient = MainGUI.pimsSystem.patient_details
                        (cal_patientNameTextField.getText(), Integer.parseInt(ssnTextField_PInfo.getText()));
                String appointment = MainGUI.pimsSystem.lookUpAppointmentDate(patient);
                if (String.valueOf(appointment).equals(""))
                    JOptionPane.showMessageDialog
                            (null, "Requested patient has no Appointment Scheduled At This Time");
                else lookUpAppointmentTextField.setText(appointment);
            } else {
                JOptionPane.showMessageDialog(null, "Must Search a Patient First");
            }
        });

        cancelAppointmentButton.addActionListener(e -> {
            if (!cal_patientNameTextField.getText().equals((""))) {
                patient = MainGUI.pimsSystem.patient_details
                        (cal_patientNameTextField.getText(), Integer.parseInt(ssnTextField_PInfo.getText()));
                if (MainGUI.pimsSystem.lookUpAppointmentDate(patient).equals(""))
                    JOptionPane.showMessageDialog
                            (null, "No Appointment Scheduled At This Time");
                else if (String.valueOf(lookUpAppointmentTextField.getText()).equals(""))
                    JOptionPane.showMessageDialog(null, "Must Lookup Appointment First");
                else {
                    if (!MainGUI.pimsSystem.patient_delete_date(patient))
                        JOptionPane.showMessageDialog
                                (null, "No Appointment Scheduled At This Time");
                    else {
                        JOptionPane.showMessageDialog(null, "Appointment Deleted");
                        lookUpAppointmentTextField.setText("");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Must Search a Patient First");
            }
        });

        // PATIENT INFO TAB LISTENERS

        // submits a new patient info into the system

        submitNewInfoButton_PInfo.addActionListener(e -> {
            // values to test if there are no input errors
            boolean emptyFields = true, illegalFields = true;

            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
            String errorMessage = "Must Enter";
            if (String.valueOf(firstNameTextField_PInfo.getText()).equals("")) {
                errorMessage += " First Name,";
                emptyFields = false;
            }
            if (String.valueOf(lastNameTextField_PInfo.getText()).equals("")) {
                errorMessage += " Last Name,";
                emptyFields = false;
            }
            if (String.valueOf(ssnTextField_PInfo.getText()).equals("")) {
                errorMessage += " Social Security #,";
                emptyFields = false;
            }
            if (String.valueOf(dobTextField_PInfo.getText()).equals("")) {
                errorMessage += " Date of Birth,";
                emptyFields = false;
            }
            if (String.valueOf(phoneNumberTextField_PInfo.getText()).equals("")) {
                errorMessage += " Phone Number,";
                emptyFields = false;
            }
            if (String.valueOf(addressTextField_PInfo).equals("")) {
                errorMessage += " Street,";
                emptyFields = false;
            }
            if (String.valueOf(cityTextField_PInfo).equals("")) {
                errorMessage += " City,";
                emptyFields = false;
            }
            if (String.valueOf(zipCodeTextField_PInfo).equals("")) {
                errorMessage += " Zip Code,";
                emptyFields = false;
            }
            if (String.valueOf(userField_PInfo).equals("")) {
                errorMessage += " Username,";
                emptyFields = false;
            }
            if (String.valueOf(pwField_PInfo).equals("")) {
                errorMessage += " password,";
                emptyFields = false;
            }
            // if there's no middle name, the text field
            // is set to "N/A"

            String middleName;
            if (String.valueOf(middleNameTextField_PInfo.getText()).equals(""))
                middleName = "N/A";
            else middleName = middleNameTextField_PInfo.getText();

            // throws error if last name has characters other than letters

            if (lastNameTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < lastNameTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(lastNameTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Last Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if first name has characters other than letters

            if (firstNameTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < firstNameTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(firstNameTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "First Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if middle name has characters other than letters

            if (middleNameTextField_PInfo.getText().length() > 0 &&
                    !String.valueOf(middleNameTextField_PInfo.getText()).equals("N/A")) {
                for (int i = 0; i < middleNameTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(middleNameTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Middle Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if SSN has characters other than numbers, or has less/more than 4 digits

            if (ssnTextField_PInfo.getText().length() > 0 && ssnTextField_PInfo.getText().length() != 4) {
                JOptionPane.showMessageDialog
                        (null, "Social Security # Must Have 4 Characters");
                illegalFields = false;
            } else if (ssnTextField_PInfo.getText().length() == 4) {
                for (int i = 0; i < 4; i++) {
                    if (!Character.isDigit(ssnTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Social Security # Must Have Only Numbers");
                        illegalFields = false;
                        break;
                    }
                }
            }
            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"

            if (dobTextField_PInfo.getText().length() > 0 && dobTextField_PInfo.getText().length() != 10) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            } else if (dobTextField_PInfo.getText().length() == 10) {
                if (!DOBparser(dobTextField_PInfo.getText())) {
                    JOptionPane.showMessageDialog
                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                    illegalFields = false;
                }
            }

            // throws error if phone number isn't formatted correctly - "###-###-####"

            if (phoneNumberTextField_PInfo.getText().length() > 0 && phoneNumberTextField_PInfo.getText().length() != 12) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            } else if (phoneNumberTextField_PInfo.getText().length() == 12) {
                if (!phoneNumberParser(phoneNumberTextField_PInfo.getText())) {
                    JOptionPane.showMessageDialog
                            (null, "Phone Number Must be formatted \"###-###-####\"");
                    illegalFields = false;
                }
            }

            // throws error if address has characters other than letters and numbers

            if (addressTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < addressTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(addressTextField_PInfo.getText().charAt(i)) &&
                            !Character.isDigit(addressTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Address Must Have Only Numbers and Letters");
                        illegalFields = false;
                    }
                }
            }

            // throws error if city has characters other than letters

            if (cityTextField_PInfo.getText().length() > 0) {
                for (int i = 1; i < cityTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(cityTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "City Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }
            // throws error if zip code has characters other than numbers, or has less/more than 4 digits

            if (zipCodeTextField_PInfo.getText().length() > 0 && zipCodeTextField_PInfo.getText().length() != 5) {
                JOptionPane.showMessageDialog
                        (null, "Zip Code Must Have 5 Characters");
                illegalFields = false;
            } else if (zipCodeTextField_PInfo.getText().length() == 5) {
                for (int i = 0; i < 5; i++) {
                    if (!Character.isDigit(zipCodeTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Zip Code Must Have Only Numbers");
                        illegalFields = false;
                        break;
                    }
                }
            }
            // checks if there are no input errors

            if (emptyFields && illegalFields) {
                if (MainGUI.pimsSystem.patient_exists(firstNameTextField_PInfo.getText(),
                        lastNameTextField_PInfo.getText(), dobTextField_PInfo.getText(), Integer.parseInt(ssnTextField_PInfo.getText())))
                    JOptionPane.showMessageDialog
                            (null, "This Patient Is Already In The System");
                else {
                    MainGUI.pimsSystem.add_patient(firstNameTextField_PInfo.getText(),
                            lastNameTextField_PInfo.getText(), middleNameTextField_PInfo.getText(),
                            "user", "password",
                            dobTextField_PInfo.getText(),
                            Integer.parseInt(ssnTextField_PInfo.getText()), Integer.parseInt(zipCodeTextField_PInfo.getText()),
                            addressTextField_PInfo.getText(), cityTextField_PInfo.getText(),
                            String.valueOf(stateComboBox_PInfo.getSelectedItem()), phoneNumberTextField_PInfo.getText());

                    // set the patient info panel in the tabbed pane to
                    // to info from the create new info patient panel

                    JOptionPane.showMessageDialog
                            (null, "Submission Successful");
                    repaint();
                    revalidate();
                }
            } else if (!String.valueOf(errorMessage).equals("Must Enter")) {
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        });

        updateInfoButton_PInfo.addActionListener(e -> {
            // values to test if there are no input errors
            boolean emptyFields = true, illegalFields = true;

            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
            String errorMessage = "Must Enter";
            if (String.valueOf(firstNameTextField_PInfo.getText()).equals("")) {
                errorMessage += " First Name,";
                emptyFields = false;
            }
            if (String.valueOf(lastNameTextField_PInfo.getText()).equals("")) {
                errorMessage += " Last Name,";
                emptyFields = false;
            }
            if (String.valueOf(ssnTextField_PInfo.getText()).equals("")) {
                errorMessage += " Social Security #,";
                emptyFields = false;
            }
            if (String.valueOf(dobTextField_PInfo.getText()).equals("")) {
                errorMessage += " Date of Birth,";
                emptyFields = false;
            }
            if (String.valueOf(phoneNumberTextField_PInfo.getText()).equals("")) {
                errorMessage += " Phone Number,";
                emptyFields = false;
            }
            if (String.valueOf(addressTextField_PInfo).equals("")) {
                errorMessage += " Street,";
                emptyFields = false;
            }
            if (String.valueOf(cityTextField_PInfo).equals("")) {
                errorMessage += " City,";
                emptyFields = false;
            }

            if (String.valueOf(zipCodeTextField_PInfo).equals("")) {
                errorMessage += " Zip Code,";
                emptyFields = false;
            }

            // if there's no middle name, the text field
            // is set to "N/A"

            String middleName;
            if (String.valueOf(middleNameTextField_PInfo.getText()).equals(""))
                middleName = "N/A";
            else middleName = middleNameTextField_PInfo.getText();

            // throws error if last name has characters other than letters

            if (lastNameTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < lastNameTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(lastNameTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Last Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if first name has characters other than letters

            if (firstNameTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < firstNameTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(firstNameTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "First Name Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if middle name has characters other than letters

            if (middleNameTextField_PInfo.getText().length() > 0 &&
                    !String.valueOf(middleNameTextField_PInfo.getText()).equals("N/A")) {
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

            if (ssnTextField_PInfo.getText().length() > 0 && ssnTextField_PInfo.getText().length() != 4) {
                JOptionPane.showMessageDialog
                        (null, "Social Security # Must Have 4 Characters");
                illegalFields = false;
            } else if (ssnTextField_PInfo.getText().length() == 4) {
                for (int i = 0; i < 4; i++) {
                    if (!Character.isDigit(ssnTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Social Security # Must Have Only Numbers");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"

            if (dobTextField_PInfo.getText().length() > 0 && dobTextField_PInfo.getText().length() != 10) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            } else if (dobTextField_PInfo.getText().length() == 10) {
                if (!DOBparser(dobTextField_PInfo.getText())) {
                    JOptionPane.showMessageDialog
                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                    illegalFields = false;
                }
            }

            // throws error if phone number isn't formatted correctly - "###-###-####"

            if (phoneNumberTextField_PInfo.getText().length() > 0 && phoneNumberTextField_PInfo.getText().length() != 12) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            } else if (phoneNumberTextField_PInfo.getText().length() == 12) {
                if (!phoneNumberParser(phoneNumberTextField_PInfo.getText())) {
                    JOptionPane.showMessageDialog
                            (null, "Phone Number Must be formatted \"###-###-####\"");
                    illegalFields = false;
                }
            }

            // throws error if address has characters other than letters and numbers

            if (addressTextField_PInfo.getText().length() > 0) {
                for (int i = 0; i < addressTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(addressTextField_PInfo.getText().charAt(i)) &&
                            !Character.isDigit(addressTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Address Must Have Only Numbers and Letters");
                        illegalFields = false;
                    }
                }
            }


            // throws error if city has characters other than letters

            if (cityTextField_PInfo.getText().length() > 0) {
                for (int i = 1; i < cityTextField_PInfo.getText().length(); i++) {
                    if (!Character.isLetter(cityTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "City Must Have Only Letters");
                        illegalFields = false;
                        break;
                    }
                }
            }
            // throws error if zip code has characters other than numbers, or has less/more than 4 digits
            if (zipCodeTextField_PInfo.getText().length() > 0 && zipCodeTextField_PInfo.getText().length() != 5) {
                JOptionPane.showMessageDialog
                        (null, "Zip Code Must Have 5 Characters");
                illegalFields = false;
            } else if (zipCodeTextField_PInfo.getText().length() == 5) {
                for (int i = 0; i < 5; i++) {
                    if (!Character.isDigit(zipCodeTextField_PInfo.getText().charAt(i))) {
                        JOptionPane.showMessageDialog
                                (null, "Zip Code Must Have Only Numbers");
                        illegalFields = false;
                        break;
                    }
                }
            }

            // checks if there are no input errors

            patient = MainGUI.pimsSystem.patient_details
                    (lastNameTextField_PInfo.getText(), Integer.parseInt(ssnTextField_PInfo.getText()));
            if (emptyFields && illegalFields && patient != null) {
                JOptionPane.showMessageDialog
                        (null, "Information Updated");
                patient.l_name = lastNameTextField_PInfo.getText();
                patient.f_name = firstNameTextField_PInfo.getText();
                patient.m_name = middleName;
                patient.SSN = Integer.parseInt(ssnTextField_PInfo.getText());
                patient.dob = dobTextField_PInfo.getText();
                patient.p_number = phoneNumberTextField_PInfo.getText();
                patient.address = addressTextField_PInfo.getText();
                patient.city = cityTextField_PInfo.getText();
                patient.state = String.valueOf(stateComboBox_PInfo.getSelectedItem());
                patient.zip = Integer.parseInt(zipCodeTextField_PInfo.getText());
                patient.user_name = userField_PInfo.getText();
                patient.password = pwField_PInfo.getText();
            } else if (!String.valueOf(errorMessage).equals("Must Enter"))
                JOptionPane.showMessageDialog(null, errorMessage);
            else if (patient == null)
                JOptionPane.showMessageDialog(null, "Error");
        });
        // BILLING TAB LISTENERS

        // SEARCH TAB LISTENERS
        searchButton.addActionListener(e -> searchPatient());
    }// end constructor


    private boolean isSSNFourDigits(String toCheck) {
        if (toCheck.length() > 0 && toCheck.length() != 4) {
            JOptionPane.showMessageDialog
                    (null, "Social Security # Must Have 4 Characters");
            return false;
        } else if (toCheck.length() == 0) {
            JOptionPane.showMessageDialog
                    (null, "No Social Security # entered");
            return false;
        } else if (toCheck.length() == 4) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(toCheck.charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Social Security # Must Have Only Numbers");
                    return false;
                }
            }
        }
        return true;
    }


	/*

	 *

	 */


    private void searchPatient() {


        String lName, fName;


        lName = lNameSearchField.getText();

        fName = fNameSearchField.getText();


        // find patients with the Last & First Name entered

        ArrayList<patient> patientsFound = MainGUI.pimsSystem.search_patient(lName, fName);


        if (patientsFound.size() > 0) {


            // display whether patients found or not


            JOptionPane.showMessageDialog(this, "Found Results for Last Name, First Name: " + lName + ", " + fName

                            + ".\nPlease select a patient and then press \"Select\" to see information.",


                    "Search Successful", JOptionPane.DEFAULT_OPTION);


            // create String ArrayList of patients: Last, First (DOB)

            ArrayList<String> foundList = new ArrayList<String>();

            String toAdd = "";


            for (patient p : patientsFound) {


                toAdd = p.l_name + ", " + p.f_name + " (" + p.dob + ")";

                foundList.add(toAdd);

            }


            int length;


            // clear combo box (in case this is a second search)

            while ((length = choosePatientCB.getItemCount()) > 0) {

                choosePatientCB.removeItemAt(length - 1);

            }


            // add Patient Options to combo box


            for (int i = 0; i < foundList.size(); i++) {

                choosePatientCB.addItem(foundList.get(i));

            }


            patient patientFound = patientsFound.get((choosePatientCB.getSelectedIndex()));


            selectPatientFoundButton.addActionListener(e -> fillPatientFoundData(patientFound));


            repaint();

            revalidate();

        }


        // no patient found

        else {


            JOptionPane.showMessageDialog(this, "No Results found for Last Name, First Name:" + lName + ", " + fName,


                    "Search Failed", JOptionPane.ERROR_MESSAGE);

        }


    } // end searchPatient





	/*

	 * fillPatientFoundData()

	 */

    private void fillPatientFoundData(patient toDisplay) {


        JOptionPane.showMessageDialog(this, "Filling in Information for Patient Found",

                "Filling in Info", JOptionPane.DEFAULT_OPTION);


        // Calendar Tab

        lookUpAppointmentTextField.setText(MainGUI.pimsSystem.lookUpAppointmentDate(toDisplay));

        //cal_SSNTextField.setText(Integer.toString(toDisplay.SSN));

        cal_patientNameTextField.setText(toDisplay.l_name + ", " + toDisplay.f_name);


        // Patient Info Tab

        lastNameTextField_PInfo.setText(toDisplay.l_name);

        firstNameTextField_PInfo.setText(toDisplay.f_name);

        middleNameTextField_PInfo.setText(toDisplay.m_name);

        ssnTextField_PInfo.setText(Integer.toString(toDisplay.SSN));

        dobTextField_PInfo.setText(toDisplay.dob);

        phoneNumberTextField_PInfo.setText(toDisplay.p_number);

        addressTextField_PInfo.setText(toDisplay.address);

        cityTextField_PInfo.setText(toDisplay.city);

        zipCodeTextField_PInfo.setText(Integer.toString(toDisplay.zip));

        stateComboBox_PInfo.setSelectedItem(toDisplay.state);

        userField_PInfo.setText(toDisplay.user_name);

        pwField_PInfo.setText(toDisplay.p_number);


        // Billing Tab

        lNameBillField.setText(toDisplay.l_name);

        ssnBillField.setText(Integer.toString(toDisplay.SSN));



        /* TO DO

         *

         * -ask how to add policy and patient history to patient class

         */


        repaint();

        revalidate();


    }// end fillPatientData()


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







	/*

	 * Calendar Tab: DatePicker & TimePicker related methods

	 *

	 */


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


    } // end createDatePicker


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


    } // end createTimePicker





    /*

     * END of DatePicker related methods & private classes

     */



	/*

	 * main for just employeeGUI

	 */

    @SuppressWarnings("unused")

    public static void main(String[] args) {


        MainGUI mainGUI = new MainGUI();


        EmployeeGUI testGUI = new EmployeeGUI();


        mainGUI.setLayout(new GridLayout(1, 0));


        mainGUI.setSize(1000, 600);


        mainGUI.validate();


        mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainGUI.setLocationRelativeTo(null); // GUI appear in center

        mainGUI.setVisible(true);


    }// end main


}// end EmployeeGUI class
