package com.ssafy.project.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.AccountMapper;
import com.ssafy.project.model.User;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	SqlSession sqlSession;

	@Override
	public void insertUser(User user) {
		sqlSession.getMapper(AccountMapper.class).insertUser(user);
	}

	@Override
	public int countByEmail(String email) {
		return sqlSession.getMapper(AccountMapper.class).countByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return sqlSession.getMapper(AccountMapper.class).findByEmail(email);
	}

}
