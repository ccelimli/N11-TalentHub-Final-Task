package org.n11.controller.contract.impl.helper.clientHelper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.utilities.client.UserServiceClient;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;

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
public class UserClientHelperTest {
    @Mock
    private UserServiceClient userServiceClient;
    @InjectMocks
    private UserClientHelper userClientHelper;

    @Test
    void shouldGetUserDetails() {
        UserDTO userDTO = new UserDTO(1L, "John", "Doe", 40.7128, -74.006);


        RestResponse<UserDTO> response = new RestResponse<>();
        response.setData(userDTO);
        ResponseEntity<RestResponse<UserDTO>> responseEntity = ResponseEntity.ok(response);

        Mockito.when(userServiceClient.getUserById(1L)).thenReturn(responseEntity);
        UserDTO result= userClientHelper.getUserDetails(1L);


        InOrder inOrder = Mockito.inOrder(userServiceClient);
        inOrder.verify(userServiceClient,times(1)).getUserById(1L);
        inOrder.verifyNoMoreInteractions();
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("John", result.firstName());
    }
}
