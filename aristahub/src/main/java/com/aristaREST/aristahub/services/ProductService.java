package com.aristaREST.aristahub.services;

//java
import java.util.List;
import com.aristaREST.aristahub.entities.Product;

public interface ProductService
{
	//CRUD
	public List<Product> findAll();
	public Product findById(int id);
	public void saveProduct(Product pdt);
	public void deleteProduct(int id);
}