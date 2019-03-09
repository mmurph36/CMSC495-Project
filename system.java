import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Kamran Lotfian
 * Main Class that houses all the functionality of the backend that will be called by the GUI
 */

public class system {
    private ArrayList<patient> p_list;

    private ArrayList<staff> s_list;
    private HashMap<String, patient> date_list;
    private HashMap<patient, String> lookupDateMap;
    private HashMap<String, patient[]> staff_lookupAppointmentsMap;
    private HashMap<String, Double> cost_list;

    private int COPAY = 50;

    // added constructor by eh

    public system() {
        p_list = new ArrayList<>();
        s_list = new ArrayList<>();
        date_list = new HashMap<>();
        lookupDateMap = new HashMap<>();
        staff_lookupAppointmentsMap = new HashMap<>();


        cost_list = new HashMap<String, Double>();
        cost_list.put("CHECKUP", 300.0);
        cost_list.put("PHYSICAL", 250.0);
        cost_list.put("DIAGNOSTIC", 500.0);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Michael's Update to Code BEGIN

        PatientDB pull = new PatientDB();
        p_list = pull.selectAll();

        //Michael's Update to Code END
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // First, Last, Middle (optional), username, password, DOB (MM/DD/YYYY), Last 4 SSN, Zip Code, Address, City, State (full name), Phone # (###-###-####)

        /*p_list.add(new patient("Amuro", "Ray", "N/A", "whitedevil", "password", "11/04/0063", 7979, 55555, "address", "Baltimore", "Maryland", "205-345-3452", true));
        p_list.add(new patient("Char", "Aznable", "N/A", "redcomet", "password", "11/17/0059", 5959, 77777, "address", "Washington", "District of Columbia", "205-346-3562", true));
        p_list.add(new patient("Haman", "Karn", "N/A", "qubeley", "password", "01/10/0081", 8989, 88888, "address", "Austin", "Texas", "235-645-3294", true));
        p_list.add(new patient("Judau", "Ashta", "N/A", "zz", "password", "09/10/0073", 1432, 99999, "address", "Boston", "Massachusetts", "205-345-3452", false));
        p_list.add(new patient("Kamille", "Bidan", "N/A", "zeta", "password", "11/11/0069", 9376, 66666, "address", "Harrison", "Pennsylvania", "205-345-3452", false));

        // patients with same first and last name
        p_list.add(new patient("John", "Doe", "N/A", "alpha", "password", "11/11/1991", 1221, 11111, "address", "Seattle", "Washington", "202-232-3563", false));
        p_list.add(new patient("John", "Doe", "N/A", "beta", "password", "01/22/2011", 1451, 22222, "address", "New York", "New York", "901-766-9087", false));*/


        //sample staff object within the staff list
        // NOTE: EH changed doctors to add dob to doctors

        s_list.add(new staff("Joseph", "Joestar", "N/A", "JJ", "password", 1111, 1));
        s_list.add(new staff("Jotaro", "Kujo", "N/A", "JK", "password", 2222, 1));
        s_list.add(new staff("Josuke", "Higashikata", "N/A", "JH", "password", 3333, 1));
        s_list.add(new staff("Giorno", "Giovanna", "N/A", "GG", "password", 4444, 1));
        s_list.add(new staff("Jolyne", "Cujoh", "N/A", "JC", "password", 5555, 1));

    }

    public static void main(String[] args) {
        system test = new system();
    }

    //will return false if patient already exists within database

    public boolean add_patient(String f_name, String l_name, String m_name, String user_name, String password, String dob,
                               int SSN, int zip, String address, String city, String state, String p_number, boolean policy) {

        PatientDB insert = new PatientDB();//Michael's Update to Code
        //check if the patient exists
        if (patient_exists(user_name, password)) return false;
        p_list.add(new patient(f_name, l_name, m_name, user_name, password, dob, SSN, zip, address, city, state, p_number, policy));
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Michael's Update to Code BEGIN   
    
        System.out.println("add patient "+l_name+" ssn " +SSN+ " user " + user_name+ " password "+password);
        insert.insertInto(f_name, l_name, m_name, user_name, password, dob, SSN, zip, address, city, state, p_number, policy); 
        
        //Michael's Update to Code END
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        return true;
    }

    //official way to check if the doctor exists

    public boolean staff_exists(int id) {
        for (staff s : s_list) {
            if (s.getId() == id) return true;
        }
        return false;
    }

    //alternate way to check if the doctor exists
    // added by EH

    public boolean staff_exists(String user_name, String password) {

        for (staff s : s_list) {
            if (s.getUser_name().equals(user_name) && s.getPassword().equals(password)) return true;
        }
        return false;
    }

    // official way to check if the patient exists

    public boolean patient_exists(String user_name, String password) {
        for (patient p : p_list) {

            if (p.getUser_name().equals(user_name) && p.getPassword().equals(password)) return true;

        }
        return false;
    }


    //get the patients details


