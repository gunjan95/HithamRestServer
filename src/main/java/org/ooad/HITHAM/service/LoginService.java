package org.ooad.HITHAM.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.Login;

public class LoginService {

	public int validateLogin(Login login) throws SQLException {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		ResultSet rs;
		String userid = login.getStudent_id();
		String password = login.getStudent_password();
		String query = "select * from student where student_id ='"+userid+"' and student_password = '"+password+"'";
		rs = dbconn.getStmt().executeQuery(query);
		if(rs.next()) {
			return 0;
		}
		else
			return 1;
		
	}
	

}
