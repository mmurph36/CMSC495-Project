/**
 * @author Kamran Lotfian
 *	The person class which is extended by both the patient and staff class
 */
public class person {
    String f_name, l_name, m_name, user_name, password, dob;

    public person(String f_name, String l_name, String m_name, String user_name, String password, String dob) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.m_name = m_name;
        this.user_name = user_name;
        this.password = password;
        this.dob = dob;
    }

    public String toString() {
        String s = "First Name: " + f_name;
        s += "\nLast Name: " + l_name;
        s += "\nMiddle Name: " + m_name;
        s += "\nDate of Birth: " + dob;

        return s;
    }
}