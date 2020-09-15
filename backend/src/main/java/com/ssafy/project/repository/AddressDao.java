package com.ssafy.project.repository;

import java.util.List;

public interface AddressDao {
	public List<String> findTownAllByCity(String city);
}
