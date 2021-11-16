package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import db.UserDAO;
import models.User;

@Path("/operations-on-users")
public class UserController {
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		List<String> message = new ArrayList<String>();
		// find given user by unique username
		User myUser = UserDAO.getOne(user.getUser_name());
		// if we found the user
		if (myUser != null) {
			// if the passwords match
			if (myUser.getUser_password().equals(user.getUser_password())) {
				// successfull login
				message.add(String.valueOf(myUser.getUser_is_admin()));
				GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
				return Response.status(200).entity(entity).build();
			}
		}
		
		// there is an error.
		message.add("An error occured!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
		return Response.status(401).entity(entity).build();
	}
		
	@POST
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(User user) {
		List<String> message = new ArrayList<String>();
		// find given user by unique username
		User newUser = UserDAO.getOne(user.getUser_name());
		// if we do not found the user (username is unique)
		if (newUser == null) {
			UserDAO.addOne(user);
			message.add("Signup Successfull!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(200).entity(entity).build();
		}
		
		// there is an error.
		message.add("An error occured!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
		return Response.status(401).entity(entity).build();
	}
}