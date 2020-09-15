package com.ssafy.project.service;

import com.ssafy.project.model.User;

public interface IAccountService {

	void signUp(User user);
	
	boolean emailDuplicateCheck(String email);
	
	User info(String email);
	
	

}
