package com.toyZone.repository.impl;

import org.springframework.stereotype.Repository;

import com.toyZone.model.Role;
import com.toyZone.repository.RoleRepo;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Repository
public class RoleRepoImpl extends AbstractRepoImpl<Integer, Role> implements RoleRepo {

}
