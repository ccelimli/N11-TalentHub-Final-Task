package org.n11.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.constant.CountryCode;
import org.n11.constant.Messages;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.enums.Gender;
import org.n11.entity.enums.Status;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;
import org.n11.service.UserEntityService;
import org.n11.service.mapper.UserMapper;
import org.n11.utilities.exceptions.ItemNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerContractImplTest {
    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserControllerContractImpl userControllerContractImpl;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void shouldSave() {
        // Given
        UserSaveRequest userSaveRequest = new UserSaveRequest(
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "john.doe@example.com",
                "1234567890",
                CountryCode.US,
                "johndoe",
                "password123",
                Gender.MALE,
                0.0,
                0.0
        );

        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        UserDTO expectedUserDTO = UserMapper.INSTANCE.convertToDTO(user);

        // When
        when(userEntityService.save(any(User.class))).thenReturn(user);

        UserDTO result = userControllerContractImpl.save(userSaveRequest);

        // Then
        verify(userEntityService, times(1)).save(any(User.class));
        assertEquals(expectedUserDTO, result);
    }

    @Test
    public void shouldUpdate() {
        // Given
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(1L, "John", "Doe", LocalDate.now(), "valid@email.com", CountryCode.DE, "8123456789", "username", "password", Gender.MALE, 0.0, 0.0);
        User user = new User();
        user.setId(1L);

        // When
        Mockito.when(userEntityService.findByIdWithControl(userUpdateRequest.id())).thenReturn(user);
        UserDTO result = userControllerContractImpl.update(userUpdateRequest);

        // Then
        InOrder inOrder = Mockito.inOrder(userEntityService);

        inOrder.verify(userEntityService, times(1)).findByIdWithControl(userUpdateRequest.id());
        inOrder.verify(userEntityService, times(1)).save(userCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        assertEquals(result.id(), user.getId());
        assertEquals(result.firstName(), user.getFirstName());
        assertEquals(result.lastName(), user.getLastName());
        assertEquals(result.birthDate(), user.getBirthDate());
        assertEquals(result.email(), user.getEmail());
        assertEquals(result.phoneNumber(), user.getPhoneNumber());
        assertEquals(result.username(), user.getUsername());
        assertEquals(result.gender(), user.getGender());
        assertEquals(result.latitude(), user.getLatitude());
        assertEquals(result.longitude(), user.getLongitude());
    }

    @Test
    public void shouldGetAllUsers(){
        // Given
        User firstUser = new User();
        firstUser.setId(1L);
        firstUser.setFirstName("John");
        firstUser.setLastName("Doe");
        firstUser.setStatus(Status.ACTIVE);

        User secondUser = new User();
        secondUser.setId(2L);
        secondUser.setFirstName("Jane");
        secondUser.setLastName("Doe");
        secondUser.setStatus(Status.DEACTIVE);

        List<User> userList = List.of(firstUser, secondUser);

        Mockito.when(userEntityService.findAll()).thenReturn(userList);

        List<UserDTO> tempUserDTOs = userList.stream()
                .filter(user -> user.getStatus() == Status.ACTIVE)
                .map(UserMapper.INSTANCE::convertToDTO)
                .collect(Collectors.toList());

        // When
        List<UserDTO> result = userControllerContractImpl.findAll();

        // Then
        assertEquals(tempUserDTOs, result);
        verify(userEntityService, times(1)).findAll();
    }

    @Test
    public void shouldFindById(){
        // Given
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");

        UserDTO expectedUserDTO = UserMapper.INSTANCE.convertToDTO(user);

        Mockito.when(userEntityService.findByIdWithControl(id)).thenReturn(user);

        // When
        UserDTO result = userControllerContractImpl.findById(id);

        // Then
        assertEquals(expectedUserDTO, result);
        verify(userEntityService, times(1)).findByIdWithControl(id);
    }

    @Test
    public void shouldFindByDeactiveUsers(){
        // Given
        User firstUser = new User();
        firstUser.setId(1L);
        firstUser.setFirstName("John");
        firstUser.setLastName("Doe");
        firstUser.setStatus(Status.ACTIVE);

        User secondUser = new User();
        secondUser.setId(2L);
        secondUser.setFirstName("Jane");
        secondUser.setLastName("Doe");
        secondUser.setStatus(Status.DEACTIVE);

        List<User> userList = List.of(firstUser, secondUser);

        Mockito.when(userEntityService.findAll()).thenReturn(userList);

        List<UserDTO> tempUserDTOs = userList.stream()
                .filter(user -> user.getStatus() == Status.DEACTIVE)
                .map(user -> UserMapper.INSTANCE.convertToDTO(user))
                .collect(Collectors.toList());

        // When
        List<UserDTO> result = userControllerContractImpl.findAllByDeactive();

        // Then
        assertEquals(tempUserDTOs, result);
        verify(userEntityService, times(1)).findAll();

    }

    @Test
    public void shouldDelete() {
        // Given
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");


        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(user);
        String result = userControllerContractImpl.delete(id);

        // Then
        assertEquals(Messages.USER_DELETED.getMessage(), result);
        verify(userEntityService, times(1)).findByIdWithControl(id);
        verify(userEntityService, times(1)).delete(user);
    }

    @Test
    public void shouldUserNotFound_Delete() {
        // Given
        Long id = 156L;

        when(userEntityService.findByIdWithControl(id)).thenReturn(null);

        // When
        assertThrows(ItemNotFoundException.class, () -> userControllerContractImpl.delete(id));

        // Then
        verify(userEntityService, times(1)).findByIdWithControl(id);
        verify(userEntityService, times(0)).delete(any(User.class)); // Ensure delete is not called
    }
    @Test
    public void shouldUserNotFound_Active() {
        // Given
        Long id = 156L;


        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(null);

        // Then
        assertThrows(ItemNotFoundException.class, () -> userControllerContractImpl.active(id));
        verify(userEntityService, times(1)).findByIdWithControl(id);
    }
    @Test
    public void shouldUserNotFound_Deactive() {
        // Given
        Long id = 156L;

        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(null);

        // Then
        assertThrows(ItemNotFoundException.class, () -> userControllerContractImpl.deactive(id));
        verify(userEntityService, times(1)).findByIdWithControl(id);
    }
    @Test
    public void shouldUserNotFound_Update() {
        // Given
        Long id = 156L;

        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(null);

        // Then
        assertThrows(ItemNotFoundException.class, () -> userControllerContractImpl.delete(id));
        verify(userEntityService, times(1)).findByIdWithControl(id);
    }
    @Test
    public void shouldUserNotFound_FindByIdInDeactive() {
        // Given
        Long id = 156L;

        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(null);

        // Then
        assertThrows(ItemNotFoundException.class, () -> userControllerContractImpl.delete(id));
        verify(userEntityService, times(1)).findByIdWithControl(id);
    }

    @Test
    public void shouldFindByIdInDeactive(){
        // Given
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setStatus(Status.DEACTIVE);

        // When
        when(userEntityService.findByIdWithControl(id)).thenReturn(user);
        when(userEntityService.findAll()).thenReturn(List.of(user));

        UserDTO result = userControllerContractImpl.findByIdInDeactive(id); // Replace methodName with the actual method name

        // Then
        assertNotNull(result);
        assertEquals(id, result.id());
    }
}
