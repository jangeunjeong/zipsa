package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.AddressMapper;

@Repository
public class AddressDaoImpl implements AddressDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<String> findTownAllByCity(String city) {
		return sqlSession.getMapper(AddressMapper.class).findTownAllByCity(city);
	}

}
