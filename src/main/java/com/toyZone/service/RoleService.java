package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.RoleDto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public interface RoleService {
    List<RoleDto> getListRoleService();

    RoleDto getRoleById(int id);
}
