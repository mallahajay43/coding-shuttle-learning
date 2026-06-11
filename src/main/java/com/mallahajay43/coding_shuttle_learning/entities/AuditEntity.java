package com.mallahajay43.coding_shuttle_learning.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(EntityListeners.class)
@Getter
@Setter
public abstract class AuditEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;
}
