import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admission {
	public Connection conn;

	public Admission() throws SQLException {
		this.conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", "kvnhan", "KVNHAN");;
	}
	
	public void queryAdmission(String admission) throws SQLException{
		String patientssn = null;
		Date admissiondate = null;
		String totalPayment = null;
		int doctorid;
		int roomnum;
		Date startdate;
		Date enddate;
		PreparedStatement pstmt =  conn.prepareStatement("select patientssn, admissiondate, totalpayment from admission where num = ?");
		PreparedStatement pstmt2 =  conn.prepareStatement("select roomnum, startdate, enddate from stayin where admissionnum = ?");
		PreparedStatement pstmt3 =  conn.prepareStatement("select doctorid from examine where admissionnum = ?");
		pstmt.setInt(1, Integer.valueOf(admission));
		pstmt2.setInt(1, Integer.valueOf(admission));
		pstmt3.setInt(1, Integer.valueOf(admission));
		ResultSet rset = pstmt.executeQuery();
		ResultSet rset2 = pstmt2.executeQuery();
		ResultSet rset3 = pstmt.executeQuery();
		while(rset.next()){
			patientssn = rset.getString("patientssn");
			admissiondate = rset.getDate("admissiondate");
			totalPayment = rset.getString("totalPayment");	
		}
		
		System.out.println("Admission Number: " + admission);
		System.out.println("Patient SSN: " + patientssn);
		System.out.println("Admission Date(Start Date): " + admissiondate);
		System.out.println("Total Payment: " + totalPayment);
		
		rset.close();
		pstmt.close();

		while(rset2.next()){
			roomnum = rset2.getInt("roomnum");
			startdate = rset2.getDate("startdate");
			enddate = rset2.getDate("enddate");
			
			System.out.print("Room Number: " + roomnum + "     ");
			System.out.print("From Date: " + startdate + "     ");
			System.out.println("TO Date: " + enddate);
		}
		
		rset2.close();
		pstmt2.close();

		while(rset3.next()){
			doctorid = rset3.getInt("doctorid");
		
			System.out.println("DoctorID: " + doctorid);
		}
		
		rset3.close();
		pstmt3.close();
		conn.close();
	}
	
	public void updateAdmission(String admission,String totalpayment) throws NumberFormatException, SQLException{
		
		PreparedStatement pstmt =  conn.prepareStatement("update admission set totalpayment = ? where admissionnum = ?");
		pstmt.setFloat(1, Float.valueOf(totalpayment));
		pstmt.setInt(2, Integer.valueOf(admission));
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();

	}
}
