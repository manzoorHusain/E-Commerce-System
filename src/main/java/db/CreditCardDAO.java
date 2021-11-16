package db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.CreditCard;

import utils.Hibernate;

public class CreditCardDAO {	
	public static CreditCard getOne(String card_number) {
		CreditCard creditCard = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		creditCard = session.get(CreditCard.class, card_number);
		transaction.commit();
		
		return creditCard;
	}
	
	public static void addOne(CreditCard creditCard) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		session.save(creditCard);
		transaction.commit();	
	}

}
