package com.ssafy.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.model.User;
import com.ssafy.project.service.IAccountService;
import com.ssafy.project.service.IKakaoAPI;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class AccountController {

	@Autowired
	private IAccountService accountservice;
	@Autowired
	private IKakaoAPI kakao;

	@GetMapping("/user/kakao_oauth")
	@ApiOperation(value = "카카오 계정으로 시작하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaologin(@RequestParam("code") String code, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		String access_Token = kakao.getAccessToken(code);
		System.out.println(access_Token);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		String nickname = userInfo.get("nickname").toString();
		String email = userInfo.get("email").toString();

		System.out.println(nickname);
		System.out.println(email);

		User user = new User();
		user.setNickname(nickname);
		user.setEmail(email);

		try {

			if (accountservice.emailDuplicateCheck(email)) {
				user = accountservice.info(email);
				resultMap.put("status", true);
				status = HttpStatus.ACCEPTED;

				System.out.println("login");
			} else { // 카카오 계정으로 회원가입
				user.setEmail(email);
				user.setNickname(nickname);
				resultMap.put("status", false);
				resultMap.put("log", "회원가입이 필요합니다.");
				status = HttpStatus.ACCEPTED;

				System.out.println("signup");
			}
			res.setHeader("access-token", access_Token);
			resultMap.put("info", user);
			resultMap.put("access-token", access_Token);
		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/user/kakao_signup")
	@ApiOperation(value = "카카오 계정으로 가입하기 및 로그인")
	public ResponseEntity<Map<String, Object>> kakaosignup(@RequestBody User.Builder user, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			User member = new User();
			member.setNickname(user.getNickname());
			member.setEmail(user.getEmail());
			accountservice.signUp(member);

			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
