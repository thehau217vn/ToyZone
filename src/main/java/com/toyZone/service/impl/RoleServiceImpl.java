package com.toyZone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyZone.dto.RoleDto;
import com.toyZone.model.Role;
import com.toyZone.repository.RoleRepo;
import com.toyZone.service.RoleService;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<RoleDto> getListRoleService() {
        // TODO Auto-generated method stub
        List<RoleDto> dtos = new ArrayList<RoleDto>();
        List<Role> entities = roleRepo.findAll();
        for (Role role : entities) {
            RoleDto dto = role.convertToDto();
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public RoleDto getRoleById(int id) {
        // TODO Auto-generated method stub
        return roleRepo.findById(id).convertToDto();
    }
}
