package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import db.ProductDAO;
import models.Product;

@Path("/products")
public class ProductController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		try {
			ProductDAO.addOne(product);			
		} catch (HibernateException e) {
			List<String> message = new ArrayList<String>();
			message.add("an error occured!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(400).entity(entity).build();  
		}
		
		List<String> message = new ArrayList<String>();
		message.add("product successfully added!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
		return Response.status(200).entity(entity).build();  
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		List<Product> products = ProductDAO.getAll();
		if (products == null) {
			List<String> message = new ArrayList<String>();
			message.add("no product found on database!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(404).entity(entity).build();  
			
		}
		GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
		return Response.status(200).entity(entity).build();
	}
}
