package com.nagarro.javaMiniAssignment2.helper;

import com.nagarro.javaMiniAssignment2.constants.Constant;
import com.nagarro.javaMiniAssignment2.dto.UserResponse;
import com.nagarro.javaMiniAssignment2.models.User;

public class Helper {
	
	public static User jsonToEntityMapper(UserResponse results) {
		User user = new User();
		user.setFirstName(results.getName().getFirst());
		user.setLastName(results.getName().getLast());
		user.setName(results.getName().getFirst() + " " + results.getName().getLast());
		user.setAge(results.getDob().getAge());
		user.setDob(results.getDob().getDate());
		user.setGender(results.getGender());
		user.setNationality(results.getNat());
		user.setNameCharacters(user.getName().length());
		user.setVerificationStatus(Constant.TO_BE_VERIFIED);
		user.setDateCreated(results.getRegistered().getDate());
		user.setDateModified(results.getRegistered().getDate());
		return user;
	}

}
