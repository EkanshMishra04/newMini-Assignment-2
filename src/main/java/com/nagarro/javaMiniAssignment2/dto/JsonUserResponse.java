package com.nagarro.javaMiniAssignment2.dto;

import java.util.List;

import com.nagarro.javaMiniAssignment2.models.Info;

public class JsonUserResponse {
	
	private UserResponse results;
    private Info info;
    
	public UserResponse getResults() {
		return results;
	}

	public void setResults(List<UserResponse> results) {
		this.results = results.get(0);
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "JsonUserResponse [results=" + results + ", info=" + info + "]";
	}
}
