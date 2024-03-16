package org.n11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.UserReviewServiceApplication;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserReviewServiceApplication.class})
public class UserReviewControllerTest extends BaseControllerTest{
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc=MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper= new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldFindAllUserReview() throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success=isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldGetByIdUserReview() throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user-reviews/252"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success=isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldSaveUserReview() throws Exception{
        UserReviewSaveRequest userReviewSaveRequest= new UserReviewSaveRequest(2L, "f3cb20bb-ac33-412d-84a7-1a757e2f9174", "Great food!", 5);
        String requestAsString= objectMapper.writeValueAsString(userReviewSaveRequest);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user-reviews")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success=isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDeleteUserReview()throws Exception{

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user-reviews/252"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        boolean success=isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdateText() throws Exception{
        UserReviewUpdateTextRequest userReviewUpdateTextRequest= new UserReviewUpdateTextRequest(252L,4, "New Context");

        String requstAsString=objectMapper.writeValueAsString(userReviewUpdateTextRequest);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/user-reviews/252")
                    .content(requstAsString)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success=isSuccess(mvcResult);
        assertTrue(success);

    }
}
