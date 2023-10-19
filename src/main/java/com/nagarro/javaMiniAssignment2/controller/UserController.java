package com.nagarro.javaMiniAssignment2.controller;

import java.util.List;

import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/users")
	public List<User> createUser(@RequestParam(value = "size", defaultValue = "1") Integer size) {
		return userService.createUserWithExecutor(size);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "sortType", defaultValue = "name") String sortType,
			@RequestParam(name = "sortOrder", defaultValue = "Odd") String sortOrder,
			@RequestParam(name = "limit", defaultValue = "5") String limit,
			@RequestParam(name = "offset", defaultValue = "0") String offset) {
		List<User> users = userService.getAllUserAfterValidations(sortType, sortOrder, limit, offset);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/listuser")
	public ResponseEntity<PaginationResponse> getAllUserListWithPaginationInfo(
			@RequestParam(name = "sortType", defaultValue = "name") String sortType,
			@RequestParam(name = "sortOrder", defaultValue = "Odd") String sortOrder,
			@RequestParam(name = "limit", defaultValue = "5") String limit,
			@RequestParam(name = "offset", defaultValue = "0") String offset) {
		return ResponseEntity.ok(userService.getAllUserListWithPaginationInfo(sortType, sortOrder, limit, offset));
	}

}