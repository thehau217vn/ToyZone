package com.toyZone.repository.impl;

import org.springframework.stereotype.Repository;

import com.toyZone.model.Role;
import com.toyZone.repository.RoleRepo;

@Repository
public class RoleRepoImpl extends AbstractRepoImpl<Integer, Role> implements RoleRepo {

}
