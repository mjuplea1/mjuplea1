package com.lguplus.homeshoppingmoa.common.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseInfoEntity {

    @Column(name = "REG_DT", updatable = false)
    @CreatedDate
    protected LocalDateTime regDt;

    @Column(name = "UPD_DT")
    @LastModifiedDate
    protected LocalDateTime updDt;
}
