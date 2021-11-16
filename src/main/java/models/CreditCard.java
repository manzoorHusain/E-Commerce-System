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
@Table(name="credit_cards")
public class CreditCard {
	@Id
	@Column(name="card_number")
	private String card_number;
	
	@Column(name="card_owner")
	private String card_owner;
	
	@Column(name="card_last_use_date")
	private String card_lud;
	
	@Column(name="card_cvc")
	private String card_cvc;
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order_card")
	private List<Order> card_orders = new ArrayList<>();
	
	public CreditCard() { }
	
	public CreditCard(String card_number) {
		setCard_number(card_number);
	}
	
	public CreditCard(String card_number, String card_owner, String card_lud, String card_cvc) {
		setCard_number(card_number);
		setCard_owner(card_owner);
		setCard_lud(card_lud);
		setCard_cvc(card_cvc);
	}
	
	public void addOrders(Order order) {
		card_orders.add(order);
		order.setOrder_card(this);
	}
	
	public void removeOrders(Order order) {
		card_orders.remove(order);
		order.setOrder_card(null);
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_owner() {
		return card_owner;
	}

	public void setCard_owner(String card_owner) {
		this.card_owner = card_owner;
	}

	public String getCard_lud() {
		return card_lud;
	}

	public void setCard_lud(String card_lud) {
		this.card_lud = card_lud;
	}

	public String getCard_cvc() {
		return card_cvc;
	}

	public void setCard_cvc(String card_cvc) {
		this.card_cvc = card_cvc;
	}
	
	public List<Order> getCard_orders() {
		return card_orders;
	}

	public void setCard_orders(List<Order> card_orders) {
		this.card_orders = card_orders;
	}

}
