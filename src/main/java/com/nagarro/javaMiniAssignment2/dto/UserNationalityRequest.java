package com.nagarro.javaMiniAssignment2.dto;

import java.util.List;

import com.nagarro.javaMiniAssignment2.models.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNationalityRequest {
	
	private String count;
	private String name;
	private List<Country> country;

}
