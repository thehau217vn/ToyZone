package com.toyZone.dto;

import com.toyZone.model.Order;
import com.toyZone.model.Product;
import com.toyZone.model.ProductOrder;

public class ProductOrderDto {
    private int idOrder;
    private int idProduct;
    private Long price;
    private int count;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductOrder convertToEntitiy() {
        ProductOrder entity = new ProductOrder();
        entity.setPrice(price);
        entity.setCount(count);
        Product product = new Product();
        product.setId(idProduct);
        entity.setProduct(product);
        Order order = new Order();
        order.setId(idOrder);
        entity.setOrder(order);
        return entity;
    }
}
