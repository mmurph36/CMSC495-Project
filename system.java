import java.util.ArrayList;

/**
 * @author Kamran Lotfian
 *	Main Class that houses all the functionality of the backend that will be called by the GUI
 */
public class system {
	static ArrayList<patient> p_list;
	static ArrayList<staff> d_list;

	public static void main(String[] args) {
		p_list = new ArrayList<patient>();
		d_list = new ArrayList<staff>();

		//sample patient object within the patient list
		p_list.add(new patient("Amuro", "Ray","N/A","whitedevil","password","11/4/0063",29,7979,5555, "address", "205-345-3452"));
		p_list.add(new patient("Char", "Aznable","N/A","redcomet","password","11/17/0059",33,5959,7777, "address", "205-346-3562"));
		p_list.add(new patient("Haman", "Karn","N/A","qubeley","password","1/10/0081",22,8989,8888, "address", "235-645-3294"));
		p_list.add(new patient("Judau", "Ashta","N/A","zz","password","9/10/0073",15,1432,9999, "address", "205-345-3452"));
		p_list.add(new patient("Kamille", "Bidan","N/A","zeta","password","11/11/0069",18,9376,6666, "address", "205-345-3452"));

		//sample doctor object within the doctor list
		d_list.add(new staff("Joseph", "Joestar", "N/A", "JJ", "password", 1111, 1, 80));
		d_list.add(new staff("Jotaro", "Kujo", "N/A", "JK", "password", 2222, 1, 30));
		d_list.add(new staff("Josuke", "Higashikata", "N/A", "JH", "password", 3333, 1, 16));
		d_list.add(new staff("Giorno", "Giovanna", "N/A", "GG", "password", 4444, 1, 15));
		d_list.add(new staff("Jolyne", "Cujoh", "N/A", "JC", "password", 5555, 1, 19));
	}

	//will return false if patient already exists within database
	public boolean add_patient(String f_name, String l_name, String m_name, String user_name, String password, String dob, int age,
			int SSN, int zip, String address, String p_number) {

		//check if the patient exists
		if(patient_exists(user_name, password)) return false;

		p_list.add(new patient(f_name, l_name, m_name, user_name, password, dob, age, SSN, zip, address, p_number));
		return true;
	}

	//official way to check if the doctor exists
	public boolean staff_exists(int id) {
		for(staff d : d_list) {
			if(d.id == id) return true;
		}

		return false;
	}

	//alternate way to check if the doctor exists
	// added by EH
	public boolean staff_exists(String user_name, String password) {
		for(staff d : d_list) {
			if(d.user_name.equals(user_name) && d.password.equals(password)) return true;
		}

		return false;
	}

	//official way to check if the patient exists
	public boolean patient_exists(String user_name, String password) {
		for(patient p : p_list) {
			if(p.user_name.equals(user_name) && p.password.equals(password)) return true;
		}

		return false;
	}

	//get the patients details
	public patient patient_details(String user_name, String password) {
		if(!patient_exists(user_name, password)) return null;//"Patient does not exist within the database.";

		for(patient p : p_list) {
			if(p.user_name.equals(user_name) && p.password.equals(password)) return p;
		}

		return null;
	}




}
