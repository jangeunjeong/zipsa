package com.ssafy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.model.User_certification;
import com.ssafy.project.repository.AnimalDAO;
import com.ssafy.project.repository.CertificationDAO;
import com.ssafy.project.repository.UserDAO;

@Service
public class MyHomeServiceImpl implements MyHomeService{
	
	@Autowired
	AnimalDAO animalDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CertificationDAO certificationDAO;
	
	@Override
	public List<Animal> getAllmyAnimals(User user) {
		// TODO Auto-generated method stub
		return animalDAO.getAnimalsByUserId(user); 
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.getUser(user);
	}

	@Override
	public int updateAnimal(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateAnimal(user);
	}

	@Override
	public int updateProfile(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}

	@Override
	public Animal getAnimal(Animal animal) {
		// TODO Auto-generated method stub
		return animalDAO.getAnimalById(animal);
	}

	@Override
	public int cntCertification(User user , Animal animal) {
		// TODO Auto-generated method stub
		user.setFk_animal_id(animal.getId());
		return certificationDAO.cntCertification(user);
	}

	@Override
	public List<String> getCertifications(User user) {
		// TODO Auto-generated method stub
		return certificationDAO.getCertifications(user);
	}

	@Override
	public int cntNowAnimalCerti(User user) {
		// TODO Auto-generated method stub
		return certificationDAO.cntNowAnimalCerti(user);
	}

	@Override
	public List<User_certification> getCertifiedAnimal(User user) {
		// TODO Auto-generated method stub
		return certificationDAO.getCertifiedAnimal(user);
	}
	
	

}
