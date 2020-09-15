package com.ssafy.project.mappers;

import java.util.List;

import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;

public interface ScriptMapper {
	
	public List<Script> getAllScriptByUserId(User user);

}
