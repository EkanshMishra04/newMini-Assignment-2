package com.nagarro.javaMiniAssignment2.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.repository.UserRepository;
import com.nagarro.javaMiniAssignment2.validators.factory.ValidatorFactory;
import com.nagarro.javaMiniAssignment2.validators.impl.EnglishAlphabetsValidator;
import com.nagarro.javaMiniAssignment2.validators.impl.NumericValidator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

public class UserServicesImplTest {
	@Mock
    private UserRepository userRepo;
    @Mock
    private WebClient api1WebClient;
    @Mock
    private WebClient.Builder api2WebClientBuilder;
    @Mock
    private WebClient.Builder api3WebClientBuilder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    static void doBeforeAll(){
        mockStatic(ValidatorFactory.class);
    }

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<User> users=new ArrayList<>();
        User user=new User();
        user.setName("name");
        user.setUserId(1L);
        user.setGender("Male");
        user.setFirstName("first-name");
        user.setLastName("last-name");
        user.setNameCharacters(4);
        user.setAge(25);
        users.add(user);
        Mockito.when(userRepo.findAll()).thenReturn(users);
    }

    @Test
    public void testCreateUserWithExecutor_SizeGreaterThan5_ThrowsCustomException() {
        int size = 6;
        assertThrows(CustomException.class, () -> userService.createUserWithExecutor(size));
    }

    @Test
    void getAllUserAfterValidations() {
        Mockito.when(ValidatorFactory.getValidator("odd")).thenReturn(EnglishAlphabetsValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("name")).thenReturn(EnglishAlphabetsValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("4")).thenReturn(NumericValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("0")).thenReturn(NumericValidator.getInstance());
        userService.getAllUserAfterValidations("name","odd","4","0");
    }


    @Test
    public void getAllUserListWithPaginationInfo() {
        Mockito.when(ValidatorFactory.getValidator("odd")).thenReturn(EnglishAlphabetsValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("name")).thenReturn(EnglishAlphabetsValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("4")).thenReturn(NumericValidator.getInstance());
        Mockito.when(ValidatorFactory.getValidator("0")).thenReturn(NumericValidator.getInstance());
//        PaginationResponse response=userService.getAllUserListWithPaginationInfo("name","odd","4","0");
        assertThrows(CustomException.class, () -> userService.getAllUserListWithPaginationInfo("name","odd","4","0"));
//        System.out.println(response);
    }

}
