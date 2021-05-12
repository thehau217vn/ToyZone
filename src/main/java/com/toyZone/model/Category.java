package com.toyZone.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.toyZone.dto.CategoryDto;

@Entity
@Table(name = "category")
public class Category extends Abstract{
	@Column(name = "name",columnDefinition = "nvarchar(255)")
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<Product>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category() {
		super();
	}
	public CategoryDto convertToDto() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(getId());
		categoryDto.setName(getName());
		categoryDto.setCreatedDate(getUpdatedAt());
		return categoryDto;
	}
	public Category convertToEntitiy(CategoryDto dto) {
		setName(dto.getName());
		return this;
	}
}
