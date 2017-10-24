package org.ooad.HITHAM.service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.ooad.HITHAM.database.Convertor;
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

	public JSONArray fetchList(){
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from teacher where teacher_status = 'active'";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
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
		String query = "update teacher set teacher_status = 'deactivated' where teacher_pk = "+id;
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
		String query = "update teacher set teacher_name ='"+teacher_name+"',teacher_id='"+teacher_id+"',teacher_password='"+teacher_password+"' where teacher_pk="+id;
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

	public JSONArray fetchStudentList(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from student where student_status = 'active' and student_pk not in (select student_pk from teacher_student_mapping where teacher_pk = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of student mapping ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int craeteTeacherStudentMapping(String s,String id) throws SQLException {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		StringTokenizer sto = new StringTokenizer(s,",");
		String query = "";
		while(sto.hasMoreTokens()) {
			query = "insert into teacher_student_mapping (teacher_pk,student_pk) values ("+id+","+sto.nextToken()+");";
			dbconn.getStmt().executeUpdate(query);
			
		}
		if(!query.equals("")) {
			dbconn.getConn().close();
			return 1;
		}
		dbconn.getConn().close();
		return 0;
	}

	public JSONArray fetchAssignedStudentList(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from student where student_status = 'active' and student_pk in (select student_pk from teacher_student_mapping where teacher_pk = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of already assigned student mapping ");
			System.out.println(e.getMessage());
			return null;
		}
	}

}
