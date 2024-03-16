package org.n11.controller.contract.impl.helper.clientHelper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.utilities.client.RestaurantServiceClient;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class RestaurantClientHelperTest {
    @Mock
    private RestaurantServiceClient restaurantServiceClient;
    @InjectMocks
    private RestaurantClientHelper restaurantClientHelper;

    @Test
    void testGetRestaurantDetails() {
        //Given
        RestaurantDTO restaurantDTO = new RestaurantDTO("24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/", LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 1.0, 39.7702467, 21.1828612);

        RestResponse<RestaurantDTO> response = new RestResponse<>();
        response.setData(restaurantDTO);
        ResponseEntity<RestResponse<RestaurantDTO>> responseEntity = ResponseEntity.ok(response);

        //When
        Mockito.when(restaurantServiceClient.getRestaurantById("24d9e003-aa1c-459e-899c-415999643999")).thenReturn(responseEntity);
        RestaurantDTO result = restaurantClientHelper.getRestaurantDetails("24d9e003-aa1c-459e-899c-415999643999");

        //Then
        InOrder inOrder=Mockito.inOrder(restaurantServiceClient);
        inOrder.verify(restaurantServiceClient,times(1)).getRestaurantById("24d9e003-aa1c-459e-899c-415999643999");
        inOrder.verifyNoMoreInteractions();
        assertNotNull(result);
        assertEquals("24d9e003-aa1c-459e-899c-415999643999", result.id());
        assertEquals("Jessica O'Henery", result.name());
    }

    @Test
    void testGetAllRestaurant() {
        //Given
        RestaurantDTO restaurantDTO1 = new RestaurantDTO("24d9e003-aa1c-459e-899c-415999643999", "Jessica O'Henery", "Mon Jun 12 08:10:20 TRT 2023", "03119 Morrow Alley", "http://webnode.com/", LocalTime.of(03, 39, 00), LocalTime.of(21, 04, 00), "OPEN", 1.0, 39.7702467, 21.1828612);
        RestaurantDTO restaurantDTO2 = new RestaurantDTO("86e3449c-ac8e-417d-8a08-cf3414fbe7b4", "New Restaurant","Mon Jan 08 06:39:48 TRT 2024","50 Eastlawn Point", "http://prweb.com",LocalTime.of(05,00,00),LocalTime.of(11,36,00),"CLOSE",1.0,40.7129, -74.007);
        List<RestaurantDTO> restaurantDTOS = Arrays.asList(restaurantDTO1, restaurantDTO2);

        RestResponse<List<RestaurantDTO>> response = new RestResponse<>();
        response.setData(restaurantDTOS);

        ResponseEntity<RestResponse<List<RestaurantDTO>>> responseEntity = ResponseEntity.ok(response);

        //When
        Mockito.when(restaurantServiceClient.getAllRestaurants()).thenReturn(responseEntity);
        List<RestaurantDTO> result = restaurantClientHelper.getAllRestaurant();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("24d9e003-aa1c-459e-899c-415999643999", result.get(0).id());
        assertEquals("Jessica O'Henery", result.get(0).name());
        assertEquals("86e3449c-ac8e-417d-8a08-cf3414fbe7b4", result.get(1).id());
        assertEquals("New Restaurant", result.get(1).name());
    }
}
