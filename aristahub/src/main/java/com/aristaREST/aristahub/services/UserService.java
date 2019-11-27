package com.aristaREST.aristahub.services;

import com.aristaREST.aristahub.entities.User;
import com.aristaREST.aristahub.entities.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService 
{
	// SAVE and FIND
	public User findByUserName(String userName);
	public void save(CrmUser crmUser);
}
