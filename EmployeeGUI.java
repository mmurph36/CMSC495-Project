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

/*
 * TO DO
 *
 *  -check for unused components in GUI
 *  -find overlaps in code, to pull out and make functions
 *  -update billing tab & patient object to account for policy
 *  	-what fields do we want on the billing tab?
 *
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;

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

    // Login panel
    JPanel loginPanel;
    GridBagConstraints loginConstraints;
    JLabel login_directionLabel, login_usernameLabel, login_passwordLabel;
    JTextField login_usernameTextField;
    JPasswordField login_passwordField;
    JButton loginButton;

    /* Employee Window (Tabbed Pane) */
    // 5 Tabs: Appointments, Patient Information, Billing, Search, Calendar
    JTabbedPane tabbedPane;

    // TAB 1: Appointments
    JPanel appTab;
    GridBagConstraints appointmentConstraints;
    JLabel app_chooseDateAndTimeLabel, cal_currentAppointmentLabel,
            cal_patientSSNLabel, app_patientNameLabel, app_lookUpAppointmentLabel,
            cal_currentPatientLabel;
    JTextField cal_currentAppointmentTextField, cal_SSNTextField,
            app_patientNameTextField, app_lookUpAppointmentTextField,
            cal_currentPatientTextField;
    DatePicker datePicker;
    TimePicker timePicker;
    JButton app_requestAppointmentButton, app_cancelAppointmentButton, app_lookUpAppointmentButton;


    // TAB 2: Patient Information
    JPanel patientTab;
    GridBagConstraints patientTabConstraints;
    JLabel pInfo_lastNameLabel, pInfo_firstNameLabel, pInfo_middleNameLabel,
            pInfo_ssnLabel, pInfo_dobLabel, pInfo_phoneNumberLabel,
            pInfo_streetLabel, pInfo_cityLabel, pInfo_stateLabel, pInfo_zipCodeLabel,
            pInfo_userLabel, pInfo_pwLabel;
    JTextField pInfo_lastNameTextField, pInfo_firstNameTextField, pInfo_middleNameTextField,
            pInfo_ssnTextField, pInfo_dobTextField, pInfo_phoneNumberTextField,
            pInfo_addressTextField, pInfo_cityTextField, pInfo_zipCodeTextField,
            pInfo_userField, pInfo_pwField;
    JComboBox<String> pInfo_stateComboBox;
    static String[] pInfo_states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
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
    JButton pInfo_updateInfoButton, pInfo_submitNewInfoButton;

    // TAB 3: Billing
    JPanel billingTab;
    GridBagConstraints billingTabConstraints;
    JLabel billing_patientBillingLabel, billing_lNameLabel, billing_ssnLabel,
            billing_billCodeLabel, billing_policyLabel, billing_amtDueLabel;
    JTextField billing_lNameField, billing_ssnField, billing_amtDueField;
    JComboBox<String> billing_codeCB, billing_policyCB;
    JButton billing_calculateButton; // NEW & TO-ADD

    // TAB 4: Search
    JPanel searchTab;
    GridBagConstraints searchTabConstraints;
    JLabel search_lNameLabel, search_fNameLabel,
            search_searchDirectionLabel, search_searchResultLabel;
    JTextField search_lNameField, search_fNameField;
    JButton search_searchButton, search_selectPatientFoundButton;
    JComboBox<String> search_choosePatientCB;
    ArrayList<patient> patientsFound;

    // TAB 5: Calendar
    JPanel calTab;
    GridBagConstraints calendarConstraints;
    JLabel cal_calendarLabel, cal_chooseDateLabel, cal_calendarDisplayLabel;
    JButton cal_chooseDateButton;
    DatePicker cal_datePicker;
    JTable cal_table;
    JScrollPane cal_scrollPane;

    // Constructor
    public EmployeeGUI() {
        initialize();
    }

    /*
     * initialize()
     * - sets up EmployeeGUI panel
     */
    private void initialize() {

        // set up EmployeeGUI JPanel
        setLayout(new BorderLayout());

        initializeLogInPanel();

        // ** initialize JTabbedPane **
        tabbedPane = new JTabbedPane();

        initializeAppointmentsTab();
        initializePatientInfoTab();
        initializeBillingTab();
        initializeSearchTab();
        initializeCalendarTab();

        // add panels to tabbed pane
        tabbedPane.add("Appointments", appTab);
        tabbedPane.add("Patient Information", patientTab);
        tabbedPane.add("Billing", billingTab);
        tabbedPane.add("Search", searchTab);
        tabbedPane.add("Calendar", calTab);

        // set up login panel - what is shown first to Employee
        add(loginPanel, BorderLayout.CENTER);

        validate();

        // ALL ACTION LISTENERS

        // Login Listeners
        loginButton.addActionListener(e -> login());

        // Calendar Tab listeners
        app_requestAppointmentButton.addActionListener(e -> app_requestAppointment());
        app_lookUpAppointmentButton.addActionListener(e -> app_lookUpAppointment());
        app_cancelAppointmentButton.addActionListener(e -> app_cancelAppointment());

        // Patient Info Tab Listeners
        pInfo_submitNewInfoButton.addActionListener(e -> pInfo_createNew());
        pInfo_updateInfoButton.addActionListener(e -> pInfo_updateExisting());

        // Billing Tab Listeners
        billing_calculateButton.addActionListener(e -> billing_calculate());

        // SearchTab Listeners
        search_searchButton.addActionListener(e -> searchPatient());
        search_selectPatientFoundButton.addActionListener(e -> search_selectPatientToDisplay());

        // Calendar Tab Listeners
        cal_chooseDateButton.addActionListener(e -> search_date());

    }// end initialize()

    /* START initialize() related Functions (called within constructor) */

    // LogIn Panel
    private void initializeLogInPanel() {

        // set up Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        loginConstraints = new GridBagConstraints();

        login_directionLabel = new JLabel("Employee Login");
        login_usernameLabel = new JLabel("Username:");
        login_passwordLabel = new JLabel("Password:");
        login_directionLabel.setFont(new Font(login_directionLabel.getFont().getFontName(), Font.PLAIN, 40));

        login_usernameTextField = new JTextField(12);
        login_passwordField = new JPasswordField(12);

        loginButton = new JButton("Login");

        // add login label
        loginConstraints.gridx = 10;
        loginConstraints.gridy = 10;
        loginConstraints.weighty = 0.2;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(50, 5, 0, 30);
        loginPanel.add(login_directionLabel, loginConstraints);

        // add username label
        loginConstraints.gridy = 20;
        loginConstraints.weighty = 0;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(40, 25, 0, 10);
        loginPanel.add(login_usernameLabel, loginConstraints);

        // add password label
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(10, 25, 0, 10);
        loginPanel.add(login_passwordLabel, loginConstraints);

        // add username textfield
        loginConstraints.gridx = 20;
        loginConstraints.gridy = 20;
        loginConstraints.gridwidth = 20;
        loginConstraints.insets = new Insets(40, 0, 0, 25);
        loginPanel.add(login_usernameTextField, loginConstraints);

        // add password textfield
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(12, 0, 0, 25);
        loginPanel.add(login_passwordField, loginConstraints);

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

    } // end initializeLogInPanel

    // TAB 1: Appointments
    private void initializeAppointmentsTab() {

        appTab = new JPanel(new GridBagLayout());
        appointmentConstraints = new GridBagConstraints();

        app_chooseDateAndTimeLabel = new JLabel("Select Date and Time For Appointment");
        app_patientNameLabel = new JLabel("Patient Name:");
        app_lookUpAppointmentLabel = new JLabel("Look Up Patient's Existing Appointment");
        app_chooseDateAndTimeLabel.setFont(new java.awt.Font(app_chooseDateAndTimeLabel.getFont().getFontName(), Font.PLAIN, 30));
        app_lookUpAppointmentLabel.setFont(new java.awt.Font(app_lookUpAppointmentLabel.getFont().getFontName(), Font.PLAIN, 25));


        datePicker = createDatePicker(true);
        timePicker = createTimePicker();

        app_patientNameTextField = new JTextField(12);
        app_patientNameTextField.setEditable(false);
        app_patientNameTextField.setBackground(Color.white);
        app_lookUpAppointmentTextField = new JTextField(15);
        app_lookUpAppointmentTextField.setEditable(false);
        app_lookUpAppointmentTextField.setBackground(Color.white);

        app_requestAppointmentButton = new JButton("Request Appointment");
        app_cancelAppointmentButton = new JButton("Cancel Appointment");
        app_lookUpAppointmentButton = new JButton("Look Up Appointment");

        // add components to appointments panel

        // appointments directions label
        appointmentConstraints.gridx = 10;
        appointmentConstraints.gridy = 10;
        appointmentConstraints.weightx = 1;
        appointmentConstraints.weighty = 0.2;
        appointmentConstraints.anchor = GridBagConstraints.NORTH;
        appointmentConstraints.insets = new Insets(20, 0, 0, 0);
        appTab.add(app_chooseDateAndTimeLabel, appointmentConstraints);


        // add patient name label
        appointmentConstraints.anchor = GridBagConstraints.NORTHEAST;
        appointmentConstraints.insets = new Insets(10, 0, 0, 40);
        appTab.add(app_patientNameLabel, appointmentConstraints);

        // add patient name textfield
        appointmentConstraints.insets = new Insets(35, 0, 0, 10);
        appTab.add(app_patientNameTextField, appointmentConstraints);

        // add date picker
        appointmentConstraints.gridy = 20;
        appointmentConstraints.weightx = 0;
        appointmentConstraints.anchor = GridBagConstraints.CENTER;
        appointmentConstraints.insets = new Insets(20, 0, 0, 110);
        appTab.add(datePicker, appointmentConstraints);

        // add time picker
        appointmentConstraints.insets = new Insets(20, 160, 0, 0);
        appTab.add(timePicker, appointmentConstraints);

        // add request appointment button
        appointmentConstraints.gridy = 30;
        appointmentConstraints.weighty = 1;
        appointmentConstraints.ipady = 10;
        appointmentConstraints.anchor = GridBagConstraints.NORTH;
        appointmentConstraints.insets = new Insets(30, 0, 0, 170);
        appTab.add(app_requestAppointmentButton, appointmentConstraints);

        // add cancel appointment button
        appointmentConstraints.ipady = 10;
        appointmentConstraints.insets = new Insets(30, 170, 0, 0);
        appTab.add(app_cancelAppointmentButton, appointmentConstraints);

        // add lookup appointment label
        appointmentConstraints.ipady = 0;
        appointmentConstraints.gridy = 40;
        appointmentConstraints.weighty = 0.2;
        appointmentConstraints.insets = new Insets(0, 0, 0, 0);
        appTab.add(app_lookUpAppointmentLabel, appointmentConstraints);

        // add lookup appt button
        appointmentConstraints.ipady = 10;
        appointmentConstraints.weighty = 1;
        appointmentConstraints.gridy = 50;
        appointmentConstraints.insets = new Insets(0, 0, 0, 195);
        appTab.add(app_lookUpAppointmentButton, appointmentConstraints);

        // add lookup appt text field
        appointmentConstraints.ipady = 5;
        appointmentConstraints.insets = new Insets(5, 180, 0, 0);
        appTab.add(app_lookUpAppointmentTextField, appointmentConstraints);

    }// end initializeAppointmentsTab

    // TAB 2: Patient Information
    private void initializePatientInfoTab() {

        patientTab = new JPanel(new GridBagLayout());
        patientTabConstraints = new GridBagConstraints();

        // create labels
        pInfo_firstNameLabel = new JLabel("First Name:");
        pInfo_middleNameLabel = new JLabel("Middle Name:");
        pInfo_lastNameLabel = new JLabel("Last Name:");
        pInfo_ssnLabel = new JLabel("Social Security #:");
        pInfo_dobLabel = new JLabel("Date of Birth:");
        pInfo_phoneNumberLabel = new JLabel("Phone Number:");
        pInfo_streetLabel = new JLabel("Street:");
        pInfo_cityLabel = new JLabel("City:");
        pInfo_stateLabel = new JLabel("State:");
        pInfo_zipCodeLabel = new JLabel("Zip Code:");
        pInfo_userLabel = new JLabel("Username:");
        pInfo_pwLabel = new JLabel("Password:");

        // create text fields
        pInfo_firstNameTextField = new JTextField(12);
        pInfo_middleNameTextField = new JTextField(12);
        pInfo_lastNameTextField = new JTextField(12);
        pInfo_ssnTextField = new JTextField(12);
        pInfo_dobTextField = new JTextField(12);
        pInfo_phoneNumberTextField = new JTextField(12);
        pInfo_addressTextField = new JTextField(12);
        pInfo_cityTextField = new JTextField(12);
        pInfo_zipCodeTextField = new JTextField(12);
        pInfo_userField = new JTextField(12);
        pInfo_pwField = new JTextField(12);

        // combo box
        pInfo_stateComboBox = new JComboBox<>(pInfo_states);

        // create buttons
        pInfo_updateInfoButton = new JButton("Update Existing Patient");
        pInfo_submitNewInfoButton = new JButton("Create New Patient File");

        // add components to the patient info panel

        // last name label
        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 10;
        patientTabConstraints.weightx = 1;
        patientTabConstraints.weighty = 0.4;
        patientTabConstraints.anchor = GridBagConstraints.WEST;
        patientTabConstraints.insets = new Insets(0, 20, 0, 0);
        patientTab.add(pInfo_lastNameLabel, patientTabConstraints);

        // add first name label
        patientTabConstraints.gridx = 20;
        patientTab.add(pInfo_firstNameLabel, patientTabConstraints);

        // add middle name label
        patientTabConstraints.gridx = 30;
        patientTab.add(pInfo_middleNameLabel, patientTabConstraints);

        // add ssn label
        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 20;
        patientTab.add(pInfo_ssnLabel, patientTabConstraints);

        // add username label
        patientTabConstraints.gridx = 20;
        patientTab.add(pInfo_userLabel, patientTabConstraints);

        // add password label
        patientTabConstraints.gridx = 30;
        patientTab.add(pInfo_pwLabel, patientTabConstraints);

        // add DOB label
        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 30;
        patientTab.add(pInfo_dobLabel, patientTabConstraints);

        // add phone # label
        patientTabConstraints.gridy = 40;
        patientTabConstraints.weighty = 1;
        patientTabConstraints.anchor = GridBagConstraints.NORTHWEST;
        patientTabConstraints.insets = new Insets(10, 20, 0, 0);
        patientTab.add(pInfo_phoneNumberLabel, patientTabConstraints);

        // add address label
        patientTabConstraints.gridy = 50;
        patientTabConstraints.anchor = GridBagConstraints.SOUTHWEST;
        patientTabConstraints.insets = new Insets(0, 20, 10, 0);
        patientTab.add(pInfo_streetLabel, patientTabConstraints);

        // add city label
        patientTabConstraints.gridy = 60;
        patientTabConstraints.weighty = 0.4;
        patientTabConstraints.anchor = GridBagConstraints.WEST;
        patientTabConstraints.insets = new Insets(0, 20, 0, 0);
        patientTab.add(pInfo_cityLabel, patientTabConstraints);

        // add state label
        patientTabConstraints.gridy = 70;
        patientTab.add(pInfo_stateLabel, patientTabConstraints);

        // add zip label
        patientTabConstraints.gridy = 80;
        patientTab.add(pInfo_zipCodeLabel, patientTabConstraints);

        // add last name text field
        patientTabConstraints.gridy = 10;
        patientTabConstraints.anchor = GridBagConstraints.EAST;
        patientTabConstraints.insets = new Insets(0, 0, 0, 40);
        patientTab.add(pInfo_lastNameTextField, patientTabConstraints);

        // add first name text field
        patientTabConstraints.gridx = 20;
        patientTabConstraints.insets = new Insets(0, 0, 0, 60);
        patientTab.add(pInfo_firstNameTextField, patientTabConstraints);

        // add middle name text field
        patientTabConstraints.gridx = 30;
        patientTab.add(pInfo_middleNameTextField, patientTabConstraints);

        // add ssn textfield
        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 20;
        patientTabConstraints.insets = new Insets(0, 0, 0, 40);
        patientTab.add(pInfo_ssnTextField, patientTabConstraints);

        // add username textfield
        patientTabConstraints.gridx = 20;
        patientTabConstraints.insets = new Insets(0, 0, 0, 60);
        patientTab.add(pInfo_userField, patientTabConstraints);

        // add password textfield
        patientTabConstraints.gridx = 30;
        patientTab.add(pInfo_pwField, patientTabConstraints);

        // add DOB textfield
        patientTabConstraints.gridx = 10;
        patientTabConstraints.gridy = 30;
        patientTabConstraints.insets = new Insets(0, 0, 0, 40);
        patientTab.add(pInfo_dobTextField, patientTabConstraints);

        // add phone number text field
        patientTabConstraints.gridy = 40;
        patientTabConstraints.anchor = GridBagConstraints.NORTHEAST;
        patientTabConstraints.insets = new Insets(10, 0, 0, 40);
        patientTab.add(pInfo_phoneNumberTextField, patientTabConstraints);

        // add address text field
        patientTabConstraints.gridy = 50;
        patientTabConstraints.anchor = GridBagConstraints.SOUTHEAST;
        patientTabConstraints.insets = new Insets(0, 0, 10, 40);
        patientTab.add(pInfo_addressTextField, patientTabConstraints);

        // add city text field
        patientTabConstraints.gridy = 60;
        patientTabConstraints.anchor = GridBagConstraints.EAST;
        patientTabConstraints.insets = new Insets(0, 0, 0, 40);
        patientTab.add(pInfo_cityTextField, patientTabConstraints);

        // add state combo box
        patientTabConstraints.gridy = 70;
        patientTab.add(pInfo_stateComboBox, patientTabConstraints);

        // add zip code text field
        patientTabConstraints.gridy = 80;
        patientTab.add(pInfo_zipCodeTextField, patientTabConstraints);

        // add update existing patient button
        patientTabConstraints.gridx = 20;
        patientTabConstraints.gridy = 70;
        patientTabConstraints.ipady = 10;
        patientTabConstraints.anchor = GridBagConstraints.CENTER;
        patientTab.add(pInfo_updateInfoButton, patientTabConstraints);

        // add submit new patient info button
        patientTabConstraints.gridy = 80;
        patientTab.add(pInfo_submitNewInfoButton, patientTabConstraints);

    } // end initializePatientInfoTab()s

    // TAB 3: Billing
    private void initializeBillingTab() {

        billingTab = new JPanel(new GridBagLayout());
        billingTabConstraints = new GridBagConstraints();

        billing_patientBillingLabel = new JLabel("Patient Billing");
        billing_lNameLabel = new JLabel("Patient Last Name:");
        billing_ssnLabel = new JLabel("SSN:");
        billing_billCodeLabel = new JLabel("Billing Code:");
        billing_policyLabel = new JLabel("Policy:");
        billing_amtDueLabel = new JLabel("Amount Due");
        billing_patientBillingLabel.setFont(new java.awt.Font(billing_patientBillingLabel.getFont().getFontName(), Font.PLAIN, 40));


        billing_lNameField = new JTextField(12);
        billing_ssnField = new JTextField(12);
        billing_amtDueField = new JTextField(12);
        billing_amtDueField.setEditable(false);

        String[] billingCodeOptions = {"1110", "2110", "3110"};
        String[] policyOptions = {"Yes", "No"};

        billing_codeCB = new JComboBox<String>(billingCodeOptions);
        billing_policyCB = new JComboBox<String>(policyOptions);

        billing_calculateButton = new JButton("Calculate Payment");

        // add contents to Billing tab

        // add patient billing label
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 0;
        billingTabConstraints.gridwidth = 40;
        billingTabConstraints.weighty = 0.2;
        billingTabConstraints.anchor = GridBagConstraints.NORTH;
        billingTabConstraints.insets = new Insets(20, 0, 0, 0);
        billingTab.add(billing_patientBillingLabel, billingTabConstraints);

        // add patient last name label
        billingTabConstraints.gridy = 10;
        billingTabConstraints.gridwidth = 10;
        billingTabConstraints.weightx = 0.2;
        billingTabConstraints.anchor = GridBagConstraints.WEST;
        billingTabConstraints.insets = new Insets(0, 20, 0, 0);
        billingTab.add(billing_lNameLabel, billingTabConstraints);

        // add ssn bill label
        billingTabConstraints.gridx = 30;
        billingTab.add(billing_ssnLabel, billingTabConstraints);

        // add last name field
        billingTabConstraints.gridx = 20;
        billingTabConstraints.weightx = 1;
        billingTab.add(billing_lNameField, billingTabConstraints);

        // add ssn field
        billingTabConstraints.gridx = 40;
        billingTab.add(billing_ssnField, billingTabConstraints);

        // add billing code label
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 20;
        billingTabConstraints.weightx = 0.2;
        billingTab.add(billing_billCodeLabel, billingTabConstraints);

        // add billing code combo box
        billingTabConstraints.gridx = 20;
        billingTab.add(billing_codeCB, billingTabConstraints);

        // add policy label
        billingTabConstraints.gridx = 30;
        billingTab.add(billing_policyLabel, billingTabConstraints);

        // add policy combo box
        billingTabConstraints.gridx = 40;
        billingTabConstraints.weightx = 1;
        billingTab.add(billing_policyCB, billingTabConstraints);

        // add amount due label
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 30;
        billingTabConstraints.weightx = 0.2;
        billingTab.add(billing_amtDueLabel, billingTabConstraints);

        // add amount due field
        billingTabConstraints.gridx = 20;
        billingTabConstraints.weightx = 1;
        billingTab.add(billing_amtDueField, billingTabConstraints);

        // ADD CALCULATE BUTTON
        billingTabConstraints.gridx = 30;
        billingTabConstraints.gridy = 30;
        billingTab.add(billing_calculateButton, billingTabConstraints);


    } // end initializeBillingTab()

    // TAB 4: Search
    private void initializeSearchTab() {

        searchTab = new JPanel(new GridBagLayout());
        searchTabConstraints = new GridBagConstraints();

        // search
        search_searchDirectionLabel = new JLabel("Search for Patient using Last Name & First Name");
        search_lNameLabel = new JLabel("Last Name:");
        search_fNameLabel = new JLabel("First Name:");
        search_searchDirectionLabel.setFont(new java.awt.Font(search_searchDirectionLabel.getFont().getFontName(), Font.PLAIN, 30));

        search_fNameField = new JTextField(12);
        search_lNameField = new JTextField(12);

        search_searchButton = new JButton("Search");

        // after search - choose a patient to display information for
        // keep invisible unless more than one patient shares First & Last name
        search_searchResultLabel = new JLabel("Result of Search");
        search_searchResultLabel.setVisible(false);

        search_choosePatientCB = new JComboBox<String>();
        search_choosePatientCB.setVisible(false);

        search_selectPatientFoundButton = new JButton("Select Patient");
        search_selectPatientFoundButton.setVisible(false);

        // add components to Search tab

        // add search directions label
        searchTabConstraints.gridx = 10;
        searchTabConstraints.gridy = 10;
        searchTabConstraints.weighty = 0.2;
        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.insets = new Insets(30, 0, 0, 0);
        searchTab.add(search_searchDirectionLabel, searchTabConstraints);

        // add last name search label
        searchTabConstraints.gridx = 10;
        searchTabConstraints.gridy = 20;
        searchTabConstraints.anchor = GridBagConstraints.CENTER;
        searchTabConstraints.insets = new Insets(0, 0, 0, 120);
        searchTab.add(search_lNameLabel, searchTabConstraints);

        // add last name search textfiel
        searchTabConstraints.insets = new Insets(0, 110, 0, 0);
        searchTab.add(search_lNameField, searchTabConstraints);

        // add first name search label
        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.gridy = 30;
        searchTabConstraints.insets = new Insets(0, 0, 0, 150);
        searchTab.add(search_fNameLabel, searchTabConstraints);

        // add first name search textfield
        searchTabConstraints.insets = new Insets(0, 110, 0, 0);
        searchTab.add(search_fNameField, searchTabConstraints);

        // add search button
        searchTabConstraints.gridy = 40;
        searchTabConstraints.ipadx = 30;
        searchTabConstraints.weighty = 0.5;
        searchTabConstraints.ipady = 10;
        searchTabConstraints.insets = new Insets(0, 0, 0, 0);
        searchTab.add(search_searchButton, searchTabConstraints);

        // add search result label
        searchTabConstraints.gridy = 50;
        searchTabConstraints.weighty = 0.2;
        searchTabConstraints.insets = new Insets(0, 0, 0, 60);
        searchTab.add(search_searchResultLabel, searchTabConstraints);

        // add choose patient combobox
        searchTabConstraints.weighty = 1;
        searchTabConstraints.insets = new Insets(50, 0, 0, 175);
        searchTab.add(search_choosePatientCB, searchTabConstraints);

        // add select patientfound button
        searchTabConstraints.ipady = 10;
        searchTabConstraints.insets = new Insets(50, 225, 0, 0);
        searchTab.add(search_selectPatientFoundButton, searchTabConstraints);

    } // end initializeBillingTab()

    // TAB 5: Calendar
    private void initializeCalendarTab() {

        calTab = new JPanel(new GridBagLayout());
        calendarConstraints = new GridBagConstraints();

        // calendar
        cal_calendarLabel = new JLabel("Search Appointments");
        cal_chooseDateLabel = new JLabel("Choose Date");
        cal_calendarDisplayLabel = new JLabel("Calendar");
        cal_chooseDateButton = new JButton("Look Up");
        cal_datePicker = createDatePicker(false);

        // set font
        cal_calendarLabel.setFont(new Font((cal_calendarLabel.getFont().getFontName()), Font.PLAIN, 40));

        // create table

        String[] columnNames = {"Time", "Date"};
        String[][] data = {
                {"", cal_datePicker.getText()},
                {"9:00am", ""}, {"10:00am", ""},
                {"11:00am", ""}, {"12:00pm", ""},
                {"1:00pm", ""}, {"2:00pm", ""},
                {"3:00pm", ""}, {"4:00pm", ""}};

        cal_table = new JTable(data, columnNames);
        cal_table.setRowHeight(20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        cal_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        cal_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        cal_scrollPane = new JScrollPane(cal_table);
        cal_scrollPane.setPreferredSize(new Dimension(220, 203));


        // add calendar label
        calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 10;
        //calendarConstraints.weightx = 1;
        calendarConstraints.weighty = 0.2;
        calendarConstraints.gridwidth = 20;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(30, 0, 0, 0);

        calTab.add(cal_calendarLabel, calendarConstraints);

        // add chooseDateLabel
        //calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 20;
        calendarConstraints.weightx = 1;
        calendarConstraints.weighty = 0.1;
        calendarConstraints.gridwidth = 10;
        calendarConstraints.anchor = GridBagConstraints.NORTHEAST;
        calendarConstraints.insets = new Insets(30, 0, 0, 100);

        calTab.add(cal_chooseDateLabel, calendarConstraints);

        // add datePicker
        calendarConstraints.anchor = GridBagConstraints.EAST;
        calendarConstraints.insets = new Insets(0, 0, 215, 50);

        calTab.add(cal_datePicker, calendarConstraints);

        // add choose date button
        calendarConstraints.weighty = 1;
        calendarConstraints.ipady = 5;
        //calendarConstraints.anchor = GridBagConstraints.WEST;
        calendarConstraints.insets = new Insets(0, 0, 115, 100);

        calTab.add(cal_chooseDateButton, calendarConstraints);

        // add calendar label
        calendarConstraints.gridx = 20;
        calendarConstraints.ipady = 0;
        calendarConstraints.weighty = 0.2;
        calendarConstraints.anchor = GridBagConstraints.NORTHWEST;
        calendarConstraints.insets = new Insets(30, 135, 0, 0);

        calTab.add(cal_calendarDisplayLabel, calendarConstraints);

        // add calendar table
        calendarConstraints.weighty = 1;
        calendarConstraints.anchor = GridBagConstraints.WEST;
        calendarConstraints.insets = new Insets(0, 50, 35, 0);

        calTab.add(cal_scrollPane, calendarConstraints);
    }

    /* END initialize() related functions*/

    /* START Action Listener related Functions */

    // Login Listeners

    private void login() {

        if (String.valueOf(login_usernameTextField.getText()).equals(""))
            JOptionPane.showMessageDialog
                    (null, "Must Enter A Username");

        else if (String.valueOf(login_passwordField.getPassword()).equals(""))
            JOptionPane.showMessageDialog
                    (null, "Must Enter A Password");

        else if (MainGUI.pimsSystem.staff_exists(login_usernameTextField.getText(), String.valueOf(login_passwordField.getPassword()))) {
            remove(loginPanel);
            add(tabbedPane);
            JOptionPane.showMessageDialog
                    (null, "Login Successful");
            repaint();
            revalidate();

            // reset username and password fields
            login_usernameTextField.setText("");
            login_passwordField.setText("");

        } else
            JOptionPane.showMessageDialog
                    (null, "Invalid Password or Username");
    } // end login()

    // Calendar Tab Listeners

    private void app_requestAppointment() {
        if (!app_patientNameTextField.getText().equals((""))) {
            patient = MainGUI.pimsSystem.patient_details
                    (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText()));
            if (patient != null) {
                String message = MainGUI.pimsSystem.add_date(datePicker.getText(), timePicker.getText(), patient);
                JOptionPane.showMessageDialog(null, message);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Must Search a Patient First (Search Tab)");
        }
    } // end app_requestAppointment

    private void app_cancelAppointment() {
        if (!app_patientNameTextField.getText().equals((""))) {
            if (!pInfo_lastNameTextField.getText().equals((""))) {
                patient = MainGUI.pimsSystem.patient_details
                        (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText()));
                if (!MainGUI.pimsSystem.patient_delete_date(patient))
                    JOptionPane.showMessageDialog
                            (null, "No Appointment Scheduled At This Time");
                else {
                    JOptionPane.showMessageDialog(null, "Appointment Deleted");
                    app_lookUpAppointmentTextField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Must Search a Patient First (Search Tab)");
            }
        }
    }// end app_cancelAppointment

    private void app_lookUpAppointment() {
        if (!app_patientNameTextField.getText().equals((""))) {
            patient = MainGUI.pimsSystem.patient_details
                    (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText()));
            String appointment = MainGUI.pimsSystem.lookUpAppointmentDate(patient);
            if (appointment.equals(""))
                JOptionPane.showMessageDialog
                        (null, "Requested patient has no Appointment Scheduled At This Time");
            else app_lookUpAppointmentTextField.setText(appointment);
        } else {
            JOptionPane.showMessageDialog(null, "Must Search a Patient First (Search Tab)");
        }
    }// end app_cancelAppointment

    // Patient Info Tab Listeners
    private void pInfo_updateExisting() {

        // values to test if there are no input errors
        boolean emptyFields = true, illegalFields = true;

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
        String errorMessage = "Must Enter";
        if (String.valueOf(pInfo_firstNameTextField.getText()).equals("")) {
            errorMessage += " First Name,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_lastNameTextField.getText()).equals("")) {
            errorMessage += " Last Name,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_ssnTextField.getText()).equals("")) {
            errorMessage += " Social Security #,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_dobTextField.getText()).equals("")) {
            errorMessage += " Date of Birth,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_phoneNumberTextField.getText()).equals("")) {
            errorMessage += " Phone Number,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_addressTextField).equals("")) {
            errorMessage += " Street,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_cityTextField).equals("")) {
            errorMessage += " City,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_zipCodeTextField).equals("")) {
            errorMessage += " Zip Code,";
            emptyFields = false;
        }

        // if there's no middle name, the text field
        // is set to "N/A"
        String middleName;
        if (String.valueOf(pInfo_middleNameTextField.getText()).equals(""))
            middleName = "N/A";
        else middleName = pInfo_middleNameTextField.getText();

        // throws error if last name has characters other than letters
        if (pInfo_lastNameTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_lastNameTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_lastNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Last Name Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if first name has characters other than letters
        if (pInfo_firstNameTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_firstNameTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_firstNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "First Name Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if middle name has characters other than letters
        if (pInfo_middleNameTextField.getText().length() > 0 &&
                !String.valueOf(pInfo_middleNameTextField.getText()).equals("N/A")) {
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
        if (pInfo_ssnTextField.getText().length() > 0 && pInfo_ssnTextField.getText().length() != 4) {
            JOptionPane.showMessageDialog
                    (null, "Social Security # Must Have 4 Characters");
            illegalFields = false;
        } else if (pInfo_ssnTextField.getText().length() == 4) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(pInfo_ssnTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Social Security # Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"
        if (pInfo_dobTextField.getText().length() > 0 && pInfo_dobTextField.getText().length() != 10) {
            JOptionPane.showMessageDialog
                    (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
            illegalFields = false;
        } else if (pInfo_dobTextField.getText().length() == 10) {
            if (!dobParser(pInfo_dobTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            }
        }

        // throws error if phone number isn't formatted correctly - "###-###-####"
        if (pInfo_phoneNumberTextField.getText().length() > 0 && pInfo_phoneNumberTextField.getText().length() != 12) {
            JOptionPane.showMessageDialog
                    (null, "Phone Number Must be formatted \"###-###-####\"");
            illegalFields = false;
        } else if (pInfo_phoneNumberTextField.getText().length() == 12) {
            if (!phoneNumberParser(pInfo_phoneNumberTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            }
        }

        // throws error if address has characters other than letters and numbers
        if (pInfo_addressTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_addressTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_addressTextField.getText().charAt(i)) &&
                        !Character.isDigit(pInfo_addressTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Address Must Have Only Numbers and Letters");
                    illegalFields = false;
                }
            }
        }

        // throws error if city has characters other than letters
        if (pInfo_cityTextField.getText().length() > 0) {
            for (int i = 1; i < pInfo_cityTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_cityTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "City Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if zip code has characters other than numbers, or has less/more than 4 digits
        if (pInfo_zipCodeTextField.getText().length() > 0 && pInfo_zipCodeTextField.getText().length() != 5) {
            JOptionPane.showMessageDialog
                    (null, "Zip Code Must Have 5 Characters");
            illegalFields = false;
        } else if (pInfo_zipCodeTextField.getText().length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(pInfo_zipCodeTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Zip Code Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }

        // checks if there are no input errors
        patient = MainGUI.pimsSystem.patient_details
                (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText()));
        if (emptyFields && illegalFields && patient != null) {
            JOptionPane.showMessageDialog
                    (null, "Information Updated");
            patient.l_name = pInfo_lastNameTextField.getText();
            patient.f_name = pInfo_firstNameTextField.getText();
            patient.m_name = middleName;
            patient.SSN = Integer.parseInt(pInfo_ssnTextField.getText());
            patient.dob = pInfo_dobTextField.getText();
            patient.p_number = pInfo_phoneNumberTextField.getText();
            patient.address = pInfo_addressTextField.getText();
            patient.city = pInfo_cityTextField.getText();
            patient.state = String.valueOf(pInfo_stateComboBox.getSelectedItem());
            patient.zip = Integer.parseInt(pInfo_zipCodeTextField.getText());
            patient.user_name = pInfo_userField.getText();
            patient.password = pInfo_pwField.getText();
        } else if (!String.valueOf(errorMessage).equals("Must Enter"))
            JOptionPane.showMessageDialog(null, errorMessage);

        else if (patient == null)
            JOptionPane.showMessageDialog(null, "Error");
    } // end pInfo_updateExisting

    private void pInfo_createNew() {

        // values to test if there are no input errors
        boolean emptyFields = true, illegalFields = true;

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
        String errorMessage = "Must Enter";
        if (String.valueOf(pInfo_firstNameTextField.getText()).equals("")) {
            errorMessage += " First Name,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_lastNameTextField.getText()).equals("")) {
            errorMessage += " Last Name,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_ssnTextField.getText()).equals("")) {
            errorMessage += " Social Security #,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_dobTextField.getText()).equals("")) {
            errorMessage += " Date of Birth,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_phoneNumberTextField.getText()).equals("")) {
            errorMessage += " Phone Number,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_addressTextField).equals("")) {
            errorMessage += " Street,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_cityTextField).equals("")) {
            errorMessage += " City,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_zipCodeTextField).equals("")) {
            errorMessage += " Zip Code,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_userField).equals("")) {
            errorMessage += " Username,";
            emptyFields = false;
        }
        if (String.valueOf(pInfo_pwField).equals("")) {
            errorMessage += " password,";
            emptyFields = false;
        }

        // if there's no middle name, the text field
        // is set to "N/A"
        String middleName;
        if (String.valueOf(pInfo_middleNameTextField.getText()).equals(""))
            middleName = "N/A";
        else middleName = pInfo_middleNameTextField.getText();

        // throws error if last name has characters other than letters
        if (pInfo_lastNameTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_lastNameTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_lastNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Last Name Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if first name has characters other than letters
        if (pInfo_firstNameTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_firstNameTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_firstNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "First Name Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if middle name has characters other than letters
        if (pInfo_middleNameTextField.getText().length() > 0 &&
                !String.valueOf(pInfo_middleNameTextField.getText()).equals("N/A")) {
            for (int i = 0; i < pInfo_middleNameTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_middleNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Middle Name Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if SSN has characters other than numbers, or has less/more than 4 digits
        if (pInfo_ssnTextField.getText().length() > 0 && pInfo_ssnTextField.getText().length() != 4) {
            JOptionPane.showMessageDialog
                    (null, "Social Security # Must Have 4 Characters");
            illegalFields = false;
        } else if (pInfo_ssnTextField.getText().length() == 4) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(pInfo_ssnTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Social Security # Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"
        if (pInfo_dobTextField.getText().length() > 0 && pInfo_dobTextField.getText().length() != 10) {
            JOptionPane.showMessageDialog
                    (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
            illegalFields = false;
        } else if (pInfo_dobTextField.getText().length() == 10) {
            if (!dobParser(pInfo_dobTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            }
        }

        // throws error if phone number isn't formatted correctly - "###-###-####"
        if (pInfo_phoneNumberTextField.getText().length() > 0 && pInfo_phoneNumberTextField.getText().length() != 12) {
            JOptionPane.showMessageDialog
                    (null, "Phone Number Must be formatted \"###-###-####\"");
            illegalFields = false;
        } else if (pInfo_phoneNumberTextField.getText().length() == 12) {
            if (!phoneNumberParser(pInfo_phoneNumberTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            }
        }

        // throws error if address has characters other than letters and numbers
        if (pInfo_addressTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_addressTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_addressTextField.getText().charAt(i)) &&
                        !Character.isDigit(pInfo_addressTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Address Must Have Only Numbers and Letters");
                    illegalFields = false;
                }
            }
        }

        // throws error if city has characters other than letters
        if (pInfo_cityTextField.getText().length() > 0) {
            for (int i = 1; i < pInfo_cityTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_cityTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "City Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if zip code has characters other than numbers, or has less/more than 4 digits
        if (pInfo_zipCodeTextField.getText().length() > 0 && pInfo_zipCodeTextField.getText().length() != 5) {
            JOptionPane.showMessageDialog
                    (null, "Zip Code Must Have 5 Characters");
            illegalFields = false;
        } else if (pInfo_zipCodeTextField.getText().length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(pInfo_zipCodeTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Zip Code Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }
        // checks if there are no input errors
        if (emptyFields && illegalFields) {

            if (MainGUI.pimsSystem.patient_exists(pInfo_firstNameTextField.getText(),
                    pInfo_lastNameTextField.getText(), pInfo_dobTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText())))
                JOptionPane.showMessageDialog
                        (null, "This Patient Is Already In The System");
            else {
                MainGUI.pimsSystem.add_patient(pInfo_firstNameTextField.getText(),
                        pInfo_lastNameTextField.getText(), pInfo_middleNameTextField.getText(),
                        "user", "password",
                        pInfo_dobTextField.getText(),
                        Integer.parseInt(pInfo_ssnTextField.getText()), Integer.parseInt(pInfo_zipCodeTextField.getText()),
                        pInfo_addressTextField.getText(), pInfo_cityTextField.getText(),
                        String.valueOf(pInfo_stateComboBox.getSelectedItem()), pInfo_phoneNumberTextField.getText(), true);

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
    } // end pInfo_createNew

    // Billing Tab Listeners

    private void billing_calculate() {

        // need to search patient before calculating amount due?

        JOptionPane.showMessageDialog(this, "Amount Due To Be Calculated. NOT IMPLEMENTED",
                "Calculate", JOptionPane.DEFAULT_OPTION);

    	/*
         * Holding space for back end code
    	 */

    }// end billing_calculate

    // Search Tab Listeners

    private void searchPatient() {

        String lName, fName;

        lName = search_lNameField.getText();
        fName = search_fNameField.getText();

        // find patients with the Last & First Name entered
        patientsFound = MainGUI.pimsSystem.search_patient(lName, fName);

        // more than one patient found
        if (patientsFound.size() > 1) {

            // create String ArrayList of patients: Last, First (DOB)
            ArrayList<String> foundList = new ArrayList<String>();
            String toAdd = "";

            // use patient data to make patient options to display
            for (patient p : patientsFound) {

                toAdd = p.l_name + ", " + p.f_name + " (" + p.dob + ")";
                foundList.add(toAdd);
            }

            int length;

            // clear combo box (in case this is a second search)
            while ((length = search_choosePatientCB.getItemCount()) > 0) {
                search_choosePatientCB.removeItemAt(length - 1);
            }

            // add Patient Options to combo box
            for (int i = 0; i < foundList.size(); i++) {
                search_choosePatientCB.addItem(foundList.get(i));
            }

            // display results
            search_searchResultLabel.setVisible(true);
            search_selectPatientFoundButton.setVisible(true);
            search_choosePatientCB.setVisible(true);

            // display whether patients found or not
            JOptionPane.showMessageDialog(this, "Found Results for Last Name, First Name: " + lName + ", " + fName
                            + ".\nPlease select a patient and then press \"Select\" to see information.",
                    "Search Successful", JOptionPane.DEFAULT_OPTION);

        }

        // one patient found
        else if (patientsFound.size() == 1) {

            // do not display drop down list
            search_searchResultLabel.setVisible(false);
            search_selectPatientFoundButton.setVisible(false);
            search_choosePatientCB.setVisible(false);

            JOptionPane.showMessageDialog(this, "Found one match for Last Name, First Name: " + lName + ", " + fName,
                    "Search Successful", JOptionPane.DEFAULT_OPTION);

            // display patient data
            patient patientFound = patientsFound.get(0);
            search_fillPatientFoundData(patientFound);
        }

        // no patient found
        else {

            // do not display drop down list
            search_searchResultLabel.setVisible(false);
            search_selectPatientFoundButton.setVisible(false);
            search_choosePatientCB.setVisible(false);

            JOptionPane.showMessageDialog(this, "No Results found for Last Name, First Name:" + lName + ", " + fName,
                    "Search Failed", JOptionPane.ERROR_MESSAGE);
        }
    } // end searchPatient

    private void search_selectPatientToDisplay() {

        // grab selected patient
        patient patientFound = patientsFound.get((search_choosePatientCB.getSelectedIndex()));
        search_fillPatientFoundData(patientFound);

    } // end search_SelectPatientToDisplay

    /*
     * fillPatientFoundData()
     * - called from search_searchPatient() & search_selectPatientToDisplay
     * - populates all tabs with patient info
     */
    private void search_fillPatientFoundData(patient toDisplay) {

        if (toDisplay != null) {
            JOptionPane.showMessageDialog(this, "Filling in Information for Patient Found",
                    "Filling in Info", JOptionPane.DEFAULT_OPTION);

            // Appointment Tab
            app_lookUpAppointmentTextField.setText(MainGUI.pimsSystem.lookUpAppointmentDate(toDisplay));
            app_patientNameTextField.setText(toDisplay.l_name + ", " + toDisplay.f_name);

            // Patient Info Tab
            pInfo_lastNameTextField.setText(toDisplay.l_name);
            pInfo_firstNameTextField.setText(toDisplay.f_name);
            pInfo_middleNameTextField.setText(toDisplay.m_name);
            pInfo_ssnTextField.setText(Integer.toString(toDisplay.SSN));
            pInfo_dobTextField.setText(toDisplay.dob);
            pInfo_phoneNumberTextField.setText(toDisplay.p_number);
            pInfo_addressTextField.setText(toDisplay.address);
            pInfo_cityTextField.setText(toDisplay.city);
            pInfo_zipCodeTextField.setText(Integer.toString(toDisplay.zip));
            pInfo_stateComboBox.setSelectedItem(toDisplay.state);
            pInfo_userField.setText(toDisplay.user_name);
            pInfo_pwField.setText(toDisplay.p_number);

            // Billing Tab
            billing_lNameField.setText(toDisplay.l_name);
            billing_ssnField.setText(Integer.toString(toDisplay.SSN));

    		/* TO DO
             *
    		 * -ask how to add policy and patient history to patient class
    		 */

            repaint();
            revalidate();

        } else
            JOptionPane.showMessageDialog(this, "No Patient to Select. Make a search first",
                    "Filling in Info", JOptionPane.DEFAULT_OPTION);

    }// end fillPatientData()

    /* END Action Listener related functions*/

    /* START Helper functions*/

    // checks if SSN 4 digits
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
    }  // end constructor

    // search date method
    private void search_date() {
        System.out.println("foo");
        String date = cal_datePicker.getText();
        HashMap<String, patient[]> tempMap =
                MainGUI.pimsSystem.getStaff_lookupAppointmentsMap();
        patient patient;
        String temp = "";
        cal_table.setValueAt(date, 0, 1);
        if (!tempMap.containsKey(date)) {
            for (int i = 1; i < 9; i++) {
                cal_table.setValueAt("No Appointment", i, 1);
            }
        } else {
            patient[] tempArray = tempMap.get(date);
            if (tempArray[0] != null) {
                patient = tempArray[0];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 1, 1);
            } else cal_table.setValueAt("No Appointment", 1, 1);
            if (tempArray[1] != null) {
                patient = tempArray[1];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 2, 1);
            } else cal_table.setValueAt("No Appointment", 2, 1);
            if (tempArray[2] != null) {
                patient = tempArray[2];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 3, 1);
            } else cal_table.setValueAt("No Appointment", 3, 1);
            if (tempArray[3] != null) {
                patient = tempArray[3];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 4, 1);
            } else cal_table.setValueAt("No Appointment", 4, 1);
            if (tempArray[4] != null) {
                patient = tempArray[4];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 5, 1);
            } else cal_table.setValueAt("No Appointment", 5, 1);
            if (tempArray[5] != null) {
                patient = tempArray[5];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 6, 1);
            } else cal_table.setValueAt("No Appointment", 6, 1);
            if (tempArray[6] != null) {
                patient = tempArray[6];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 7, 1);
            } else cal_table.setValueAt("No Appointment", 7, 1);
            if (tempArray[7] != null) {
                patient = tempArray[7];
                temp = patient.f_name + " " + patient.l_name;
                cal_table.setValueAt(temp, 8, 1);
            } else cal_table.setValueAt("No Appointment", 8, 1);
        }
    }

    // method to parse the DOB and make
    // sure it's in the "MM/DD/YYYY" format
    private boolean dobParser(String string) {
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
    } // end dobParser


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

    } // end phoneNumberParser

    /* END helper functions */

	/* START Calendar Tab: DatePicker & TimePicker related methods */

    // method to create a date picker
    private DatePicker createDatePicker(boolean veto) {
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        datePickerSettings.setAllowEmptyDates(false);
        datePickerSettings.setAllowKeyboardEditing(false);
        DatePicker datePicker = new DatePicker(datePickerSettings);
        if (veto) {
            // If today is Saturday or Sunday, this sets the default
            // to the following Monday
            if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
                datePicker.setDate(LocalDate.now().plusDays(3));
            } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
                datePicker.setDate(LocalDate.now().plusDays(2));
            } else datePicker.setDate(LocalDate.now().plusDays(1));
            // Veto Policy to disallow weekends
            datePickerSettings.setVetoPolicy(new VetoDates());
        } else datePicker.setDate(LocalDate.now());
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

    /* END of TimePicker & DatePicker related methods */

    // main for just employeeGUI
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