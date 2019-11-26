package com.aristaREST.aristahub.dao;

import com.aristaREST.aristahub.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<User, Long>
{
	User findByUsername(String username);
}
