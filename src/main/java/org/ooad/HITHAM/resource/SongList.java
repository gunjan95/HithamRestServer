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
		if(! dbconn.isStatus()){
			return Response.status(210).entity("DBError").build();
		}
		ResultSet rs;
		String query = "select * from playlist where playlist_id in (select playlist_id from student_song_assignment where student_id = "+id+")";
		rs = dbconn.getStmt().executeQuery(query);
		JSONArray jsonarray = new JSONArray();
		while(rs.next()) {
			int pid =  rs.getInt("playlist_id");
			String pname = rs.getString("playlist_name");
			String query2 = "select * from songlist where songlist_playlist = "+pid;
			DatabaseConnection dbconn1 = new DatabaseConnection();
			ResultSet rs1 = dbconn1.getStmt().executeQuery(query2);
			JSONObject jsob = new JSONObject();
			jsob.put("playlist_name",pname);
			jsob.put("playlist_id",pid);
			jsob.put("songArray",Convertor.convertToJSON(rs1));
			jsonarray.put(jsob);
			
		}
		dbconn.getConn().close();
		return Response.ok().entity(jsonarray.toString()).build();
    }
	
}
