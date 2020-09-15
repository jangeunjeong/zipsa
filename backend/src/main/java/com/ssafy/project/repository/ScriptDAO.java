package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;

public interface ScriptDAO {
	
	public List<Script> getAllScriptByUserId(User user);

}
