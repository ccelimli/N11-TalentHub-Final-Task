package org.n11.utilities.general.entity;


import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}
