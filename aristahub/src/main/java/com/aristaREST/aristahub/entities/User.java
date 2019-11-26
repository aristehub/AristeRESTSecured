package com.aristaREST.aristahub.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class User 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long user_id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "passowrd", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "date_created", nullable = false)
	private String date_created;
	
	@Column(name= "role")
	private String roles = "";
	
	//no database member methods
	private int active;
	private String permissions = "";
	
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	//HiberNate requires default
	protected User() {}
	
	public User(long id, String username, String password, String email, String date, String roles, String permisions)
	{
		this.user_id = id;
		this.username = username;
	 	this.password = password;
	 	this.email = email;
	 	this.date_created = date;
	 	this.roles = roles;
	 	this.permissions = permisions;
	 	this.active = 1;
	}
	
	/*
	 * -------------------------------------------------------	
	 * 					SETTERS AND GETTERS TO_STRING
	 * -------------------------------------------------------
	 */


	
	public long getId()  { return user_id; }

	public void setId(long id) { this.user_id = id; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public int getActive() { return active; }

	public void setActive(int active) { this.active = active; }
	
	public String getEmail() { return email; }
	
	public String getDate_created() { return date_created; }

	public String getRoles() { return roles; }

	public void setRoles(String roles) { this.roles = roles; }

	public String getPermissions() { return permissions; }

	public void setPermissions(String permissions) { this.permissions = permissions; }
	
	//get roles and permissions
	public List<String> getRoleList()
	{
		if(this.roles.length() > 0)
		{
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionsList()
	{
		if(this.permissions.length() > 0)
		{
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}
	
}
