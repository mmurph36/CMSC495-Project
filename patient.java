/**
 * @author Kamran Lotfian
 *	basic patient class with its own unique toString method
 */
public class patient extends person{
    int SSN, zip;
    String address, p_number, city, state;

    public patient(String f_name, String l_name, String m_name, String user_name, String password, String dob,
                   int SSN, int zip, String address, String city, String state, String p_number) {

        super(f_name, l_name, m_name, user_name, password, dob);

        this.SSN = SSN;
        this.zip = zip;
        this.address = address;
        this.p_number = p_number;
        this.city = city;
        this.state = state;
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
