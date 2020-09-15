package com.ssafy.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.model.Stray_animal;
import com.ssafy.project.service.IAddressService;
import com.ssafy.project.service.IStrayAnimalService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class BreedController {
	@Autowired
	private IStrayAnimalService strayAnimalService;

	@Autowired
	private IAddressService addressService;

	@GetMapping("/breed/strayanimal/townlist/{city}")
	@ApiOperation(value = "시/군/구 리스트 전체 불러오기")
	public ResponseEntity<Map<String, Object>> findTownAllByCity(HttpServletRequest req, @PathVariable String city) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			List<String> townList = addressService.findTownAllByCity(city);

			resultMap.put("townList", townList);
			resultMap.put("status", true);
			status = HttpStatus.OK;

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/breed/strayanimal/centerlist/{address}")
	@ApiOperation(value = "보호소 리스트 전체 불러오기")
	public ResponseEntity<Map<String, Object>> findCenterNameAllByAddress(HttpServletRequest req,
			@PathVariable String address) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			List<String> centerNameList = strayAnimalService.findCenterNameAllByAddress(address);

			resultMap.put("centerNameList", centerNameList);
			resultMap.put("status", true);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/breed/strayanimal/breed")
	@ApiOperation(value = "품종 리스트 전체 불러오기")
	public ResponseEntity<Map<String, Object>> findBreedAllByAnimal(HttpServletRequest req,
			@RequestBody Stray_animal.SearchKeyword keyword) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			keyword.setBreed(keyword.getBreed().replace("전체", ""));
			keyword.setAddress(keyword.getAddress().replace("전체", ""));
			keyword.setCenter_name(keyword.getCenter_name().replace("전체", ""));
			List<String> breedList = strayAnimalService.findBreedAllByAnimal(keyword);

			for (int i = 0; i < breedList.size(); i++) {
				String breed = breedList.get(i).replace("[고양이]", "").replace("[개]", "");
				breedList.set(i, breed);
			}
			resultMap.put("breedList", breedList);
			resultMap.put("status", true);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/breed/strayanimal/list")
	@ApiOperation(value = "유기동물 리스트 전체 불러오기")
	public ResponseEntity<Map<String, Object>> findStrayAnimalAll(HttpServletRequest req,
			@RequestBody Stray_animal.SearchKeyword keyword) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			keyword.setBreed(keyword.getBreed().replace("전체", ""));
			keyword.setAddress(keyword.getAddress().replace("전체", ""));
			keyword.setCenter_name(keyword.getCenter_name().replace("전체", ""));
			List<Stray_animal.List> strayAnimalList = strayAnimalService.findAll(keyword);

			for (int i = 0; i < strayAnimalList.size(); i++) {
				String breed = strayAnimalList.get(i).getBreed();
				strayAnimalList.get(i).setBreed(breed.replace("]", "] "));
			}
			resultMap.put("strayAnimalList", strayAnimalList);
			resultMap.put("status", true);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/breed/strayanimal/detail/{id}")
	@ApiOperation(value = "유기동물 상세 불러오기")
	public ResponseEntity<Map<String, Object>> findById(HttpServletRequest req, @PathVariable String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			Stray_animal strayAnimal = strayAnimalService.findById(id);
			strayAnimal.setBreed(strayAnimal.getBreed().replace("]", "] "));
			List<Stray_animal> strayAnimalList = new ArrayList<>();
			strayAnimalList.add(strayAnimal);

			resultMap.put("strayAnimal", strayAnimalList);
			resultMap.put("status", true);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
