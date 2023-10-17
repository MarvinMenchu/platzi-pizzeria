package com.platzi.pizza.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@MappedSuperclass
public class AuditableEntity {
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
