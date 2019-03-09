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
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.text.DecimalFormat;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;


@SuppressWarnings("serial")
public class EmployeeGUI extends JPanel {

    // Patient Information - store info when searched
    private patient patient; // for search

    // EmployeeGUI title
    private String employeeGUItitle;

    // Login panel
    private JPanel loginPanel;
    private GridBagConstraints loginConstraints;
    private JLabel login_directionLabel, login_usernameLabel, login_passwordLabel;
    private JTextField login_usernameTextField;
    private JPasswordField login_passwordField;
    private JButton loginButton;

    /* Employee Window (Tabbed Pane) */
    // 5 Tabs: Appointments, Patient Information, Billing, Search, Calendar
    private JTabbedPane tabbedPane;

    // TAB 1: Appointments
    private JPanel appTab;
    private GridBagConstraints appointmentConstraints;
    private JLabel app_chooseDateAndTimeLabel, cal_currentAppointmentLabel,
            cal_patientSSNLabel, app_patientNameLabel, app_lookUpAppointmentLabel,
            cal_currentPatientLabel;
    private JTextField cal_currentAppointmentTextField, cal_SSNTextField,
            app_patientNameTextField, app_lookUpAppointmentTextField,
            cal_currentPatientTextField;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private JButton app_requestAppointmentButton, app_cancelAppointmentButton, app_lookUpAppointmentButton;


    // TAB 2: Patient Information
    private JPanel patientTab;
    private GridBagConstraints patientTabConstraints;
    private JLabel pInfo_lastNameLabel, pInfo_firstNameLabel, pInfo_middleNameLabel,
            pInfo_ssnLabel, pInfo_dobLabel, pInfo_phoneNumberLabel,
            pInfo_streetLabel, pInfo_cityLabel, pInfo_stateLabel, pInfo_zipCodeLabel,
            pInfo_userLabel, pInfo_pwLabel, pInfo_policyLabel;
    private JTextField pInfo_lastNameTextField, pInfo_firstNameTextField, pInfo_middleNameTextField,
            pInfo_ssnTextField, pInfo_dobTextField, pInfo_phoneNumberTextField,
            pInfo_addressTextField, pInfo_cityTextField, pInfo_zipCodeTextField,
            pInfo_userField, pInfo_pwField;
    private JComboBox<String> pInfo_stateComboBox, pInfo_policyComboBox;
    private String[] pInfo_states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
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
    private JButton pInfo_updateInfoButton, pInfo_submitNewInfoButton;

    // TAB 3: Billing
    private JPanel billingTab;
    private GridBagConstraints billingTabConstraints;
    private JLabel billing_patientBillingLabel, billing_fullNameLabel, billing_ssnLabel,
            billing_billCodeLabel, billing_policyLabel, billing_amtDueLabel,
            billing_historyLabel;
    private JTextField billing_fullNameField, billing_ssnField, billing_policyField, billing_amtDueField;
    private JComboBox<String> billing_codeCB;
    private JScrollPane billing_historyScrollPane;
    private JTextArea billing_patientHistoryTextArea;
    private JButton billing_calculateButton;
    
    // JDialog "pop up" payment tab
    private JDialog paymentDialog; 
    private JPanel paymentPanel;
    private GridBagConstraints paymentPanelConstraints;
    private JLabel payment_instructionLabel, payment_nameLabel, payment_cardNumberLabel, payment_cardCodeLabel,
    		payment_cardExpMonthLabel, payment_cardExpYearLabel;
    private JTextField payment_nameField, payment_cardNumberField, payment_cardCodeField, payment_amtDueField;
	private JComboBox<String> payment_monthCB, payment_yearCB;
    private JButton payment_payButton;

    // TAB 4: Search
    private JPanel searchTab;
    private GridBagConstraints searchTabConstraints;
    private JLabel search_lNameLabel, search_fNameLabel,
            search_searchDirectionLabel, search_searchResultLabel;
    private JTextField search_lNameField, search_fNameField;
    private JButton search_searchButton, search_selectPatientFoundButton;
    private JComboBox<String> search_choosePatientCB;
    private ArrayList<patient> patientsFound;

    // TAB 5: Calendar
    private JPanel calTab;
    private GridBagConstraints calendarConstraints;
    private JLabel cal_calendarLabel, cal_chooseDateLabel, cal_calendarDisplayLabel;
    private JButton cal_chooseDateButton;
    private DatePicker cal_datePicker;
    private JTable cal_table;
    private JScrollPane cal_scrollPane;

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
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // setBackground(MainGUI.backgroundColor);

