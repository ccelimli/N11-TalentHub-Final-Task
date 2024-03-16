package org.n11.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.controller.contract.impl.helper.clientHelper.UserClientHelper;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.enums.Rate;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.service.UserReviewEntityService;
import org.n11.service.mapper.UserReviewMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerContractImplTest {
    @Mock
    private UserReviewEntityService userReviewEntityService;
    @Mock
    private UserClientHelper userClientHelper;

    @Mock
    private RestaurantClientHelper restaurantClientHelper;

    @InjectMocks
    UserReviewControllerContractImpl userReviewControllerContractImpl;

    @Captor
    private ArgumentCaptor<UserReview> userReviewCaptor;

    @Test
    public void shouldSave() {
        //Given
        UserReviewSaveRequest userReviewSaveRequest = new UserReviewSaveRequest(2L, "f3cb20bb-ac33-412d-84a7-1a757e2f9174", "Great food!", 5);
        UserReview userReview = UserReviewMapper.INSTANCE.convertToEntity(userReviewSaveRequest);
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/",
                LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 0.0, 39.7702467, 21.1828612
        );
        UserDTO userDTO = new UserDTO(2L, "Alice", "Smith", 40.7128, -74.006);
        UserReviewDTO userReviewDTO = UserReviewMapper.INSTANCE.convertToDTO(userReview, userDTO, restaurantDTO);

        //when
        Mockito.when(userReviewEntityService.save(any(UserReview.class))).thenReturn(userReview);
        Mockito.when(userClientHelper.getUserDetails(anyLong())).thenReturn(userDTO);
        Mockito.when(restaurantClientHelper.getRestaurantDetails(anyString())).thenReturn(restaurantDTO);

        UserReviewDTO resultUserDTO = userReviewControllerContractImpl.save(userReviewSaveRequest);

        //Then
        ArgumentCaptor<UserReview> userReviewCaptor = ArgumentCaptor.forClass(UserReview.class);
        InOrder inOrder = Mockito.inOrder(userReviewEntityService, userClientHelper, restaurantClientHelper);
        inOrder.verify(userReviewEntityService, times(1)).save(userReviewCaptor.capture());
        inOrder.verify(userClientHelper, times(1)).getUserDetails(userReviewSaveRequest.userId());
        inOrder.verify(restaurantClientHelper, times(1)).getRestaurantDetails(userReviewSaveRequest.restaurantId());
        inOrder.verifyNoMoreInteractions();
        assertEquals(userReviewDTO, resultUserDTO);

    }

    @Test
    public void shoulGetAllUserReviews(){
        Long id = 252L;
        Long userId = 2L;
        String restaurantId = "f3cb20bb-ac33-412d-84a7-1a757e2f9174";
        String reviewText = "This is a review text";
        Rate rate = Rate.ONE;
        LocalDate reviewDate = LocalDate.of(2024,03,15);

        // Create UserReview object
        UserReview userReview = new UserReview(id, userId, restaurantId, reviewText, rate, reviewDate);

        UserReview userReview1= new UserReview();
        userReview1.setId(252L);
        userReview1.setUserId(2L);
        userReview1.setRestaurantId("f3cb20bb-ac33-412d-84a7-1a757e2f9174");
        userReview1.setReviewText("This is a review text");
        userReview1.setRate(Rate.FIVE);
        userReview1.setReviewDate(LocalDate.of(2024,03,15));
    }
}

