package com.ssafy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;
import com.ssafy.project.repository.AnimalDAO;
import com.ssafy.project.repository.ItemDAO;
import com.ssafy.project.repository.ScriptDAO;
import com.ssafy.project.repository.UserDAO;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	ItemDAO itemDAO;
	@Autowired
	AnimalDAO animalDAO;
	@Autowired
	ScriptDAO scriptDAO;

	@Override
	public String getCoin(User user) {
		// TODO Auto-generated method stub
		return (userDAO.selectFinance(user) - itemDAO.getAllMyItemsPrices(user)) +"";
	}

	@Override
	public List<Animal> getAllAnimalsInfo() {
		// TODO Auto-generated method stub
		return animalDAO.getALLAnimals();
	}

	@Override
	public List<Script> getAllScriptByUserId(User user) {
		// TODO Auto-generated method stub
		return scriptDAO.getAllScriptByUserId(user);
	}

}
