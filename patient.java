import java.util.ArrayList;

/**
 * @author Kamran Lotfian
 *	basic patient class with its own unique toString method
 */
public class patient extends person{
    int SSN, zip;
    String address, p_number, city, state;
    boolean policy;
    ArrayList<String> apptPaymentHistory;
    
    public patient(String f_name, String l_name, String m_name, String user_name, String password, String dob,
                   int SSN, int zip, String address, String city, String state, String p_number, boolean policy) {

        super(f_name, l_name, m_name, user_name, password, dob);

        this.SSN = SSN;
        this.zip = zip;
        this.address = address;
        this.p_number = p_number;
        this.city = city;
        this.state = state;
        this.policy = policy;
        apptPaymentHistory = new ArrayList<String>();
        apptPaymentHistory.add("Payment & Appointment History for: " + l_name + ", " + f_name);
    }

    public String toString() {
        String s = "*****Patient Report*****\n";
        s += super.toString();
        s += "\nSSN: " + SSN;
        s += "\nZip Code: " + zip;
        s += "\nAddress: " + address;
        s += "\nCity: " + city;
        s += "\nPhone Number: " + p_number;

        return s;
    }
}
