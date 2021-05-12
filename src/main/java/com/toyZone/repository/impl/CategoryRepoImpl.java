package com.toyZone.repository.impl;

import org.springframework.stereotype.Repository;

import com.toyZone.model.Category;
import com.toyZone.repository.CategoryRepo;

@Repository
public class CategoryRepoImpl extends AbstractRepoImpl<Integer, Category> implements CategoryRepo {

}
