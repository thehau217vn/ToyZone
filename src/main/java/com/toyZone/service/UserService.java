package com.toyZone.service;

import com.toyZone.dto.UserDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface UserService {
    UserDto saveUserService(UserDto userDto);

    String deleteUserService(Integer id);

    Boolean updateUserVerifyStatus (Integer id);

    Object[] viewPageUserService(int offset, int limit);

    UserDto findByIdUserService(Integer id);

    Object[] findFilterUserService(String[] filter);

    UserDto getUserByUserNameAndPassWordService(String account, String password);
}
