package org.n11.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.constant.Messages;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.controller.contract.impl.helper.clientHelper.UserClientHelper;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.enums.Rate;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;
import org.n11.service.UserReviewEntityService;
import org.n11.service.mapper.UserReviewMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class UserReviewControllerContractImplTest {
    @Mock
    private UserReviewEntityService userReviewEntityService;
    @Mock
    private UserClientHelper userClientHelper;

    @Mock
    private RestaurantClientHelper restaurantClientHelper;

    @InjectMocks
    private UserReviewControllerContractImpl userReviewControllerContractImpl;

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
        LocalDate reviewDate = LocalDate.of(2024, 03, 15);

        UserReview userReview = new UserReview(id, userId, restaurantId, reviewText, rate, reviewDate);
        UserReview userReview1 = new UserReview();
        userReview1.setId(252L);
        userReview1.setUserId(2L);
        userReview1.setRestaurantId("24d9e003-aa1c-459e-899c-415999643999");
        userReview1.setReviewText("This is a review text");
        userReview1.setRate(Rate.FIVE);
        userReview1.setReviewDate(LocalDate.of(2024, 03, 15));

        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/",
                LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 0.0, 39.7702467, 21.1828612
        );
        UserDTO userDTO = new UserDTO(2L, "Alice", "Smith", 40.7128, -74.006);

        List<UserReview> userReviewList = List.of(userReview, userReview1);

        //When
        Mockito.when(userReviewEntityService.findAll()).thenReturn(userReviewList);
        List<UserReviewDTO> tempUserDTOs = userReviewList.stream()
                .map(ur -> UserReviewMapper.INSTANCE.convertToDTO(ur, userDTO, restaurantDTO))
                .collect(Collectors.toList());
        Mockito.when(userClientHelper.getUserDetails(anyLong())).thenReturn(userDTO);
        Mockito.when(restaurantClientHelper.getRestaurantDetails(anyString())).thenReturn(restaurantDTO);

        List<UserReviewDTO> result = userReviewControllerContractImpl.findAll();

        //Then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService, userClientHelper, restaurantClientHelper);
        inOrder.verify(userReviewEntityService, times(1)).findAll();
        inOrder.verify(userClientHelper, times(1)).getUserDetails(userDTO.id());
        inOrder.verify(restaurantClientHelper, times(1)).getRestaurantDetails(restaurantDTO.id());
        inOrder.verifyNoMoreInteractions();
        assertEquals(tempUserDTOs, result);
        Mockito.verify(userReviewEntityService, times(1)).findAll();
    }

    @Test
    public void shouldFindByIdUserReview(){
        //Given
        UserReview userReview = new UserReview(252L,2L,"24d9e003-aa1c-459e-899c-415999643999", "This is a review text",Rate.FIVE,LocalDate.of(2024, 03, 15));

        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/",
                LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 0.0, 39.7702467, 21.1828612
        );
        UserDTO userDTO = new UserDTO(2L, "Alice", "Smith", 40.7128, -74.006);

        UserReviewDTO userReviewDTO = UserReviewMapper.INSTANCE.convertToDTO(userReview, userDTO, restaurantDTO);

        //When
        Mockito.when(userReviewEntityService.findByIdWithControl(userReview.getId())).thenReturn(userReview);
        Mockito.when(userClientHelper.getUserDetails(userDTO.id())).thenReturn(userDTO);
        Mockito.when(restaurantClientHelper.getRestaurantDetails(restaurantDTO.id())).thenReturn(restaurantDTO);

        UserReviewDTO result = userReviewControllerContractImpl.findById(userReview.getId());

        //Then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService, userClientHelper, restaurantClientHelper);
        inOrder.verify(userReviewEntityService, times(1)).findByIdWithControl(userReview.getId());
        inOrder.verify(userClientHelper, times(1)).getUserDetails(userDTO.id());
        inOrder.verify(restaurantClientHelper, times(1)).getRestaurantDetails(restaurantDTO.id());
        inOrder.verifyNoMoreInteractions();
        assertEquals(userReviewDTO, result);
    }

    @Test
    public void shouldDeleteUserReview(){
        //Given
        UserReview userReview = new UserReview(252L,2L,"24d9e003-aa1c-459e-899c-415999643999", "This is a review text",Rate.FIVE,LocalDate.of(2024, 03, 15));

        //When
        Mockito.when(userReviewEntityService.findByIdWithControl(userReview.getId())).thenReturn(userReview);
        String result=userReviewControllerContractImpl.delete(userReview.getId());

        //Then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService);
        inOrder.verify(userReviewEntityService,times(1)).findByIdWithControl(userReview.getId());
        inOrder.verify(userReviewEntityService,times(1)).delete(userReview);
        assertEquals(Messages.SUCCESSFUL_DELETE.getMessage(),result);
    }

    @Test
    public void shouldUpdateByReviewText(){
        //Given
        UserReviewUpdateTextRequest userReviewUpdateTextRequest = new UserReviewUpdateTextRequest(252L, 5, "New Context");
        UserReview userReviewTemp = new UserReview(252L,2L,"24d9e003-aa1c-459e-899c-415999643999", "This is a review text",Rate.FIVE,LocalDate.of(2024, 03, 15));
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/",
                LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 0.0, 39.7702467, 21.1828612
        );
        UserDTO userDTO = new UserDTO(2L, "Alice", "Smith", 40.7128, -74.006);
        UserReview userReview = UserReviewMapper.INSTANCE.updateText(userReviewTemp, userReviewUpdateTextRequest);

        //When
        Mockito.when(userReviewEntityService.findByIdWithControl(userReviewUpdateTextRequest.id())).thenReturn(userReview);
        Mockito.when(userClientHelper.getUserDetails(anyLong())).thenReturn(userDTO);
        Mockito.when(restaurantClientHelper.getRestaurantDetails(anyString())).thenReturn(restaurantDTO);

        UserReviewDTO result = userReviewControllerContractImpl.updateByReviewText(userReviewUpdateTextRequest);

        //Then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService, userClientHelper, restaurantClientHelper);
        inOrder.verify(userReviewEntityService, times(1)).findByIdWithControl(userReviewUpdateTextRequest.id());
        inOrder.verify(userReviewEntityService, times(1)).save(userReviewCaptor.capture());
        inOrder.verify(userClientHelper, times(1)).getUserDetails(anyLong());
        inOrder.verify(restaurantClientHelper, times(1)).getRestaurantDetails(anyString());
        inOrder.verifyNoMoreInteractions();

        UserReview capturedUserReview = userReviewCaptor.getValue();
        assertEquals(result.id(), capturedUserReview.getId());
        assertEquals(result.reviewText(), capturedUserReview.getReviewText());
        assertEquals(result.rate(), capturedUserReview.getRate().getValue());
    }

    @Test
    public void shouldFindByRestaurantId(){
        String restaurantId = "24d9e003-aa1c-459e-899c-415999643999";
        UserReview userReview1 = new UserReview(252L,2L,"24d9e003-aa1c-459e-899c-415999643999","This is a review text",Rate.ONE,LocalDate.of(2024, 03, 15));

        UserReview userReview2 = new UserReview(252L,3L,"24d9e003-aa1c-459e-899c-415999643999","This is a review text",Rate.FIVE,LocalDate.of(2024, 03, 15));;


        List<UserReview> userReviewList = Arrays.asList(userReview1, userReview2);

        RestaurantDTO restaurantDTO = new RestaurantDTO(
                "24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/",
                LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 0.0, 39.7702467, 21.1828612
        );
        UserDTO userDTO1 = new UserDTO(2L, "Alice", "Smith", 40.7128, -74.006);
        UserDTO userDTO2 = new UserDTO(3L, "John", "Doe", 51.5074, -0.1278);

        when(userReviewEntityService.findByRestaurantIdUserReviews(restaurantId)).thenReturn(userReviewList);
        when(userClientHelper.getUserDetails(userReview1.getUserId())).thenReturn(userDTO1);
        when(userClientHelper.getUserDetails(userReview2.getUserId())).thenReturn(userDTO2);
        when(restaurantClientHelper.getRestaurantDetails(restaurantId)).thenReturn(restaurantDTO);

        // When
        List<UserReviewDTO> result = userReviewControllerContractImpl.findByResturantId(restaurantId);

        assertEquals(2, result.size());
    }
}

