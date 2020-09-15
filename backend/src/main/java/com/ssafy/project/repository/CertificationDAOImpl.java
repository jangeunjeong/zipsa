package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.CertificationMapper;
import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.model.User_certification;

@Repository
public class CertificationDAOImpl implements CertificationDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int cntCertification(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CertificationMapper.class).cntCertification(user);
	}

	@Override
	public List<String> getCertifications(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CertificationMapper.class).getCertifications(user);
	}

	@Override
	public int cntNowAnimalCerti(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CertificationMapper.class).cntNowAnimalCerti(user);
	}

	@Override
	public List<User_certification> getCertifiedAnimal(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CertificationMapper.class).getCertifiedAnimal(user);
	}

}
