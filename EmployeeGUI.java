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

import javax.swing.JButton;

import javax.swing.JTabbedPane;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import com.github.lgooddatepicker.components.DatePickerSettings;

import com.github.lgooddatepicker.components.TimePicker;

import com.github.lgooddatepicker.components.TimePickerSettings;

import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import com.github.lgooddatepicker.optionalusertools.PickerUtilities;

import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;



import java.awt.*;

import javax.swing.JComboBox;



@SuppressWarnings("serial")

public class EmployeeGUI extends JPanel{



    // mainGUI

    MainGUI mainGUI;



    // mainPanel of this EmployeeGUI - choose New or Existing Employee

    JPanel mainPanel;

    JLabel chooseLabel;

    JButton existingEmployeeButton, newEmployeeButton, backButton;





    // Patient Information - store info when searched

    patient patient;



    // Employee Information

    private String empUser;

    private String empPW;



    // EmployeeGUI title

    String employeeGUItitle;



    // Login panel variables

    JPanel loginPanel;

    JLabel logInLabel, usernameLabel, passwordLabel;

    JTextField usernameTextField, passwordTextField;

    JButton loginButton, cancelButton;



    // Create new employee panel

    JPanel createNewEmpPanel;

    JLabel createNewEmpLabel;

    JLabel usernameLabel_cne;

    JLabel passwordLabel_cne;



    JTextField usernameTextField_cne;

    JTextField passwordTextField_cne;



    JButton submitButton;

    JButton cancelButton_cne;



    // Employee Window (Tabbed Pane)

    // 4 Tabs: Calendar, Patient Information, Billing, Search

    JTabbedPane tabbedPane;

    JPanel patientTab, billingTab, searchTab;



    // TAB 1: Calendar

    JPanel calTab;

    JLabel chooseDateAndTimeLabel;

    DatePicker datePicker;

    TimePicker timePicker;

    JButton requestAppointmentButton, logoutButton_calendar;



    // TAB 2: Patient Information

    JLabel lNameLabel, fNameLabel, mNameLabel, ssnLabel, dobLabel,

    phoneLabel, streetLabel, cityLabel, stateLabel, zipLabel;

    JButton submitNewInfoButton = new JButton("Submit Information");



	/*

	 * note:

	 * -consider how to restrict how user can enter this in.

	 * -ex: have a month, date, year field

	 */



    JTextField lNameField, fNameField, mNameField, ssnField, dobField,

    phoneField, streetField, cityField, zipField;

    JComboBox<String> statesCB;





    // TAB 3: Billing

    JLabel patientBillingLabel, lNameBillLabel, ssnBillLabel, billingCodeLabel, policyLabel, amtDueLabel;

    JTextField lNameBillField, ssnBillField, amtDueField;

    JComboBox<String> codeCB, policyCB;



    // ADD CALCULATE BUTTON



    // TAB 4: Search

    JLabel lNameSearchLabel, ssnSearchLabel, searchDirectionLabel;

    JTextField lNameSearchField, ssnSearchField;

    JPanel searchPanel, lNameSearchPanel, ssnSearchPanel, searchButtonPanel;

    JButton searchButton, selectButton;

    JComboBox<String> choosePatientCB;





    // Constructor

    public EmployeeGUI(){



        initialize();

    }



	/*

	 * initialize()

	 *

	 * - sets up EmployeeGUI panel

	 */

