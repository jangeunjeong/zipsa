package com.ssafy.project.service;

import java.util.List;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.model.User_certification;

public interface MyHomeService {

	public List<Animal> getAllmyAnimals(User user);
	public User getUser(User user);
	public int updateProfile(User user);
	public int updateAnimal(User user);
	public Animal getAnimal(Animal animal);
	public int cntCertification(User user, Animal animal);
	public List<String> getCertifications(User user);
	public int cntNowAnimalCerti(User user);
	public List<User_certification> getCertifiedAnimal(User user);
	
	


}
