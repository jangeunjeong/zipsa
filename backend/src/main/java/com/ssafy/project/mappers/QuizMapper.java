package com.ssafy.project.mappers;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Quiz_result;
import com.ssafy.project.model.User;

@Mapper
public interface QuizMapper {

	public List<Quiz.List> getCommonQuizs(User user);

	public List<Quiz> getMyQuizs(User user);

	public Quiz getQuiz(Quiz quiz);

	public int insertQuizResult(Quiz_result quiz_result);

	public List<Quiz.List> getFailedQuizs(User user);

	public int deleteFailedQuizs(Quiz_result quiz_result);

	public List<Quiz_choice> findQuizChoiceByQuizId(long id);

	public List<Quiz.List> findTodayQuizByEmail(String email);

	public List<Long> findQuizIdByUserId(Long userId);

	public List<Long> findQuizIdByAnimalId(Long userId);

	public void saveTodayQuiz(HashMap<String, Long> hm);

	public void deleteTodayQuiz();

	public void deleteSolvedTodayQuiz(long id);

	public List<Quiz.List> findSolvedQuizByEmail(String email);

}
