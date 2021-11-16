package db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Product;

import utils.Hibernate;

public class ProductDAO {
	@SuppressWarnings("unchecked")
	public static List<Product> getAll() {
		List<Product> allProducts = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		allProducts = session.createQuery("from Product").getResultList();
		transaction.commit();
		
		return allProducts;	
	}
	
	public static Product getOne(int id) {
		Product product = null;
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		product = session.get(Product.class, id);
		transaction.commit();
		
		return product;
	}
	
	public static void addOne(Product product) {
		Transaction transaction = null;
		
		Session session = Hibernate.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		
		session.save(product);
		transaction.commit();	
	}

}
