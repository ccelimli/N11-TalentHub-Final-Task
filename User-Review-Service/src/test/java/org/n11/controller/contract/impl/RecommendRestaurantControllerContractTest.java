package org.n11.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.controller.contract.impl.helper.Algorithm;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.controller.contract.impl.helper.clientHelper.UserClientHelper;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.RecommendRestaurantRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class RecommendRestaurantControllerContractTest {
    @Mock
    private RestaurantClientHelper restaurantClientHelper;

    @Mock
    private UserClientHelper userClientHelper;

    @Mock
    private UserReviewControllerContract userReviewControllerContract;

    @InjectMocks
    private RecommendRestaurantControllerContractImpl recommendRestaurantControllerContractImpl;

    @Test
    public void shouldRecommendRestaurants() {
        // Given
        RecommendRestaurantRequest recommendRestaurantRequest = new RecommendRestaurantRequest(1L, 80000.0);
        RestaurantDTO restaurantDTO1 = new RestaurantDTO("24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/", LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 1.0, 39.7702467, 21.1828612);
        RestaurantDTO restaurantDTO2 = new RestaurantDTO("86e3449c-ac8e-417d-8a08-cf3414fbe7b4", "New Restaurant","Mon Jan 08 06:39:48 TRT 2024","50 Eastlawn Point", "http://prweb.com",LocalTime.of(05,00,00),LocalTime.of(11,36,00),"CLOSE",1.0,40.7129, -74.007);
        List<RestaurantDTO> restaurantDTOS = Arrays.asList(restaurantDTO1, restaurantDTO2);

        UserDTO userDTO = new UserDTO(1L, "John", "Doe", 40.7128, -74.006);
        List<UserReviewDTO> userReviewDTOs = Arrays.asList(
                new UserReviewDTO(252L,"Alice Smith",  "Jessica O'Henery","This is a review text", 1, LocalDate.of(2024,03,15)),
                new UserReviewDTO(2L, "Good service.","New Restaurant",  "This is a review text",1,LocalDate.of(2024,03,15))
        );

        when(restaurantClientHelper.getAllRestaurant()).thenReturn(restaurantDTOS);
        when(userReviewControllerContract.findByResturantId(restaurantDTO1.id())).thenReturn(userReviewDTOs);
        when(userReviewControllerContract.findByResturantId(restaurantDTO2.id())).thenReturn(userReviewDTOs);
        when(userClientHelper.getUserDetails(recommendRestaurantRequest.userId())).thenReturn(userDTO);

        // When
        List<RestaurantDTO> result = recommendRestaurantControllerContractImpl.recommendRestaurants(recommendRestaurantRequest);

        // Then

        InOrder inOrder=Mockito.inOrder(userReviewControllerContract,restaurantClientHelper,userClientHelper);
        inOrder.verify(restaurantClientHelper,times(1)).getAllRestaurant();
        inOrder.verify(userReviewControllerContract,times(1)).findByResturantId(restaurantDTO1.id());
        inOrder.verify(userReviewControllerContract, times(1)).findByResturantId(restaurantDTO2.id());
        inOrder.verify(userClientHelper, times(1)).getUserDetails(recommendRestaurantRequest.userId());
        inOrder.verifyNoMoreInteractions();
        assertEquals(2, result.size());
    }

    @Test
    public void shouldTestRestaurants() {
        // Given
        RestaurantDTO restaurantDTO1 = new RestaurantDTO("24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/", LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 1.0, 39.7702467, 21.1828612);
        RestaurantDTO restaurantDTO2 = new RestaurantDTO("86e3449c-ac8e-417d-8a08-cf3414fbe7b4", "New Restaurant","Mon Jan 08 06:39:48 TRT 2024","50 Eastlawn Point", "http://prweb.com",LocalTime.of(05,00,00),LocalTime.of(11,36,00),"CLOSE",1.0,40.7129, -74.007);
        List<RestaurantDTO> restaurantDTOS = Arrays.asList(restaurantDTO1, restaurantDTO2);

        List<UserReviewDTO> userReviewDTOs = Arrays.asList(
                new UserReviewDTO(252L,"Alice Smith",  "Jessica O'Henery","This is a review text", 1, LocalDate.of(2024,03,15)),
                new UserReviewDTO(2L, "Good service.","New Restaurant",  "This is a review text",1,LocalDate.of(2024,03,15))
        );

        when(restaurantClientHelper.getAllRestaurant()).thenReturn(restaurantDTOS);
        when(userReviewControllerContract.findByResturantId(restaurantDTO1.id())).thenReturn(userReviewDTOs);
        when(userReviewControllerContract.findByResturantId(restaurantDTO2.id())).thenReturn(userReviewDTOs);

        // When
        List<RestaurantDTO> result = recommendRestaurantControllerContractImpl.getAllRestaurants();

        // Then
        assertEquals(2, result.size());
    }
}
