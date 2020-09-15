package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.AccountMapper;
import com.ssafy.project.mappers.AnimalMapper;
import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;

@Repository
public class AnimalDAOImpl implements AnimalDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Animal findById(long id) {
		return sqlSession.getMapper(AnimalMapper.class).findById(id);
	}

	@Override
	public List<Animal> getALLAnimals() {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AnimalMapper.class).getAllAnimals();
	}

	@Override
	public List<Animal> getAnimalsByUserId(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AnimalMapper.class).getAnimalsByUserId(user);
	}

	@Override
	public Animal getAnimalByUserId(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AnimalMapper.class).getAnimalByUserId(user);
	}

	@Override
	public Animal getAnimalById(Animal animal) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AnimalMapper.class).getAnimalById(animal);
	}

	@Override
	public Animal getNowAnimal(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AnimalMapper.class).getNowAnimal(user);
	}

//	@Override
//	public void insertUser(User user) {
//		sqlSession.getMapper(AccountMapper.class).insertUser(user);
//	}
//
//	@Override
//	public int countByEmail(String email) {
//		return sqlSession.getMapper(AccountMapper.class).countByEmail(email);
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		return sqlSession.getMapper(AccountMapper.class).findByEmail(email);
//	}

}
