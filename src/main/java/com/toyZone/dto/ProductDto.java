package com.toyZone.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.toyZone.model.Category;
import com.toyZone.model.Product;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class ProductDto extends AbtractDto<ProductDto> {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;
    @NotNull(message = "Giá tiền không được để trống")
    @DecimalMin(value = "10000", message = "Giá phải từ 10,000 VND trở lên")
    private Long price;
    private Float discount;
    @NotNull(message = "Số lượng không được bỏ trống")
    @DecimalMin(value = "1", message = "Số lượng lớn hơn 1")
    private int count;
    private String image;
    private String content;
    private Integer idCategory;

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

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getPriceDiscount() {
        return (long) (price * discount);
    }

    public Product convertToEntity() {
        Product entity = new Product();
        entity.setId(id);
        entity.setName(name);
        entity.setPrice(price);
        entity.setDiscount(discount);
        entity.setContent(content);
        entity.setImage(image);
        entity.setCount(count);
        Category category = new Category();
        category.setId(idCategory);
        entity.setCategory(category);
        return entity;
    }
}
