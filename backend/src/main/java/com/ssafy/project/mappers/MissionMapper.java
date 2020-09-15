package com.ssafy.project.mappers;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_complete;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.User;

@Mapper
public interface MissionMapper {	
	
	int initDogMission(Mission_state mission_state);
	int initCatMission(Mission_state mission_state);
	List<Mission> getDogDailyMissions();
	List<Mission> getCatDailyMissions();
	Mission_state getMissionState(Mission_state mission_state);
	int setMissionStateDay1(Mission_state mission_state);
	int setMissionStateDay2(Mission_state mission_state);
	int setMissionStateDay3(Mission_state mission_state);
	int setMissionStateDay4(Mission_state mission_state);
	int setMissionStateDay5(Mission_state mission_state);
	int setMissionStateDay6(Mission_state mission_state);
	int setMissionStateDay7(Mission_state mission_state);
	List<Integer> chkMissionStateDay1(Mission_state mission_state);
	List<Integer> chkMissionStateDay2(Mission_state mission_state);
	List<Integer> chkMissionStateDay3(Mission_state mission_state);
	List<Integer> chkMissionStateDay4(Mission_state mission_state);
	List<Integer> chkMissionStateDay5(Mission_state mission_state);
	List<Integer> chkMissionStateDay6(Mission_state mission_state);
	List<Integer> chkMissionStateDay7(Mission_state mission_state);
	List<Integer> getCntCompleteByMissionId(User user);
	List<Integer> chkWeeklyMissionState(User user);
	int insertMissionComplete(Mission_complete mission_complete);
	int deleteMissionState(User user);
}
