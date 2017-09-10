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

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class LoginSignUp {
	
	@POST
	public Response loginUser(Login login) throws SQLException{
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

}
