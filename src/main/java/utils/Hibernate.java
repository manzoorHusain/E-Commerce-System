package utils;

import models.*;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Hibernate {
	private static SessionFactory sessionFactory;
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/proje?characterEncoding=UTF-8";
	private static final String USERNAME = "seidoglan";
	private static final String PASSWORD = "seid123";
	private static final String DIALECT = "org.hibernate.dialect.MariaDBDialect";
	
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null)	return sessionFactory;
		
		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		
		properties.put(Environment.DRIVER, DRIVER);
		properties.put(Environment.URL, URL);
		properties.put(Environment.USER, USERNAME);
		properties.put(Environment.PASS, PASSWORD);
		properties.put(Environment.DIALECT, DIALECT);
		properties.put("hibernate.connection.CharSet", "utf8");
		properties.put("hibernate.connection.characterEncoding", "utf8");
		properties.put("hibernate.connection.useUnicode", "true");
		properties.put(Environment.SHOW_SQL, "true");
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		properties.put(Environment.HBM2DDL_AUTO, "update");
		properties.put("hibernate.c3p0.min_size", 10);
		properties.put("hibernate.c3p0.max_size", 20);
		properties.put("hibernate.c3p0.acquire_increment", 5);
		

		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(CreditCard.class);
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(OrderedProduct.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

}
