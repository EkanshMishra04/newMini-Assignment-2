package com.nagarro.javaMiniAssignment2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nagarro.javaMiniAssignment2.models.Dob;
import com.nagarro.javaMiniAssignment2.models.Id;
import com.nagarro.javaMiniAssignment2.models.Location;
import com.nagarro.javaMiniAssignment2.models.Login;
import com.nagarro.javaMiniAssignment2.models.Name;
import com.nagarro.javaMiniAssignment2.models.Picture;
import com.nagarro.javaMiniAssignment2.models.Registered;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gender",
    "name",
    "location",
    "email",
    "login",
    "dob",
    "registered",
    "phone",
    "cell",
    "id",
    "picture",
    "nat"
})
public class UserResponse {
	@JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("email")
    private String email;
    @JsonProperty("login")
    private Login login;
    @JsonProperty("dob")
    private Dob dob;
    @JsonProperty("registered")
    private Registered registered;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("id")
    private Id id;
    @JsonProperty("picture")
    private Picture picture;
    @JsonProperty("nat")
    private String nat;
	@Override
	public String toString() {
		return "UserResponse [gender=" + gender + ", name=" + name + "\n, location=" + location + "\n, email=" + email
				+ "\n, login=" + login + "\n, dob=" + dob + "\n, registered=" + registered + "\n, phone=" + phone + "\n, cell="
				+ cell + "\n, id=" + id + "\n, picture=" + picture + ", nat=" + nat + "]";
	}
}