package com.ssafy.project.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.model.Stray_animal;
import com.ssafy.project.repository.StrayAnimalDao;

@Service
public class StrayAnimalServiceImpl implements IStrayAnimalService {
	@Autowired
	private StrayAnimalDao strayAnimalDao;

	@Override
	public List<Stray_animal.List> findAll(Stray_animal.SearchKeyword keyword) {
		return strayAnimalDao.findAll(keyword);
	}

	@Override
	public Stray_animal findById(String id) {
		return strayAnimalDao.findById(id);
	}

	@Override
	public List<String> findCenterNameAllByAddress(String address) {
		return strayAnimalDao.findCenterNameAllByAddress(address);
	}

	@Override
	public List<String> findBreedAllByAnimal(Stray_animal.SearchKeyword keyword) {
		return strayAnimalDao.findBreedAllByAnimal(keyword);
	}

	public int CSVInsert() {
	
		try {
			System.out.println("CSV");
			File csv = new File("C:/Animal.csv");
			BufferedReader br = new BufferedReader(new FileReader(csv));
			StringTokenizer st;
			String line = "";
			int num = 0;

			while ((line = br.readLine()) != null) {
				// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
				System.out.println(line);
				if(num == 0) {
					num++;
					continue;
				}
				st = new StringTokenizer(line,"|");
				Stray_animal stray_animal = new Stray_animal(Long.parseLong(st.nextToken()),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken());
				System.out.println(stray_animal.toString());
				strayAnimalDao.insertCSV(stray_animal);
				num++;
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;

	}

}
