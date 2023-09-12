package com.CrackCode.ORM_CUSTOM_repository_CONFIG.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  /*  @Column(updatable = false)
    private Long createdBy;
    @Column(updatable = false)
    private String creator;//just for create a post now
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @JsonFormat(pattern = "dd-MM-yyy hh:mm:ss a", timezone = "Asia/Dhaka")
    private Date createdAt;
    @Column(updatable = false)
    private Long updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyy hh:mm:ssm a", timezone = "Asia/Dhaka")
    private Date updatedAt;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData() {
        this.createdAt = new Date();
        this.isActive = true;
    }

    @PreUpdate
    public void setPreUpdateData() {
        this.updatedAt = new Date();
    }*/
}
