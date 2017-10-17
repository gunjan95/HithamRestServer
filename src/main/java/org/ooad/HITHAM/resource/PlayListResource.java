// this file consists of services related to  teacher
package org.ooad.HITHAM.resource;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.ooad.HITHAM.database.Convertor;
import org.ooad.HITHAM.database.DatabaseConnection;
import org.ooad.HITHAM.model.PlayListModel;

@Path("playlist")
public class PlayListResource {
	
		// To create a new playlist
		@POST 
		@Path("/create")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response create(PlayListModel pm) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String playlist_name = pm.getPlaylist_name();
			String query = "insert into playlist (playlist_name) values ('"+playlist_name+"')";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();
		}
		
		
		
		//to display playlists available
		@GET
		@Path("/fetchall")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response displayplaylistAvailable() throws Exception
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String query = "select * from playlist where playlist_status = 'active'";
			ResultSet rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			System.out.println(jsonarray.toString());
			dbconn.getConn().close();
			return Response.ok().entity(jsonarray.toString()).build();
		}
		
		
		
		/*
		 *  Update the existing playlist
		 */
		
		@POST 
		@Path("/edit/{playlist_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response update(PlayListModel plm,@PathParam("song_id") String id) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String playlist_name = plm.getPlaylist_name();
			
			String query = "update song set playlist_name = '"+playlist_name+"' where playlist_id = '"+id+"'";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();
		}
		
		/*
		 * Delete the playlist.
		 */
		
		@POST
		@Path("/delete/{playlist_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response delete(PlayListModel pm,@PathParam("playlist_id") String id) throws SQLException {
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String query = "update playlist set playlist_status = 'deactivated' where playlist_id = '"+id+"'";
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@GET
		@Path("/displayplaylist/{student_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response displayplaylist(@PathParam("student_id") String id) throws Exception
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String query = "select * from playlist where playlist_id not in( select playlist_id from student_song_assignment where student_id = '"+id+"')";
			ResultSet rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return Response.ok().entity(jsonarray.toString()).build();
		}
		

		//to assign playlist to a student
		@POST 
		@Path("/assign/{student_id}/{playlist_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response assign(@PathParam("student_id") String sid,@PathParam("playlist_id") String playlist_id) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			
			String query = "insert into student_song_assignment (student_id,playlist_id) values ('"+sid+"',"+playlist_id+")";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();
		}
		
		
		@POST 
		@Path("/songassign/{songlist_id}/{playlist_id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response song_play_assign(@PathParam("songlist_id") String songid,@PathParam("playlist_id") String playlist_id) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			
			String query = "insert into songlist_playlist_mapping (songlist_id,playlist_id) values ("+songid+","+playlist_id+")";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();
		}
		
		// get all songs
		@GET
		@Path("/displaysong/{playlist_id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response displaysonglist(@PathParam("playlist_id") String playlist_id) throws Exception
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String query = "select * from songlist where songlist_id not in( select songlist_id from songlist_playlist_mapping where playlist_id = "+playlist_id+")";
			ResultSet rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return Response.ok().entity(jsonarray.toString()).build();
		}
		
		
}
