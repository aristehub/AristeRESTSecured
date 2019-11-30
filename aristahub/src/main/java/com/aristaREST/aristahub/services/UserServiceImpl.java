package com.aristaREST.aristahub.services;


import com.aristaREST.aristahub.dao.RoleDAO;
import com.aristaREST.aristahub.dao.UserDAO;
import com.aristaREST.aristahub.entities.User;
import com.aristaREST.aristahub.entities.Role;
import com.aristaREST.aristahub.entities.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
//import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	// need to inject user DAO
	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	@Override
	@Transactional
	public User findByUserName(String userName) 
	{
		// check the database if the user already exists
		return userDao.findByUsername(userName);
	}
	
	@Override
	@Transactional
	public void save(CrmUser crmUser) 
	{
		User user = new User();
		 // assign user details to the user object after validation crmUser ...
		user.setUsername(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		//get current time
		//Date time = new Date();
		//add to string method...
		//user.setDate_created(time.toString());
		//set roles and permissions -> for developing purposes I will set all to USER
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		//find user
		User user = userDao.findByUsername(userName);
		if (user == null) 
		{
			//empty case no found....
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		//return mapped user with roles
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) 
	{
		//extract roles....
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
