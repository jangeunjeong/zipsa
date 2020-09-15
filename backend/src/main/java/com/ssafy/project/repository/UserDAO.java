package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.User;

public interface UserDAO {

	public int insertUser(User user);

//	public int insertAnimalId(User user);
//	public int insertFinance(User user);
	public int selectFinance(User user);

//	public int inserAllergy(User user);
//	public int insertHousingType(User user);
//	public int insertWorkingTime(User user);
//	public int insertAgreement(User user);
	public User getUser(User user);

	public int updateUser(User user);

	public int updateAnimal(User user);

	public String getNowAnimalName(User user);

	public boolean getCertiStateByAnimalById(User user);

	public List<Long> findAllId();

}
