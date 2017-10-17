/*
 * this is a Teacher resource. It contains methods for creating Teacher account,
 * editing and deleting it.
 * 
 * 
 */

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

import org.ooad.HITHAM.database.Convertor;
import org.ooad.HITHAM.model.Login;
import org.ooad.HITHAM.model.TeacherModel;
import org.ooad.HITHAM.service.TeacherService;

@Path("teacher")
public class TeacherResource {
	
/*
 * this method is for Teacher login authentication only.
 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(Login login)throws SQLException{
		// please write code here
		return Response.ok().build();
	}

/*
 *  this is to create Teacher account. 
 */
	@POST
	@Path("/createteacher")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTeacher(TeacherModel sm)throws SQLException{
		TeacherService Teacherservice = new TeacherService();
		int returnvalue = Teacherservice.insert(sm);
		if(returnvalue == 1)
			return Response.status(201).entity("success").build();
		else
			return Response.status(500).build();
	}
	
/*
 *  this method is to show Teacher list.
 */
	@GET
	@Path("/fetchall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchTeacherList(){
		TeacherService Teacherservice = new TeacherService();
		ResultSet  result= Teacherservice.fetchList();
		if(result != null)
			try {
				return Response.status(201).entity((Convertor.convertToJSON(result)).toString()).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			return Response.status(500).build();
		
		return Response.status(500).build();
	}
	
/*
 * This method is for deleting Teacher. change status to deactivated.
 */
	@POST
	@Path("/delete/{Teacher_id}")
	public Response deleteTeacher(@PathParam("Teacher_id") String id) {
		TeacherService Teacherservice = new TeacherService();
		int returnvalue = Teacherservice.delete(id);
		if(returnvalue == 1)
			return Response.ok().entity("success").build();
		else
			return Response.status(500).build();
	}
	



/*
 * This method is for deleting Teacher. change status to deactivated.
 */
	@POST
	@Path("/edit/{Teacher_id}")
	public Response updateTeacher(TeacherModel sm,@PathParam("Teacher_id") String id) {
		TeacherService Teacherservice = new TeacherService();
		int returnvalue = Teacherservice.update(sm,id);
		if(returnvalue == 1)
			return Response.ok().entity("success").build();
		else
			return Response.status(500).build();
	}
	
}

