package com.ssafy.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public int setUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(user);
	}

	@Override
	public String getNowAnimalName(User user) {
		// TODO Auto-generated method stub
		return userDAO.getNowAnimalName(user);
	}

	@Override
	public boolean getCertiStateByAnimalById(User user) {
		// TODO Auto-generated method stub
		return userDAO.getCertiStateByAnimalById(user);
	}

//	@Override
//	public int setAnimal(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertAnimalId(user);
//	}
//
//	@Override
//	public int setFinance(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertFinance(user);
//	}
//
//	@Override
//	public int setAllergy(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.inserAllergy(user);
//	}
//
//	@Override
//	public int setRegiform(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertHousingType(user);
//	}
//
//	@Override
//	public int setWorktime(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertWorkingTime(user);
//	}
//
//	@Override
//	public int setAgreement(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertAgreement(user);
//	}

}
