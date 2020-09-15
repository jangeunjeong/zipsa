package com.ssafy.project.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;

@Mapper
public interface AnimalMapper {

	public Animal findById(long id);
	public List<Animal> getAllAnimals();
	public List<Animal> getAnimalsByUserId(User user);
	public Animal getAnimalByUserId(User user);
	public Animal getAnimalById(Animal animal);
	public Animal getNowAnimal(User user);

}
