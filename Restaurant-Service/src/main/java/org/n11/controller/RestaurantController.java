package org.n11.controller;

import org.n11.controller.contract.RestaurantControllerContact;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateActivityRequest;
import org.n11.entity.request.RestaurantUpdateRequest;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantControllerContact restaurantControllerContact;

    public RestaurantController(RestaurantControllerContact restaurantControllerContact) {
        this.restaurantControllerContact = restaurantControllerContact;
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> save(@RequestBody RestaurantSaveRequest restaurantSaveRequest){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.save(restaurantSaveRequest)));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<RestResponse<String>> delete(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.deleteRestaurant(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> update(@PathVariable String id,@RequestBody RestaurantUpdateRequest restaurantUpdateRequest){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.updateRestaurant(restaurantUpdateRequest)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> findAll(){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.findAllRestaurants()));
    }

    @GetMapping("{/id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> findById(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.findById(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> changeToActivity(@PathVariable String id,@RequestBody RestaurantUpdateActivityRequest restaurantUpdateActivityRequest){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.changeToActivity(restaurantUpdateActivityRequest)));
    }
}
