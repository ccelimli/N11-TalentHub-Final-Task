package org.n11.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.controller.contract.impl.UserControllerContractImpl;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.enums.Gender;
import org.n11.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserControllerContractImpl userControllerContractImpl; // Assuming UserService is the class containing the active method

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void testActive() {

    }
}