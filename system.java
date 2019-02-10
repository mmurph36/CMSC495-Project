import java.util.ArrayList;

/**
 * @author Kamran Lotfian
 *	Main Class that houses all the functionality of the backend that will be called by the GUI
 */
public class system {
	static ArrayList<patient> p_list;
	static ArrayList<doctor> d_list;
	
	public static void main(String[] args) {
		p_list = new ArrayList<patient>();
		d_list = new ArrayList<doctor>();
		
		//sample patient object within the patient list
		p_list.add(new patient("a", "a","a","a","a","a",5,6,5555, "a", "a"));
		
	}
	
	//will return false if patient already exists within database
	public boolean add_patient(String f_name, String l_name, String m_name, String user_name, String password, String dob, int age,
			int SSN, int zip, String address, String p_number) {
		
		//check if the patient exists
		if(patient_exists(SSN)) return false;
		
		p_list.add(new patient(f_name, l_name, m_name, user_name, password, dob, age, SSN, zip, address, p_number));
		return true;
	}
	
	//official way to check if the patient exists
	public boolean patient_exists(int SSN) {
		for(patient p : p_list) {
			if(p.SSN == SSN) return true;
		}
		
		return false;
	}
	
	//get the patients details
	public String patient_details(int SSN) {
		if(patient_exists(SSN)) return "Patient does not exist within the database.";
		
		for(patient p : p_list) {
			if(p.SSN == SSN) return p.toString();
		}
		
		return "";
	}
	
	
	
	
}
