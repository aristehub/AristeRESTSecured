package com.aristaREST.aristahub.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aristaREST.aristahub.entities.User;

@Service
public class UserDAOImpo implements CommandLineRunner
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private UserDAO userdao;
	private PasswordEncoder passEnc;
	
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	@Autowired
	public UserDAOImpo(UserDAO udao, PasswordEncoder pass)
	{
		this.userdao = udao;
		this.passEnc = pass;
	}
	/*
	 * -------------------------------------------------------	
	 * 						METHOD
	 * -------------------------------------------------------
	 */
	@Override
	public void run(String... args) throws Exception {
		
		//User temp = new User();		
		//List<User> users = Arrays.asList(temp, temp2, temp3);
		//save all
		//this.userdao.save(temp);
	}

}
