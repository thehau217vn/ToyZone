package com.toyZone.dto;

/**
 * @Author : Hau Nguyen
 * @Created : 5/20/21, Thursday
 **/

public class RoleDto extends AbtractDto<RoleDto> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
