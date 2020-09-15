package com.ssafy.project.mappers;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.User;

@Mapper
public interface AccountMapper {

	void insertUser(User user);

	int countByEmail(String email);

	User findByEmail(String email);

}
