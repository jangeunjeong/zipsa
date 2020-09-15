package com.ssafy.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.repository.AddressDao;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<String> findTownAllByCity(String city) {
		return addressDao.findTownAllByCity(city);
	}

}
