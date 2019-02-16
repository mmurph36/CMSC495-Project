/**
 * @author Kamran Lotfian
 * basic doctor class with its own unique toString method
 */
public class doctor extends person {
	int id, access_level;
	
	public doctor(String f_name, String l_name, String m_name, String user_name, String password, String dob, int age,
			int id, int access_level) {
		super(f_name, l_name, m_name, user_name, password, dob, age);
		
		this.id = id;
		this.access_level = access_level;
	}
	
	public String toString() {
		String s = "*****Doctor Details*****\n";
		s += super.toString();
		s += "\nID: " + id;
		s += "\nAccess Level: " + access_level;
		
		return s;
	}

}