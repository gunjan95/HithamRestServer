package org.ooad.HITHAM.service;


import java.sql.ResultSet;
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

	public ResultSet fetchList(){
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from student where student_status = 'active'";
		try{
			ResultSet result =  dbconn.getStmt().executeQuery(query);
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of student");
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
		String query = "update student set student_status = 'deactivated' where student_pk = "+id;
		try {
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}

	public int update(StudentModel sm, String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String student_name = sm.getStudent_name();
		String student_id = sm.getStudent_id();
		String student_password = sm.getStudent_password();
		String student_profile = sm.getStudent_profile();
		String query = "update student set student_name ='"+student_name+"',student_id='"+student_id+"',student_password='"+student_password+"',student_profile='"+student_profile+"' where student_pk="+id;
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
