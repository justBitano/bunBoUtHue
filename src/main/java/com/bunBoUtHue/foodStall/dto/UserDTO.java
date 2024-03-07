package com.bunBoUtHue.foodStall.dto;

import com.bunBoUtHue.foodStall.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private UserRole role;


}
