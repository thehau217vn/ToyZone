package com.toyZone.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.toyZone.dto.ProductDto;

@Entity
@Table(name = "product")
public class Product extends Abstract{
	@Column(name = "name",columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "discount")
	private Float discount;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "content",columnDefinition = "nvarchar(2000)")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<ProductOrder> productOrders = new ArrayList<ProductOrder>();
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}
	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}
	
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Product() {
		super();
	}
	public ProductDto convertToDto() {
		ProductDto dto = new ProductDto();
		dto.setId(id);
		dto.setName(name);
		dto.setPrice(price);
		dto.setImage(image);
		dto.setContent(content);
		dto.setDiscount(discount);
		dto.setCreatedDate(getUpdatedAt());
		dto.setCount(count);
		dto.setIdCategory(category.getId());
		return dto;
	}
	public Product convertToEntity(ProductDto productDto) {
		setName(productDto.getName());
		setPrice(productDto.getPrice());
		setDiscount(productDto.getDiscount());
		setContent(productDto.getContent());
		setImage(productDto.getImage());
		setCount(productDto.getCount());
		Category category = new Category();
		category.setId(productDto.getIdCategory());
		setCategory(category);
		return this;
	}
}
