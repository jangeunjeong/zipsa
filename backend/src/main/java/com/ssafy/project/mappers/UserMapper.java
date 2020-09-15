package com.ssafy.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.User;

@Mapper
public interface UserMapper {

	int insertUser(User user);

//	int insertAnimalId(User user);
//	int insertFinance(User user);
//	int insertAllergy(User user);
//	int insertHousingType(User user);
//	int inserWorkingTime(User user);
//	int insertAgreement(User user);
	int selectFinance(User user);

	User getUser(User user);

	int updateUser(User user);

	int updateAnimal(User user);

	String getNowAnimalName(User user);

	boolean getCertiHave(User user);
	
	List<Long> findAllId();

}
