package com.ssafy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.repository.AnimalDAO;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDAO animalDAO;

	@Override
	public Animal findById(long id) {
		// TODO Auto-generated method stub
		return animalDAO.findById(id);
	}

	@Override
	public List<Animal> getAllAnimalsInfo() {
		// TODO Auto-generated method stub
		return animalDAO.getALLAnimals();
	}

	@Override
	public List<Animal> getAnimalsInfoByUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal getMyAnimal(User user) {
		// TODO Auto-generated method stub
		return animalDAO.getAnimalByUserId(user);
	}

	@Override
	public Animal getNowAnimal(User user) {
		// TODO Auto-generated method stub
		return animalDAO.getNowAnimal(user);
	}


}
