package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import db.ProductDAO;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int order_id;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="user_name")
	private User order_user;
	
	@ManyToOne(targetEntity = CreditCard.class, fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCard order_card;
	
	@Column(name="order_price")
	private Double order_price;

	@OneToMany(targetEntity = OrderedProduct.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private List<OrderedProduct> order_orderedProducts= new ArrayList<>();
	
	public Order() { }
	
	public Order(User orderUser, CreditCard orderCard, Double orderPrice) {
		setOrder_user(orderUser);
		setOrder_card(orderCard);
		setOrder_price(orderPrice);
	}
	
	public void addOrderedProduct(OrderedProduct orderedProduct) {
		order_orderedProducts.add(orderedProduct);
	}
	
	public void removeOrderedProduct(OrderedProduct orderedProduct) {
		order_orderedProducts.remove(orderedProduct);
	}
	
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getOrder_user() {
		return order_user.getUser_name() + "/" + order_user.getUser_email();
	}

	public void setOrder_user(User order_user) {
		this.order_user = order_user;
	}

	public String getOrder_card() {
		return order_card.getCard_number() + "/" + order_card.getCard_owner();
	}

	public void setOrder_card(CreditCard order_card) {
		this.order_card = order_card;
	}

	public Double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	public List<String> getOrder_orderedProducts() {
		List<String> response = new ArrayList<>();
		for (OrderedProduct orderedProduct: order_orderedProducts) {
			Product product = ProductDAO.getOne(orderedProduct.getOrdered_product_product());
			response.add(product.getProduct_name());
			response.add(String.valueOf(product.getProduct_price() * orderedProduct.getQuantity()));
			response.add(String.valueOf(orderedProduct.getQuantity()));
			response.add("/");
		}
		return response;
	}

	public void setOrder_orderedProducts(List<OrderedProduct> order_orderedProducts) {
		this.order_orderedProducts = order_orderedProducts;
	}
	
}
