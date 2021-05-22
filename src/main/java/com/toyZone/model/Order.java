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

import com.toyZone.dto.OrderDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Entity
@Table(name = "`order`")
public class Order extends Abstract {
    @Column(name = "content", columnDefinition = "nvarchar(255)")
    private String content;
    @Column(name = "delivey_address", columnDefinition = "nvarchar(255)")
    private String deliveryAddress;
    @Column(name = "phone")
    private String phone;
    @Column(name = "receiver", columnDefinition = "nvarchar(255)")
    private String receiver;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "total_money")
    private Long totalMoney;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrders = new ArrayList<ProductOrder>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public User getCustomer() {
        return user;
    }

    public void setCustomer(User user) {
        this.user = user;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Order() {
        super();
    }

    public OrderDto convertToDto() {
        OrderDto orderdto = new OrderDto();
        orderdto.setId(getId());
        orderdto.setContent(content);
        orderdto.setCreatedDate(updatedAt);
        orderdto.setDeliveryAddress(deliveryAddress);
        orderdto.setTotalMoney(totalMoney);
        orderdto.setPhone(phone);
        orderdto.setReceiver(receiver);
        if (user != null) {
            orderdto.setUserDto(user.convertToDto());
        }
        orderdto.setStatus(status);
        return orderdto;
    }

}
