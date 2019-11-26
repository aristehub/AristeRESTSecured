package com.aristaREST.aristahub.entities;
//persistence packages
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name="product")
public class Product 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int product_id;
	@Column(name = "name")
	private String product_name;
	@Column(name = "price")
	private double price;
	@Column(name="remining")
	private int product_reminding;
	@Column(name="summary")
	private String summary;
	@Column(name="date")
	private String posted;
	
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	//Hibernate requires default
	public Product() {}
	public Product(String name, String summary_, String date)
	{
		product_name = name;
		summary = summary_;
		posted = date;
	}
	/*
	 * -------------------------------------------------------	
	 * 					SETTERS AND GETTERS TO_STRING
	 * -------------------------------------------------------
	 */
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProduct_reminding() {
		return product_reminding;
	}
	public void setProduct_reminding(int product_reminding) {
		this.product_reminding = product_reminding;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPosted() {
		return posted;
	}
	public void setPosted(String posted) {
		this.posted = posted;
	}
	//to string
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", price=" + price
				+ ", product_reminding=" + product_reminding + ", summary=" + summary + ", posted=" + posted + "]";
	}
	
	
}
