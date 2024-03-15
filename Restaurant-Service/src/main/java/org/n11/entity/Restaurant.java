package org.n11.entity;


import lombok.Getter;
import lombok.Setter;
import org.n11.entity.enums.Activity;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.time.LocalTime;
import java.util.UUID;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Getter
@Setter
@SolrDocument(solrCoreName = "n11_restaurants")
public class Restaurant {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string", required = true)
    private String name;

    @Indexed(name = "phone_number", type = "string", required = true)
    private String phoneNumber;

    @Indexed(name = "address", type = "string", required = true)
    private String address;

    @Indexed(name = "website", type = "string", required = true)
    private String website;

    @Indexed(name = "opening_time", type = "time", required = true)
    private LocalTime openingTime;

    @Indexed(name = "closing_time", type = "time", required = true)
    private LocalTime closingTime;

    @Indexed(name = "latitude", type = "pdouble", required = true)
    private Double latitude;

    @Indexed(name = "longitude", type = "pdouble", required = true)
    private Double longitude;

    @Indexed(name = "activity", type = "string", required = true)
    private Activity activity;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }

    public Restaurant(String name, String phoneNumber, String address, String website, LocalTime openingTime, LocalTime closingTime, Double latitude, Double longitude) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.website = website;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}