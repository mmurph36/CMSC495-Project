import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class EmployeeGUI extends JPanel{
	
	MainGUI mainGUI; 
	
	// Patient Information - store info when searched
	patient patient;
	
	// Employee Information
	private String empUser; 
	private String empPW;
	
	// Employee Label
	JLabel employeeGUILabel;
	
	// Login window variables
	JPanel loginPanel;
	JLabel userLabel, pwLabel;
	JTextField userField, pwField;
	JButton loginButton, cancelButton;
	
	// QUESTION: What does the cancel button (of the login panel) do?
	
	// Employee Window (Tabbed Pane)
	// 4 Tabs: Calendar, Patient Information, Billing, Search
	JTabbedPane jtp; 
	JPanel calTab, patientTab, billingTab, searchTab;
	
	// TAB 1: Calendar
	DatePickerPanel datePanel; 
	
	// TAB 2: Patient Information 
	JLabel lNameLabel, fNameLabel, mNameLabel, ssnLabel, dobLabel, 
		phoneLabel, streetLabel, cityLabel, stateLabel, zipLabel;
	
	/*
	 * note:
	 * -consider how to restrict how user can enter this in.
	 * -ex: have a month, date, year field
	 */
	
	JTextField lNameField, fNameField, mNameField, ssnField, dobField,
		phoneField, streetField, cityField, zipField;
	JComboBox<String> statesCB;
	
	
	// TAB 3: Billing
	JLabel lNameBillLabel, ssnBillLabel, billingCodeLabel, policyLabel, amtDueLabel;
	JTextField lNameBillField, ssnBillField, amtDueField;
	JComboBox<String> codeCB, policyCB;
	
	// ADD CALCULATE BUTTON
	
	// TAB 4: Search
	JLabel lNameSearchLabel, ssnSearchLabel, searchDirectionLabel;
	JTextField lNameSearchField, ssnSearchField;
	JPanel searchPanel, lNameSearchPanel, ssnSearchPanel, searchButtonPanel;
	JButton searchButton, selectButton;
	JComboBox<String> choosePatientCB;

	// constructor
	public EmployeeGUI(MainGUI main){
		initialize(main);
	}
	
	// Default Constructor
	private void initialize(MainGUI main) {
		
		mainGUI = main;
		
		// set up EmployeeGUI JPanel
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//setSize(1000, 500);
		employeeGUILabel = new JLabel("Employee PIMS"); // may not use
		
		// initialize login panel variables
		loginPanel = new JPanel(new GridLayout(0,2,5,5));
		
		userLabel = new JLabel("Username:", SwingConstants.RIGHT);
		pwLabel = new JLabel("Password:", SwingConstants.RIGHT);
		userField = new JTextField(20);
		userField.setEditable(true);
		pwField = new JTextField(20);
		pwField.setEditable(true);
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		
		// set up login panel
		loginPanel.add(userLabel);
		loginPanel.add(userField);
		loginPanel.add(pwLabel);
		loginPanel.add(pwField);
		loginPanel.add(loginButton);
		loginPanel.add(cancelButton);
		
		// initialize JTabbedPane
		jtp = new JTabbedPane();
		calTab = new JPanel();
		patientTab = new JPanel(new GridLayout(0, 2, 5, 5));
		billingTab = new JPanel(new GridLayout(0, 2, 5, 5));
		searchTab = new JPanel(new GridLayout(1, 0 ,5 ,5));
		
		// TAB 1: Calendar
		datePanel = new DatePickerPanel();
		calTab.add(datePanel);
		
		// TAB 2: Patient Information
		lNameLabel = new JLabel("Last Name");
		fNameLabel = new JLabel("First Name");
		mNameLabel = new JLabel("Middle Name");
		ssnLabel = new JLabel("Social Security Number");
		dobLabel = new JLabel("Date of Birth");
		phoneLabel = new JLabel("Telephone Number:");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("ZIP Code");

		lNameField = new JTextField(20);
		lNameField.setEditable(true);
		fNameField = new JTextField(20);
		fNameField.setEditable(true);
		mNameField = new JTextField(20);
		mNameField.setEditable(true);
		ssnField = new JTextField(20);
		ssnField.setEditable(true);
		dobField  = new JTextField(20);
		dobField.setEditable(true);
		phoneField = new JTextField(20);
		phoneField.setEditable(true);
		streetField = new JTextField(20);
		streetField.setEditable(true);
		cityField = new JTextField(20);
		cityField.setEditable(true);
		zipField = new JTextField(20);
		zipField.setEditable(true);
		
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
		statesCB = new JComboBox<String>(states);
		
		
		// add components to tab 2
		patientTab.add(lNameLabel);
		patientTab.add(lNameField);
		patientTab.add(fNameLabel);
		patientTab.add(fNameField);
		patientTab.add(mNameLabel);
		patientTab.add(mNameField);
		patientTab.add(ssnLabel);
		patientTab.add(ssnField);
		patientTab.add(dobLabel);
		patientTab.add(dobField);
		patientTab.add(phoneLabel);
		patientTab.add(phoneField);
		patientTab.add(streetLabel);
		patientTab.add(streetField);
		patientTab.add(cityLabel);
		patientTab.add(cityField);
		patientTab.add(stateLabel);
		patientTab.add(statesCB);
		patientTab.add(zipLabel);
		patientTab.add(zipField);

		// TAB 3: Billing
		lNameBillLabel = new JLabel("Patient Last Name:");
		ssnBillLabel = new JLabel("SSN:");
		billingCodeLabel = new JLabel("Billing Code:");
		policyLabel = new JLabel("Policy:");
		amtDueLabel = new JLabel("Amount Due");
		lNameBillField= new JTextField(20);
		ssnBillField= new JTextField(20);
		amtDueField= new JTextField(20);
		amtDueField.setEditable(false);
		
		
		String[] billingCodeOptions = {"1110", "2110", "3110"};
		String[] policyOptions = {"Yes", "No"};
		codeCB = new JComboBox<String>(billingCodeOptions);
		policyCB = new JComboBox<String>(policyOptions);
		
		// add contents to Billing tab
		billingTab.add(lNameBillLabel);
		billingTab.add(lNameBillField);
		billingTab.add(ssnBillLabel);
		billingTab.add(ssnBillField);
		billingTab.add(billingCodeLabel);
		billingTab.add(codeCB);
		billingTab.add(policyLabel);
		billingTab.add(policyCB);
		billingTab.add(amtDueLabel);
		billingTab.add(amtDueField);
		
		// ADD CALCULATE BUTTON

		
		// TAB 4: Search
		searchButton = new JButton("Search"); 
		searchPanel = new JPanel(new GridLayout(1, 0,5 ,5));
		lNameSearchPanel = new JPanel(new FlowLayout());
		ssnSearchPanel = new JPanel(new FlowLayout());
		searchButtonPanel = new JPanel();
		searchDirectionLabel = new JLabel("Search for Patient using Last Name OR SSN");
		lNameSearchLabel = new JLabel("Last Name:");
		lNameSearchField = new JTextField(20);
		ssnSearchLabel = new JLabel("SSN:");
		ssnSearchField = new JTextField(20);
		
		// add components to Search tab
		lNameSearchPanel.add(lNameLabel);
		lNameSearchPanel.add(lNameField);
		ssnSearchPanel.add(ssnLabel);
		ssnSearchPanel.add(ssnField);
		searchPanel.add(lNameSearchPanel);
		searchPanel.add(ssnSearchPanel);
		searchButtonPanel.add(searchButton);
		searchTab.add(searchPanel);
		searchTab.add(searchButtonPanel);
		
		
		
		// add panels to tabbed pane
		jtp.add("Calendar", calTab);
		jtp.add("Patient Information", patientTab);
		jtp.add("Billing", billingTab);
		jtp.add("Search", searchTab);
		
		
		// set up main panel
		add(loginPanel, BorderLayout.CENTER);
		validate();
		
		// various action listeners for buttons
		loginButton.addActionListener (e -> checkLogin());
		
		
		// Q: What does the cancel button (of the login panel) do?
		
		
		searchButton.addActionListener(e -> searchPatient());
		
		/*searchButton.addActionListener (e -> search ((String)(searchCB.getSelectedItem()), searchTF.getText()));
		expandButton.addActionListener (e -> expandAllNodes());
		collapseButton.addActionListener (e -> collapseAllNodes());
		sortCB.addActionListener(e -> updateSortByCB());
		sortButton.addActionListener(e -> {
			try {
				sort();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		});*/
	}// end constructor

	/*
	 * checkLogin()
	 * 
	 * -checks username and password for employee
	 * -returns true if credentials are correct
	 * -false if not, and error message will display
	 */
	private boolean checkLogin(){
		
		String title, toDisplay;
		
		title = "Login";
		toDisplay = "Login failed";
		
		// grab what user entered in Username and PW fields
		empUser = userField.getText();
		empPW = pwField.getText();
		
		// for now, as long as user and pw are not empty, one can log in	
		if (!empUser.equals("") && !empPW.equals("")){
			
			/*
			 * BACKEND insert validation in if statement above
			 * need to validate user login
			 */
			
			toDisplay = "Login successful";
			remove(loginPanel);
			revalidate();
			repaint();
			
			//setSize(1000,500);
			add(jtp, BorderLayout.CENTER);
			validate();
			
			JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.DEFAULT_OPTION);
			return true;
		}
		
		JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.ERROR_MESSAGE);
		return false;
	} // end checkLogin()
	
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
	 * main for just employeeGUI
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		MainGUI mainGUI = new MainGUI();

		EmployeeGUI testGUI = new EmployeeGUI(mainGUI);
		
		mainGUI.setLayout(new GridLayout(1,0));
		mainGUI.setSize(1000,600);
		
		//mainGUI.add(testGUI);
		
		mainGUI.validate();

		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null); // GUI appear in center
		mainGUI.setVisible(true);
		
	}// end main
}// end EmployeeGUI class