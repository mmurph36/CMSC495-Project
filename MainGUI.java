/* Author: Esther Ho
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class MainGUI extends JFrame{

	private system pimsSystem;
	String guiTitle;
	JPanel employeePanel, patientPanel;
	
	// "Start Panel" - login GUI variables
	JPanel startPanel = new JPanel(new BorderLayout());
	JLabel loginLabel = new JLabel("Choose Login", SwingConstants.CENTER);
	JPanel loginChoicePanel = new JPanel(new FlowLayout());
	JButton employeeButton = new JButton("Employee");
	JButton patientButton = new JButton("Patient");
	
	// Back Button to go back to Login
	JPanel backPanel = new JPanel();
	JButton backToLoginButton = new JButton("Back to \"Choose Login\" Page");
	
	
	// default constructor
	public MainGUI() {
		
		guiTitle = "PIMS";
		
		// initialize system object
		pimsSystem = new system();
		
		// sets up main GUI
		setTitle(guiTitle);
		setLayout(new BorderLayout());
		setSize(1000,600);
	
		// set up start Panel
		loginLabel.setFont(new java.awt.Font(loginLabel.getFont().getFontName(), 0, 16));
		startPanel.add(loginLabel, BorderLayout.PAGE_START);
		loginChoicePanel.add(employeeButton);
		loginChoicePanel.add(patientButton);
		startPanel.add(loginChoicePanel, BorderLayout.CENTER);
		
		// Add start panel to JFrame
		
		add(startPanel,BorderLayout.CENTER);
		
		// Add back button to back panel
		backPanel.add(backToLoginButton);
		
		validate();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // GUI appear in center
		setVisible(true);
		
		// Action Listeners
		
		employeeButton.addActionListener (e -> employeeMenu());
		patientButton.addActionListener (e -> patientMenu());
		backToLoginButton.addActionListener (e -> returnToLogin());
		
	}// end constructor
	
	/*
	 * getter for system object for Patient and Employee Panel
	 */
	public system getSystem(){
		return pimsSystem;
	}
	
	/*
	 * getter for startPanel
	 */
	public Component getStartPanel() {
		return startPanel;
	}
	
	/*
	 * 
	 */ 
	private void employeeMenu(){
		
		JOptionPane.showMessageDialog(this, "Employee Login selected", "Loading Employee Menu", JOptionPane.DEFAULT_OPTION);
		
		remove(startPanel);
		revalidate();
		repaint();
		employeePanel = new EmployeeGUI(this);
		this.add(backPanel, BorderLayout.PAGE_START);
		this.add(employeePanel, BorderLayout.CENTER);
		validate();
	
	}// end employeeMenu
	
	/*
	 * 
	 */
	private void patientMenu(){
		
		remove(startPanel);
		revalidate();
		repaint();
		
		JOptionPane.showMessageDialog(this, "creates new PatientGUI panel", "PatientGUI to be created", JOptionPane.DEFAULT_OPTION);
		
		//this.add(backPanel, BorderLayout.SOUTH);
		
		// UNCOMMENT BELOW to show PatientGUI when code added
		patientPanel = new PatientGUI(this);
		this.add(backPanel, BorderLayout.PAGE_START);
		this.add(patientPanel, BorderLayout.CENTER);
		
		
		validate();
	}// end patientMenu
	
	/*
	 * 
	 */
	void returnToLogin(){
		
		//JOptionPane.showMessageDialog(this, "Click Login Type Again", "Returning to Login", JOptionPane.DEFAULT_OPTION);
		
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
