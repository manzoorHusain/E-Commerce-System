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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private int product_id;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_price")
	private Double product_price;
	
	@Column(name="product_img")
	private String product_img;
	
	@OneToMany(targetEntity = OrderedProduct.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordered_product_product")
	private List<OrderedProduct> product_OrderedProducts = new ArrayList<>();
	
	public Product() { }
	
	public Product(int id) {
		setProduct_id(id);
	}
	
	public Product(String product_name, Double product_price, String product_img) {
		setProduct_name(product_name);
		setProduct_price(product_price);
		setProduct_img(product_img);
	}
	
	public void addOrderedProduct(OrderedProduct orderedProduct) {
		product_OrderedProducts.add(orderedProduct);
		orderedProduct.setOrdered_product_product(this);
	}
	
	public void removeOrderedProduct(OrderedProduct orderedProduct) {
		product_OrderedProducts.remove(orderedProduct);
		orderedProduct.setOrdered_product_product(null);
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Double product_price) {
		this.product_price = product_price;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public List<OrderedProduct> getProduct_OrderedProducts() {
		return product_OrderedProducts;
	}

	public void setProduct_OrderedProducts(List<OrderedProduct> product_OrderedProducts) {
		this.product_OrderedProducts = product_OrderedProducts;
	}
	
	
}
