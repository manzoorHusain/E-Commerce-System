package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordered_products")
public class OrderedProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ordered_product_id")
	private int ordered_product_id;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product ordered_product_product;
	
	@Column(name="ordered_product_quantity")
	private int quantity;
	
	public OrderedProduct() { }
	
	
	public OrderedProduct(Order order, Product product) {
		setOrdered_product_product(product);
	}


	public int getOrdered_product_product() {
		return ordered_product_product.getProduct_id();
	}


	public void setOrdered_product_product(Product ordered_product_product) {
		this.ordered_product_product = ordered_product_product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
