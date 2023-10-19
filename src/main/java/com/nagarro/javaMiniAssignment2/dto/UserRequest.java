package com.nagarro.javaMiniAssignment2.dto;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private Date dob;
	private String nationality;
	private String verificationStatus;
	@CreatedDate
	private LocalDate dateCreated;
	@LastModifiedDate
	private LocalDate dateModified;
}