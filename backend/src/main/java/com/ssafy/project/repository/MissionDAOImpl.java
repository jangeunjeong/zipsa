package com.ssafy.project.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.project.mappers.MissionMapper;
import com.ssafy.project.mappers.UserMapper;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_complete;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.User;

@Repository
public class MissionDAOImpl implements MissionDAO{
	
	@Autowired
	SqlSession sqlsession;


	@Override
	public int initDogMission(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).initDogMission(mission_state);
	}

	@Override
	public int initCatMission(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).initCatMission(mission_state);
	}

	@Override
	public List<Mission> getDogMissions() {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).getDogDailyMissions();
	}

	@Override
	public List<Mission> getCatMissions() {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).getCatDailyMissions();
	}

	@Override
	public Mission_state getMissionState(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).getMissionState(mission_state);
	}

	@Override
	public int setMissionStateDay1(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay1(mission_state);
	}

	@Override
	public int setMissionStateDay2(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay2(mission_state);
	}

	@Override
	public int setMissionStateDay3(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay3(mission_state);
	}

	@Override
	public int setMissionStateDay4(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay4(mission_state);
	}

	@Override
	public int setMissionStateDay5(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay5(mission_state);
	}

	@Override
	public int setMissionStateDay6(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay6(mission_state);
	}

	@Override
	public int setMissionStateDay7(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).setMissionStateDay7(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay1(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay1(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay2(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay2(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay3(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay3(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay4(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay4(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay5(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay5(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay6(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay6(mission_state);
	}

	@Override
	public List<Integer> chkMissionStateDay7(Mission_state mission_state) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkMissionStateDay7(mission_state);
	}
	

	@Override
    public List<Integer> getCntCompleteByMissionId(User user) {
        // TODO Auto-generated method stub
        return sqlsession.getMapper(MissionMapper.class).getCntCompleteByMissionId(user);
    }

	@Override
	public List<Integer> chkWeeklyMissionState(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).chkWeeklyMissionState(user);
	}

	@Override
	public int insertMissionComplete(Mission_complete mission_complete) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).insertMissionComplete(mission_complete);
	}

	@Override
	public int deleteMissionState(User user) {
		// TODO Auto-generated method stub
		return sqlsession.getMapper(MissionMapper.class).deleteMissionState(user);
	}

}
