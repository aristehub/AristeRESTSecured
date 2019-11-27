package com.aristaREST.aristahub.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aristaREST.aristahub.dao.ProductDAO;
import com.aristaREST.aristahub.entities.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProductServiceImpo implements ProductService
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	 private ProductDAO productDAO;
	 /*
	 * -------------------------------------------------------	
	 * 					CONSTRUCTOR
	 * -------------------------------------------------------
	 */
	//Inject product DAO
	@Autowired
	public ProductServiceImpo(ProductDAO typeDAO)
	{
		productDAO = typeDAO;
	}
	
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	 //delegate to DAO
	@Override
	@Transactional
	public List<Product> findAll() { return productDAO.findAll(); }
	
	@Override
	@Transactional
	public Product findById(int id) { return productDAO.findById(id); }
	
	@Override
	@Transactional
	public void saveProduct(Product pdt) { productDAO.saveProduct(pdt); }
	
	@Override
	@Transactional
	public void deleteProduct(int id) { productDAO.deleteProduct(id); }
}