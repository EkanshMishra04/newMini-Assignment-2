package com.nagarro.javaMiniAssignment2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	private String message;
	private String code;
	private String timeStamp;

}
