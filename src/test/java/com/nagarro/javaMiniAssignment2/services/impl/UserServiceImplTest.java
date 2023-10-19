package com.nagarro.javaMiniAssignment2.services.impl;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import com.nagarro.javaMiniAssignment2.repository.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {


    @Mock
    private UserRepositoryInterface userRepository;
    @Mock
    private WebClient api1WebClient;
    @Mock
    private WebClient.Builder api2WebClientBuilder;
    @Mock
    private WebClient.Builder api3WebClientBuilder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserWithExecutor_SizeGreaterThan5_ThrowsCustomException() {
        int size = 6;
        assertThrows(CustomException.class, () -> userService.createUserWithExecutor(size));
    }

    @Test
    void createUserWithExecutor() {
    }

    @Test
    void getAllUserAfterValidations() {
    }

    @Test
    void getAllUserListWithPaginationInfo() {
    }
}