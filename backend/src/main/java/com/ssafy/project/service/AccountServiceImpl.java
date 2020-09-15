package com.ssafy.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.User;
import com.ssafy.project.repository.AccountDao;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public void signUp(User user) {
		accountDao.insertUser(user);
	}

	@Override
	public boolean emailDuplicateCheck(String email) {
		int check = accountDao.countByEmail(email);

		if (check == 0)
			return false;
		else
			return true;
	}

	@Override
	public User info(String email) {
		return accountDao.findByEmail(email);
	}

}
