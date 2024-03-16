package org.n11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public class BaseControllerTest {
    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();

        RestResponse restResponse = objectMapper.readValue(content, RestResponse.class);

        boolean success = restResponse.isSuccess();
        return success;
    }
}
