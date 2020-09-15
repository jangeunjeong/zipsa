package com.ssafy.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.model.Stray_animal;

@Mapper
public interface StrayAnimalMapper {
	public List<Stray_animal.List> findAll(Stray_animal.SearchKeyword keyword);

	public Stray_animal findById(String id);

	public List<String> findCenterNameAllByAddress(String address);

	public List<String> findBreedAllByAnimal(Stray_animal.SearchKeyword keyword);
	
	public int insertCSV(Stray_animal stray_animal);
}
