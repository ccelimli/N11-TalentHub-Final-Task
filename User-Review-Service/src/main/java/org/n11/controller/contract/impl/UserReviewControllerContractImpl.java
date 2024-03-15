package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.constant.ErrorMessages;
import org.n11.constant.Messages;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.controller.contract.impl.helper.clientHelper.UserClientHelper;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;
import org.n11.service.UserReviewEntityService;
import org.n11.service.mapper.UserReviewMapper;
import org.n11.utilities.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
    private final RestaurantClientHelper restaurantClientHelper;
    private final UserClientHelper userClientHelper;

    @Override
    public UserReviewDTO save(UserReviewSaveRequest userReviewSaveRequest) {
        UserReview userReview= UserReviewMapper.INSTANCE.convertToEntity(userReviewSaveRequest);
        this.userReviewEntityService.save(userReview);
        UserDTO userDTO= userClientHelper.getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO= restaurantClientHelper.getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    @Override
    public UserReviewDTO findById(Long id) {
        UserReview userReview = checkUserReview(id);
        UserDTO userDTO= userClientHelper.getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO= restaurantClientHelper.getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    @Override
    public List<UserReviewDTO> findAll() {
       List<UserReview> userReviewList= this.userReviewEntityService.findAll();
       List<UserReviewDTO> userReviewDTOList = new ArrayList<>();
       for(UserReview userReview: userReviewList){
           UserDTO userDTO= userClientHelper.getUserDetails(userReview.getUserId());
           RestaurantDTO restaurantDTO= restaurantClientHelper.getRestaurantDetails(userReview.getRestaurantId());
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
    @Transactional
    public UserReviewDTO updateByReviewText(UserReviewUpdateTextRequest userReviewUpdateTextRequest) {
        UserReview userReview=checkUserReview(userReviewUpdateTextRequest.id());
        UserReviewMapper.INSTANCE.updateText(userReview, userReviewUpdateTextRequest);
        this.userReviewEntityService.save(userReview);
        UserDTO userDTO= userClientHelper.getUserDetails(userReview.getUserId());
        RestaurantDTO restaurantDTO= restaurantClientHelper.getRestaurantDetails(userReview.getRestaurantId());
        return UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
    }

    @Override
    public List<UserReviewDTO> findByResturantId(String id){
        List<UserReview> userReviewList=this.userReviewEntityService.findByRestaurantIdUserReviews(id);
        List<UserReviewDTO> userReviewDTOList=new ArrayList<>();
        for(UserReview userReview: userReviewList){
            UserDTO userDTO= userClientHelper.getUserDetails(userReview.getUserId());
            RestaurantDTO restaurantDTO= restaurantClientHelper.getRestaurantDetails(userReview.getRestaurantId());
            UserReviewDTO userReviewDTO=UserReviewMapper.INSTANCE.convertToDTO(userReview,userDTO,restaurantDTO);
            userReviewDTOList.add(userReviewDTO);
        }
        return userReviewDTOList;
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
}
