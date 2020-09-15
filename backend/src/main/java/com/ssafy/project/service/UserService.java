package com.ssafy.project.service;

import org.springframework.stereotype.Service;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;

public interface UserService {
	
//	public int setAnimal(User user);
//	public int setFinance(User user);
//	public int setAllergy(User user);
//	public int setRegiform(User user);
//	public int setWorktime(User user);
//	public int setAgreement(User user);
	public int setUser(User user);

	public String getNowAnimalName(User user);

	public boolean getCertiStateByAnimalById(User user);

}
