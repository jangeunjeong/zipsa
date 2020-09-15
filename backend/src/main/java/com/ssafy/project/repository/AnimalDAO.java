package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;

public interface AnimalDAO {

	public Animal findById(long id);
	public List<Animal> getALLAnimals();
	public List<Animal> getAnimalsByUserId(User user);
	public Animal getAnimalByUserId(User user);
	public Animal getAnimalById(Animal animal);
	public Animal getNowAnimal(User user);

}
