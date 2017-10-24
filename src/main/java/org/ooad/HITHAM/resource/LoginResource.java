package org.ooad.HITHAM.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ooad.HITHAM.model.Login;
import org.ooad.HITHAM.service.LoginService;

@Path("validate")
public class LoginResource {

	@POST
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response loginUser(Login login)throws SQLException{
		LoginService loginservice = new LoginService();
		int returnvalue = loginservice.validateLogin(login);
		if(returnvalue == 0){
			//admin Login successful
			return Response.status(200).entity("success admin").build();
		}
		else if(returnvalue == 1){
			//teacher Login is successful
			return Response.status(200).entity("success teacher").build();
		}
		else{
			//DB ERROR
			return Response.status(500).build();
		}
	}
}
