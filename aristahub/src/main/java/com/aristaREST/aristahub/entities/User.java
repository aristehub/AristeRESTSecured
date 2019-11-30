package com.aristaREST.aristahub.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.aristaREST.aristahub.entities.Role;

@Entity
@Table(name="user")
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
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "passowrd", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	//@Column(name = "date_created", nullable = false)
	//private String date_created;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	//no database member methods
	private int active;
	private String permissions = "";
	
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	//HiberNate requires default
	public User() {}
	public User(long id, String name, String last, String username, String password, String email, Collection<Role> roles, String permisions)
	{
		this.user_id = id;
		this.firstName = name;
		this.lastName = last;
		this.username = username;
	 	this.password = password;
	 	this.email = email;
	 	//this.date_created = date;
	 	this.roles = roles;
	 	this.permissions = permisions;
	 	this.active = 1;
	}
	
	/*
	 * -------------------------------------------------------	
	 * 					SETTERS AND GETTERS TO_STRING
	 * -------------------------------------------------------
	 */
	public long getUser_id() { return user_id;	}
	public void setUser_id(long user_id) { this.user_id = user_id; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public long getId()  { return user_id; }
	public void setId(long id) { this.user_id = id; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public int getActive() { return active; }
	public void setActive(int active) { this.active = active; }	
	public String getEmail() { return email; }	
	public void setEmail(String em) { this.email = em; }	
	//public String getDate_created() { return date_created; }
	//public void setDate_created(String date_created) { this.date_created = date_created; }
	public String getPermissions() { return permissions; }
	public void setPermissions(String permissions) { this.permissions = permissions; }
	//get roles and permissions
	public Collection<Role> getRoles() { return roles; }
	public void setRoles(Collection<Role> roles) { this.roles = roles; }	
}
