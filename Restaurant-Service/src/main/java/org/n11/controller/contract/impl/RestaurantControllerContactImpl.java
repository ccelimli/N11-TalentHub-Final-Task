package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.constant.ErrorMessages;
import org.n11.constant.Messages;
import org.n11.controller.contract.RestaurantControllerContact;
import org.n11.entity.Restaurant;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.enums.Activity;
import org.n11.entity.enums.Status;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateActivityRequest;
import org.n11.entity.request.RestaurantUpdateRequest;
import org.n11.service.RestaurantEntityService;
import org.n11.service.mapper.RestaurantMapper;
import org.n11.utilities.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class RestaurantControllerContactImpl implements RestaurantControllerContact {
    private final RestaurantEntityService restaurantEntityService;
    @Override
    public RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToEntity(restaurantSaveRequest);
        this.restaurantEntityService.save(restaurant);
        RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.convertToDTO(restaurant);
        return restaurantDTO.withStatus(getRestaurantStatus(restaurantDTO.openingTime(), restaurantDTO.closingTime()));
    }

    @Override
    public String deleteRestaurant(String id) {
        Restaurant restaurant= findByRestaurant(id);
        this.restaurantEntityService.deleteRestaurant(restaurant);
        return Messages.SUCCESSFUL_DELETE.getContext();
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest) {
        Restaurant restaurant=findByRestaurant(restaurantUpdateRequest.id());
        Restaurant updateRestaurant=RestaurantMapper.INSTANCE.convertTOUpdate(restaurant,restaurantUpdateRequest);
        this.restaurantEntityService.updateRestaurant(updateRestaurant);
        RestaurantDTO restaurantDTO= RestaurantMapper.INSTANCE.convertToDTO(updateRestaurant);
        return restaurantDTO.withStatus(getRestaurantStatus(restaurantDTO.openingTime(), restaurantDTO.closingTime()));    }

    @Override
    public List<RestaurantDTO> findAllRestaurants() {
        Iterable<Restaurant> restaurantList = this.restaurantEntityService.findAllRestaurants();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.convertToDTO(restaurant);
            restaurantDTO = restaurantDTO.withStatus(getRestaurantStatus(restaurantDTO.openingTime(), restaurantDTO.closingTime()));
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS.stream().filter(restaurantDTO -> restaurantDTO.activity()==Activity.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO findById(String id) {
        Restaurant restaurant= findByRestaurant(id);
        RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.convertToDTO(restaurant);
        return  restaurantDTO.withStatus(getRestaurantStatus(restaurantDTO.openingTime(), restaurantDTO.closingTime()));
    }

    @Override
    public RestaurantDTO changeToActivity(RestaurantUpdateActivityRequest restaurantUpdateActivityRequest) {
        Restaurant restaurant= findByRestaurant(restaurantUpdateActivityRequest.id());
        restaurant.setActivity(restaurantUpdateActivityRequest.activity());
        this.restaurantEntityService.save(restaurant);
        return RestaurantMapper.INSTANCE.convertToDTO(restaurant);
    }

    @Override
    public List<RestaurantDTO> findByName(String name) {
        List<Restaurant> restaurantList= this.restaurantEntityService.findByActivity(name);
        if (restaurantList.isEmpty()){
            throw new ItemNotFoundException(ErrorMessages.EMPTY_LIST);
        }
        return RestaurantMapper.INSTANCE.convertToDTOS(restaurantList);
    }

    private static Status getRestaurantStatus(LocalTime openingTime, LocalTime closingTime) {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        LocalTime currentTime = now.toLocalTime();

        if (closingTime.isBefore(openingTime)) {
            if (currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)) {
                return Status.OPEN;
            } else {
                return Status.CLOSE;
            }
        } else {
            if (currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)) {
                return Status.OPEN;
            } else {
                return Status.CLOSE;
            }
        }
    }

    private Restaurant findByRestaurant(String id){
        Restaurant restaurant = this.restaurantEntityService.findById(id);
        if (restaurant==null){
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_RESTAURANT);
        }
        else{
            return restaurant;
        }
    }
}
