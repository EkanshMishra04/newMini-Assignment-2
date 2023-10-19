package com.nagarro.javaMiniAssignment2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationInfo {
	private boolean hasNextPage;
	private boolean hasPrevPage;
	private int total;
}
