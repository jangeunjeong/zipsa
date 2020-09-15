package com.ssafy.project.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Item;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_complete;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.User;
import com.ssafy.project.repository.ItemDAO;
import com.ssafy.project.repository.MissionDAO;
import com.ssafy.project.repository.UserDAO;

@Service
public class MissionServiceImpl implements MissionService{
	
	@Autowired
	private MissionDAO missionDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public int initMission(User user) {
		// TODO Auto-generated method stub
		int missionSize = 10;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String date = sdf.format(new Date());
		
		long userId = userDAO.getUser(user).getId();
		long animal_id = userDAO.getUser(user).getFk_animal_id();
		int res = 0;
				
		if(animal_id == 1 || animal_id == 3 || animal_id == 4) {
			missionSize = 9;
			for (int i = 1; i <= missionSize; i++) {
				Mission_state mission_state = new Mission_state();
				mission_state.setFk_mission_id(i);
				mission_state.setFk_user_id(userId);
				mission_state.setStart_date(date);
				missionDAO.initDogMission(mission_state);//email, mission_id, date
			}
			res = 1;
		}else {
			for (int i = 1; i <= missionSize; i++) {
				Mission_state mission_state = new Mission_state();
				mission_state.setFk_mission_id(i);
				mission_state.setFk_user_id(userId);
				mission_state.setStart_date(date);
				missionDAO.initCatMission(mission_state);//email, mission_id, date
			}
			res = 1;
		}
		
		return res;
	}

	@Override
	public List<Mission> getMissions(User user) {
		// TODO Auto-generated method stub
		
		long animal_id = userDAO.getUser(user).getFk_animal_id();
		List<Mission> missions = null;
		
		if(animal_id == 1 || animal_id == 3 || animal_id == 4) {
			missions = missionDAO.getDogMissions();
		}else {
			missions = missionDAO.getCatMissions();
		}
		
		return missions;
	}

	@Override
	public List<Item> getMissionItems(Mission mission) {
		// TODO Auto-generated method stub
		return itemDAO.getMissionItems(mission);
	}

	@Override
	public Mission_state getMissionState(User user, Mission_state mission_state) {
		// TODO Auto-generated method stub
		mission_state.setFk_user_id(userDAO.getUser(user).getId());
		
		return missionDAO.getMissionState(mission_state);
	}

	@Override
	public int setMissionState(User user, Mission_state mission_state){
		// TODO Auto-generated method stub
		
		int res = 0;
		
		mission_state.setFk_user_id(userDAO.getUser(user).getId());
		String start_date = missionDAO.getMissionState(mission_state).getStart_date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String date = sdf.format(new Date());
		
		try {
			int day = (int) (sdf.parse(date).getTime() - sdf.parse(start_date).getTime())/(24*60*60*1000) + 1;
			
			switch (day) {
			case 1:
				res = missionDAO.setMissionStateDay1(mission_state);
				break;
			case 2:
				res = missionDAO.setMissionStateDay2(mission_state);
				break;
			case 3:
				res = missionDAO.setMissionStateDay3(mission_state);
				break;
			case 4:
				res = missionDAO.setMissionStateDay4(mission_state);
				break;
			case 5:
				res = missionDAO.setMissionStateDay5(mission_state);
				break;
			case 6:
				res = missionDAO.setMissionStateDay6(mission_state);
				break;
			case 7:
				res = missionDAO.setMissionStateDay7(mission_state);
				break;

			default:
				break;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int chkMissionState(User user) {
		// TODO Auto-generated method stub
		
		List<Integer> list = null;
		int res = 1;
		
		Mission_state mission_state = new Mission_state();
		mission_state.setFk_mission_id(1);
		mission_state.setFk_user_id(userDAO.getUser(user).getId());
		String start_date = missionDAO.getMissionState(mission_state).getStart_date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String date = sdf.format(new Date());
		
		try {
			int day = (int) (sdf.parse(date).getTime() - sdf.parse(start_date).getTime())/(24*60*60*1000);
			
			switch (day) {
			case 1:
				list = missionDAO.chkMissionStateDay1(mission_state);
				break;
			case 2:
				list = missionDAO.chkMissionStateDay2(mission_state);
				break;
			case 3:
				list = missionDAO.chkMissionStateDay3(mission_state);
				break;
			case 4:
				list = missionDAO.chkMissionStateDay4(mission_state);
				break;
			case 5:
				list = missionDAO.chkMissionStateDay5(mission_state);
				break;
			case 6:
				list = missionDAO.chkMissionStateDay6(mission_state);
				break;
			case 7:
				list = missionDAO.chkMissionStateDay7(mission_state);
				break;

			default:
				return 1;
			}
			
			for(int i : list) {
				if(i == 0) return 0;
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
	
	@Override
    public List<Integer> getCntCompleteByMissionId(User user) {
        // TODO Auto-generated method stub
        return missionDAO.getCntCompleteByMissionId(user);
    }

	@Override
	public int chkFinalMissionState(User user) {
		// TODO Auto-generated method stub
		user = userDAO.getUser(user);
		
		Mission_state mission_state = new Mission_state();
		mission_state.setFk_user_id(user.getId());
		List<Integer> list = missionDAO.chkMissionStateDay7(mission_state);
		int res = 1;
		
		for(int i : list) {
			if(i == 0) return 0;
		}
		
		list = missionDAO.chkWeeklyMissionState(user);
		
		for(int i : list) {
			if(i == 0) return 0;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String date = sdf.format(new Date());
		int missionSize = 9;
		
		if(res == 1) {
			long animal_id = user.getFk_animal_id();
			if(animal_id == 1 || animal_id == 3 || animal_id == 4) {
				missionSize = 9;
				for (int i = 1; i <= missionSize; i++) {
					Mission_complete mission_complete = new Mission_complete();
					mission_complete.setFk_mission_id(i);
					mission_complete.setFk_user_id(user.getId());
					mission_complete.setDate_time(date);
					missionDAO.insertMissionComplete(mission_complete);
				}
			}else {
				missionSize = 10;
				for (int i = 1; i <= missionSize; i++) {
					Mission_complete mission_complete = new Mission_complete();
					mission_complete.setFk_mission_id(i);
					mission_complete.setFk_user_id(user.getId());
					mission_complete.setDate_time(date);
					missionDAO.insertMissionComplete(mission_complete);
				}
			}
			
			missionDAO.deleteMissionState(user);
			
		}
		return res;
	
	}
	
	
	
}
