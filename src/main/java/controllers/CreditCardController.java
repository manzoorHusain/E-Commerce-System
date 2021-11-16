package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import db.CreditCardDAO;
import models.CreditCard;


@Path("/credit-cards")
public class CreditCardController {
	@POST
	@Path("/add-card")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCard(CreditCard card) {
		try {
			System.out.println(card.getCard_owner());
			CreditCardDAO.addOne(card);			
		} catch (HibernateException e) {
			List<String> message = new ArrayList<String>();
			message.add("an error occured!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(400).entity(entity).build();  
		}
		
		List<String> message = new ArrayList<String>();
		message.add("card successfully added!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {};
		return Response.status(200).entity(entity).build();  
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPayment(CreditCard card) {
		CreditCard creditCard = CreditCardDAO.getOne(card.getCard_number());
		if (creditCard == null) {
			List<String> message = new ArrayList<String>();
			message.add("credit card payment error!!");
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
			return Response.status(404).entity(entity).build();  
		}
		
		if (card.getCard_owner().equalsIgnoreCase(creditCard.getCard_owner()) && card.getCard_cvc().equalsIgnoreCase(creditCard.getCard_cvc()) && 
				card.getCard_lud().equalsIgnoreCase(creditCard.getCard_lud())) {
			try {
				Date today = new Date();
				Date lud = new SimpleDateFormat("dd/M/yyyy").parse(card.getCard_lud());
				// one day at microseconds -> 86400000
				long diff = lud.getTime() - today.getTime();
				if (diff / 86400000 >= 0) {
					List<String> message = new ArrayList<String>();
					message.add("payment successfull!!");
					GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
					return Response.status(200).entity(entity).build();  
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<String> message = new ArrayList<String>();
		message.add("credit card payment error!!");
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(message) {}; 
		return Response.status(404).entity(entity).build();
	}
}
