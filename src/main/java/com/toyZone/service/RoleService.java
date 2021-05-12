package com.toyZone.service;

import java.util.List;

import com.toyZone.dto.RoleDto;

public interface RoleService {
	List<RoleDto> getListRoleService();
	RoleDto getRoleById(int id);
}