        initializeLogInPanel();

        // ** initialize JTabbedPane **
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(MainGUI.backgroundColor);
        tabbedPane.setForeground(MainGUI.fontColor);

        initializeAppointmentsTab();
        initializePatientInfoTab();
        initializeBillingTab();
        initializeSearchTab();
        initializeCalendarTab();
        
        // NEW payment JDialog
        initializePaymentDialog();

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

        // Appointments Tab listeners
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
        
        // Payment Dialog Listeners
        payment_payButton.addActionListener(e -> payment_pay());

    }// end initialize()

    /* START initialize() related Functions (called within constructor) */

    // LogIn Panel
    private void initializeLogInPanel() {

        // set up Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        loginConstraints = new GridBagConstraints();
        loginPanel.setBackground(MainGUI.backgroundColor);

        login_directionLabel = new JLabel("Employee Login");
        login_usernameLabel = new JLabel("Username:");
        login_passwordLabel = new JLabel("Password:");
        login_directionLabel.setFont(new Font(login_directionLabel.getFont().getFontName(), Font.PLAIN, 40));

        login_directionLabel.setForeground(MainGUI.fontColor);
        login_usernameLabel.setForeground(MainGUI.fontColor);
        login_passwordLabel.setForeground(MainGUI.fontColor);

        login_usernameTextField = new JTextField(12);
        login_passwordField = new JPasswordField(12);

        loginButton = new JButton("Login");
        loginButton.setForeground(MainGUI.fontColor);

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
        loginConstraints.insets = new Insets(40, 40, 0, 0);
        loginPanel.add(login_usernameLabel, loginConstraints);

        // add password label
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(10, 40, 0, 0);
        loginPanel.add(login_passwordLabel, loginConstraints);

        // add username textfield
        loginConstraints.gridx = 20;
        loginConstraints.gridy = 20;
        loginConstraints.gridwidth = 20;
        loginConstraints.insets = new Insets(40, 0, 0, 35);
        loginPanel.add(login_usernameTextField, loginConstraints);

        // add password textfield
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(12, 0, 0, 35);
        loginPanel.add(login_passwordField, loginConstraints);

        // add login button
        loginConstraints.gridx = 10;
        loginConstraints.gridy = 40;
        loginConstraints.weighty = 1;
        loginConstraints.ipadx = 15;
        loginConstraints.ipady = 10;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(30, 0, 0, 40);
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

        app_chooseDateAndTimeLabel.setForeground(MainGUI.fontColor);
        app_patientNameLabel.setForeground(MainGUI.fontColor);
        app_lookUpAppointmentLabel.setForeground(MainGUI.fontColor);

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

        app_requestAppointmentButton.setForeground(MainGUI.fontColor);
        app_cancelAppointmentButton.setForeground(MainGUI.fontColor);
        app_lookUpAppointmentButton.setForeground(MainGUI.fontColor);

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
        pInfo_policyLabel = new JLabel("Policy:");

        pInfo_firstNameLabel.setForeground(MainGUI.fontColor);
        pInfo_middleNameLabel.setForeground(MainGUI.fontColor);
        pInfo_lastNameLabel.setForeground(MainGUI.fontColor);
        pInfo_ssnLabel.setForeground(MainGUI.fontColor);
        pInfo_dobLabel.setForeground(MainGUI.fontColor);
        pInfo_phoneNumberLabel.setForeground(MainGUI.fontColor);
        pInfo_streetLabel.setForeground(MainGUI.fontColor);
        pInfo_cityLabel.setForeground(MainGUI.fontColor);
        pInfo_stateLabel.setForeground(MainGUI.fontColor);
        pInfo_zipCodeLabel.setForeground(MainGUI.fontColor);
        pInfo_userLabel.setForeground(MainGUI.fontColor);
        pInfo_pwLabel.setForeground(MainGUI.fontColor);
        pInfo_policyLabel.setForeground(MainGUI.fontColor);

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

        String[] policyOptions = {"Yes", "No"};
        pInfo_policyComboBox = new JComboBox<>(policyOptions);

        // create buttons
        pInfo_updateInfoButton = new JButton("Update Existing Patient");
        pInfo_submitNewInfoButton = new JButton("Create New Patient File");

        pInfo_updateInfoButton.setForeground(MainGUI.fontColor);
        pInfo_submitNewInfoButton.setForeground(MainGUI.fontColor);

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

        // add policy label
        patientTabConstraints.gridx = 20;
        patientTabConstraints.gridy = 30;
        patientTab.add(pInfo_policyLabel, patientTabConstraints);

        // add DOB label
        patientTabConstraints.gridx = 10;
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

        // add policy combo box
        patientTabConstraints.gridx = 20;
        patientTabConstraints.gridy = 30;
        patientTabConstraints.anchor = GridBagConstraints.CENTER;
        patientTab.add(pInfo_policyComboBox, patientTabConstraints);

        // add DOB textfield
        patientTabConstraints.gridx = 10;
        patientTabConstraints.anchor = GridBagConstraints.EAST;
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
        billing_fullNameLabel = new JLabel("Patient Full Name:");
        billing_billCodeLabel = new JLabel("Billing Code:");
        billing_policyLabel = new JLabel("Policy:");
        billing_amtDueLabel = new JLabel("Amount Due:");
        billing_patientBillingLabel.setFont(new java.awt.Font
                (billing_patientBillingLabel.getFont().getFontName(), Font.PLAIN, 40));
        billing_historyLabel = new JLabel("Payment & Appointment History:");

        billing_fullNameField = new JTextField(12);
        billing_fullNameField.setEditable(false);
        billing_fullNameField.setBackground(Color.white);
        billing_ssnField = new JTextField(12);
        billing_amtDueField = new JTextField(12);
        billing_amtDueField.setEditable(false);
        billing_amtDueField.setBackground(Color.white);
        billing_policyField = new JTextField(12);
        billing_policyField.setEditable(false);
        billing_policyField.setBackground(Color.white);

        String[] billingCodeOptions = {"CHECKUP", "PHYSICAL", "DIAGNOSTIC"};
        //String[] policyOptions = {"No", "Yes"};

        billing_codeCB = new JComboBox<String>(billingCodeOptions);

        // add text area
        billing_patientHistoryTextArea = new JTextArea();
        billing_patientHistoryTextArea.setEditable(false);
        billing_patientHistoryTextArea.setLineWrap(true);

        billing_historyScrollPane = new JScrollPane(billing_patientHistoryTextArea);
        billing_historyScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        billing_calculateButton = new JButton("Calculate Payment");

        billing_patientBillingLabel.setForeground(MainGUI.fontColor);
        billing_fullNameLabel.setForeground(MainGUI.fontColor);
        billing_billCodeLabel.setForeground(MainGUI.fontColor);
        billing_policyLabel.setForeground(MainGUI.fontColor);
        billing_amtDueLabel.setForeground(MainGUI.fontColor);
        billing_historyLabel.setForeground(MainGUI.fontColor);

        billing_calculateButton.setForeground(MainGUI.fontColor);

        // add contents to Billing tab

        // add patient billing label
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 10;
        billingTabConstraints.gridwidth = 40;
        billingTabConstraints.weighty = 0.2;
        billingTabConstraints.anchor = GridBagConstraints.NORTH;
        billingTabConstraints.insets = new Insets(20, 0, 0, 0);
        billingTab.add(billing_patientBillingLabel, billingTabConstraints);

        // add patient name label
        billingTabConstraints.gridy = 20;
        billingTabConstraints.weightx = 0.2;
        billingTabConstraints.gridwidth = 10;
        billingTabConstraints.anchor = GridBagConstraints.EAST;
        billingTabConstraints.insets = new Insets(0, 0, 0, 20);
        billingTab.add(billing_fullNameLabel, billingTabConstraints);

        // add billing code label
        billingTabConstraints.gridy = 30;
        billingTab.add(billing_billCodeLabel, billingTabConstraints);

        // add policy label
        billingTabConstraints.gridy = 40;
        billingTab.add(billing_policyLabel, billingTabConstraints);

        // add name field
        billingTabConstraints.gridx = 20;
        billingTabConstraints.gridy = 20;
        billingTabConstraints.weightx = 0.4;
        billingTabConstraints.anchor = GridBagConstraints.WEST;
        billingTabConstraints.insets = new Insets(0, 30, 0, 0);
        billingTab.add(billing_fullNameField, billingTabConstraints);

        // add billing code combo box
        billingTabConstraints.gridy = 30;
        billingTab.add(billing_codeCB, billingTabConstraints);

        // add policy textfield
        billingTabConstraints.gridy = 40;
        billingTab.add(billing_policyField, billingTabConstraints);

        // add amount due label
        billingTabConstraints.gridx = 30;
        billingTabConstraints.gridy = 30;
        billingTabConstraints.weightx = 0.2;
        billingTabConstraints.anchor = GridBagConstraints.WEST;
        billingTab.add(billing_amtDueLabel, billingTabConstraints);
        billingTabConstraints.insets = new Insets(0, 0, 0, 0);

        // add amount due field
        billingTabConstraints.gridx = 40;
        billingTabConstraints.weightx = 0.5;
        billingTabConstraints.insets = new Insets(0, 0, 0, 80);
        billingTab.add(billing_amtDueField, billingTabConstraints);

        // add calculate button
        billingTabConstraints.gridx = 30;
        billingTabConstraints.gridy = 40;
        billingTabConstraints.weightx = 0.2;
        billingTabConstraints.ipady = 10;
        billingTabConstraints.gridwidth = 20;
        billingTabConstraints.anchor = GridBagConstraints.WEST;
        billingTabConstraints.insets = new Insets(0, 70, 20, 0);
        billingTab.add(billing_calculateButton, billingTabConstraints);

        // add patient history label
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 50;
        billingTabConstraints.ipady = 0;
        billingTabConstraints.gridwidth = 10;
        billingTabConstraints.insets = new Insets(0, 30, 0, 0);
        billingTab.add(billing_historyLabel, billingTabConstraints);

        // add patient history text area (wrapped in scroll pane)
        billingTabConstraints.gridx = 10;
        billingTabConstraints.gridy = 60;
        billingTabConstraints.gridwidth = 40;
        billingTabConstraints.fill = GridBagConstraints.BOTH;
        billingTabConstraints.insets = new Insets(0, 0, 0, 0);
        billingTab.add(billing_historyScrollPane, billingTabConstraints);


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
        search_searchDirectionLabel.setForeground(MainGUI.fontColor);
        search_lNameLabel.setForeground(MainGUI.fontColor);
        search_fNameLabel.setForeground(MainGUI.fontColor);


        search_fNameField = new JTextField(12);
        search_lNameField = new JTextField(12);

        search_searchButton = new JButton("Search");
        search_searchButton.setForeground(MainGUI.fontColor);

        // after search - choose a patient to display information for
        // keep invisible unless more than one patient shares First & Last name
        search_searchResultLabel = new JLabel("Result of Search");
        search_searchResultLabel.setVisible(false);
        search_searchResultLabel.setForeground(MainGUI.fontColor);

        search_choosePatientCB = new JComboBox<String>();
        search_choosePatientCB.setVisible(false);

        search_selectPatientFoundButton = new JButton("Select Patient");
        search_selectPatientFoundButton.setVisible(false);
        search_selectPatientFoundButton.setForeground(MainGUI.fontColor);

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

        cal_calendarLabel.setForeground(MainGUI.fontColor);
        cal_chooseDateLabel.setForeground(MainGUI.fontColor);
        cal_calendarDisplayLabel.setForeground(MainGUI.fontColor);
        cal_chooseDateLabel.setForeground(MainGUI.fontColor);

        // create table

        String[] columnNames = {"Time", "Patient"};
        String[][] data = {
                {cal_datePicker.getText(), ""},
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
        calendarConstraints.weighty = 0.2;
        calendarConstraints.gridwidth = 20;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(30, 0, 0, 0);

        calTab.add(cal_calendarLabel, calendarConstraints);

        // add chooseDateLabel
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

    private void initializePaymentDialog(){
    	
    	paymentDialog = new JDialog(); 
    	paymentDialog.setTitle("PIMS Payment Form");
    	
    	
        paymentPanel = new JPanel(new GridBagLayout());
        paymentPanelConstraints = new GridBagConstraints();
        
        payment_instructionLabel = new JLabel("Enter Payment Information");
        payment_nameLabel = new JLabel("Cardholder Name:");
        payment_cardNumberLabel = new JLabel("Credit Card Number:"); 
        payment_cardCodeLabel = new JLabel("Security Code");
        payment_cardExpMonthLabel  = new JLabel("Expiration Month:");
        payment_cardExpYearLabel = new JLabel("Expiration Year:");
        
        payment_instructionLabel.setFont(new java.awt.Font(payment_instructionLabel.getFont().getFontName(), Font.PLAIN, 30));
        payment_instructionLabel.setForeground(MainGUI.fontColor);
        payment_nameLabel.setForeground(MainGUI.fontColor);
        payment_cardNumberLabel.setForeground(MainGUI.fontColor);
        payment_cardCodeLabel.setForeground(MainGUI.fontColor);
        payment_cardExpMonthLabel.setForeground(MainGUI.fontColor);
        payment_cardExpYearLabel.setForeground(MainGUI.fontColor);
        
        payment_nameField = new JTextField(12);
        payment_cardNumberField = new JTextField(12);
        payment_cardCodeField = new JTextField(12);
        payment_amtDueField = new JTextField(12);
        payment_amtDueField.setEditable(false);
        payment_amtDueField.setText(billing_amtDueField.getText());
        
        
        String[] monthOptions = {"01", "02", "03", "04", "05", "06",
        		"07", "08", "09", "10", "11", "12"};
        payment_monthCB = new JComboBox<String>(monthOptions);
        
        String[] yearOptions = {"2019", "2020", "2021", "2022", "2023", "2024", "2025"};
        
        payment_yearCB = new JComboBox<String>(yearOptions);
        payment_payButton = new JButton("Pay");
        
        payment_payButton.setForeground(MainGUI.fontColor);
        
        
        //add components to payment panel
        
        // instruction label
        paymentPanelConstraints.gridwidth = 40;
        paymentPanelConstraints.weighty = 0.2;
        paymentPanelConstraints.anchor = GridBagConstraints.NORTH;
        paymentPanelConstraints.insets = new Insets(20, 0, 0, 0);
        paymentPanel.add(payment_instructionLabel, paymentPanelConstraints);
        
        // card name label
        paymentPanelConstraints.gridy = 20;
        paymentPanelConstraints.weightx = 0.2;
        paymentPanelConstraints.gridwidth = 10;
        paymentPanelConstraints.anchor = GridBagConstraints.EAST;
        paymentPanelConstraints.insets = new Insets(0, 0, 0, 20);
        paymentPanel.add(payment_nameLabel, paymentPanelConstraints);
        
        // card number label
        paymentPanelConstraints.gridy = 30;
        paymentPanel.add(payment_cardNumberLabel, paymentPanelConstraints);
        
        // expiration month label
        paymentPanelConstraints.gridy = 40;
        paymentPanel.add(payment_cardExpMonthLabel, paymentPanelConstraints);
        
        // security code label
        paymentPanelConstraints.gridx = 30;
        paymentPanelConstraints.gridy = 20;
        paymentPanel.add(payment_cardCodeLabel, paymentPanelConstraints);
        
        // expiration year label
        paymentPanelConstraints.gridy = 30;
        paymentPanel.add(payment_cardExpYearLabel, paymentPanelConstraints);
        
        // card name field
        paymentPanelConstraints.gridx = 20;
        paymentPanelConstraints.gridy = 20;
        //paymentPanelConstraints.insets = new Insets(10, 10, 10, 10);
        paymentPanel.add(payment_nameField, paymentPanelConstraints);
        
        // card number field
        paymentPanelConstraints.gridy = 30;
        paymentPanel.add(payment_cardNumberField, paymentPanelConstraints);
        
        // expiration month combo box
        paymentPanelConstraints.gridy = 40;
        paymentPanelConstraints.anchor = GridBagConstraints.WEST;
        paymentPanel.add(payment_monthCB, paymentPanelConstraints);
        
        // security code field
        paymentPanelConstraints.gridx = 40;
        paymentPanelConstraints.gridy = 20;
        paymentPanelConstraints.anchor = GridBagConstraints.EAST;
        paymentPanel.add(payment_cardCodeField, paymentPanelConstraints);
        
        // expiration year combo box
        paymentPanelConstraints.gridy = 30;
        paymentPanelConstraints.anchor = GridBagConstraints.WEST;
        paymentPanel.add(payment_yearCB, paymentPanelConstraints);
        
        // payment button
        paymentPanelConstraints.gridx = 30;
        paymentPanelConstraints.gridy = 50;
        paymentPanel.add(payment_payButton, paymentPanelConstraints);
        
        
        // add panel to dialog
        paymentDialog.add(paymentPanel);
        paymentDialog.setSize(700, 400);
        paymentDialog.setLocationRelativeTo(null);
        //paymentDialog.setVisible(true);
        
        
    }// end initializePaymentDialog
    
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

    // Appointments Tab Listeners

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
            patient.setL_name(pInfo_lastNameTextField.getText());
            patient.setF_name(pInfo_firstNameTextField.getText());
            patient.setM_name(middleName);
            patient.setSSN(Integer.parseInt(pInfo_ssnTextField.getText()));
            patient.setDob(pInfo_dobTextField.getText());
            patient.setP_number(pInfo_phoneNumberTextField.getText());
            patient.setAddress(pInfo_addressTextField.getText());
            patient.setCity(pInfo_cityTextField.getText());
            patient.setState(String.valueOf(pInfo_stateComboBox.getSelectedItem()));
            patient.setZip(Integer.parseInt(pInfo_zipCodeTextField.getText()));
            patient.setUser_name(pInfo_userField.getText());
            patient.setPassword(pInfo_pwField.getText());

            String policy = (String) pInfo_policyComboBox.getSelectedItem();

            if (policy.equals("Yes"))
                patient.setPolicy(true);
            else
                patient.setPolicy(true);


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

                boolean patientPolicy;
                String policy = (String) pInfo_policyComboBox.getSelectedItem();

                if (policy.equals("Yes"))
                    patientPolicy = true;
                else
                    patientPolicy = true;

                //Michael's code change
                MainGUI.pimsSystem.add_patient(pInfo_firstNameTextField.getText(),
                        pInfo_lastNameTextField.getText(), pInfo_middleNameTextField.getText(),
                        pInfo_userField.getText(), pInfo_pwField.getText(),
                        pInfo_dobTextField.getText(),
                        Integer.parseInt(pInfo_ssnTextField.getText()), Integer.parseInt(pInfo_zipCodeTextField.getText()),
                        pInfo_addressTextField.getText(), pInfo_cityTextField.getText(),
                        String.valueOf(pInfo_stateComboBox.getSelectedItem()), pInfo_phoneNumberTextField.getText(), patientPolicy);

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
        // need to search patient before calculating amount due
        if (billing_fullNameField.equals("")){
            JOptionPane.showMessageDialog(this, "Must search for a patient first!\nGo to the Search Tab.",
                    "Need to Search Patient", JOptionPane.ERROR_MESSAGE);
        }
        if (MainGUI.pimsSystem.lookUpAppointmentDate(patient).equals("")){
            JOptionPane.showMessageDialog(this, "No Appointment to pay for!\nGo to Appointment Tab to make one.",
                    "Nothing to pay for", JOptionPane.ERROR_MESSAGE);
        }
        // patient has been searched - get info from patient info panel
        else {
            patient =  MainGUI.pimsSystem.patient_details
                    (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_ssnTextField.getText()));
            // patient has a policy, amount due is copay: $50
            // no policy, amount due is cost amount
            double toPay = MainGUI.pimsSystem.charge(patient, billing_codeCB.getSelectedItem().toString());
            billing_amtDueField.setText("$" + doubleToDecimalString(toPay));


            JOptionPane.showMessageDialog(this, "Amount Due Calculated. Click \"Ok\" to go to Payment Form",
                    "Calculate", JOptionPane.DEFAULT_OPTION);

            // returns true if appointment payed & recorded
            if(!MainGUI.pimsSystem.recordApptPayment(patient, billing_amtDueField.getText()))
                JOptionPane.showMessageDialog
                        (null, "This Patient Doesn't Have An Appointment Scheduled");
            else {
            	
            	paymentDialog.setVisible(true);
            }
        }

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
                toAdd = p.getL_name() + ", " + p.getF_name() + " (" + p.getDob() + ")";
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

            // true = yes, false = no policy
            String policy;
            if (toDisplay.isPolicy())
                policy = "Yes";
            else
                policy = "No";

            // Appointment Tab
            app_lookUpAppointmentTextField.setText(MainGUI.pimsSystem.lookUpAppointmentDate(toDisplay));
            app_patientNameTextField.setText(toDisplay.getL_name() + ", " + toDisplay.getL_name());

            // Patient Info Tab
            pInfo_lastNameTextField.setText(toDisplay.getL_name());
            pInfo_firstNameTextField.setText(toDisplay.getF_name());
            pInfo_middleNameTextField.setText(toDisplay.getM_name());
            pInfo_ssnTextField.setText(Integer.toString(toDisplay.getSSN()));
            pInfo_dobTextField.setText(toDisplay.getDob());
            pInfo_phoneNumberTextField.setText(toDisplay.getP_number());
            pInfo_addressTextField.setText(toDisplay.getAddress());
            pInfo_cityTextField.setText(toDisplay.getCity());
            pInfo_zipCodeTextField.setText(Integer.toString(toDisplay.getZip()));
            pInfo_stateComboBox.setSelectedItem(toDisplay.getState());
            pInfo_userField.setText(toDisplay.getUser_name());
            pInfo_pwField.setText(toDisplay.getPassword());
            pInfo_policyComboBox.setSelectedItem(policy);

            // Billing Tab
            billing_fullNameField.setText(toDisplay.getL_name() + ", " + toDisplay.getF_name());
            billing_ssnField.setText(Integer.toString(toDisplay.getSSN()));
            billing_policyField.setText(policy);
            billing_policyField.setEditable(false);
            printHistory(toDisplay);

            repaint();
            revalidate();

        } else
            JOptionPane.showMessageDialog(this, "No Patient to Select. Make a search first",
                    "Filling in Info", JOptionPane.DEFAULT_OPTION);

    }// end fillPatientData()
    
    // Payment dialog Listeners
    
    private void payment_pay(){
    	
    	// only pay if all fields are filled out
    	
    	// update patient history
    	clearHistory();
        printHistory(patient);
    }

    /* END Action Listener related functions*/

    /* START Helper functions*/

    // format a number's decimal places to a string
    // must pass in a double!
    private String doubleToDecimalString(double toFormat){

        String d = "";
        DecimalFormat df = new DecimalFormat("0.00");
        d = df.format(toFormat);

        return d;
    }

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
    }  // end isSSNFourDigits

    // prints out Patient Appointment & Billing History in the billing tab
    private void printHistory(patient toPrint){
        if (toPrint == null)
            JOptionPane.showMessageDialog
                    (null, "No Patient to print");
        else{
            String history = "";
            for (String s: toPrint.getApptPaymentHistory()){
                history += s + "\n";
            }
            billing_patientHistoryTextArea.setText(history);
        }
    }

    private void clearHistory(){
        billing_patientHistoryTextArea.setText("");
    }

    // search date method
    private void search_date() {
        String date = cal_datePicker.getText();
        HashMap<String, patient[]> tempMap =
                MainGUI.pimsSystem.getStaff_lookupAppointmentsMap();
        patient patient;
        String temp = "";
        cal_table.setValueAt(date, 0, 0);
        if (!tempMap.containsKey(date)) {
            for (int i = 1; i < 9; i++) {
                cal_table.setValueAt("No Appointment", i, 1);
            }
        } else {
            patient[] tempArray = tempMap.get(date);
            if (tempArray[0] != null) {
                patient = tempArray[0];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 1, 1);
            } else cal_table.setValueAt("No Appointment", 1, 1);
            if (tempArray[1] != null) {
                patient = tempArray[1];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 2, 1);
            } else cal_table.setValueAt("No Appointment", 2, 1);
            if (tempArray[2] != null) {
                patient = tempArray[2];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 3, 1);
            } else cal_table.setValueAt("No Appointment", 3, 1);
            if (tempArray[3] != null) {
                patient = tempArray[3];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 4, 1);
            } else cal_table.setValueAt("No Appointment", 4, 1);
            if (tempArray[4] != null) {
                patient = tempArray[4];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 5, 1);
            } else cal_table.setValueAt("No Appointment", 5, 1);
            if (tempArray[5] != null) {
                patient = tempArray[5];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 6, 1);
            } else cal_table.setValueAt("No Appointment", 6, 1);
            if (tempArray[6] != null) {
                patient = tempArray[6];
                temp = patient.getF_name() + " " + patient.getL_name();
                cal_table.setValueAt(temp, 7, 1);
            } else cal_table.setValueAt("No Appointment", 7, 1);
            if (tempArray[7] != null) {
                patient = tempArray[7];
                temp = patient.getF_name() + " " + patient.getL_name();
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

	/* START Appointments Tab: DatePicker & TimePicker related methods */

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

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = MainGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
/*
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
    */

}// end EmployeeGUI class
