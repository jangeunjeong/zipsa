package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.StrayAnimalMapper;
import com.ssafy.project.model.Stray_animal;

@Repository
public class StrayAnimalDaoImpl implements StrayAnimalDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Stray_animal.List> findAll(Stray_animal.SearchKeyword keyword) {
		return sqlSession.getMapper(StrayAnimalMapper.class).findAll(keyword);
	}

	@Override
	public Stray_animal findById(String id) {
		return sqlSession.getMapper(StrayAnimalMapper.class).findById(id);
	}

	@Override
	public List<String> findCenterNameAllByAddress(String address) {
		return sqlSession.getMapper(StrayAnimalMapper.class).findCenterNameAllByAddress(address);
	}

	@Override
	public List<String> findBreedAllByAnimal(Stray_animal.SearchKeyword keyword) {
		return sqlSession.getMapper(StrayAnimalMapper.class).findBreedAllByAnimal(keyword);
	}

	@Override
	public int insertCSV(Stray_animal stray_animal) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(StrayAnimalMapper.class).insertCSV(stray_animal);
	}

}
