package com.nagarro.javaMiniAssignment2.sortStrategies.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nagarro.javaMiniAssignment2.models.User;
import com.nagarro.javaMiniAssignment2.sortStrategies.SortStrategy;

public class AgeEvenSortStrategy implements SortStrategy {

	@Override
    public List<User> sort(List<User> users) {
		Map<Boolean, List<User>> partitioned = users.stream()
				.collect(Collectors.partitioningBy(user -> user.getAge() % 2 == 0));

		List<User> even = partitioned.get(true); // Even nameCharacters
		even.sort(Comparator.comparing(User::getAge));
		List<User> odd = partitioned.get(false); // Odd nameCharacters
		odd.sort(Comparator.comparing(User::getAge));
		even.addAll(odd);

		return even; // Return the combined list
    }

}
