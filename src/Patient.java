import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
	public Connection conn;
	
	public Patient() throws SQLException {
		this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", "kvnhan", "KVNHAN");;
	}
	
	public void queryPatient(String ssn) throws SQLException{
		String firstname = null;
		String lastname = null;
		String address = null;
		int tele = 0;
		PreparedStatement pstmt =  conn.prepareStatement("select * from patient where ssn = ?");
		pstmt.setString(1, ssn);
		ResultSet rset = pstmt.executeQuery();
		while(rset.next()){
			firstname = rset.getString("firstname");
			lastname = rset.getString("lastname");
			address = rset.getString("address");
			tele = rset.getInt("telenum");
		}
		
		System.out.println("Patient SSN: " + ssn);
		System.out.println("Patient First Name: " + firstname);
		System.out.println("Patient Last Name: " + lastname);
		System.out.println("Patient Address: " + address);
		System.out.println("Patient Telephone Number: " + tele);
		
		rset.close();
		pstmt.close();
		conn.close();
		
	}

}
