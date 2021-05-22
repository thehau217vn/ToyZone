package com.toyZone.repository;

import com.toyZone.model.ProductOrder;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface ProductOrderRepo extends AbstractRepo<Integer, ProductOrder> {
    boolean deleteProductOrder(ProductOrder productOrder);
}
