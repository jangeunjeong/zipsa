package com.ssafy.project.service;

import java.util.HashMap;

public interface IKakaoAPI {

	String getAccessToken(String authorize_code);

	HashMap<String, Object> getUserInfo(String access_Token);

	void kakaoLogout(String access_Token);

	void profile(String access_Token);

	void secession(String access_Token);
}
