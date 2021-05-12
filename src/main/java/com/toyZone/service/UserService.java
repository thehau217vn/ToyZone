package com.toyZone.service;

import com.toyZone.dto.UserDto;

public interface UserService {
    UserDto saveUserService(UserDto userDto);

    String deleteUserService(Integer id);

    Object[] viewPageUserService(int offset, int limit);

    UserDto findByIdUserService(Integer id);

    Object[] findFilterUserService(String[] filter);

    UserDto getUserByUserNameAndPassWordService(String account, String password);
}
