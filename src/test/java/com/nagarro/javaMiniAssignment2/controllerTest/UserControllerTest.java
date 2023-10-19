package com.nagarro.javaMiniAssignment2.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.javaMiniAssignment2.constants.Constant;
import com.nagarro.javaMiniAssignment2.controller.UserController;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.services.UserService;
import com.nagarro.javaMiniAssignment2.services.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
//@WebMvcTest(UserController.class)
//@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@MockBean
	private UserServiceImpl userService;
	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		// You can leave this method empty when using the MockitoExtension
	}

	@Test
	public void testCreateUser() {
		int size = 5;
		List<User> users = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			User user = new User(); // Assuming you have a User class
			user.setUserId((long) i);
			user.setName("User" + i);
			users.add(user);
		}

		Mockito.when(userService.createUserWithExecutor(size)).thenReturn(users);

		// Act
		List<User> createdUsers = createSampleUsers(size);
		

		// Assert
		assertEquals(size, createdUsers.size());
		assertEquals(users, createdUsers);
		Mockito.verify(userService).createUserWithExecutor(size);

	}

	// Helper method to create sample users for testing
	private List<User> createSampleUsers(int size) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			User user = new User();
			user.setUserId((long) i);
			user.setName("User"+i);
//			user.setFirstName("Irma" + i);
//			user.setLastName("Schönberger");
//			user.setAge(48);
//			user.setGender("female");
//			user.setDob("1975-08-19T04:12:40.553Z");
//			user.setNationality("DE");
//			user.setName(user.getFirstName() + " " + user.getLastName());
//			user.setNameCharacters(user.getName().length());
//			user.setVerificationStatus(Constant.TO_BE_VERIFIED);
			users.add(user);
		}
		return users;
	}
}
