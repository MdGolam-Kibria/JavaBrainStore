package com.CrackCode.ORM_CUSTOM_repository_CONFIG.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User extends BaseModel {
    private String username;
    private String email;
    private String phone;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    private Long divisionId;
    private Long districtId;
    private Long thanaId;
    private String designation;
    private Boolean isAvailable;
    private Long parentId;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Role> roles;
    @Transient
    private Long roleId;
    @Transient
    private String roleName;
}
