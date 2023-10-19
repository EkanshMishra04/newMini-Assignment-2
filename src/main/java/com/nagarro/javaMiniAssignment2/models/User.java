package com.nagarro.javaMiniAssignment2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
    private String firstName;
    private String lastName;
	private int age;
	private String gender;
	private String dob;
	private String nationality;

	private String name;
	private Integer nameCharacters;
	private String verificationStatus;
	
	

	@CreatedDate
	private String dateCreated;

	@LastModifiedDate
	private String dateModified;


}
