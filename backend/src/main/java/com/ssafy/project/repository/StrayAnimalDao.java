package com.ssafy.project.repository;

import java.util.List;

import com.ssafy.project.model.Stray_animal;

public interface StrayAnimalDao {
	public List<Stray_animal.List> findAll(Stray_animal.SearchKeyword keyword);

	public Stray_animal findById(String id);

	public List<String> findCenterNameAllByAddress(String address);

	public List<String> findBreedAllByAnimal(Stray_animal.SearchKeyword keyword);
	
	public int insertCSV(Stray_animal stray_animal);
}
