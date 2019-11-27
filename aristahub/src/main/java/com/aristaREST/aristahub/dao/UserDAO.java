package com.aristaREST.aristahub.dao;

import com.aristaREST.aristahub.entities.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO
{
	//FIND and SAVE
	User findByUsername(String username);
	public void save(User user);
}