    public patient patient_details(String l_name, int SSN) {
        for (patient p : p_list) {
            if (p.getL_name().equals(l_name) && p.getSSN() == SSN) return p;
        }
        return null;
    }


    public patient setPatientDetails(String user_Name, String password) {
        for (patient p : p_list) {
            if (p.getUser_name().equals(user_Name) && p.getPassword().equals(password)) return p;

        }

        return null;
    }


    // added by EH
    // updated by DC
    // for staff end: may not have access to a patient's username & password
    public boolean patient_exists(String first_name, String last_name, String DOB, int ssn) {
        for (patient p : p_list) {
            if (p.getL_name().equals(last_name) && p.getF_name().equals(first_name) && p.getDob().equals(DOB) && p.getSSN() == ssn)
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
            //if (p.l_name.equalsIgnoreCase(last_name) && p.f_name.equalsIgnoreCase(first_name)) patients_found.add(p);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Michael's Update to Code BEGIN

            System.out.println("p = "+p.getL_name()+ "   and  last_name = "+last_name);
            System.out.println("p = "+p.getF_name()+ "   and  First_name = "+first_name);

            String pL = p.getL_name();
            String pF = p.getF_name();
            boolean containsL = p.getL_name().toLowerCase().contains(last_name.toLowerCase());
            boolean containsF = p.getF_name().toLowerCase().contains(first_name.toLowerCase());
            System.out.println("This : " + containsL + " and this: " + containsF);

            if (containsL && containsF) {
                System.out.println("TRUE");
            }else{
                System.out.println("FALSE" + pL + " versus " + last_name);
            }
            if (containsL && containsF) patients_found.add(p);

            //Michael's Update to Code END
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            addDateToStaffCalendar(date, time, p);
            return "Appointment Saved";
        }
    }

    // adds date to staff calendar
    private void addDateToStaffCalendar(String date, String time, patient patient) {
        if (!staff_lookupAppointmentsMap.containsKey(date)) {
            patient[] times = new patient[8];
            staff_lookupAppointmentsMap.put(date, times);
        }
        patient[] tempArray = staff_lookupAppointmentsMap.get(date);

        switch (time) {
            case "9:00am":
                tempArray[0] = patient;
                break;
            case "10:00am":
                tempArray[1] = patient;
                break;
            case "11:00am":
                tempArray[2] = patient;
                break;
            case "12:00pm":
                tempArray[3] = patient;
                break;
            case "1:00pm":
                tempArray[4] = patient;
                break;
            case "2:00pm":
                tempArray[5] = patient;
                break;
            case "3:00pm":
                tempArray[6] = patient;
                break;
            case "4:00pm":
                tempArray[7] = patient;
                break;
        }
    }

    //delete date from the date list

    public boolean patient_delete_date(patient patient) {
        if (!lookupDateMap.containsKey(patient)) return false;
        String date_time = lookupDateMap.get(patient);
        date_list.remove(date_time);
        lookupDateMap.remove(patient);

        String date = "";
        String time = "";
        int i = 0;
        while (true) {
            if (date_time.charAt(i) != ' ') {
                date += date_time.charAt(i);
                i++;
            } else break;
        }
        while (i < date_time.length()) {
            time += date_time.charAt(i);
            i++;
        }
        deleteDateFromStaffCalendar(date, time, patient);
        return true;
    }

    // deletes date from staff calendar
    private void deleteDateFromStaffCalendar(String date, String time, patient patient) {
        if (!staff_lookupAppointmentsMap.containsKey(date)) {
            JOptionPane.showMessageDialog(null, "Error");
        } else {
            patient[] tempArray = staff_lookupAppointmentsMap.get(date);

            switch (time) {
                case "9:00am":
                    tempArray[0] = patient;
                    break;
                case "10:00am":
                    tempArray[1] = patient;
                    break;
                case "11:00am":
                    tempArray[2] = patient;
                    break;
                case "12:00pm":
                    tempArray[3] = patient;
                    break;
                case "1:00pm":
                    tempArray[4] = patient;
                    break;
                case "2:00pm":
                    tempArray[5] = patient;
                    break;
                case "3:00pm":
                    tempArray[6] = patient;
                    break;
                case "4:00pm":
                    tempArray[7] = patient;
                    break;
            }
        }
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

    //appointment charge
    public double charge(patient p, String charge) {
    	/*
        if (p.policy) {
            return cost_list.get(charge) - COPAY;
        } else {
            return cost_list.get(charge);
        } */

        if (p.isPolicy()) {
            return COPAY;
        } else {
            return cost_list.get(charge);
        }
    }

    public boolean recordApptPayment(patient patient, String charge) {
    	
        String appt = lookUpAppointmentDate(patient);
        if (patient != null & !appt.equals("")) {
            patient.getApptPaymentHistory().add("Paid: " + charge + " for the appointment on: " + appt);
            return true;
        } else return false;
    }

    public HashMap<String, patient[]> getStaff_lookupAppointmentsMap() {
        return staff_lookupAppointmentsMap;
    }
}
