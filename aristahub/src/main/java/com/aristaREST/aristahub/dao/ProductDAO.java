package com.aristaREST.aristahub.dao;

import com.aristaREST.aristahub.entities.Product;
import java.util.List;

public interface ProductDAO 
{
	//CRUD
	public List<Product> findAll();	
	public Product findById(int id);	
	public void saveProduct(Product newProduct);	
	public void deleteProduct(int id);
}
