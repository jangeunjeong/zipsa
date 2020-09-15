package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.ScriptMapper;
import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;

@Repository
public class ScriptDAOImpl implements ScriptDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Script> getAllScriptByUserId(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ScriptMapper.class).getAllScriptByUserId(user);
	}

}
