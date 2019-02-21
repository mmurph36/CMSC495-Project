import java.util.ArrayList;

/**
 * @author Kamran Lotfian
 *	Main Class that houses all the functionality of the backend that will be called by the GUI
 */
public class system {
	static ArrayList<patient> p_list;
	static ArrayList<staff> d_list;

	// added constructor by eh
	public system(){
		p_list = new ArrayList<patient>();
		d_list = new ArrayList<staff>();


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
		/*
		p_list = new ArrayList<patient>();
		d_list = new ArrayList<doctor>();

		//sample patient object within the patient list
		p_list.add(new patient("Amuro", "Ray","N/A","whitedevil","password","11/4/0063",29,7979,5555, "address", "205-345-3452"));
		p_list.add(new patient("Char", "Aznable","N/A","redcomet","password","11/17/0059",33,5959,7777, "address", "205-346-3562"));
		p_list.add(new patient("Haman", "Karn","N/A","qubeley","password","1/10/0081",22,8989,8888, "address", "235-645-3294"));
		p_list.add(new patient("Judau", "Ashta","N/A","zz","password","9/10/0073",15,1432,9999, "address", "205-345-3452"));
		p_list.add(new patient("Kamille", "Bidan","N/A","zeta","password","11/11/0069",18,9376,6666, "address", "205-345-3452"));

		//sample doctor object within the doctor list
		// NOTE: EH changed doctors to add dob to doctors
		d_list.add(new doctor("Joseph", "Joestar", "N/A", "JJ", "password", "1/11/0011", 1111, 1, 80));
		d_list.add(new doctor("Jotaro", "Kujo", "N/A", "JK", "password", "2/22/0022", 2222, 1, 30));
		d_list.add(new doctor("Josuke", "Higashikata", "N/A", "JH", "password", "3/3/0033", 3333, 1, 16));
		d_list.add(new doctor("Giorno", "Giovanna", "N/A", "GG", "password", "4/4/0044", 4444, 1, 15));
		d_list.add(new doctor("Jolyne", "Cujoh", "N/A", "JC", "password", "5/5/0055", 5555, 1, 19));*/
		
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

}