package com.aristaREST.aristahub.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aristaREST.aristahub.entities.Product;

@Repository
public class ProductDAOHibernate implements ProductDAO{

	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private EntityManager manager;
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	@Autowired
	public ProductDAOHibernate(EntityManager et)
	{
		this.manager = et;
	}
	/*
	 * -------------------------------------------------------	
	 * 					METHODS IMPLEMENTED
	 * -------------------------------------------------------
	 */
	@Override
	public List<Product> findAll() {
		//get Hibernate Session
		Session current = manager.unwrap(Session.class);
		//create query
		Query<Product> q = current.createQuery("FROM product", Product.class);
		//execute query
		List<Product> products = q.getResultList();
		return products;
	}
	@Override
	public Product findById(int id) {
		//get Hibernate session
		Session current = manager.unwrap(Session.class);
		//get current
		Product toReturn = current.get(Product.class, id);
		return toReturn;
	}
	@Override
	public void saveProduct(Product newProduct) 
	{
		//get Hibernate session
		Session current = manager.unwrap(Session.class);
		//save Product
		current.saveOrUpdate(newProduct);
	}
	@Override
	public void deleteProduct(int id) {
		//get Hibernate session
		Session current = manager.unwrap(Session.class);
		//delete from PK
		Query q = current.createQuery("DELETE FROM product WHERE id=product_id");
		q.setParameter("prodcut_id", id);
		//execute query
		q.executeUpdate();
	}
}
