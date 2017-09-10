package org.ooad.HITHAM.service;

import java.sql.SQLException;

import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.StudentModel;

public class StudentService {

	public int insert(StudentModel sm) throws SQLException{
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String student_name = sm.getStudent_name();
		String student_id = sm.getStudent_id();
		String student_password = sm.getStudent_password();
		String student_profile = sm.getStudent_profile();
		String query = "insert into student (student_name,student_id,student_password,student_profile) values ('"+student_name+"','"+student_id+"','"+student_password+"','"+student_profile+"')";
		System.out.println(query);
		dbconn.getStmt().executeUpdate(query);
		dbconn.getConn().close();
		return 1;
	}

}