    private void initialize() {







        // set up EmployeeGUI JPanel

        setLayout(new BorderLayout());

        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        employeeGUItitle = "Employee PIMS"; // may not use

        //mainGUI.setTitle(employeeGUItitle);



        // **set up MainPanel to choose new/existing employee**

        mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints mainPanelConstraints = new GridBagConstraints();



        chooseLabel = new JLabel("Choose New or Existing Employee");



        existingEmployeeButton = new JButton("Existing Employee");

        newEmployeeButton = new JButton("New Employee");

        backButton = new JButton("Back");



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



        mainPanel.add(existingEmployeeButton, mainPanelConstraints);



        mainPanelConstraints.ipadx = 20;

        mainPanelConstraints.anchor = GridBagConstraints.EAST;

        mainPanelConstraints.insets = new Insets(30, 0, 0, 110);



        mainPanel.add(newEmployeeButton, mainPanelConstraints);



        mainPanelConstraints.gridy = 20;

        mainPanelConstraints.weighty = 1;

        mainPanelConstraints.ipadx = 45;

        mainPanelConstraints.ipady = 5;

        mainPanelConstraints.anchor = GridBagConstraints.NORTHEAST;

        mainPanelConstraints.insets = new Insets(0, 0, 10, 0);



        mainPanel.add(backButton, mainPanelConstraints);



        add(mainPanel);



        // ** set up Login Panel **



        loginPanel = new JPanel(new GridBagLayout());



        GridBagConstraints loginConstraints = new GridBagConstraints();



        logInLabel = new JLabel("Employee Login");

        usernameLabel = new JLabel("Username:");

        passwordLabel = new JLabel("Password:");



        usernameTextField = new JTextField(12);

        passwordTextField = new JTextField(12);



        loginButton = new JButton("Login");

        cancelButton = new JButton("Cancel");



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

        loginConstraints.insets = new Insets(40, 25, 0, 10);



        loginPanel.add(usernameLabel, loginConstraints);



        loginConstraints.gridy = 30;

        loginConstraints.gridwidth = 10;

        loginConstraints.insets = new Insets(10, 25, 0, 10);



        loginPanel.add(passwordLabel, loginConstraints);



        loginConstraints.gridx = 20;

        loginConstraints.gridy = 20;

        loginConstraints.gridwidth = 20;

        loginConstraints.insets = new Insets(40, 0, 0, 25);



        loginPanel.add(usernameTextField, loginConstraints);



        loginConstraints.gridy = 30;

        loginConstraints.gridwidth = 10;

        loginConstraints.insets = new Insets(12, 0, 0, 25);



        loginPanel.add(passwordTextField, loginConstraints);



        loginConstraints.gridx = 10;

        loginConstraints.gridy = 40;

        loginConstraints.weighty = 1;

        loginConstraints.ipadx = 15;

        loginConstraints.ipady = 5;

        loginConstraints.gridwidth = 20;

        loginConstraints.anchor = GridBagConstraints.NORTHWEST;

        loginConstraints.insets = new Insets(30, 40, 0, 0);



        loginPanel.add(loginButton, loginConstraints);



        loginConstraints.gridx = 20;

        loginConstraints.gridy = 40;

        loginConstraints.ipadx = 10;

        loginConstraints.gridwidth = 10;

        loginConstraints.insets = new Insets(30, 45, 0, 0);



        loginPanel.add(cancelButton, loginConstraints);



        // ** Create new Employee Panel **

        createNewEmpPanel = new JPanel(new GridBagLayout());



        GridBagConstraints createNewEmpConstraints = new GridBagConstraints();



        createNewEmpLabel = new JLabel("New Employee");

        usernameLabel_cne = new JLabel("Username:");

        passwordLabel_cne = new JLabel("Password:");



        usernameTextField_cne = new JTextField(12);

        passwordTextField_cne = new JTextField(12);



        submitButton = new JButton("Submit");

        cancelButton_cne = new JButton("Cancel");



        createNewEmpLabel.setFont

                (new Font("Serif", Font.PLAIN, 40));



        createNewEmpConstraints.gridx = 10;

        createNewEmpConstraints.gridy = 0;

        createNewEmpConstraints.weighty = 0.2;

        createNewEmpConstraints.gridwidth = 20;

        createNewEmpConstraints.anchor = GridBagConstraints.NORTH;

        createNewEmpConstraints.insets = new Insets(50, 10, 0, 30);



        createNewEmpPanel.add(createNewEmpLabel, createNewEmpConstraints);





        createNewEmpConstraints.gridy = 10;

        createNewEmpConstraints.weighty = 0;

        createNewEmpConstraints.gridwidth = 10;

        createNewEmpConstraints.anchor = GridBagConstraints.CENTER;

        createNewEmpConstraints.insets = new Insets(40, 0, 0, 10);



        createNewEmpPanel.add(usernameLabel_cne, createNewEmpConstraints);





        createNewEmpConstraints.gridy = 20;

        createNewEmpConstraints.gridwidth = 10;

        createNewEmpConstraints.insets = new Insets(10, 0, 0, 10);



        createNewEmpPanel.add(passwordLabel_cne, createNewEmpConstraints);





        createNewEmpConstraints.gridx = 20;

        createNewEmpConstraints.gridy = 10;

        createNewEmpConstraints.gridwidth = 20;

        createNewEmpConstraints.insets = new Insets(40, 0, 0, 10);



        createNewEmpPanel.add(usernameTextField_cne, createNewEmpConstraints);





        createNewEmpConstraints.gridy = 20;

        createNewEmpConstraints.gridwidth = 10;

        createNewEmpConstraints.insets = new Insets(12, 0, 0, 10);



        createNewEmpPanel.add(passwordTextField_cne, createNewEmpConstraints);





        createNewEmpConstraints.gridx = 10;

        createNewEmpConstraints.gridy = 30;

        createNewEmpConstraints.weighty = 1;

        createNewEmpConstraints.ipadx = 15;

        createNewEmpConstraints.ipady = 5;

        createNewEmpConstraints.gridwidth = 20;

        createNewEmpConstraints.anchor = GridBagConstraints.NORTHWEST;

        createNewEmpConstraints.insets = new Insets(30, 10, 0, 0);



        createNewEmpPanel.add(submitButton, createNewEmpConstraints);





        createNewEmpConstraints.gridx = 20;

        createNewEmpConstraints.ipadx = 10;

        createNewEmpConstraints.gridwidth = 10;

        createNewEmpConstraints.insets = new Insets(30, 45, 0, 0);



        createNewEmpPanel.add(cancelButton_cne, createNewEmpConstraints);





        // ** initialize JTabbedPane **

        tabbedPane = new JTabbedPane();



        // TAB 1: Calendar



        calTab = new JPanel(new GridBagLayout());



        GridBagConstraints calendarConstraints = new GridBagConstraints();



        chooseDateAndTimeLabel = new JLabel("Select Date and Time For Appointment");



        datePicker = createDatePicker();

        timePicker = createTimePicker();

        requestAppointmentButton = new JButton("Request Appointment");

        logoutButton_calendar = new JButton("Log Out");



        chooseDateAndTimeLabel.setFont(new Font("Serif", Font.PLAIN, 25));





        // set the constraints for each component and add

        // them to the calendar panel



        calendarConstraints.gridx = 10;

        calendarConstraints.gridy = 10;

        calendarConstraints.weighty = 1;

        calendarConstraints.anchor = GridBagConstraints.NORTH;

        calendarConstraints.insets = new Insets(20, 0, 0, 0);



        calTab.add(chooseDateAndTimeLabel, calendarConstraints);





        calendarConstraints.weighty = 0;

        calendarConstraints.anchor = GridBagConstraints.WEST;

        calendarConstraints.insets = new Insets(0, 70, 0, 0);



        calTab.add(datePicker, calendarConstraints);





        calendarConstraints.anchor = GridBagConstraints.EAST;

        calendarConstraints.insets = new Insets(0, 0, 0, 75);



        calTab.add(timePicker, calendarConstraints);





        calendarConstraints.ipady = 10;

        calendarConstraints.anchor = GridBagConstraints.SOUTH;

        calendarConstraints.insets = new Insets(0, 0, 60, 0);



        calTab.add(requestAppointmentButton, calendarConstraints);





        calendarConstraints.gridy = 20;

        calendarConstraints.weighty = 1;

        calendarConstraints.anchor = GridBagConstraints.NORTHEAST;



        // calTab.add(logoutButton_calendar, calendarConstraints);



        // TAB 2: Patient Information

        patientTab = new JPanel(new GridBagLayout());



        GridBagConstraints patientTabConstraints = new GridBagConstraints();





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



        JComboBox<String> stateComboBox_TBP = new JComboBox<>(states);



        // create buttons

        JButton updateInfoButton = new JButton("Update Information");

        JButton logoutButton_PI = new JButton("Log Out");





        // set the constraints for each component and add

        // them to the patient info panel



        patientTabConstraints.gridx = 10;

        patientTabConstraints.gridy = 10;

        patientTabConstraints.weightx = 1;

        patientTabConstraints.weighty = 0.4;

        patientTabConstraints.anchor = GridBagConstraints.WEST;

        patientTabConstraints.insets = new Insets(0, 20, 0, 0);



        patientTab.add(lastNameLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 20;



        patientTab.add(firstNameLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 30;



        patientTab.add(middleNameLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 10;

        patientTabConstraints.gridy = 20;



        patientTab.add(SSNLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 30;



        patientTab.add(DOBLabel_TBP, patientTabConstraints);



        patientTabConstraints.gridy = 40;

        patientTabConstraints.weighty = 1;

        patientTabConstraints.anchor = GridBagConstraints.NORTHWEST;

        patientTabConstraints.insets = new Insets(10, 20, 0, 0);



        patientTab.add(phoneNumberLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 50;

        patientTabConstraints.anchor = GridBagConstraints.SOUTHWEST;

        patientTabConstraints.insets = new Insets(0, 20, 10, 0);



        patientTab.add(streetLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 60;

        patientTabConstraints.weighty = 0.4;

        patientTabConstraints.anchor = GridBagConstraints.WEST;

        patientTabConstraints.insets = new Insets(0, 20, 0, 0);



        patientTab.add(cityLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 70;



        patientTab.add(stateLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 80;



        patientTab.add(zipCodeLabel_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 10;

        patientTabConstraints.anchor = GridBagConstraints.EAST;

        patientTabConstraints.insets = new Insets(0, 0, 0, 40);



        patientTab.add(lastNameTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 20;

        patientTabConstraints.insets = new Insets(0, 0, 0, 60);



        patientTab.add(firstNameTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 30;



        patientTab.add(middleNameTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 10;

        patientTabConstraints.gridy = 20;

        patientTabConstraints.insets = new Insets(0, 0, 0, 40);



        patientTab.add(SSNTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 30;



        patientTab.add(DOBTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 40;

        patientTabConstraints.anchor = GridBagConstraints.NORTHEAST;

        patientTabConstraints.insets = new Insets(10, 0, 0, 40);



        patientTab.add(phoneNumberTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 50;

        patientTabConstraints.anchor = GridBagConstraints.SOUTHEAST;

        patientTabConstraints.insets = new Insets(0, 0, 10, 40);



        patientTab.add(streetTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 60;

        patientTabConstraints.anchor = GridBagConstraints.EAST;

        patientTabConstraints.insets = new Insets(0, 0, 0, 40);



        patientTab.add(cityTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 70;



        patientTab.add(stateComboBox_TBP, patientTabConstraints);





        patientTabConstraints.gridy = 80;



        patientTab.add(zipCodeTextField_TBP, patientTabConstraints);





        patientTabConstraints.gridx = 20;

        patientTabConstraints.ipady = 10;

        patientTabConstraints.anchor = GridBagConstraints.CENTER;



        patientTab.add(updateInfoButton, patientTabConstraints);





        patientTabConstraints.gridx = 30;

        patientTabConstraints.anchor = GridBagConstraints.EAST;



        //patientTab.add(logoutButton_PI, patientTabConstraints);





        // TAB 3: Billing



        billingTab = new JPanel(new GridBagLayout());


        GridBagConstraints billingTabConstraints = new GridBagConstraints();



        patientBillingLabel = new JLabel("Patient Billing");

        lNameBillLabel = new JLabel("Patient Last Name:");

        ssnBillLabel = new JLabel("SSN:");

        billingCodeLabel = new JLabel("Billing Code:");

        policyLabel = new JLabel("Policy:");

        amtDueLabel = new JLabel("Amount Due");

        lNameBillField= new JTextField(12);

        ssnBillField= new JTextField(12);

        amtDueField= new JTextField(12);

        amtDueField.setEditable(false);


        patientBillingLabel.setFont(new Font("Serif", Font.PLAIN, 40));



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

        //billingTabConstraints.weighty = 0.4;

        billingTabConstraints.anchor = GridBagConstraints.WEST;

        billingTabConstraints.insets = new Insets(0, 20, 0, 0);



        billingTab.add(lNameBillLabel, billingTabConstraints);



        billingTabConstraints.gridx = 30;



        billingTab.add(ssnBillLabel, billingTabConstraints);



        billingTabConstraints.gridx = 20;
        billingTabConstraints.weightx = 1;

        //billingTabConstraints.anchor = GridBagConstraints.EAST;

        //billingTabConstraints.insets = new Insets(0, 0, 0, 40);



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

        //searchPanel = new JPanel(new GridLayout(1, 0,5 ,5));

        //lNameSearchPanel = new JPanel(new FlowLayout());

        //ssnSearchPanel = new JPanel(new FlowLayout());

        //searchButtonPanel = new JPanel();

        searchDirectionLabel = new JLabel("Search for Patient using Last Name OR SSN");

        lNameSearchLabel = new JLabel("Last Name:");

        lNameSearchField = new JTextField(12);

        ssnSearchLabel = new JLabel("Social Security #:");

        ssnSearchField = new JTextField(12);



        searchDirectionLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        // add components to Search tab

        searchTabConstraints.gridx = 10;
        searchTabConstraints.gridy = 10;
        searchTabConstraints.weighty = 0.2;
        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.insets = new Insets(30, 0, 0, 0);

        searchTab.add(searchDirectionLabel, searchTabConstraints);


        searchTabConstraints.gridx = 10;

        searchTabConstraints.gridy = 20;

        //searchTabConstraints.weighty = 0;

        searchTabConstraints.anchor = GridBagConstraints.CENTER;

        searchTabConstraints.insets = new Insets(0, 0, 0, 120);


        searchTab.add(lNameSearchLabel, searchTabConstraints);




        searchTabConstraints.insets = new Insets(0, 110, 0, 0);

        searchTab.add(lNameSearchField, searchTabConstraints);




        searchTabConstraints.anchor = GridBagConstraints.NORTH;
        searchTabConstraints.gridy = 30;

        searchTabConstraints.insets = new Insets(0, 0, 0, 150);

        searchTab.add(ssnSearchLabel, searchTabConstraints);





        searchTabConstraints.insets = new Insets(0, 110, 0, 0);

        searchTab.add(ssnSearchField, searchTabConstraints);



        //searchTab.add(lNameSearchPanel, searchTabConstraints);



        //searchTab.add(ssnSearchPanel, searchTabConstraints);




        searchTabConstraints.gridy = 40;
        searchTabConstraints.weighty = 1;
        searchTabConstraints.ipadx = 30;
        searchTabConstraints.ipady = 10;
        searchTabConstraints.insets = new Insets(0, 0, 0, 0);

        searchTab.add(searchButton, searchTabConstraints);



        //searchTab.add(searchPanel, searchTabConstraints);



        //searchTab.add(searchButtonPanel, searchTabConstraints);







        // add panels to tabbed pane

        tabbedPane.add("Calendar", calTab);

        tabbedPane.add("Patient Information", patientTab);

        tabbedPane.add("Billing", billingTab);

        tabbedPane.add("Search", searchTab);





        // set up back button

        backButton = new JButton("Back");



        // set up main panel

        add(loginPanel, BorderLayout.CENTER);

        //add(backButton, BorderLayout.PAGE_END);

        validate();



        // various action listeners for buttons

        //loginButton.addActionListener (e -> checkLogin());





        // Q: What does the cancel button (of the login panel) do?





        searchButton.addActionListener(e -> searchPatient());



        // create ActionListeners for all the buttons



        existingEmployeeButton.addActionListener(e -> {

            remove(mainPanel);

            add(loginPanel);

            repaint();

            revalidate();

        });



        newEmployeeButton.addActionListener(e -> {

            remove(mainPanel);

            add(createNewEmpPanel);

            repaint();

            revalidate();

        });



        backButton.addActionListener(e -> backToStart());



        loginButton.addActionListener(e -> {

            if (String.valueOf(usernameTextField.getText()).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Username");

            else if (String.valueOf(passwordTextField.getText()).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Password");

            else if (MainGUI.pimsSystem.doctor_exists(usernameTextField.getText(), passwordTextField.getText())) {

                remove(loginPanel);

                add(tabbedPane);

                JOptionPane.showMessageDialog

                        (null, "Login Successful");

                repaint();

                revalidate();



                // reset username and password fields



                usernameTextField.setText("");

                passwordTextField.setText("");

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



            usernameTextField_cne.setText("");

            passwordTextField_cne.setText("");

        });





        submitButton.addActionListener(e -> {

            if (String.valueOf(usernameTextField_cne.getText()).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Username");

            else if (String.valueOf(passwordTextField_cne.getText()).equals(""))

                JOptionPane.showMessageDialog

                        (null, "Must Enter A Password");

            else if (usernameTextField_cne.getText().length() < 4)

                JOptionPane.showMessageDialog

                        (null, "Username Must Have At Least 4 Characters");

            else if (passwordTextField_cne.getText().length() < 4)

                JOptionPane.showMessageDialog

                        (null, "Password Must Have At Least 4 Characters");

            /*else if (!mainGUI.getSystem().patient_exists(usernameTextField_cne.getText(), passwordTextField_cne.getText())){

                remove(createNewEmpPanel);

                add(createNewPatientInfoPanel);

                JOptionPane.showMessageDialog

                        (null, "Submission Successful");

                repaint();

                revalidate();

            } */



            else

                JOptionPane.showMessageDialog

                        (null, "The Username and Password are Already Taken");



        });





        cancelButton_cne.addActionListener(e -> {

            remove(createNewEmpPanel);

            add(mainPanel);

            repaint();

            revalidate();



            // reset username and password fields



            usernameTextField_cne.setText("");

            passwordTextField_cne.setText("");

        });





        // submits a new patient info into the system

/*

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

                    if (!mainGUI.getSystem().add_patient(firstNameTextField.getText(),

                        lastNameTextField.getText(), middleNameTextField.getText(),

                        usernameTextField_cne.getText(), passwordTextField_cne.getText(),

                        DOBTextField.getText(), 30, Integer.parseInt(SSNTextField.getText()),

                        Integer.parseInt(zipCodeTextField.getText()), streetTextField.getText(),

                        phoneNumberTextField.getText()))

                    JOptionPane.showMessageDialog

                            (null, "This Patient Is Already In System");

                    else {

                        mainGUI.getSystem().add_patient(firstNameTextField.getText(),

                                lastNameTextField.getText(), middleNameTextField.getText(),

                                usernameTextField_cne.getText(), passwordTextField_cne.getText(),

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

        }); */



/*

        cancelButton_cnip.addActionListener(e -> {

            remove(createNewPatientInfoPanel);

            add(mainPanel);

            repaint();

            revalidate();



            // reset username and password fields



            usernameTextField_cne.setText("");

            passwordTextField_cne.setText("");

        }); */





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

            remove(this);

            revalidate();

            repaint();

            mainGUI.add(mainGUI.getStartPanel());

            //mainGUI.returnToLogin();

            JOptionPane.showMessageDialog

                    (null, "Logout Successful");



            validate();

        });



        logoutButton_calendar.addActionListener(e -> {

            remove(tabbedPane);

            remove(this);

            revalidate();

            repaint();



            mainGUI.add(mainGUI.getStartPanel());

            JOptionPane.showMessageDialog

                    (null, "Logout Successful");

            validate();



        });

    }// end constructor



	/*

	 * backToStart()

	 * - returns user back to start or choose login screen

	 */

    private void backToStart(){



        remove(mainPanel);

        remove(this);

        repaint();

        revalidate();

        mainGUI.add(mainGUI.getStartPanel());

        mainGUI.setTitle(mainGUI.guiTitle);



        validate();

    }



	/*

	 * checkLogin()

	 *

	 * -checks username and password for employee

	 * -returns true if credentials are correct

	 * -false if not, and error message will display

	 */

	/*

	private boolean checkLogin(){



		String title, toDisplay;



		title = "Login";

		toDisplay = "Login failed";



		// grab what user entered in Username and PW fields

		empUser = usernameTextField.getText();

		empPW = passwordTextField.getText();



		// for now, as long as user and pw are not empty, one can log in

		if (!empUser.equals("") && !empPW.equals("")){



			/*

			 * BACKEND insert validation in if statement above

			 * need to validate user login

			 */



			/*



			toDisplay = "Login successful";

			remove(loginPanel);

			revalidate();

			repaint();



			//setSize(1000,500);

			add(tabbedPane, BorderLayout.CENTER);

			validate();



			JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.DEFAULT_OPTION);

			return true;

		}



		JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.ERROR_MESSAGE);

		return false;

	} // end checkLogin()



	*/



	/*

	 *

	 */

    private void searchPatient(){



        String lName, ssn;

        lName = lNameSearchField.getText();

        ssn = ssnSearchField.getText();



        JOptionPane.showMessageDialog(this, "Searcing for Last Name:" + lName + ", or SSN:" + ssn,

                "Searching...", JOptionPane.DEFAULT_OPTION);





		/*

		 * BACKEND - grab patient information

		 * -return array list or array of Patients with

		 * corresponding data

		 */

        ArrayList<String> patientsFound = new ArrayList<String>();

        String[] patientOptions;



        // display whether patients found or not

        JOptionPane.showMessageDialog(this, "Found Results for for Last Name:" + lName + ", or SSN:" + ssn,

                "Search Successful", JOptionPane.DEFAULT_OPTION);





        // create JComboBox for Patient Options to select from

        // using patientsFound

        choosePatientCB = new JComboBox<String>();

        selectButton = new JButton("Select Patient");

        selectButton.addActionListener(e-> fillPatientFoundData());





    } // end searchPatient



	/*

	 *

	 */

    private void fillPatientFoundData(){



        JOptionPane.showMessageDialog(this, "Filling in Information for Patient Found",

                "Filling in Info", JOptionPane.DEFAULT_OPTION);





		/*

		 * BACKEND - need specific patient object getters

		 */

        // Patient patientSearched;



		/*

		 * To-Do:

		 *

		 * -fill in all fields with patientSearched's information

		 * -ex: first, middle, last name, billing info, etc

		 * -populate fields across all tabs

		 */





    }// end fillPatientData()





	/*

	 * DatePicker related methods & private classes

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



        mainGUI.setLayout(new GridLayout(1,0));

        mainGUI.setSize(1000,600);



        //mainGUI.add(testGUI);



        mainGUI.validate();



        mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainGUI.setLocationRelativeTo(null); // GUI appear in center

        mainGUI.setVisible(true);



    }// end main

}// end EmployeeGUI class