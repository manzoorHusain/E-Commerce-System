package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import db.OrderDAO;
import models.Order;

@Path("/orders")
public class OrderController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(Order order) {
		try {
			OrderDAO.addOne(order);			
		} catch (HibernateException e) {
			e.printStackTrace();
			List<String> message = new ArrayList<String>();
			message.add("an error occured!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(400).entity(entity).build();  
		}
		
		List<String> message = new ArrayList<String>();
		message.add("order successfully added!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
		return Response.status(200).entity(entity).build();  
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrders() {
		List<Order> orders = OrderDAO.getAll();
		if (orders == null) {
			List<String> message = new ArrayList<String>();
			message.add("no order found on database!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(404).entity(entity).build();  
			
		}
		GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(orders) {};
		return Response.status(200).entity(entity).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder(@PathParam("id") int id) {
		Order order = OrderDAO.getOne(id);
		if (order == null) {
			List<String> message = new ArrayList<String>();
			message.add("no order found on database!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(404).entity(entity).build();  
			
		}
		GenericEntity<Order> entity = new GenericEntity<Order>(order) {};
		return Response.status(200).entity(entity).build();
	}
	
	@GET
	@Path("/user/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserOrders(@PathParam("username") String username) {
		List<Order> orders = OrderDAO.getUserOrders(username);
		if (orders == null) {
			List<String> message = new ArrayList<String>();
			message.add("no order found on database!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(404).entity(entity).build();  
			
		}
		GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(orders) {};
		return Response.status(200).entity(entity).build();
	}
}
