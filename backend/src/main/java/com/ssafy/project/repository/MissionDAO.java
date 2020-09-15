package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_complete;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.User;

public interface MissionDAO {
	
	public int initDogMission(Mission_state mission_state);
	public int initCatMission(Mission_state mission_state);
	public List<Mission> getDogMissions();
	public List<Mission> getCatMissions();
	public Mission_state getMissionState(Mission_state mission_state);
	public int setMissionStateDay1(Mission_state mission_state);
	public int setMissionStateDay2(Mission_state mission_state);
	public int setMissionStateDay3(Mission_state mission_state);
	public int setMissionStateDay4(Mission_state mission_state);
	public int setMissionStateDay5(Mission_state mission_state);
	public int setMissionStateDay6(Mission_state mission_state);
	public int setMissionStateDay7(Mission_state mission_state);
	public List<Integer> chkMissionStateDay1(Mission_state mission_state);
	public List<Integer> chkMissionStateDay2(Mission_state mission_state);
	public List<Integer> chkMissionStateDay3(Mission_state mission_state);
	public List<Integer> chkMissionStateDay4(Mission_state mission_state);
	public List<Integer> chkMissionStateDay5(Mission_state mission_state);
	public List<Integer> chkMissionStateDay6(Mission_state mission_state);
	public List<Integer> chkMissionStateDay7(Mission_state mission_state);
    public List<Integer> getCntCompleteByMissionId(User user);
    public List<Integer> chkWeeklyMissionState(User user);
    public int insertMissionComplete(Mission_complete mission_complete);
    public int deleteMissionState(User user);
}
