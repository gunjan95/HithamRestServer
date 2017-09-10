// this file consists of services related to student for getting songlist

package org.ooad.HITHAM.resource;

import java.sql.ResultSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ooad.HITHAM.database.Convertor;
import org.ooad.HITHAM.database.DatabaseConnection;



@Path("songlist")
public class SongList {

	@GET
	@Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSongList(@PathParam("studentId") String id) throws Exception {
       
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
		String query = "select * from playlist where playlist_id in (select playlist_id from student_song_assignment where student_id = '"+id+"')";
		dbconn.createConn();
		rs = dbconn.getStmt().executeQuery(query);
		JSONArray playlist = new JSONArray();
		
		while(rs.next()) {
			int pid =  rs.getInt("playlist_id");
			String pname = rs.getString("playlist_name");
			String query2 = "select songlist_id from songlist where songlist_id in ( select  songlist_id from songlist_playlist_mapping where playlist_id = "+pid+")";
			DatabaseConnection dbconn1 = new DatabaseConnection();
			ResultSet rs2 = dbconn1.getStmt().executeQuery(query2);
			JSONArray playlist_songIDs = new JSONArray();
			while(rs2.next()) {
				playlist_songIDs.put(rs2.getInt("songlist_id"));
			}
			JSONObject jsob = new JSONObject();
			jsob.put("playlist_name",pname);
			jsob.put("playlist_id",pid);
			jsob.put("playlist_songIDs",playlist_songIDs);
			playlist.put(jsob);
		}
		
		jso.put("playlists",playlist);
		
		//query to get a unique song list of a user
	    String songquery = "select * from songlist where songlist_id in ( select  songlist_id from songlist_playlist_mapping where playlist_id in (select playlist_id from student_song_assignment where student_id = '"+ id +"'))";
		rs = dbconn.getStmt().executeQuery(songquery);
		jso.put("songslist",Convertor.convertToJSON(rs));
		
		
		dbconn.getConn().close();
		return Response.ok().entity(jso.toString()).build();
    }
	
}
