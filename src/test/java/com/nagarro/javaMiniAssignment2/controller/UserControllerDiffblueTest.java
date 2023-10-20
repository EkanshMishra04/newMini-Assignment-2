package com.nagarro.javaMiniAssignment2.controller;

import static org.mockito.Mockito.when;

import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.services.impl.UserServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserController#createUser(Integer)}
     */
    @Test
    void testCreateUser() throws Exception {
        when(userServiceImpl.createUserWithExecutor(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/users");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("size", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getAllUserListWithPaginationInfo(String, String, String, String)}
     */
    @Test
    void testGetAllUserListWithPaginationInfo() throws Exception {
        when(userServiceImpl.getAllUserListWithPaginationInfo(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(new PaginationResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listuser")
                .param("limit", "foo")
                .param("offset", "foo")
                .param("sortOrder", "foo")
                .param("sortType", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"data\":null,\"pageInfo\":null}"));
    }

    /**
     * Method under test: {@link UserController#getAllUsers(String, String, String, String)}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(userServiceImpl.getAllUserAfterValidations(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .param("limit", "foo")
                .param("offset", "foo")
                .param("sortOrder", "foo")
                .param("sortType", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

