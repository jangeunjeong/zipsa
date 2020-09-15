package com.ssafy.project.repository;

import com.ssafy.project.model.User;

public interface AccountDao {

	public void insertUser(User user);

	public int countByEmail(String email);

	public User findByEmail(String email);

}
