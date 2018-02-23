import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.driver.*;

public class main {

	public static void main(String[] args) throws SQLException {
		/*
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", "kvnhan@CS", "KVNHAN");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		*/
		System.out.print("Reporting ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();    
		String[] login = input.split(" "); 
		if(login.length > 2){
			if(login[2].equals("1")){
				System.out.print("Enter Patient SSN: ");
				input = sc.nextLine();
				Patient p = new Patient();
				p.queryPatient(input);
			}else if(login[2].equals("2")){
				System.out.print("Enter Doctor ID: ");
				input = sc.nextLine();
				Doctor d = new Doctor();
				d.queryDoctor(input);
			}else if(login[2].equals("3")){
				System.out.print("Enter Admission Number: ");
				input = sc.nextLine();
				Admission a = new Admission();
				a.queryAdmission(input);
			}else{
				System.out.print("Enter Admission Number: ");
				input = sc.nextLine();
				System.out.print("Enter the new total payments: ");
				String input2 = sc.nextLine();
				Admission a = new Admission();
				a.updateAdmission(input, input2);
			}
		}else{
			System.out.println("1. Reporting Patients basic Information");
			System.out.println("2. Reporting Doctors basic Information");
			System.out.println("3. Reporting Admissions Information");
			System.out.println("4. Reporting Update Information");
		}

	}

}
