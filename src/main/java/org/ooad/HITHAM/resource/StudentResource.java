/*
 * this is a student resource. It contains methods for creating student account,
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
import org.ooad.HITHAM.model.StudentModel;
import org.ooad.HITHAM.service.LoginService;
import org.ooad.HITHAM.service.StudentService;

@Path("student")
public class StudentResource {
	
/*
 * this method is for student login authentication only.(not used till demo 1)
 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(Login login)throws SQLException{
		LoginService loginservice = new LoginService();
		int returnvalue = loginservice.validateLogin(login);
		if(returnvalue == 0){
			//Login successful
			return Response.ok().entity("success").build();
		}
		else if(returnvalue == 1){
			//Login is NOT successful
			return Response.status(204).build();
		}
		else{
			//DB ERROR
			return Response.status(500).build();
		}
	}

/*
 *  this is to create student account. (used in demo 1)
 */
	@POST
	@Path("/createstudent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudent(StudentModel sm)throws SQLException{
		StudentService studentservice = new StudentService();
		int returnvalue = studentservice.insert(sm);
		if(returnvalue == 1)
			return Response.status(201).entity("success").build();
		else
			return Response.status(500).build();
	}
	
/*
 *  this method is to show student list.
 */
	@GET
	@Path("/fetchall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchStudentList(){
		StudentService studentservice = new StudentService();
		ResultSet  result= studentservice.fetchList();
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
 * This method is for deleting student. change status to deactivated.
 */
	@POST
	@Path("/delete/{student_id}")
	public Response deleteStudent(@PathParam("student_id") String id) {
		StudentService studentservice = new StudentService();
		int returnvalue = studentservice.delete(id);
		if(returnvalue == 1)
			return Response.ok().entity("success").build();
		else
			return Response.status(500).build();
	}
	



/*
 * This method is for Editing student. change status to deactivated.
 */
	@POST
	@Path("/edit/{student_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudent(StudentModel sm,@PathParam("student_id") String id) {
		StudentService studentservice = new StudentService();
		int returnvalue = studentservice.update(sm,id);
		if(returnvalue == 1)
			return Response.ok().entity("success").build();
		else
			return Response.status(500).build();
	}
	
}
