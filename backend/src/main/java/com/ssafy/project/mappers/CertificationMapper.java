package com.ssafy.project.mappers;

import java.util.List;

import com.ssafy.project.model.Animal;
import com.ssafy.project.model.User;
import com.ssafy.project.model.User_certification;

public interface CertificationMapper {
	
	public int cntCertification(User user);
	public List<String> getCertifications(User user);
	public int cntNowAnimalCerti(User user);
	public List<User_certification> getCertifiedAnimal(User user);

}
