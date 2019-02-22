

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

 * TO-DO: as of 2/20 evening

 * 



 * 

 * Patient Info Tab:

 * -to add a new patient, need to have a way for patient to add username or password.

 * -need a way for patient to update user/pw for both Employee or Patient side?

 * 

 * Search Tab:

 * -check if SSN field is a number. 

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



    // Patient Information - store info when searched



    private patient patient; // for search 





    // EmployeeGUI title



    String employeeGUItitle;







    // Login panel variables



    JPanel loginPanel;



    JLabel logInLabel, usernameLabel, passwordLabel;



    JTextField usernameTextField;

    JPasswordField passwordField;



    JButton loginButton;





    // Employee Window (Tabbed Pane)



    // 4 Tabs: Calendar, Patient Information, Billing, Search



    JTabbedPane tabbedPane;



    JPanel billingTab, searchTab;







    // TAB 1: Calendar



    JPanel calTab;



    JLabel chooseDateAndTimeLabel, currentAppointmentLabel,
            cal_patientFirstLabel, cal_patientLastLabel, lookUpAppointmentLabel,
            currentPatientLabel;



    JTextField currentAppointmentTextField, cal_patientFirstTextField,
            cal_patientLastTextField, lookUpAppointmentTextField,
            currentPatientTextField;



    DatePicker datePicker;



    TimePicker timePicker;



    JButton requestAppointmentButton, cancelAppointmentButton, lookUpAppointmentButton;







    // TAB 2: Patient Information



    JPanel patientTab;



    JLabel lNameLabel, fNameLabel, mNameLabel, ssnLabel, dobLabel,



    phoneLabel, streetLabel, cityLabel, stateLabel, zipLabel, patient_userLabel, patient_pwLabel;



    //JButton submitNewInfoButton;

  







	/*

	 * note:

	 * -consider how to restrict how user can enter this in.

	 * -ex: have a month, date, year field

	 */







    JTextField lNameField, fNameField, mNameField, ssnField, dobField,



    phoneField, streetField, cityField, zipField, patient_userField, patient_pwField;



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



    JButton searchButton, selectPatientFoundButton;



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



        // employeeGUItitle = "Employee PIMS"; // may not use



        //mainGUI.setTitle(employeeGUItitle);





        // ** set up Login Panel **







        loginPanel = new JPanel(new GridBagLayout());







        GridBagConstraints loginConstraints = new GridBagConstraints();







        logInLabel = new JLabel("Employee Login");



        usernameLabel = new JLabel("Username:");



        passwordLabel = new JLabel("Password:");







        usernameTextField = new JTextField(12);



        passwordField = new JPasswordField(12);







        loginButton = new JButton("Login");








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







        loginPanel.add(passwordField, loginConstraints);







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

        currentAppointmentLabel = new JLabel("Current Appointment:");

        cal_patientFirstLabel = new JLabel("Patient First Name");

        cal_patientLastLabel = new JLabel("Patient Last Name");

        lookUpAppointmentLabel = new JLabel("Look Up Patient's Existing Appointment");

        currentPatientLabel = new JLabel("Current Patient:");



        datePicker = createDatePicker();


        timePicker = createTimePicker();



        /* NEW text fields*/

        currentAppointmentTextField = new JTextField(12);

        currentAppointmentTextField.setEditable(false);

        cal_patientFirstTextField = new JTextField(12);

        cal_patientFirstTextField.setEditable(false);

        cal_patientLastTextField = new JTextField(12);

        cal_patientLastTextField.setEditable(false);

        lookUpAppointmentTextField = new JTextField(15);

        lookUpAppointmentTextField.setEditable(false);

        lookUpAppointmentTextField.setBackground(Color.white);

        currentPatientTextField = new JTextField(12);

        currentPatientTextField.setEditable(false);

        currentPatientTextField.setBackground(Color.white);



        requestAppointmentButton = new JButton("Request Appointment");

        

        /* NEW BUTTON*/

        cancelAppointmentButton = new JButton ("Cancel Appointment");

        lookUpAppointmentButton = new JButton("Look Up Appointment");



        chooseDateAndTimeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        lookUpAppointmentLabel.setFont(new Font("Serif", Font.PLAIN, 25));






        // set the constraints for each component and add
        // them to the calendar panel

        calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 10;
        calendarConstraints.weightx = 1;
        calendarConstraints.weighty = 0.2;
        calendarConstraints.anchor = GridBagConstraints.NORTH;
        calendarConstraints.insets = new Insets(20, 0, 0, 0);

        calTab.add(chooseDateAndTimeLabel, calendarConstraints);


        calendarConstraints.gridy = 20;
        calendarConstraints.weightx = 0;
        calendarConstraints.weighty = 0.1;
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


        calendarConstraints.gridx = 10;
        calendarConstraints.gridy = 10;
        calendarConstraints.ipady = 0;
        calendarConstraints.weighty = 0.2;
        calendarConstraints.weightx = 1;
        calendarConstraints.anchor = GridBagConstraints.NORTHWEST;
        calendarConstraints.insets = new Insets(20, 10, 0, 0);

        calTab.add(currentPatientLabel, calendarConstraints);


        calendarConstraints.insets = new Insets(40, 10, 0, 0);

        calTab.add(currentPatientTextField, calendarConstraints);



        /* ADD new labels, fields, button */







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



        /* NEW JLabels */

        JLabel patient_userLabel = new JLabel ("Username");



        JLabel patient_pwLabel = new JLabel ("Password");



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

        JTextField patient_userField = new JTextField(12);



        JTextField patient_pwField = new JTextField(12);



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

        

        /* NEW BUTTON */

        JButton submitNewInfoButton = new JButton ("Create New Patient File with current information");









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







        patientTab.add(addressTextField_TBP, patientTabConstraints);











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



        /*

         * 

         *  ADD new labels, fields, and buttons. Need formatting help

         */

        // patientTab.add(submitNewInfoButton, patientTabConstraints);





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





        searchTabConstraints.gridy = 40;

        searchTabConstraints.weighty = 1;

        searchTabConstraints.ipadx = 30;

        searchTabConstraints.ipady = 10;

        searchTabConstraints.insets = new Insets(0, 0, 0, 0);



        searchTab.add(searchButton, searchTabConstraints);





        // add panels to tabbed pane



        tabbedPane.add("Calendar", calTab);



        tabbedPane.add("Patient Information", patientTab);



        tabbedPane.add("Billing", billingTab);



        tabbedPane.add("Search", searchTab);





        // set up login panel - what is shown first to Employee



        add(loginPanel, BorderLayout.CENTER);





        validate();





        // ACTION LISTENERS 



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



            }



            else



                JOptionPane.showMessageDialog



                        (null, "Invalid Password or Username");



        });



        // PATIENT INFO TAB LISTENERS



        // submits a new patient info into the system

        submitNewInfoButton.addActionListener(e -> {



            // values to test if there are no input errors

            boolean emptyFields = true, illegalFields = true;



            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));

            String errorMessage = "Must Enter";

            if (String.valueOf(firstNameTextField_TBP.getText()).equals("")) {

                errorMessage += " First Name,";

                emptyFields = false;

            }

            if (String.valueOf(lastNameTextField_TBP.getText()).equals("")) {

                errorMessage += " Last Name,";

                emptyFields = false;

            }

            if (String.valueOf(SSNTextField_TBP.getText()).equals("")) {

                errorMessage += " Social Security #,";

                emptyFields = false;

            }

            if (String.valueOf(DOBTextField_TBP.getText()).equals("")) {

                errorMessage += " Date of Birth,";

                emptyFields = false;

            }

            if (String.valueOf(phoneNumberTextField_TBP.getText()).equals("")) {

                errorMessage += " Phone Number,";

                emptyFields = false;

            }

            if (String.valueOf(addressTextField_TBP).equals("")) {

                errorMessage += " Street,";

                emptyFields = false;

            }

            if (String.valueOf(cityTextField_TBP).equals("")) {

                errorMessage += " City,";

                emptyFields = false;

            }

            if (String.valueOf(zipCodeTextField_TBP).equals("")) {

                errorMessage += " Zip Code,";

                emptyFields = false;

            }





            // if there's no middle name, the text field

            // is set to "N/A"



            String middleName;



            if (String.valueOf(middleNameTextField_TBP.getText()).equals(""))

                middleName = "N/A";

            else middleName = middleNameTextField_TBP.getText();



            // throws error if last name has characters other than letters



            if (lastNameTextField_TBP.getText().length() > 0) {

                for (int i = 0; i < lastNameTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(lastNameTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Last Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if first name has characters other than letters



            if (firstNameTextField_TBP.getText().length() > 0) {

                for (int i = 0; i < firstNameTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(firstNameTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "First Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if middle name has characters other than letters



            if (middleNameTextField_TBP.getText().length() > 0 &&

                    !String.valueOf(middleNameTextField_TBP.getText()).equals("N/A")) {

                for (int i = 0; i < middleNameTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(middleNameTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Middle Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if SSN has characters other than numbers, or has less/more than 4 digits



            if (SSNTextField_TBP.getText().length() > 0 && SSNTextField_TBP.getText().length() != 4) {

                JOptionPane.showMessageDialog

                        (null, "Social Security # Must Have 4 Characters");

                illegalFields = false;

            } else if (SSNTextField_TBP.getText().length() == 4) {

                for (int i = 0; i < 4; i++) {

                    if (!Character.isDigit(SSNTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Social Security # Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"



            if (DOBTextField_TBP.getText().length() > 0 && DOBTextField_TBP.getText().length() != 10) {

                JOptionPane.showMessageDialog

                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                illegalFields = false;

            } else if (DOBTextField_TBP.getText().length() == 10) {

                if (!DOBparser(DOBTextField_TBP.getText())) {

                    JOptionPane.showMessageDialog

                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                    illegalFields = false;

                }

            }



            // throws error if phone number isn't formatted correctly - "###-###-####"



            if (phoneNumberTextField_TBP.getText().length() > 0 && phoneNumberTextField_TBP.getText().length() != 12) {

                JOptionPane.showMessageDialog

                        (null, "Phone Number Must be formatted \"###-###-####\"");

                illegalFields = false;

            } else if (phoneNumberTextField_TBP.getText().length() == 12) {

                if (!phoneNumberParser(phoneNumberTextField_TBP.getText())) {

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



            if (cityTextField_TBP.getText().length() > 0) {

                for (int i = 1; i < cityTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(cityTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "City Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if zip code has characters other than numbers, or has less/more than 4 digits



            if (zipCodeTextField_TBP.getText().length() > 0 && zipCodeTextField_TBP.getText().length() != 5) {

                JOptionPane.showMessageDialog

                        (null, "Zip Code Must Have 5 Characters");

                illegalFields = false;

            } else if (zipCodeTextField_TBP.getText().length() == 5) {

                for (int i = 0; i < 5; i++) {

                    if (!Character.isDigit(zipCodeTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Zip Code Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // checks if there are no input errors



            if (emptyFields && illegalFields) {

                if (MainGUI.pimsSystem.patient_exists(firstNameTextField_TBP.getText(),

                        lastNameTextField_TBP.getText(), DOBTextField_TBP.getText(), Integer.parseInt(SSNTextField_TBP.getText())))

                    JOptionPane.showMessageDialog

                            (null, "This Patient Is Already In System");

                else {



                    MainGUI.pimsSystem.add_patient(firstNameTextField_TBP.getText(),

                            lastNameTextField_TBP.getText(), middleNameTextField_TBP.getText(),

                            "user", "password",

                            DOBTextField_TBP.getText(),

                            Integer.parseInt(SSNTextField_TBP.getText()), Integer.parseInt(zipCodeTextField_TBP.getText()),

                            addressTextField_TBP.getText(), cityTextField_TBP.getText(),

                            String.valueOf(stateComboBox_TBP.getSelectedItem()), phoneNumberTextField_TBP.getText());



                    // set the patient info panel in the tabbed pane to

                    // to info from the create new info patient panel

                	

                	/*

                    firstNameTextField_TBP.setText(firstNameTextField_TBP.getText());

                    middleNameTextField_TBP.setText(middleName);

                    lastNameTextField_TBP.setText(lastNameTextField_TBP.getText());

                    SSNTextField_TBP.setText(SSNTextField_TBP.getText());

                    DOBTextField_TBP.setText(DOBTextField_TBP.getText());

                    phoneNumberTextField_TBP.setText(phoneNumberTextField_TBP.getText());

                    addressTextField_TBP.setText(addressTextField_TBP.getText());

                    cityTextField_TBP.setText(cityTextField_TBP.getText());

                    zipCodeTextField_TBP.setText(zipCodeTextField_TBP.getText());

                    stateComboBox_TBP.setSelectedItem(stateComboBox_TBP.getSelectedItem());

                    //remove(createNewPatientInfoPanel);

                   // add(tabbedPane);

                    * 

                    * 

                    */

                    JOptionPane.showMessageDialog

                            (null, "Submission Successful");

                    repaint();

                    revalidate();

                }

            } else if (!String.valueOf(errorMessage).equals("Must Enter")) {

                JOptionPane.showMessageDialog(null, errorMessage);

            }

        });





        updateInfoButton.addActionListener(e -> {

            // values to test if there are no input errors

            boolean emptyFields = true, illegalFields = true;



            //UIManager.put("OptionPane.minimumSize",new Dimension(500,300));

            String errorMessage = "Must Enter";

            if (String.valueOf(firstNameTextField_TBP.getText()).equals("")) {

                errorMessage += " First Name,";

                emptyFields = false;

            }

            if (String.valueOf(lastNameTextField_TBP.getText()).equals("")) {

                errorMessage += " Last Name,";

                emptyFields = false;

            }

            if (String.valueOf(SSNTextField_TBP.getText()).equals("")) {

                errorMessage += " Social Security #,";

                emptyFields = false;

            }

            if (String.valueOf(DOBTextField_TBP.getText()).equals("")) {

                errorMessage += " Date of Birth,";

                emptyFields = false;

            }

            if (String.valueOf(phoneNumberTextField_TBP.getText()).equals("")) {

                errorMessage += " Phone Number,";

                emptyFields = false;

            }

            if (String.valueOf(addressTextField_TBP).equals("")) {

                errorMessage += " Street,";

                emptyFields = false;

            }

            if (String.valueOf(cityTextField_TBP).equals("")) {

                errorMessage += " City,";

                emptyFields = false;

            }

            if (String.valueOf(zipCodeTextField_TBP).equals("")) {

                errorMessage += " Zip Code,";

                emptyFields = false;

            }





            // if there's no middle name, the text field

            // is set to "N/A"



            String middleName;



            if (String.valueOf(middleNameTextField_TBP.getText()).equals(""))

                middleName = "N/A";

            else middleName = middleNameTextField_TBP.getText();



            // throws error if last name has characters other than letters



            if (lastNameTextField_TBP.getText().length() > 0) {

                for (int i = 0; i < lastNameTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(lastNameTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Last Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if first name has characters other than letters



            if (firstNameTextField_TBP.getText().length() > 0) {

                for (int i = 0; i < firstNameTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(firstNameTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "First Name Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if middle name has characters other than letters



            if (middleNameTextField_TBP.getText().length() > 0 &&

                    !String.valueOf(middleNameTextField_TBP.getText()).equals("N/A")) {

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



            if (SSNTextField_TBP.getText().length() > 0 && SSNTextField_TBP.getText().length() != 4) {

                JOptionPane.showMessageDialog

                        (null, "Social Security # Must Have 4 Characters");

                illegalFields = false;

            } else if (SSNTextField_TBP.getText().length() == 4) {

                for (int i = 0; i < 4; i++) {

                    if (!Character.isDigit(SSNTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Social Security # Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"



            if (DOBTextField_TBP.getText().length() > 0 && DOBTextField_TBP.getText().length() != 10) {

                JOptionPane.showMessageDialog

                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                illegalFields = false;

            } else if (DOBTextField_TBP.getText().length() == 10) {

                if (!DOBparser(DOBTextField_TBP.getText())) {

                    JOptionPane.showMessageDialog

                            (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                    illegalFields = false;

                }

            }



            // throws error if phone number isn't formatted correctly - "###-###-####"



            if (phoneNumberTextField_TBP.getText().length() > 0 && phoneNumberTextField_TBP.getText().length() != 12) {

                JOptionPane.showMessageDialog

                        (null, "Phone Number Must be formatted \"###-###-####\"");

                illegalFields = false;

            } else if (phoneNumberTextField_TBP.getText().length() == 12) {

                if (!phoneNumberParser(phoneNumberTextField_TBP.getText())) {

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



            if (cityTextField_TBP.getText().length() > 0) {

                for (int i = 1; i < cityTextField_TBP.getText().length(); i++) {

                    if (!Character.isLetter(cityTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "City Must Have Only Letters");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // throws error if zip code has characters other than numbers, or has less/more than 4 digits



            if (zipCodeTextField_TBP.getText().length() > 0 && zipCodeTextField_TBP.getText().length() != 5) {

                JOptionPane.showMessageDialog

                        (null, "Zip Code Must Have 5 Characters");

                illegalFields = false;

            } else if (zipCodeTextField_TBP.getText().length() == 5) {

                for (int i = 0; i < 5; i++) {

                    if (!Character.isDigit(zipCodeTextField_TBP.getText().charAt(i))) {

                        JOptionPane.showMessageDialog

                                (null, "Zip Code Must Have Only Numbers");

                        illegalFields = false;

                        break;

                    }

                }

            }



            // checks if there are no input errors



            patient = MainGUI.pimsSystem.patient_details

                    (lastNameTextField_TBP.getText(), Integer.parseInt(SSNTextField_TBP.getText()));





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

            } else if (!String.valueOf(errorMessage).equals("Must Enter"))

                JOptionPane.showMessageDialog(null, errorMessage);

            else if (patient == null)

                JOptionPane.showMessageDialog(null, "Error");

        });



        requestAppointmentButton.addActionListener(e -> {

            JOptionPane.showMessageDialog

                    (null, "Appointment Saved");

        });



        // BILLING TAB LISTENERS



        // SEARCH TAB LISTENERS



        searchButton.addActionListener(e -> searchPatient());



    }// end constructor





	/*

	 *

	 */



    private void searchPatient(){







        String lName;

        int ssn;



        lName = lNameSearchField.getText();



        ssn = Integer.getInteger(ssnSearchField.getText());

       

        

        /* TO-DO: check if ssn is an integer*/







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



        selectPatientFoundButton = new JButton("Select Patient");



        //patient patientFound = choosePatientCB.getSelectedItem()



        //selectPatientFoundButton.addActionListener(e-> fillPatientFoundData(patientFound));











    } // end searchPatient







	/*

	 *

	 */



    private void fillPatientFoundData(patient toDisplay){







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

