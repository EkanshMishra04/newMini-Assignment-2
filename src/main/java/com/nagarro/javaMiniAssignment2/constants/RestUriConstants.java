package com.nagarro.javaMiniAssignment2.constants;

public interface RestUriConstants {

	String GET_RANDOM_USERS = "https://randomuser.me/api/";
	String GET_USER_NATIONALITY = "https://api.nationalize.io/?name=";
	String GET_USER_GENDER = "https://api.genderize.io/?name=";

	String BASE_URL = "/";
	String CREATE_USER = "/users";
	String GET_ALL_USERS = "/users";
	String GET_USERS_LIST_WITH_PAGINATION_INFO = "/listuser";
}
