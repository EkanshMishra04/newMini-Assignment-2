package com.nagarro.javaMiniAssignment2.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.javaMiniAssignment2.constants.ServiceConstants;
import com.nagarro.javaMiniAssignment2.controller.UserController;
import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import com.nagarro.javaMiniAssignment2.helper.Helper;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.services.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	private UserServiceImpl userService;
	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
	}

	@Test
	void testSizeOutOfRange() {
		Integer size = 6;
		Mockito.when(userService.createUserWithExecutor(size))
				.thenThrow(new CustomException("Size cannot have value greater than 5", 400));

		assertThrows(CustomException.class, () -> userService.createUserWithExecutor(size));
	}

	@Test
	void testCreateUserException() throws Exception {
		when(userService.createUserWithExecutor(anyInt()))
				.thenThrow(new CustomException("Custom Exception Message", HttpStatus.BAD_REQUEST.value()));

		ResultActions result = mockMvc
				.perform(post("/users").param("size", "4").contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isBadRequest()).andExpect(content().string("Custom Exception Message"));
	}

	@Test
	void testCreateUser_Success() throws Exception {
		List<User> users = new ArrayList<>();
		// Mock the userService to return a list of users
		when(userService.createUserWithExecutor(anyInt())).thenReturn(users);

		ResultActions result = mockMvc.perform(post("/users").param("size", "3") // You can change the size as needed
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void testCreateUserSuccessWithUserList() throws Exception {
		Integer size = 3;
		List<User> expectedUsers = createSampleUsers(size);

		when(userService.createUserWithExecutor(anyInt())).thenReturn(expectedUsers);

		ResultActions result = mockMvc
				.perform(post("/users").param("size", "3").contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

		String responseContent = result.andReturn().getResponse().getContentAsString();
		List<User> actualUsers = new ObjectMapper().readValue(responseContent, new TypeReference<List<User>>() {
		});
		assertEquals(expectedUsers, actualUsers);

	}
	
	private List<User> createSampleUsers(Integer size) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			User user = new User();
			user.setUserId((long) i);
			user.setName("User" + i);
			user.setFirstName("Irma" + i);
			user.setLastName("Schonberger");
			user.setAge(48);
			user.setGender("female");
			user.setDob("1975-08-19T04:12:40.553Z");
			user.setNationality("DE");
			user.setName(user.getFirstName() + " " + user.getLastName());
			user.setNameCharacters(user.getName().length());
			user.setVerificationStatus(ServiceConstants.TO_BE_VERIFIED);
			users.add(user);
		}
		return users;
	}

	
	
	
	
	// getListUsers
	@Test
    public void testGetAllUserListWithPaginationInfo() throws Exception {
        // Create a sample PaginationResponse
        PaginationResponse paginationResponse = createSamplePaginationResponse();

        // Mock the UserService to return the sample PaginationResponse
        Mockito.when(userService.getAllUserListWithPaginationInfo(any(), any(), any(), any())).thenReturn(paginationResponse);

        // Perform a GET request to your endpoint
        ResultActions result = mockMvc.perform(get("/listuser")
                .param("sortType", "name")
                .param("sortOrder", "Odd")
                .param("limit", "5")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        String responseContent = result.andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        PaginationResponse actualResponse = objectMapper.readValue(responseContent, new TypeReference<PaginationResponse>() {});
        assertEquals(paginationResponse, actualResponse);
    }
    private PaginationResponse createSamplePaginationResponse() {
        PaginationResponse paginationResponse = new PaginationResponse();
        return paginationResponse;
    }

}
