import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kamran Lotfian
 *	Main Class that houses all the functionality of the backend that will be called by the GUI
 */
public class system {
	static ArrayList<patient> p_list;
	static ArrayList<staff> d_list;
	static HashMap<String, person> date_list;
	
	// added constructor by eh
	public system(){
		p_list = new ArrayList<patient>();
		d_list = new ArrayList<staff>();
		date_list = new HashMap<String, person>();


        p_list.add(new patient("Amuro", "Ray","N/A","whitedevil","password","11/04/0063",7979,5555, "address", "Baltimore", "Maryland", "205-345-3452"));

        p_list.add(new patient("Char", "Aznable","N/A","redcomet","password","11/17/0059",5959,7777, "address", "Washington D.C.", "N/A", "205-346-3562"));

        p_list.add(new patient("Haman", "Karn","N/A","qubeley","password","1/10/0081",8989,8888, "address", "Austin", "Texas", "235-645-3294"));

        p_list.add(new patient("Judau", "Ashta","N/A","zz","password","9/10/0073",1432,9999, "address", "Boston", "Massachusetts", "205-345-3452"));

        p_list.add(new patient("Kamille", "Bidan","N/A","zeta","password","11/11/0069",9376,6666, "address", "Harrison", "Pennsylvania", "205-345-3452"));



        //sample staff object within the staff list

        // NOTE: EH changed doctors to add dob to doctors

        d_list.add(new staff("Joseph", "Joestar", "N/A", "JJ", "password", 1111, 1));

        d_list.add(new staff("Jotaro", "Kujo", "N/A", "JK", "password", 2222, 1));

        d_list.add(new staff("Josuke", "Higashikata", "N/A", "JH", "password", 3333, 1));

        d_list.add(new staff("Giorno", "Giovanna", "N/A", "GG", "password", 4444, 1));

        d_list.add(new staff("Jolyne", "Cujoh", "N/A", "JC", "password", 5555, 1));
}
	
	public static void main(String[] args) {
		system test = new system();
	}

	//will return false if patient already exists within database
	public boolean add_patient(String f_name, String l_name, String m_name, String user_name, String password, String dob,

            int SSN, int zip, String address, String city, String state, String p_number) {

		//check if the patient exists
		if(patient_exists(user_name, password)) return false;

		 p_list.add(new patient(f_name, l_name, m_name, user_name, password, dob, SSN, zip, address, city, state, p_number));		return true;
	}

	//official way to check if the doctor exists
	public boolean doctor_exists(int id) {
		for(staff d : d_list) {
			if(d.id == id) return true;
		}

		return false;
	}

	//alternate way to check if the doctor exists
	// added by EH
	public boolean doctor_exists(String user_name, String password) {
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

    public patient patient_details(String l_name, int SSN) {

        for (patient p : p_list) {

            if (p.l_name.equals(l_name) && p.SSN == SSN) return p;

        }

        return null;
    }

    public patient setPatientDetails(String user_Name, String password) {
        for (patient p : p_list) {

            if (p.user_name.equals(user_Name) && p.password.equals(password)) return p;

        }

        return null;
    }


	// added by EH
	// search for patient
	public ArrayList<patient> search_patient(String last_name, String first_name){
		
		ArrayList<patient> patients_found = new ArrayList<patient>();
		
			for(patient p: p_list){

				  if (p.l_name.equals(last_name) && p.f_name.equals(first_name)) patients_found.add(p);
			}
		return patients_found;
	}

	//add date to the date list
	public boolean add_date(String s1, String s2, person p) {
		String s = s1 + " " + s2;

		if(date_list.containsKey(s)) return false;
		date_list.put(s, p);
		
		return true;
	}
	
	//delete date from the date list
	public boolean delete_date(String s1, String s2) {
		String s = s1 + " " + s2;
		
		if(!date_list.containsKey(s1)) return false;
		date_list.remove(s);
		
		return true;
	}
}
