package com.nagarro.javaMiniAssignment2.controller;

import java.util.List;

import com.nagarro.javaMiniAssignment2.constants.RestUriConstants;
import com.nagarro.javaMiniAssignment2.constants.ServiceConstants;
import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.services.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestUriConstants.BASE_URL)
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping(RestUriConstants.CREATE_USER)
	public List<User> createUser(@RequestParam(value = ServiceConstants.SIZE, defaultValue = ServiceConstants.DEFAULT_SIZE) Integer size) {
		return userService.createUserWithExecutor(size);
	}

	@GetMapping(RestUriConstants.GET_ALL_USERS)
	public ResponseEntity<List<User>> getAllUsers(
			@RequestParam(name = ServiceConstants.SORT_TYPE, defaultValue = ServiceConstants.DEFAULT_SORT_TYPE) String sortType,
			@RequestParam(name = ServiceConstants.SORT_ORDER, defaultValue = ServiceConstants.DEFAULT_SORT_ORDER) String sortOrder,
			@RequestParam(name = ServiceConstants.LIMIT, defaultValue = ServiceConstants.DEFAULT_LIMIT) String limit,
			@RequestParam(name = ServiceConstants.OFFSET, defaultValue = ServiceConstants.DEFAULT_OFFSET) String offset) {
		List<User> users = userService.getAllUserAfterValidations(sortType, sortOrder, limit, offset);
		return ResponseEntity.ok(users);
	}

	@GetMapping(RestUriConstants.GET_USERS_LIST_WITH_PAGINATION_INFO)
	public ResponseEntity<PaginationResponse> getAllUserListWithPaginationInfo(
			@RequestParam(name = ServiceConstants.SORT_TYPE, defaultValue = ServiceConstants.DEFAULT_SORT_TYPE) String sortType,
			@RequestParam(name = ServiceConstants.SORT_ORDER, defaultValue = ServiceConstants.DEFAULT_SORT_ORDER) String sortOrder,
			@RequestParam(name = ServiceConstants.LIMIT, defaultValue = ServiceConstants.DEFAULT_LIMIT) String limit,
			@RequestParam(name = ServiceConstants.OFFSET, defaultValue = ServiceConstants.DEFAULT_OFFSET) String offset) {
		return ResponseEntity.ok(userService.getAllUserListWithPaginationInfo(sortType, sortOrder, limit, offset));
	}

}