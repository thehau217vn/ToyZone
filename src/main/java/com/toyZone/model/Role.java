package com.toyZone.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.toyZone.dto.RoleDto;

@Entity
@Table(name = "role")
public class Role extends Abstract{
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "role")
	private List<User> users = new ArrayList<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getCustomers() {
		return users;
	}

	public void setCustomers(List<User> users) {
		this.users = users;
	}
	public RoleDto convertToDto() {
		RoleDto dto = new RoleDto();
		dto.setName(name);
		dto.setId(id);
		return dto;
	}
	public Role() {
		super();
	}
	
}
