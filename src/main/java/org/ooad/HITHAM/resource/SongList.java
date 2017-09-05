package org.ooad.HITHAM.resource;

import java.sql.ResultSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
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
		String query = "select * from songlist where songlist_id in (select songlist_id from student_song_assignment where student_id = "+id+")";
		rs = dbconn.getStmt().executeQuery(query);
		JSONArray jsonarray = Convertor.convertToJSON(rs);
		dbconn.getConn().close();
		return Response.status(201).entity(jsonarray.toString()).build();
    }
	
}
