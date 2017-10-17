package org.ooad.HITHAM.service;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.TeacherModel;

public class TeacherService {

	public int insert(TeacherModel sm) throws SQLException{
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String teacher_name = sm.getTeacher_name();
		String teacher_id = sm.getTeacher_id();
		String teacher_password = sm.getTeacher_password();
		String query = "insert into teacher (teacher_name,teacher_id,teacher_password) values ('"+teacher_name+"','"+teacher_id+"','"+teacher_password+"')";
		System.out.println(query);
		dbconn.getStmt().executeUpdate(query);
		dbconn.getConn().close();
		return 1;
	}

	public ResultSet fetchList(){
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from teacher where teacher_status = 'active'";
		try{
			ResultSet result =  dbconn.getStmt().executeQuery(query);
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of teacher");
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String query = "update teacher set teacher_status = 'deactivated' where teacher_id = '"+id+"'";
		try {
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}

	public int update(TeacherModel sm, String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String teacher_name = sm.getTeacher_name();
		String teacher_id = sm.getTeacher_id();
		String teacher_password = sm.getTeacher_password();
		String query = "update teacher set teacher_name ='"+teacher_name+"',teacher_id='"+teacher_id+"',teacher_password='"+teacher_password+"' where teacher_id='"+id+"'";
		//System.out.println(query);
		try {
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 10;
		}
		
		return 1;
	}

}
