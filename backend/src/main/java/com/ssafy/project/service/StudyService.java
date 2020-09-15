package com.ssafy.project.service;

import java.util.List;
import java.util.Map;

import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.User;

public interface StudyService {

	public List<Quiz.List> getCommonQuizs(User user);

	public List<Quiz> getMyQuizs(User user);

	public Map<Long, Boolean> checkQuizAnswer(Quiz quiz, User user);

	public List<Quiz.List> getFailedQuize(User user);
	
	public List<Quiz_choice> findQuizChoiceByQuizId(long id);

	public List<Quiz.List> findTodayQuizByEmail(String email);
	
	public List<Quiz.List> findSolvedQuizByEmail(String email);

}
