/**
 * @author Kamran Lotfian
 *	basic patient class with its own unique toString method
 */
public class patient extends person{
	int SSN, zip;
	String address, p_number;
	
	public patient(String f_name, String l_name, String m_name, String user_name, String password, String dob, int age,
			int SSN, int zip, String address, String p_number) {

		super(f_name, l_name, m_name, user_name, password, dob, age);
		
		this.SSN = SSN;
		this.zip = zip;
		this.address = address;
		this.p_number = p_number;
	}
	
	public String toString() {
		String s = "*****Patient Report*****\n";
		s += super.toString();
		s += "\nSSN: " + SSN;
		s += "\nZip Code: " + zip;
		s += "\nAddress: " + address;
		s += "\nPhone Number: " + p_number;
		
		return s;
	}
}