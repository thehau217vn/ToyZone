package com.toyZone.repository.impl;

import org.springframework.stereotype.Repository;

import com.toyZone.model.User;
import com.toyZone.repository.UserRepo;

@Repository
public class UserRepoImpl extends AbstractRepoImpl<Integer, User> implements UserRepo {

}
