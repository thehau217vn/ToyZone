package com.toyZone.repository.impl;

import org.springframework.stereotype.Repository;

import com.toyZone.model.User;
import com.toyZone.repository.UserRepo;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Repository
public class UserRepoImpl extends AbstractRepoImpl<Integer, User> implements UserRepo {

}
