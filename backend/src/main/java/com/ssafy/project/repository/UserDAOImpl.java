package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.UserMapper;
import com.ssafy.project.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	SqlSession sqlsession;

//	@Override
//	public int insertAnimalId(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).insertAnimalId(user);
//	}
//
//	@Override
//	public int insertFinance(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).insertFinance(user);
//	}
//	
//
//	@Override
//	public int inserAllergy(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).insertAllergy(user);
//	}
//
//	@Override
//	public int insertHousingType(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).insertHousingType(user);
//	}
//
//	@Override
//	public int insertWorkingTime(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).inserWorkingTime(user);
//	}
//
//	@Override
//	public int insertAgreement(User user) {
//		// TODO Auto-generated method stub
//		return sqlsession.getMapper(UserMapper.class).insertAgreement(user);
//	}

	@Override
	public int selectFinance(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).selectFinance(user);
	}
	
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).getUser(user);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).updateUser(user);
	}

	@Override
	public int updateAnimal(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).updateAnimal(user);
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).insertUser(user);
	}

	@Override
	public String getNowAnimalName(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).getNowAnimalName(user);
	}

	@Override
	public boolean getCertiStateByAnimalById(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(UserMapper.class).getCertiHave(user);
	}
	
	@Override
	public List<Long> findAllId() {
		return sqlsession.getMapper(UserMapper.class).findAllId();
	}


}
