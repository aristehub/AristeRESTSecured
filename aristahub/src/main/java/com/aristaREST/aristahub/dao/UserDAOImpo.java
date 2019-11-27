package com.aristaREST.aristahub.dao;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.aristaREST.aristahub.entities.User;

@Repository
public class UserDAOImpo implements UserDAO
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	@Autowired
	private EntityManager entityManager;
	
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	@Autowired
	public UserDAOImpo(EntityManager entity, PasswordEncoder pass)
	{
		this.entityManager = entity;
	}
	/*
	 * -------------------------------------------------------	
	 * 						METHOD
	 * -------------------------------------------------------
	 */
	@Override
	public User findByUsername(String theUserName) {
		// get the current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using user name
		Query<User> theQuery = currentSession.createQuery("from user where username=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}
	@Override
	public void save(User newUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create the user ...
		currentSession.saveOrUpdate(newUser);
	}
}
