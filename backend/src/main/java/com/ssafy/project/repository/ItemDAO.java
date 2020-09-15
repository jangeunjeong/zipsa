package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Item;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.User;

public interface ItemDAO {
	
	public int getAllMyItemsPrices(User user);
	public List<Item> getMissionItems(Mission mission);

}
