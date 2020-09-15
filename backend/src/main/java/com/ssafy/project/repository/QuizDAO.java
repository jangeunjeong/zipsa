package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Quiz_result;
import com.ssafy.project.model.User;

public interface QuizDAO {

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

	public void saveTodayQuiz(Long userId, Long quizId);

	public void deleteTodayQuiz();

	public void deleteSolvedTodayQuiz(long id);
	
	public List<Quiz.List> findSolvedQuizByEmail(String email);


}
