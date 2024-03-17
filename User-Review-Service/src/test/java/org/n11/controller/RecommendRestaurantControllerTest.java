package org.n11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.UserReviewServiceApplication;
import org.n11.entity.request.RecommendRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserReviewServiceApplication.class})
public class RecommendRestaurantControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldGetAllRestaurants() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommend-restaurants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success = isSuccess(mvcResult);
        assertTrue(success);

    }

    @Test
    void shouldRecommendRestaurants() throws Exception {
        RecommendRestaurantRequest recommendRestaurantRequest = new RecommendRestaurantRequest(1L, 10.0);
        String requestAsString = objectMapper.writeValueAsString(recommendRestaurantRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommend-restaurants/2")
                    .content(requestAsString)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success=isSuccess(mvcResult);
        assertTrue(success);
    }
}
