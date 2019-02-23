/* Author: Esther Ho & Ari Ohsie
 * CMSC 495
 * PIMS Project
 *
 * File Name: MainGUI.java
 *
 *
 *
 * DISCLAIMER: EmployeeGUI & PatientGUI use code from the following project for the calendar
 *  https://github.com/LGoodDatePicker/LGoodDatePicker
 */
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainGUI extends JFrame{

    public static system pimsSystem;
    String guiTitle;

    JPanel employeePanel, patientPanel;

    // "Start Panel" - login GUI variables
    JPanel startPanel = new JPanel(new BorderLayout());
    JLabel welcomeLabel_1 = new JLabel("Welcome to PIMS", SwingConstants.CENTER);
    JLabel welcomeLabel2 = new JLabel("Patient Information Management System", SwingConstants.CENTER);
    JLabel chooseEmployeeOrPatientLabel =
            new JLabel("Choose Employee or Patient", SwingConstants.CENTER);

    /* NEW JLabel To Distinguish Employee & Patient menu
     *  - to be displayed in Left or Center on top, in the same line where the backToLoginButton is
     */
    JPanel welcomePanel = new JPanel(new GridBagLayout());
    
    JButton employeeButton = new JButton("Employee");
    JButton patientButton = new JButton("Patient");


    // Back Button to go back to Login
    
    JLabel employeeMenuLabel = new JLabel("PIMS - Employee Menu", SwingConstants.CENTER);
    JLabel patientMenuLabel= new JLabel("PIMS - Patient Menu", SwingConstants.CENTER);
    JButton backToLoginPatientButton = new JButton("Back to \"Welcome\" Page");
    JButton backToLoginEmployeeButton = new JButton("Back to \"Welcome\" Page");
    JPanel backPatientPanel = new JPanel(new GridBagLayout());
    JPanel backEmployeePanel = new JPanel(new GridBagLayout());

    // default constructor
    public MainGUI() {

        guiTitle = "PIMS";

        // initialize system object
        pimsSystem = new system();

        // sets up main GUI
        setTitle(guiTitle);
        setLayout(new BorderLayout());
        setSize(1000,600);
        setResizable(false);

        // set up start Panel
        welcomeLabel_1.setFont(new java.awt.Font(welcomeLabel_1.getFont().getFontName(), Font.PLAIN, 40));
        welcomeLabel2.setFont(new java.awt.Font(welcomeLabel2.getFont().getFontName(), Font.PLAIN, 40));
        chooseEmployeeOrPatientLabel.setFont(new java.awt.Font(chooseEmployeeOrPatientLabel.getFont().getFontName(), Font.PLAIN, 30));

        GridBagConstraints welcomePanelConstraints = new GridBagConstraints();

        welcomePanelConstraints.gridx = 10;
        welcomePanelConstraints.gridy = 10;
        welcomePanelConstraints.anchor = GridBagConstraints.NORTH;
        welcomePanelConstraints.insets = new Insets(30, 0, 0, 0);
        welcomePanel.add(welcomeLabel_1, welcomePanelConstraints);

        welcomePanelConstraints.weighty = 0.4;
        welcomePanelConstraints.anchor = GridBagConstraints.CENTER;
        welcomePanelConstraints.insets = new Insets(10, 0, 0, 0);
        welcomePanel.add(welcomeLabel2, welcomePanelConstraints);

        welcomePanelConstraints.gridy = 20;
        welcomePanelConstraints.weighty = 0;
        welcomePanelConstraints.insets = new Insets(0, 0, 0, 0);
        welcomePanel.add(chooseEmployeeOrPatientLabel, welcomePanelConstraints);

        welcomePanelConstraints.gridy = 30;
        welcomePanelConstraints.weighty = 1;
        welcomePanelConstraints.ipadx = 20;
        welcomePanelConstraints.ipady = 15;
        welcomePanelConstraints.anchor = GridBagConstraints.NORTH;
        welcomePanelConstraints.insets = new Insets(40, 0, 0, 150);
        welcomePanel.add(employeeButton, welcomePanelConstraints);
        
        welcomePanelConstraints.gridy = 30;
        welcomePanelConstraints.ipadx = 30;
        welcomePanelConstraints.insets = new Insets(40, 150, 0, 0);
        welcomePanel.add(patientButton, welcomePanelConstraints);

        startPanel.add(welcomePanel, BorderLayout.CENTER);

        // Add start panel to JFrame
        add(startPanel,BorderLayout.CENTER);

        patientMenuLabel.setFont(new java.awt.Font(patientMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        employeeMenuLabel.setFont(new java.awt.Font(employeeMenuLabel.getFont().getFontName(), Font.PLAIN, 30));

        // Add labels to back patient &  back employee panel
        // BACK PANELS

        patientMenuLabel.setFont(new java.awt.Font(patientMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        employeeMenuLabel.setFont(new java.awt.Font(employeeMenuLabel.getFont().getFontName(), Font.PLAIN, 30));

        // Add label & back button to back patient and employee panels

        GridBagConstraints backButtonConstraints = new GridBagConstraints();

        backButtonConstraints.weightx = 1;
        backButtonConstraints.ipadx = 20;
        backButtonConstraints.ipady = 10;
        backButtonConstraints.anchor = GridBagConstraints.NORTHWEST;
        backButtonConstraints.insets = new Insets(10, 0, 0, 10);

        backPatientPanel.add(patientMenuLabel, backButtonConstraints);

        backEmployeePanel.add(employeeMenuLabel, backButtonConstraints);


        backButtonConstraints.anchor = GridBagConstraints.NORTHEAST;

        backPatientPanel.add(backToLoginPatientButton, backButtonConstraints);

        backEmployeePanel.add(backToLoginEmployeeButton, backButtonConstraints);

        
        
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // GUI appear in center
        setVisible(true);
        
        // Action Listeners
        employeeButton.addActionListener (e -> employeeMenu());

        patientButton.addActionListener (e -> patientMenu());

        backToLoginPatientButton.addActionListener(e -> returnToLogin());

        backToLoginEmployeeButton.addActionListener (e -> returnToLogin());

    }// end constructor

	/*
	 * getter for startPanel
	 */
    public Component getStartPanel() {
        return startPanel;
    }

	/*
	 * employeeMenu()
	 */
    private void employeeMenu(){

        remove(startPanel);
        revalidate();
        repaint();
        
        employeePanel = new EmployeeGUI();
        this.add(backEmployeePanel, BorderLayout.PAGE_START);
        this.add(employeePanel, BorderLayout.CENTER);
        
        validate();
        
    }// end employeeMenu

    
	/*
	 * patientMenu()
	 */
    private void patientMenu(){
    	
        remove(startPanel);
        revalidate();
        repaint();
        
        patientPanel = new PatientGUI();	
        this.add(backPatientPanel, BorderLayout.PAGE_START);
        this.add(patientPanel, BorderLayout.CENTER);

        validate();

    }// end patientMenu


    /*
	 * returnToLogin()
	 */
    private void returnToLogin(){

        getContentPane().removeAll();
        revalidate();
        repaint();

        this.add(startPanel, BorderLayout.CENTER);

       revalidate();
       repaint();

    }// end patientMenu


	/*
	 * MAIN to run entire project
	 */
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        MainGUI testGUI = new MainGUI();

    }// end main
    
}// end MainGUI