package com.ssafy.project.service;

import java.util.List;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;

public interface HomeService {
	
	public String getCoin(User user);
	public List<Animal> getAllAnimalsInfo();
	public List<Script> getAllScriptByUserId(User user);

}
