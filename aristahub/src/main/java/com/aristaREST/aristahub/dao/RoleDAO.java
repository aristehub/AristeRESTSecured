package com.aristaREST.aristahub.dao;

import com.aristaREST.aristahub.entities.Role;

public interface RoleDAO 
{
	//FIND only ROLE STRING ONE to ONE
	public Role findRoleByName(String theRoleName);
	
}
