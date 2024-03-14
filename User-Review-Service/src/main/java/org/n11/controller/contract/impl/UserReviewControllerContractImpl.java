package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.constant.ErrorMessages;
import org.n11.constant.Messages;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;
import org.n11.service.UserReviewEntityService;
import org.n11.service.mapper.UserReviewMapper;
import org.n11.utilities.client.RestaurantServiceClient;
import org.n11.utilities.client.UserServiceClient;
import org.n11.utilities.exceptions.ItemNotFoundException;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class UserReviewControllerContractImpl implements UserReviewControllerContract{
    private final UserReviewEntityService userReviewEntityService;
    private final UserServiceClient userServiceClient;
    private final RestaurantServiceClient restaurantServiceClient;

    @Override
    public UserReviewDTO save(UserReviewSaveRequest userReviewSaveRequest) {
        UserReview userReview= UserReviewMapper.INSTANCE.convertToEntity(userReviewSaveRequest);
        this.userReviewEntityService.save(userReview);
        UserDTO userDTO= getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO=getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    @Override
    public UserReviewDTO findById(Long id) {
        UserReview userReview = checkUserReview(id);
        UserDTO userDTO= getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO=getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    @Override
    public List<UserReviewDTO> findAll() {
       List<UserReview> userReviewList= this.userReviewEntityService.findAll();
       List<UserReviewDTO> userReviewDTOList = null;
       for(UserReview userReview: userReviewList){
           UserDTO userDTO= getUserDetails(userReview.getUserId());
           RestaurantDTO restaurantDTO=getRestaurantDetails(userReview.getRestaurantId());
           UserReviewDTO userReviewDTO=UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
           userReviewDTOList.add(userReviewDTO);
       }

       return userReviewDTOList;
    }

    @Override
    public String delete(Long id) {
        UserReview userReview=checkUserReview(id);
        this.userReviewEntityService.delete(userReview);
        return Messages.SUCCESSFUL_DELETE.getMessage();
    }

    @Override
    public UserReviewDTO updateByReviewText(UserReviewUpdateTextRequest userReviewUpdateTextRequest) {
        UserReview userReview=checkUserReview(userReviewUpdateTextRequest.id());
        userReview=UserReviewMapper.INSTANCE.updateText(userReview, userReviewUpdateTextRequest);
        UserDTO userDTO= getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO=getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    private UserReview checkUserReview(Long id){
        UserReview userReview=this.userReviewEntityService.findByIdWithControl(id);
        if (userReview==null){
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_ITEM);
        }
        else{
            return userReview;
        }
    }

    private UserDTO getUserDetails(Long userId) {
        ResponseEntity<RestResponse<UserDTO>> userServiceResponse = userServiceClient.getUserById(userId);
        return userServiceResponse.getBody().getData();
    }

    private RestaurantDTO getRestaurantDetails(String restaurantId) {
        ResponseEntity<RestResponse<RestaurantDTO>> restaurantServiceResponse = restaurantServiceClient.getRestaurantById(restaurantId);
        return restaurantServiceResponse.getBody().getData();
    }
}
