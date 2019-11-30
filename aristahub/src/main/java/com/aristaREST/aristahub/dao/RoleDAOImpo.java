package com.aristaREST.aristahub.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aristaREST.aristahub.entities.Role;

@Repository
public class RoleDAOImpo implements RoleDAO 
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
	 * 						METHOD
	 * -------------------------------------------------------
	 */
	@Override
	public Role findRoleByName(String theRoleName) 
	{

		// get the current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		Role theRole = null;
		//role null handling..
		try 
		{
			theRole = theQuery.getSingleResult();
		} catch (Exception e) 
		{
			theRole = null;
		}
		return theRole;
	}
}
