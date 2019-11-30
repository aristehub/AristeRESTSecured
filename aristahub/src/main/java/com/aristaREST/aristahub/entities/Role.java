package com.aristaREST.aristahub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	/*
	 * -------------------------------------------------------	
	 * 						CONSTRUCTORS
	 * -------------------------------------------------------
	 */
	public Role() {}
	public Role(String name) { this.name = name; }
	/*
	 * -------------------------------------------------------	
	 * 					SETTERS AND GETTERS TO_STRING
	 * -------------------------------------------------------
	 */
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	@Override
	public String toString() { return "Role{" + "id=" + id + ", name='" + name + '\'' + '}'; }
}