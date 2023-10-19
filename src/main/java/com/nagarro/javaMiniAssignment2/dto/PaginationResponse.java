package com.nagarro.javaMiniAssignment2.dto;

import java.util.List;

import com.nagarro.javaMiniAssignment2.models.PaginationInfo;
import com.nagarro.javaMiniAssignment2.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
	private List<User> data;
	private PaginationInfo pageInfo;
}