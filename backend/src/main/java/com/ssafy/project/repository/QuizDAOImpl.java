package com.ssafy.project.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.QuizMapper;
import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Quiz_result;
import com.ssafy.project.model.User;

@Repository
public class QuizDAOImpl implements QuizDAO{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Quiz.List> getCommonQuizs(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).getCommonQuizs(user);
	}
	
	@Override
	public List<Quiz> getMyQuizs(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).getMyQuizs(user);
	}

	@Override
	public Quiz getQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).getQuiz(quiz);
	}

	@Override
	public int insertQuizResult(Quiz_result quiz_result) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).insertQuizResult(quiz_result);
	}

	@Override
	public List<Quiz.List> getFailedQuizs(User user) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).getFailedQuizs(user);
	}

	@Override
	public int deleteFailedQuizs(Quiz_result quiz_result) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QuizMapper.class).deleteFailedQuizs(quiz_result);
	}
	
	@Override
	public List<Quiz_choice> findQuizChoiceByQuizId(long id) {
		return sqlSession.getMapper(QuizMapper.class).findQuizChoiceByQuizId(id);
	}

	@Override
	public List<Quiz.List> findTodayQuizByEmail(String email) {
		return sqlSession.getMapper(QuizMapper.class).findTodayQuizByEmail(email);
	}

	@Override
	public List<Long> findQuizIdByUserId(Long userId) {
		return sqlSession.getMapper(QuizMapper.class).findQuizIdByUserId(userId);
	}

	@Override
	public List<Long> findQuizIdByAnimalId(Long userId) {
		return sqlSession.getMapper(QuizMapper.class).findQuizIdByAnimalId(userId);
	}

	@Override
	public void saveTodayQuiz(Long userId, Long quizId) {
		HashMap<String, Long> hm = new HashMap<>();
		hm.put("userId", userId);
		hm.put("quizId", quizId);
		sqlSession.getMapper(QuizMapper.class).saveTodayQuiz(hm);
	}

	@Override
	public void deleteTodayQuiz() {
		sqlSession.getMapper(QuizMapper.class).deleteTodayQuiz();
	}

	@Override
	public void deleteSolvedTodayQuiz(long id) {
		sqlSession.getMapper(QuizMapper.class).deleteSolvedTodayQuiz(id);
	}
	
	@Override
	public List<Quiz.List> findSolvedQuizByEmail(String email) {
	    return sqlSession.getMapper(QuizMapper.class).findSolvedQuizByEmail(email);
	}

}
