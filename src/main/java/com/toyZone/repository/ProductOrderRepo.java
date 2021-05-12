package com.toyZone.repository;

import com.toyZone.model.ProductOrder;

public interface ProductOrderRepo extends AbstractRepo<Integer, ProductOrder> {
    boolean deleteProductOrder(ProductOrder productOrder);
}
