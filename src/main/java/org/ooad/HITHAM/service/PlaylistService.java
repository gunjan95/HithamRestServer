package org.ooad.HITHAM.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.ooad.HITHAM.database.Convertor;
import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.PlayListModel;

public class PlaylistService {

	public int createPlaylist(PlayListModel pm) throws SQLException {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String playlist_name = pm.getPlaylist_name();
		String query = "insert into playlist (playlist_name) values ('"+playlist_name+"')";
		System.out.println(query);
		dbconn.getStmt().executeUpdate(query);
		dbconn.getConn().close();
		return 1;
	}

	public  ResultSet fetchAllPlaylist() {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from playlist where playlist_status = 'active'";
		try{
			ResultSet result =  dbconn.getStmt().executeQuery(query);
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of playlist");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public int update(PlayListModel plm, String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String playlist_name = plm.getPlaylist_name();
		String query = "update playlist set playlist_name = '"+playlist_name+"' where playlist_id = "+id;
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
	
	
	public int delete(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String query = "update playlist set playlist_status = 'deactivated' where playlist_id = "+id;
		try {
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}

	public ResultSet fetchParticularPlaylist(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from playlist where playlist_status = 'active' and teacher_pk = "+id;
		try{
			ResultSet result =  dbconn.getStmt().executeQuery(query);
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of playlist");
			System.out.println(e.getMessage());
			return null;
		}
	}

	
	public int craetePlaylistRecordingMapping(String s, String id) throws SQLException {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		StringTokenizer sto = new StringTokenizer(s,",");
		String query = "";
		while(sto.hasMoreTokens()) {
			query = "insert into recording_playlist_mapping (playlist_id,recording_id) values ("+id+","+sto.nextToken()+");";
			dbconn.getStmt().executeUpdate(query);
			
		}
		if(!query.equals("")) {
			dbconn.getConn().close();
			return 1;
		}
		dbconn.getConn().close();
		return 0;
	}

	public JSONArray fetchRecordingList(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from recording where recording_status = 'active' and recording_id not in (select recording_id from recording_playlist_mapping where playlist_id = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of recording ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public JSONArray fetchAssignedRecordingList(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from recording where recording_status = 'active' and recording_id  in (select recording_id from recording_playlist_mapping where playlist_id = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of  already assigned recording ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public JSONArray fetchPlaylistAssignedToStudent(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from playlist where playlist_status = 'active' and playlist_id  in (select playlist_id from student_playlist_assignment where student_pk = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of recording ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public JSONArray fetchPlaylistAvailForStudent(String id) {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return null;
		}
		String query = "select * from playlist where playlist_status = 'active' and playlist_id not in (select playlist_id from student_playlist_assignment where student_pk = "+id+")";
		try{
			ResultSet rs =  dbconn.getStmt().executeQuery(query);
			JSONArray result = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return result;
		}
		catch(Exception e) {
			System.out.println("Exception in fetchList of recording ");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int craeteStudentPlaylistMapping(String s, String tid, String sid) throws SQLException {
		// TODO Auto-generated method stub
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		StringTokenizer sto = new StringTokenizer(s,",");
		String query = "";
		while(sto.hasMoreTokens()) {
			query = "insert into student_playlist_assignment (playlist_id,student_pk,teacher_pk) values ("+sto.nextToken()+","+sid+","+tid+");";
			dbconn.getStmt().executeUpdate(query);
			
		}
		if(!query.equals("")) {
			dbconn.getConn().close();
			return 1;
		}
		dbconn.getConn().close();
		return 0;
	}

}
