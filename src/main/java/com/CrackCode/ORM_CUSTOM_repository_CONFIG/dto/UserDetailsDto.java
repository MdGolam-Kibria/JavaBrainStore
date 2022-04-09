package com.CrackCode.ORM_CUSTOM_repository_CONFIG.dto;

import com.CrackCode.ORM_CUSTOM_repository_CONFIG.model.User;
import lombok.Data;

@Data
public class UserDetailsDto {
    private Long roleId;
    private String roleName;
    private User user;
}
