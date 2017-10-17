// this file consists of services related to student for getting songlist

package org.ooad.HITHAM.resource;

import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ooad.HITHAM.database.Convertor;
import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.Login;
import org.ooad.HITHAM.service.LoginService;



@Path("songlist")
public class SongList {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongList(Login login) throws Exception {
		
		LoginService loginservice = new LoginService();
		int returnvalue = loginservice.validateLogin(login);
		if(returnvalue == 0){
			//Login successful
			String id = login.getStudent_id();
			DatabaseConnection dbconn = new DatabaseConnection();
			JSONObject jso = new JSONObject();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			ResultSet rs,rs1;
			
			// query to get profile of a student
			String queryProfile = "select student_profile from student where student_id = '"+id+"'";
			rs1 = dbconn.getStmt().executeQuery(queryProfile);
			while(rs1.next()){
				jso.put("profile",rs1.getString("student_profile"));
			}	
			dbconn.getConn().close();
			
			// query to get a playlist of a user
			String query = "select * from playlist where playlist_id in (select playlist_id from student_playlist_assignment where student_id = '"+id+"')";
			dbconn.createConn();
			rs = dbconn.getStmt().executeQuery(query);
			JSONArray playlist = new JSONArray();
			
			while(rs.next()) {
				int pid =  rs.getInt("playlist_id");
				String pname = rs.getString("playlist_name");
				String query2 = "select song_id from song where song_id in ( select  song_id from recording where recording_id in( select recording_id from recording_playlist_mapping where playlist_id = "+pid+"))";
				DatabaseConnection dbconn1 = new DatabaseConnection();
				ResultSet rs2 = dbconn1.getStmt().executeQuery(query2);
				JSONArray playlist_songIDs = new JSONArray();
				while(rs2.next()) {
					playlist_songIDs.put(rs2.getInt("song_id"));
				}
				JSONObject jsob = new JSONObject();
				jsob.put("playlist_name",pname);
				jsob.put("playlist_id",pid);
				jsob.put("playlist_songIDs",playlist_songIDs);
				playlist.put(jsob);
			}
			
			jso.put("playlists",playlist);
			
			//query to get a unique song list of a user
		    String songquery = "select s.song_id, s.song_name, s.song_composer , s.song_singer, s.song_raaga , s.song_taal, s.song_url ,r.recording_id , r.recording_name, r.recording_color, r.recording_pic_url from song s,recording r where s.song_id = r.song_id and s.song_id in ( select song_id from song where song_id in ( select  song_id from recording where recording_id in( select recording_id from recording_playlist_mapping where playlist_id in (select playlist_id from student_playlist_assignment where student_id = '"+ id +"'))))";
			rs = dbconn.getStmt().executeQuery(songquery);
			jso.put("songslist",Convertor.convertToJSON(rs));
			jso.put("status", true);
			
			dbconn.getConn().close();
			return Response.ok().entity(jso.toString()).build();
			
		}
		else if(returnvalue == 1){
			//Login is NOT successful
			JSONObject jsb = new JSONObject();
			jsb.put("status", false);
			return Response.ok().entity(jsb.toString()).build();
		}
		else{
			//DB ERROR
			JSONObject jsb = new JSONObject();
			jsb.put("status", false);
			return Response.ok().entity(jsb.toString()).build();
		}
		
		
    }
	
}
