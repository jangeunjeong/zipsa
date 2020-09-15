package com.ssafy.project.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Quiz_result;
import com.ssafy.project.model.User;
import com.ssafy.project.repository.QuizDAO;
import com.ssafy.project.repository.UserDAO;

@Service
public class StudyServiceImpl implements StudyService{
	
	@Autowired
	QuizDAO quizDAO;
	
	@Autowired
	UserDAO userDAO;

	@Override
	public List<Quiz.List> getCommonQuizs(User user) {
		// TODO Auto-generated method stub
		return quizDAO.getCommonQuizs(user);
	}
	
	@Override
	public List<Quiz> getMyQuizs(User user) {
		// TODO Auto-generated method stub
		return quizDAO.getMyQuizs(user);
	}

	@Override
	public Map<Long, Boolean> checkQuizAnswer(Quiz quiz, User user) {
		Quiz quiz2 = quizDAO.getQuiz(quiz);
		boolean ans = false;
		Map<Long, Boolean> map = new HashMap<Long, Boolean>();
		ans = quiz.getAnswer().equals(quiz2.getAnswer()) ? true : false;

		Quiz_result del_quiz_result = new Quiz_result();
		del_quiz_result.setFk_user_id(userDAO.getUser(user).getId() + "");
		del_quiz_result.setFk_quiz_id(quiz.getId() + "");
		quizDAO.deleteFailedQuizs(del_quiz_result);

		Quiz_result quiz_result = new Quiz_result();
		quiz_result.setFk_user_id(userDAO.getUser(user).getId() + "");
		quiz_result.setFk_quiz_id(quiz.getId() + "");
		quiz_result.setUser_answer(quiz.getAnswer());
		quiz_result.setIs_answer(ans ? "1" : "0");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		quiz_result.setQuiz_try_time(sdf.format(date));

		quizDAO.insertQuizResult(quiz_result);
		
		if (ans) {
			quizDAO.deleteSolvedTodayQuiz(quiz.getId());
		}

		map.put(quiz.getId(), ans);

		return map;
	}

	@Override
	public List<Quiz.List> getFailedQuize(User user) {
		return quizDAO.getFailedQuizs(user);
	}

	@Override
	public List<Quiz_choice> findQuizChoiceByQuizId(long id) {
		return quizDAO.findQuizChoiceByQuizId(id);
	}

	@Override
	public List<Quiz.List> findTodayQuizByEmail(String email) {
		return quizDAO.findTodayQuizByEmail(email);
	}

	@Scheduled(cron = "45 13 0 * * ?") // 매일 0시에 수행
	public void saveTodayQuizScheduled() {
		quizDAO.deleteTodayQuiz();

		List<Long> userIdList = userDAO.findAllId();

		for (int i = 0; i < userIdList.size(); i++) {
			List<Long> quizIdList = quizDAO.findQuizIdByUserId(userIdList.get(i));

			if (quizIdList.size() < 2) {
				List<Long> quizIdList2 = quizDAO.findQuizIdByAnimalId(userIdList.get(i));

				for (int k = 0; k < quizIdList2.size(); k++) {
					if (!quizIdList2.isEmpty() && quizIdList2.get(k) != null) {
						quizIdList.add(quizIdList2.get(k));
					}
				}
			}

			for (int j = 0; j < 2; j++) {
				quizDAO.saveTodayQuiz(userIdList.get(i), quizIdList.get(j));
			}
		}

	}
	
	@Override
	public List<Quiz.List> findSolvedQuizByEmail(String email) {
	    return quizDAO.findSolvedQuizByEmail(email);
	}

}
