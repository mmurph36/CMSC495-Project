import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sqlitetutorial.net
 */
public class PatientDB {

    /**
     * Connect to the test.db database
     * @return the Connection object
     */

    private String sql = "SELECT l_name, f_name, m_name, user_name, password, dob, SSN, zip, address, city, state, p_number, policy" +
            "       FROM patientinfo";

    private ArrayList<patient> pat_list;

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/estherho/git/CMSC495-Project/src/patients.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the table
     */
    public ArrayList<patient> selectAll(){

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            pat_list = new ArrayList<>();
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("l_name") +  "\t" +
                        rs.getString("f_name") + "\t" +
                        rs.getString("m_name"));

                pat_list.add(new patient(rs.getString("f_name"), rs.getString("l_name"),
                        rs.getString("m_name"), rs.getString("user_name"),
                        rs.getString("password"), rs.getString("dob"),
                        rs.getInt("SSN"),rs.getInt("zip"),rs.getString("address"),
                        rs.getString("city"),rs.getString("state"),rs.getString("p_number"),
                        rs.getBoolean("policy")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat_list;
    }
}