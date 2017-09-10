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
import org.ooad.HITHAM.model.SongListModel;

@Path("playlist")
public class PlayList {
	
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
		
		// to add a song to existing playlist
		@POST 
		@Path("/add")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response create(SongListModel slm) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String songlist_name = slm.getSonglist_name();
			String songlist_url = slm.getSonglist_url();
			String songlist_pic_url = slm.getSonglist_pic_url();
			String songlist_song_color = slm.getSonglist_song_color();
			songlist_url = songlist_url.replace("open","uc?export=download&");
			songlist_pic_url = songlist_pic_url.replace("open","uc?export=download&");
			String query = "insert into songlist (songlist_name,songlist_url,songlist_pic_url,songlist_song_color) values ('"+songlist_name+"','"+songlist_url+"','"+songlist_pic_url+"','"+songlist_song_color+"')";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
			return Response.status(201).entity("success").build();
		}
		
		//to display playlists available
		@GET
		@Path("/display")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response displayplaylistAvailable() throws Exception
		{
			DatabaseConnection dbconn = new DatabaseConnection();
			if(! dbconn.isStatus()){
				return Response.status(210).entity("DBError").build();
			}
			String query = "select * from playlist";
			ResultSet rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			dbconn.getConn().close();
			return Response.ok().entity(jsonarray.toString()).build();
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
