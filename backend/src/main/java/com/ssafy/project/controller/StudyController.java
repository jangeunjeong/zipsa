package com.ssafy.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Quiz_result;
import com.ssafy.project.model.User;
import com.ssafy.project.service.AnimalService;
import com.ssafy.project.service.StudyService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class StudyController {
	@Autowired
	private StudyService studyService;

	@Autowired
	private AnimalService animalService;

	@GetMapping("/study/quiz/{email}")
	@ApiOperation(value = "퀴즈 리스트 불러오기")
	public ResponseEntity<Map<String, Object>> getQuizs(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			List<Quiz.List> commonQuizs = studyService.getCommonQuizs(user); // 내가키우는 동물 종류 퀴즈 //긁어오기 공통 1번 긁기 // 내가키우는
																				// 것만 3번

			for (int i = 0; i < commonQuizs.size(); i++) {
				List<Quiz_choice> list = studyService.findQuizChoiceByQuizId(commonQuizs.get(i).getId());
				List<String> quizChoiceList = new ArrayList<>();

				quizChoiceList.add(list.get(0).getChoice_1());
				quizChoiceList.add(list.get(0).getChoice_2());
				quizChoiceList.add(list.get(0).getChoice_3());
				quizChoiceList.add(list.get(0).getChoice_4());

				commonQuizs.get(i).setQuizChoiceList(quizChoiceList);
			}

			List<Quiz> myQuizs = studyService.getMyQuizs(user);

			resultMap.put("commonQuizs", commonQuizs);
			resultMap.put("myQuizs", myQuizs);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/study/quiz/solve/{email}")
	@ApiOperation(value = "퀴즈 제출")
	public ResponseEntity<Map<String, Object>> postQuizAnswer(HttpServletRequest req, @PathVariable String email,
			@RequestBody List<Quiz> quizList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		System.out.println("여기 옴?");
		System.out.println(email);

		try {
			User user = new User();
			user.setEmail(email);

			Map<Long, Boolean> map = new HashMap<Long, Boolean>();

			List<Quiz_result.Result> quizResultList = new ArrayList<>();

			for (int i = 0; i < quizList.size(); i++) {
				long quizId = quizList.get(i).getId();
				long answer = Long.parseLong(quizList.get(i).getAnswer());
				map = studyService.checkQuizAnswer(quizList.get(i), user);

				Quiz_result.Result quizResult = new Quiz_result.Result();
				quizResult.setUser_answer(answer);
				quizResult.setFk_quiz_id(quizId);
				quizResult.setIs_answer(map.get(quizId) ? 1 : 0);

				quizResultList.add(quizResult);
			}
			resultMap.put("quizResultList", quizResultList);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/study/quiz/retry/{email}")
	@ApiOperation(value = "틀렸던 퀴즈 리스트")
	public ResponseEntity<Map<String, Object>> getQuizsInfoRetry(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			List<Quiz.List> res = studyService.getFailedQuize(user);

			for (int i = 0; i < res.size(); i++) {
				List<Quiz_choice> list = studyService.findQuizChoiceByQuizId(res.get(i).getId());
				List<String> quizChoiceList = new ArrayList<>();

				quizChoiceList.add(list.get(0).getChoice_1());
				quizChoiceList.add(list.get(0).getChoice_2());
				quizChoiceList.add(list.get(0).getChoice_3());
				quizChoiceList.add(list.get(0).getChoice_4());

				res.get(i).setQuizChoiceList(quizChoiceList);
			}
			resultMap.put("result", res);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}// 퀴즈 다시풀기 컨트롤러 필요

	@GetMapping("/study/quiz/today/{email}")
	@ApiOperation(value = "오늘의 퀴즈 리스트 가져오기")
	public ResponseEntity<Map<String, Object>> findTodayQuizByEmail(HttpServletRequest req,
			@PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			List<Quiz.List> todayQuizList = studyService.findTodayQuizByEmail(email);

			for (int i = 0; i < todayQuizList.size(); i++) {
				List<Quiz_choice> list = studyService.findQuizChoiceByQuizId(todayQuizList.get(i).getId());
				List<String> quizChoiceList = new ArrayList<>();

				quizChoiceList.add(list.get(0).getChoice_1());
				quizChoiceList.add(list.get(0).getChoice_2());
				quizChoiceList.add(list.get(0).getChoice_3());
				quizChoiceList.add(list.get(0).getChoice_4());

				todayQuizList.get(i).setQuizChoiceList(quizChoiceList);
			}
			resultMap.put("todayQuizList", todayQuizList);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}// 퀴즈 다시풀기 컨트롤러 필요

	@GetMapping("/study/learn/{email}")
	@ApiOperation(value = "내 애완동물 공부하기")
	public ResponseEntity<Map<String, Object>> findLearnByEmail(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			Animal animal = animalService.getMyAnimal(user);
			List<Animal> animalList = new ArrayList<>();
			animalList.add(animal);

			resultMap.put("animal", animalList);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
