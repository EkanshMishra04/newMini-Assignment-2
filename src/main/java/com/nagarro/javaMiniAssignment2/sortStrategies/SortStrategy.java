package com.nagarro.javaMiniAssignment2.sortStrategies;

import java.util.List;

import com.nagarro.javaMiniAssignment2.models.User;

public interface SortStrategy {
	List<User> sort(List<User> users);
}

