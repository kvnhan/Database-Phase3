import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	public Connection conn;

	public Doctor() throws SQLException {
		this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", "kvnhan", "KVNHAN");;
	}
	
	public void queryDoctor(String id) throws SQLException{
		String firstname = null;
		String lastname = null;
		String gender = null;
		String specialty = null;
		PreparedStatement pstmt =  conn.prepareStatement("select * from doctor where id = ?");
		pstmt.setInt(1, Integer.valueOf(id));
		ResultSet rset = pstmt.executeQuery();
		while(rset.next()){
			firstname = rset.getString("firstname");
			lastname = rset.getString("lastname");
			gender = rset.getString("gender");
			specialty = rset.getString("specialty");
			
		}
		
		System.out.println("Doctor ID: " + id);
		System.out.println("Doctor First Name: " + firstname);
		System.out.println("Doctor Last Name: " + lastname);
		System.out.println("Doctor Gender: " + gender);
		System.out.println("Doctor Specialty: " + specialty);
		
		rset.close();
		pstmt.close();
		conn.close();
	}

}
