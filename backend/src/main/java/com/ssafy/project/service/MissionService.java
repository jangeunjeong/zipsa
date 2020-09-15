package com.ssafy.project.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.project.mappers.MissionMapper;
import com.ssafy.project.model.Item;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.User;

public interface MissionService {
	
	public int initMission(User user);
	public List<Mission> getMissions(User user);
	public List<Item> getMissionItems(Mission mission);
	public Mission_state getMissionState(User user, Mission_state mission_state);
	public int setMissionState(User user, Mission_state mission_state);
	public int chkMissionState(User user);
    public List<Integer> getCntCompleteByMissionId(User user);
    public int chkFinalMissionState(User user);
}
