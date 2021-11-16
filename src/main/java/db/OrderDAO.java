package db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Order;
import utils.Hibernate;

public class OrderDAO {
	@SuppressWarnings("unchecked")
	public static List<Order> getAll() {
		List<Order> allOrders = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		allOrders = session.createQuery("from Order").getResultList();
		transaction.commit();
		
		return allOrders;	
	}
	
	@SuppressWarnings("unchecked")
	public static List<Order> getUserOrders(String username) {
		List<Order> userOrders = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		userOrders = session.createQuery("from Order WHERE user_name = " + "'" + username + "'").getResultList();
		transaction.commit();
		
		return userOrders;	
	}
	
	public static Order getOne(int id) {
		Order order = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		order = session.get(Order.class, id);
		transaction.commit();
		
		return order;
	}
	
	public static void addOne(Order order) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		session.save(order);
		transaction.commit();	
	}

}
