package com.ebanking.blockchain.common;

import com.ebanking.blockchain.config.CustomInstantDeserializer;
import com.ebanking.blockchain.enums.DataStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommonEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private DataStatus dataStatus;

    @CreatedBy
    @Column(length = 36, name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "created", updatable = false)
    @CreatedDate
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant created;

    @LastModifiedBy
    @Column(length = 36)
    private String updatedBy;

    @LastModifiedDate
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant updated;

    @PrePersist
    public void prePersist() {
        this.dataStatus = DataStatus.CREATED;
    }
}
