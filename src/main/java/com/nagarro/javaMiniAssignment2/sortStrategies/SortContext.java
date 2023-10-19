package com.nagarro.javaMiniAssignment2.sortStrategies;

import java.util.List;

import com.nagarro.javaMiniAssignment2.models.User;

public class SortContext {
	private SortStrategy strategy;

	public void setStrategy(SortStrategy strategy) {
		this.strategy = strategy;
	}

	public List<User> sortList(List<User> users) {
		return strategy.sort(users);
	}
}
