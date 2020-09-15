package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.ItemMapper;
import com.ssafy.project.model.Item;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.User;


@Repository
public class ItemDAOImpl implements ItemDAO{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int getAllMyItemsPrices(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ItemMapper.class).getAllMyItemsPrices(user);
	}

	@Override
	public List<Item> getMissionItems(Mission mission) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ItemMapper.class).getMissionItems(mission);
	}

}
