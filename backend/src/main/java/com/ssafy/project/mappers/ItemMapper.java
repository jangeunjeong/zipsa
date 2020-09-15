package com.ssafy.project.mappers;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.Item;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.User;

@Mapper
public interface ItemMapper {

	int getAllMyItemsPrices(User user);
	List<Item> getMissionItems(Mission mission);

}
