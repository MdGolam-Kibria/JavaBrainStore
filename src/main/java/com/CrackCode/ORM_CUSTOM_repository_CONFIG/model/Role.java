package com.CrackCode.ORM_CUSTOM_repository_CONFIG.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role extends BaseModel {
    @NaturalId
    @Column(name = "name",length = 128)
    //@NotEmpty(message = "Role name Is Mandatory")
    private String name;
    @ManyToMany(mappedBy = "roles" )
    @JsonIgnore
    private List<User> users = new ArrayList<>();

}
