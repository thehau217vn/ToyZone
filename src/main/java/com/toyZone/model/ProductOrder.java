package com.toyZone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.toyZone.dto.ProductOrderDto;

@Entity
@Table(name = "product_order")
<<<<<<< HEAD
public class ProductOrder implements Serializable{
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "odder_id")
	private Order order;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "price")
	private Long price;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public ProductOrder() {
		super();
	}
	public ProductOrderDto convertToDto() {
		ProductOrderDto productOrderDto = new ProductOrderDto();
		productOrderDto.setIdOrder(order.getId());
		productOrderDto.setIdProduct(product.getId());
		productOrderDto.setPrice(price);
		productOrderDto.setCount(count);
		return productOrderDto;
	}
=======
public class ProductOrder implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odder_id")
    private Order order;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private Long price;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductOrder() {
        super();
    }

    public ProductOrderDto convertToDto() {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setIdOrder(order.getId());
        productOrderDto.setIdProduct(product.getId());
        productOrderDto.setPrice(price);
        productOrderDto.setCount(count);
        return productOrderDto;
    }
>>>>>>> develop
}
