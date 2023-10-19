package com.nagarro.javaMiniAssignment2.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nagarro.javaMiniAssignment2.constants.ServiceConstants;
import com.nagarro.javaMiniAssignment2.constants.RestUriConstants;
import com.nagarro.javaMiniAssignment2.dto.JsonUserResponse;
import com.nagarro.javaMiniAssignment2.dto.PaginationResponse;
import com.nagarro.javaMiniAssignment2.dto.UserGenderRequest;
import com.nagarro.javaMiniAssignment2.dto.UserNationalityRequest;
import com.nagarro.javaMiniAssignment2.dto.UserResponse;
import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import com.nagarro.javaMiniAssignment2.exceptions.ExecutionException;
import com.nagarro.javaMiniAssignment2.exceptions.InterruptedException;
import com.nagarro.javaMiniAssignment2.exceptions.ReadTimeoutException;
import com.nagarro.javaMiniAssignment2.helper.Helper;
import com.nagarro.javaMiniAssignment2.models.PaginationInfo;
import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.repository.UserRepositoryInterface;
import com.nagarro.javaMiniAssignment2.services.UserService;
import com.nagarro.javaMiniAssignment2.sortStrategies.impl.AgeEvenSortStrategy;
import com.nagarro.javaMiniAssignment2.sortStrategies.impl.AgeOddSortStrategy;
import com.nagarro.javaMiniAssignment2.sortStrategies.impl.NameEvenSortStrategy;
import com.nagarro.javaMiniAssignment2.sortStrategies.impl.NameOddSortStrategy;
import com.nagarro.javaMiniAssignment2.sortStrategies.SortContext;
import com.nagarro.javaMiniAssignment2.validators.Validator;
import com.nagarro.javaMiniAssignment2.validators.factory.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoryInterface userRepo;

	@Autowired
	private WebClient api1WebClient;

	@Autowired
	@Qualifier("api2WebClient")
	private WebClient.Builder api2WebClientBuilder;

	@Autowired
	@Qualifier("api3WebClient")
	private WebClient.Builder api3WebClientBuilder;

	// Method 1
	@Override
	public List<User> createUserWithExecutor(Integer size) {
		if (size > 5) {
			throw new CustomException("Size cannot have value greater than 5", 400);
		}
		List<User> users = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			users.add(addNewUserDataFromApis());
		}
		return users;
	}

	// Method 2 helper for method 3
	@Override
	public List<User> getAllUserAfterValidations(String sortType, String sortOrder, String limit, String offset) {
		validateParameters(sortType, sortOrder, limit, offset);
		return getAllUsers(sortType, sortOrder, Integer.parseInt(limit), Integer.parseInt(offset));
	}

	// Method 3
	@Override
	public PaginationResponse getAllUserListWithPaginationInfo(String sortType, String sortOrder, String limit,
			String offset) {
		List<User> users = getAllUserAfterValidations(sortType, sortOrder, limit, offset);
		return getUserListWithPaginationDetail(users, Integer.parseInt(limit), Integer.parseInt(offset));

	}

	// Helper for method 1
	private User addNewUserDataFromApis() {

		User userMono = api1Caller();
		if (userMono == null || userMono.getFirstName() == null) {
			throw new CustomException("User Api error", 404);
		}

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		try {

			Future<UserNationalityRequest> nationalityRequestFuture = executorService.submit(() -> {
				return api2WebClientBuilder.baseUrl(RestUriConstants.GET_USER_NATIONALITY + userMono.getFirstName()).build()
						.get().retrieve().bodyToMono(UserNationalityRequest.class).block();
			});

			Future<UserGenderRequest> genderRequestFuture = executorService.submit(() -> {
				return api3WebClientBuilder.baseUrl(RestUriConstants.GET_USER_GENDER + userMono.getFirstName()).build().get()
						.retrieve().bodyToMono(UserGenderRequest.class).block();
			});

			UserNationalityRequest nationalityRequestMono = nationalityRequestFuture.get();
			UserGenderRequest genderRequestMono = genderRequestFuture.get();

			if (nationalityRequestMono.getCountry().equals(new ArrayList<>())
					|| genderRequestMono.getGender() == null) {

				userMono.setVerificationStatus(ServiceConstants.TO_BE_VERIFIED);
			} else {

				boolean flag1 = nationalityRequestMono.getCountry().stream()
						.anyMatch(c -> c.getCountry_id().equalsIgnoreCase(userMono.getNationality()));
				boolean flag2 = genderRequestMono.getGender().equalsIgnoreCase(userMono.getGender());

				if (flag1 && flag2) {
					userMono.setVerificationStatus(ServiceConstants.VERIFIED);
				}
			}
		} catch (ExecutionException e) {
			throw new ExecutionException("Concurrent Execution Error", 408);
		}  catch (InterruptedException e) {
			throw new ExecutionException("Concurrent Execution Error due to interruptions", 408);
		} 
		 catch (ReadTimeoutException e) {
				throw new ExecutionException("ReadTimeout Execution Error", 408);
			} 
		catch (Exception e) {
			throw new CustomException("Bad Request connection timed out", 401);
		} finally {
			executorService.shutdown();
		}
		return userRepo.save(userMono);
	}

	// Api 1 caller
	private User api1Caller() {

		return api1WebClient.get().retrieve().bodyToMono(JsonUserResponse.class).map(response -> {

			UserResponse results = response.getResults();

			return Helper.jsonToEntityMapper(results);
		}).block();
	}

	// helper for method 2
	private void validateParameters(String sortType, String sortOrder, String limit, String offset) {
		Validator sortTypeValidator = ValidatorFactory.getValidator(sortType);
		Validator sortOrderValidator = ValidatorFactory.getValidator(sortOrder);
		Validator limitValidator = ValidatorFactory.getValidator(limit);
		Validator offsetValidator = ValidatorFactory.getValidator(offset);

		if (!sortTypeValidator.validate(sortType, ServiceConstants.SORT_TYPE)) {
			throw new CustomException("sortType can only have values Name or Age", HttpStatus.BAD_REQUEST.value());
		}
		if (!sortOrderValidator.validate(sortOrder, ServiceConstants.SORT_ORDER)) {
			throw new CustomException("sortOrder can only have values Even or Odd", HttpStatus.BAD_REQUEST.value());
		}
		if (!limitValidator.validate(limit, ServiceConstants.LIMIT)) {
			throw new CustomException("limit can only have positive values", HttpStatus.BAD_REQUEST.value());
		}
		if (!offsetValidator.validate(offset, ServiceConstants.OFFSET)) {
			throw new CustomException("offset can only have positive values", HttpStatus.BAD_REQUEST.value());
		}
	}

	// Helper for method 2 after validation
	private List<User> getAllUsers(String sortType, String sortOrder, Integer limit, Integer offset) {
		List<User> users = userRepo.findAll();
		SortContext context = new SortContext();

		if (sortType.equalsIgnoreCase("name") && sortOrder.equalsIgnoreCase("even")) {
			context.setStrategy(new NameEvenSortStrategy());
		} else if (sortType.equalsIgnoreCase("name") && sortOrder.equalsIgnoreCase("odd")) {
			context.setStrategy(new NameOddSortStrategy());
		} else if (sortType.equalsIgnoreCase("age") && sortOrder.equalsIgnoreCase("even")) {
			context.setStrategy(new AgeEvenSortStrategy());
		} else if (sortType.equalsIgnoreCase("age") && sortOrder.equalsIgnoreCase("odd")) {
			context.setStrategy(new AgeOddSortStrategy());
		}
		users = context.sortList(users);
		// custom pagination logic
		int endIndex = Math.min(offset + limit, users.size());
		return users.subList(offset, endIndex);

	}

	// Helper for method 3
	private PaginationResponse getUserListWithPaginationDetail(List<User> users, Integer limit, Integer offset) {

		if (offset < 0 || offset >= users.size() || limit <= 0 || limit > 5) {
			throw new CustomException("Invalid Input for Limit or Offset", 400);
		}

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setHasNextPage(offset + limit < users.size());
		pageInfo.setHasPrevPage(offset > 0);
		pageInfo.setTotal(users.size());

		int endIndex = Math.min(offset + limit, users.size());

		PaginationResponse response = new PaginationResponse();
		response.setData(users.subList(offset, endIndex));
		response.setPageInfo(pageInfo);
		return response;
	}
}
