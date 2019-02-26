import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Kamran Lotfian
 *	Main Class that houses all the functionality of the backend that will be called by the GUI
 */

public class system {
    static ArrayList<patient> p_list;

    static ArrayList<staff> d_list;
    static HashMap<String, patient> date_list;

    static HashMap<patient, String> lookupDateMap;


    // added constructor by eh



    public system() {
        p_list = new ArrayList<>();
        d_list = new ArrayList<>();
        date_list = new HashMap<>();
        lookupDateMap = new HashMap<>();



        // First, Last, Middle (optional), username, password, DOB (MM/DD/YYYY), Last 4 SSN, Zip Code, Address, City, State (full name), Phone # (###-###-####)

        p_list.add(new patient("Amuro", "Ray", "N/A", "whitedevil", "password", "11/04/0063", 7979, 55555, "address", "Baltimore", "Maryland", "205-345-3452"));
        p_list.add(new patient("Char", "Aznable", "N/A", "redcomet", "password", "11/17/0059", 5959, 77777, "address", "Washington", "District of Columbia", "205-346-3562"));
        p_list.add(new patient("Haman", "Karn", "N/A", "qubeley", "password", "01/10/0081", 8989, 88888, "address", "Austin", "Texas", "235-645-3294"));
        p_list.add(new patient("Judau", "Ashta", "N/A", "zz", "password", "09/10/0073", 1432, 99999, "address", "Boston", "Massachusetts", "205-345-3452"));
        p_list.add(new patient("Kamille", "Bidan", "N/A", "zeta", "password", "11/11/0069", 9376, 66666, "address", "Harrison", "Pennsylvania", "205-345-3452"));

        // patients with same first and last name
        p_list.add(new patient("John", "Doe", "N/A", "alpha", "password", "11/11/1991", 1221, 11111, "address", "Seattle", "Washington", "202-232-3563"));
        p_list.add(new patient("John", "Doe", "N/A", "beta", "password", "01/22/2011", 1451, 22222, "address", "New York", "New York", "901-766-9087"));


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
        if (patient_exists(user_name, password)) return false;
        p_list.add(new patient(f_name, l_name, m_name, user_name, password, dob, SSN, zip, address, city, state, p_number));
        return true;
    }

    //official way to check if the doctor exists

    public boolean staff_exists(int id) {
        for (staff d : d_list) {
            if (d.id == id) return true;
        }
        return false;
    }

    //alternate way to check if the doctor exists
    // added by EH

    public boolean staff_exists(String user_name, String password) {

        for (staff d : d_list) {
            if (d.user_name.equals(user_name) && d.password.equals(password)) return true;
        }
        return false;
    }

    // official way to check if the patient exists

    public boolean patient_exists(String user_name, String password) {
        for (patient p : p_list) {
            if (p.user_name.equals(user_name) && p.password.equals(password)) return true;
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
    // updated by DC
    // for staff end: may not have access to a patient's username & password
    public boolean patient_exists(String first_name, String last_name, String DOB, int ssn) {
        for (patient p : p_list) {
            if (p.l_name.equals(last_name) && p.f_name.equals(first_name) && p.dob.equals(DOB) && p.SSN == ssn)
                return true;
        }
        return false;
    }

    // added by EH
    // modified by DC
    // search for patient - from Employee Menu
    // returns all patients with First & Last name

    public ArrayList<patient> search_patient(String last_name, String first_name) {
        ArrayList<patient> patients_found = new ArrayList<patient>();
        for (patient p : p_list) {
            if (p.l_name.equalsIgnoreCase(last_name) && p.f_name.equalsIgnoreCase(first_name)) patients_found.add(p);
        }
        return patients_found;
    }

    //add date to the date list

    public String add_date(String date, String time, patient p) {
        String s = date + "  " + time;

        if (lookupDateMap.containsKey(p))
            return "Sorry, You Already Have an Appointment Scheduled.";
        else if (date_list.containsKey(s))
            return "Sorry, This Time Slot Is Taken. Select Another Date or Time.";
        else {
            date_list.put(s, p);
            lookupDateMap.put(p, s);
            return "Appointment Saved";
        }
    }

    //delete date from the date list

    public boolean patient_delete_date(patient patient) {
        if (!lookupDateMap.containsKey(patient)) return false;
        String date_time = lookupDateMap.get(patient);
        date_list.remove(date_time);
        lookupDateMap.remove(patient);
        return true;
    }


    public boolean staff_delete_date(String date, String time) {
        String date_time = date + "  " + time;
        if (!date_list.containsKey(date)) return false;
        patient patient = date_list.get(date_time);
        date_list.remove(date_time);
        lookupDateMap.remove(patient);
        return true;

    }

    public patient lookUpAppointmentPatient(String date, String time) {
        String s = date + "  " + time;
        return date_list.get(s);
    }


    public String lookUpAppointmentDate(patient patient) {
        if (!lookupDateMap.containsKey(patient))
            return "";
        return lookupDateMap.get(patient);
    }
}