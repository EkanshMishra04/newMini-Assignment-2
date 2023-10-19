package com.nagarro.javaMiniAssignment2.services;

import java.util.List;

import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.models.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

	List<User> createUserWithExecutor(Integer size);

	List<User> getAllUserAfterValidations(String sortType, String sortOrder, String limit, String offset);

	PaginationResponse getAllUserListWithPaginationInfo(String sortType, String sortOrder, String limit, String offset);
}
