package com.ssafy.project.service;

import java.util.List;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;

public interface AnimalService {

	public Animal findById(long id);
	public List<Animal> getAllAnimalsInfo();
	public List<Animal> getAnimalsInfoByUserId();
	public Animal getMyAnimal(User user);
	public Animal getNowAnimal(User user);

}
