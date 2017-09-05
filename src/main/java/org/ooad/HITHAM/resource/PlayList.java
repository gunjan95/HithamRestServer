package org.ooad.HITHAM.resource;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
			String query = "insert into playlist (playlist_name) values ('"+playlist_name+"') ";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			return Response.status(201).entity("success").build();
		}
		
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
			int songlist_playlist = slm.getSonglist_playlist();
			String query = "insert into songlist (songlist_name,songlist_url,songlist_pic_url,songlist_song_color,songlist_playlist) values ('"+songlist_name+"','"+songlist_url+"','"+songlist_pic_url+"','"+songlist_song_color+"',"+songlist_playlist+")";
			System.out.println(query);
			dbconn.getStmt().executeUpdate(query);
			return Response.status(201).entity("success").build();
		}
		
		@GET
		@Path("/display")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response displayplaylist() throws Exception
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
		
}
