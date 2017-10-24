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
		String userid = login.getusername();
		String password = login.getpassword();
		String query = "select * from admin where admin_username ='"+userid+"' and admin_password = '"+password+"'";
		System.out.println(query);
		rs = dbconn.getStmt().executeQuery(query);
		if(rs.next()) {
			return 0;
		}
		else
		{
			
			String query1 = "select * from teacher where teacher_id ='"+userid+"' and teacher_password = '"+password+"'";
			ResultSet rs1 = dbconn.getStmt().executeQuery(query1);
			if(rs1.next()){
				return 1;
			}
		}
		dbconn.getConn().close();
		return 2;
		
	}

}
