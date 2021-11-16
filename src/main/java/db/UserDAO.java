package db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.User;

import utils.Hibernate;

public class UserDAO {
	@SuppressWarnings("unchecked")
	public static List<User> getAll() {
		List<User> allUsers = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		allUsers = session.createQuery("from User").getResultList();
		transaction.commit();
		
		return allUsers;	
	}
	
	public static User getOne(String user_name) {
		User user = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		user = session.get(User.class, user_name);
		transaction.commit();
		
		return user;
	}
	
	public static void addOne(User user) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		session.save(user);
		transaction.commit();	
	}
	
	public static void updateOne(User user) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		session.update(user);
		transaction.commit();
	}
	
	public static void deleteOne(String user_name) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		User user = session.get(User.class, user_name);
		if (user != null) 	session.delete(user);
		transaction.commit();	
	}

}
