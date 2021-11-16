package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {	
	@Id
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="user_password")
	private String user_password;
	
	@Column(name="user_is_admin", columnDefinition = "TINYINT(1)")
	private Boolean user_is_admin;
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order_user")
	private List<Order> user_orders = new ArrayList<>();

	public User() { }
	
	public User(String user_name) {
		setUser_name(user_name);
	}

	public User(String user_name, String user_email, String user_password, Boolean user_is_admin) {
		setUser_name(user_name);
		setUser_email(user_email);
		setUser_password(user_password);
		setUser_is_admin(user_is_admin);
	}
	
	public void addOrders(Order order) {
		order.setOrder_user(this);
		user_orders.add(order);
	}
	
	public void removeOrders(Order order) {
		user_orders.remove(order);
		order.setOrder_user(null);
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_password() {
		return user_password;
	}


	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public Boolean getUser_is_admin() {
		return user_is_admin;
	}


	public void setUser_is_admin(Boolean user_is_admin) {
		this.user_is_admin = user_is_admin;
	}

	public List<Order> getUser_orders() {
		return user_orders;
	}

	public void setUser_orders(List<Order> user_orders) {
		this.user_orders = user_orders;
	}
}
