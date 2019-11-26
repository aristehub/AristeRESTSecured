package com.aristaREST.aristahub.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aristaREST.aristahub.dao.UserDAO;
import com.aristaREST.aristahub.entities.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService
{

	private UserDAO userdao;
	
	public UserPrincipalDetailsService(UserDAO usr)
	{
		this.userdao = usr;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userdao.findByUsername(username);
		UserPrincipal up = new UserPrincipal(user);
		
		return up;
	}
	

}
