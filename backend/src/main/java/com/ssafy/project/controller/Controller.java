package com.ssafy.project.controller;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ssafy.project.model.Animal;
import com.ssafy.project.model.Item;
import com.ssafy.project.model.Message;
import com.ssafy.project.model.Mission;
import com.ssafy.project.model.Mission_state;
import com.ssafy.project.model.Quiz;
import com.ssafy.project.model.Quiz_choice;
import com.ssafy.project.model.Script;
import com.ssafy.project.model.User;
import com.ssafy.project.model.User_certification;
import com.ssafy.project.service.AnimalService;
import com.ssafy.project.service.HomeService;
import com.ssafy.project.service.MissionService;
import com.ssafy.project.service.MyHomeService;
import com.ssafy.project.service.StrayAnimalServiceImpl;
import com.ssafy.project.service.StudyService;
import com.ssafy.project.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api") // url과 컨트롤러의 메서드 매핑할때 사용하는 스프링 프레임워크 어노테이션
@RestController // 메서드마다 @ResponseBody 안붙여도 전송가능
public class Controller {
//http://localhost:8888/swagger-ui.html#!/controller
	@Autowired
	private AnimalService animalService;

	@Autowired
	private UserService userService;

	@Autowired
	private HomeService homeService;

	@Autowired
	private StudyService studyService;

	@Autowired
	private MyHomeService myhomeService;

	@Autowired
	private MissionService missionService;
	
	@Autowired
	private StrayAnimalServiceImpl StrayAnimalService;

	@Value("${resources.user_location}")
	private String resourcesLocation;
	@Value("${resources.user_uri_path}")
	private String resourcesUriPath;
	@Value("${server.port}")
	private String serverPort;

	@PostMapping("/image/upload/{email}")
	@ApiOperation(value = "이미지 업로드")
	public ResponseEntity<Map<String, Object>> updateProfileImg(@PathVariable String email,
			@RequestBody MultipartFile file) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		String[] extensionArr = { ".jpg", ".jpeg", ".png" };

		User user = new User();
		user.setEmail(email);
		String userId = myhomeService.getUser(user).getId() + "";

		try {
			for (int i = 0; i < extensionArr.length; i++) {
				File oldFile = new File(resourcesLocation + "/" + userId + extensionArr[i]);//
				if (oldFile.exists()) {
					oldFile.delete();

				}
			}
			String fileType = file.getContentType();
			StringTokenizer st = new StringTokenizer(fileType, "/");
			st.nextToken();
			String fileExtension = st.nextToken();
			String userImg = "/" + userId + "." + fileExtension;
			file.transferTo(new File(resourcesLocation + userImg));

			status = HttpStatus.OK;
			resultMap.put("status", true);
			System.out.println("IMAGE UPLOAD SUCS-" + userImg);
			System.out.println("UPLOAD TO:" + (resourcesLocation + userImg));
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/myhome/animal/distinct/{email}")
	@ApiOperation(value = "사진 판별")
	public ResponseEntity<Map<String, Object>> test(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		User user = new User();
		user.setEmail(email);
		String userId = myhomeService.getUser(user).getId() + "";

		String[] extensionArr = { ".jpg", ".jpeg", ".png" };

		String file_name = "";
		for (int i = 0; i < extensionArr.length; i++) {
			File file = new File(resourcesLocation + "/" + userId + extensionArr[i]);//
			if (file.exists()) {
				file_name = userId + extensionArr[i];

			}
		}

		String pythonPath;
		String filePath;

		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			pythonPath = "C:\\Program Files\\Python35\\python.exe";
			filePath = "C:\\Mask_RCNN-master\\AI_Client.py";
		} else {
			pythonPath = "/usr/bin/python3";
			filePath = "/home/zipsa/Mask_RCNN-master/AI_Client.py";
		}

		ProcessBuilder pb = new ProcessBuilder().command(pythonPath, filePath, file_name);
		Process p;

		try {
			p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "MS949"));

			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
			String ref_line = sb.toString();
			ref_line = ref_line.replaceAll(match, "");
			StringTokenizer st = new StringTokenizer(ref_line);
			List<String> res = new ArrayList<>();

			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				switch (s) {
				case "1":
					res.add("말티즈");
					break;

				case "2":
					res.add("골든리트리버");
					break;

				case "3":
					res.add("러시안블루");
					break;

				case "4":
					res.add("페르시안");
					break;
				default:
					break;
				}

			}

			resultMap.put("res", res);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/animal/{id}")
	@ApiOperation(value = "동물정보 가지고오기")
	public ResponseEntity<Map<String, Object>> findAllAnimals(HttpServletRequest req, @PathVariable long id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		Animal animalInfo = animalService.findById(id);
		resultMap.put("status", true);
		status = HttpStatus.ACCEPTED;
		resultMap.put("info", animalInfo);

		try {

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/ready/set/submit")
	@ApiOperation(value = "사전 조사 후 회원가입")
	public ResponseEntity<Map<String, Object>> setAnimal(HttpServletRequest req, @RequestBody User.Builder user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();//
		HttpStatus status = null;

		try {
			int res = userService.setUser(user.build());
			resultMap.put("result", res == 1 ? "TRUE" : "FALSE");
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/mission/init")
	@ApiOperation(value = "미션 생성하기") // email,fk_animal_id
	public ResponseEntity<Map<String, Object>> initMission(HttpServletRequest req, @RequestBody User.Builder user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			int res = missionService.initMission(user.build());
			resultMap.put("result", res == 1 ? "TRUE" : "FALSE");
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/mission/daily/{email}")
	@ApiOperation(value = "데일리 미션 리스트 가져오기")
	public ResponseEntity<Map<String, Object>> getDailyMission(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			List<Mission> missions = missionService.getMissions(user);
			resultMap.put("missions", missions);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/mission/items/{mission_id}")
	@ApiOperation(value = "미션에 맞는 아이템 가져오기")
	public ResponseEntity<Map<String, Object>> getMissionItems(HttpServletRequest req, @PathVariable long mission_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			Mission mission = new Mission();
			mission.setId(mission_id);

			List<Item> items = missionService.getMissionItems(mission);
			resultMap.put("items", items);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/mission/{mission_id}/{email}")
	@ApiOperation(value = "미션 상태 상세")
	public ResponseEntity<Map<String, Object>> getDailyMission(HttpServletRequest req, @PathVariable long mission_id,
			@PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			Mission_state mission_state = new Mission_state();
			mission_state.setFk_mission_id(mission_id);

			User user = new User();
			user.setEmail(email);

			mission_state = missionService.getMissionState(user, mission_state);

			resultMap.put("mission_state", mission_state);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/mission/set/{mission_id}/{email}")
	@ApiOperation(value = "미션 상태 변경") // email,fk_animal_id
	public ResponseEntity<Map<String, Object>> setMissionState(HttpServletRequest req, @PathVariable long mission_id,
			@PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			Mission_state mission_state = new Mission_state();
			mission_state.setFk_mission_id(mission_id);

			User user = new User();
			user.setEmail(email);

			int res = missionService.setMissionState(user, mission_state);

			resultMap.put("result", res == 1 ? "TRUE" : "FALSE");
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/mission/check/{email}")
	@ApiOperation(value = "미션 데일리 수행 점검")
	public ResponseEntity<Map<String, Object>> chkDailyMission(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			User user = new User();
			user.setEmail(email);

			int res = missionService.chkMissionState(user);

			resultMap.put("result", res == 1 ? "TRUE" : "FALSE");
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/mission/check/final/{email}")
	@ApiOperation(value = "미션완료 최종 확인")
	public ResponseEntity<Map<String, Object>> chkFinalMission(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			User user = new User();
			user.setEmail(email);

			int res = missionService.chkFinalMissionState(user);

			resultMap.put("result", res == 1 ? "TRUE" : "FALSE");
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/home/script/{email}")
	@ApiOperation(value = "내가 키우는 동물 대사")
	public ResponseEntity<Map<String, Object>> getAnimalsInfo(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			User user = new User();
			user.setEmail(email);

			List<Script> scripts = homeService.getAllScriptByUserId(user);
			resultMap.put("scripts", scripts);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/home/coin/{email}")
	@ApiOperation(value = "코인 조회(finance - item_have*item_price)")
	public ResponseEntity<Map<String, Object>> getCoin(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			String res = homeService.getCoin(user);
			resultMap.put("result", res);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/home/animal/{email}")
	@ApiOperation(value = "내가 키우고있는 동물 정보 조회")
	public ResponseEntity<Map<String, Object>> getMyAnimalInfo(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			Animal animal = animalService.getMyAnimal(user);
			resultMap.put("animal", animal);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/myhome/{email}")
    @ApiOperation(value = "마이홈 유저 정보 리스트 불러오기")
    public ResponseEntity<Map<String, Object>> getMyhome(HttpServletRequest req, @PathVariable String email) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = null;

    try {
        User user = new User();
        user.setEmail(email);

        Animal nowAnimal = animalService.getNowAnimal(user);
        if(nowAnimal.getBreed().equals("1")) {
            nowAnimal.setBreed("강아지");
        }else if(nowAnimal.getBreed().equals("2")) {
            nowAnimal.setBreed("고양이");
        }else if(nowAnimal.getBreed().equals("3")) {
            nowAnimal.setBreed("말티즈");
        }else if(nowAnimal.getBreed().equals("4")) {
            nowAnimal.setBreed("골든 리트리버");
        }else if(nowAnimal.getBreed().equals("5")) {
            nowAnimal.setBreed("러시안 블루");
        }else {
            nowAnimal.setBreed("페르시안");
        }
        String animal_name = userService.getNowAnimalName(user);
        String certi_have = myhomeService.cntNowAnimalCerti(user) > 0 ? "TRUE" : "FALSE";
//            System.out.println(myhomeService.cntNowAnimalCerti(user));
//            List<Animal> animals = myhomeService.getAllmyAnimals(user);// 여태 길렀던거랑 기르고있는 동물
            List<User_certification> certifiedAnimals = myhomeService.getCertifiedAnimal(user);
            String nickname = myhomeService.getUser(user).getNickname();
            resultMap.put("certifiedAnimals", certifiedAnimals); //
            resultMap.put("nickname", nickname);
            resultMap.put("nowAnimal", nowAnimal);
            resultMap.put("animal_name", animal_name);
            resultMap.put("certi_have", certi_have);
            resultMap.put("status", true);
            status = HttpStatus.ACCEPTED;

    } catch (RuntimeException e) {
        resultMap.put("message", e.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    } 

    return new ResponseEntity<Map<String, Object>>(resultMap, status);
}

	@PostMapping("/myhome/profile/update")
	@ApiOperation(value = "프로필 수정")
	public ResponseEntity<Map<String, Object>> putMyhome(HttpServletRequest req, @RequestBody User.Builder user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			int res = myhomeService.updateProfile(user.build());
			resultMap.put("result", res);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// Animal //반려동물 사진, 반려동물 이름, 품종(강아지||고양이)
	@PostMapping("/myhome/animal/update")
	@ApiOperation(value = "반려동물 추가(email, fk_animal_id)") // 강아지 고양이만 주면 ai로 판단해서 동물 종류 user에 넣기
	public ResponseEntity<Map<String, Object>> addAnimal(HttpServletRequest req, @RequestBody User.Builder user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {

			int res = myhomeService.updateAnimal(user.build());
			resultMap.put("result", res);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/myhome/animal/detail/{id}/{email}")
	@ApiOperation(value = "반려동물 상세")
	public ResponseEntity<Map<String, Object>> getMyAnimal(HttpServletRequest req, @PathVariable int id,
			@PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			Animal animal = new Animal();
			animal.setId(id);

			animal = myhomeService.getAnimal(animal);// 내가 키우고있는 반려동물 아님// 동물 id 파라미터로 오면 동물 상세
			String certi_have = myhomeService.cntCertification(user, animal) > 0 ? "TRUE" : "FALSE";// 파라미터로 온 동물 id에 대해
																									// 인증서 있는지 없는지
			resultMap.put("animal", animal);
			resultMap.put("certi_have", certi_have);// 인증서 그림 속성 필요
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/myhome/profile/detail/{email}")
	@ApiOperation(value = "프로필 상세")
	public ResponseEntity<Map<String, Object>> getMyprofile(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			user = myhomeService.getUser(user);
			resultMap.put("user", user);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/myhome/certification/{email}")
	@ApiOperation(value = "반려동물 인증서 상세")
	public ResponseEntity<Map<String, Object>> getMyCertification(HttpServletRequest req, @PathVariable String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			User user = new User();
			user.setEmail(email);

			List<String> certifications = myhomeService.getCertifications(user);
			List<Integer> cntComplete = missionService.getCntCompleteByMissionId(user);
			System.out.println(cntComplete.toString());
			resultMap.put("certifications", certifications);
			resultMap.put("cntComplete", cntComplete);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/user/chatbot/{message}")
	public ResponseEntity<Map<String, Object>> msg(@PathVariable String message, HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			System.out.println("chatbot test cont");
			IamOptions iamOptions = new IamOptions.Builder().apiKey("sGWrUWp6MpdjiEtpA2lwuCTlv77w5bcSKtJmfYELNM7r").build();
			Assistant service = new Assistant("2020-02-06", iamOptions);
			service.setEndPoint("https://api.kr-seo.assistant.watson.cloud.ibm.com/instances/595ef15f-d42a-4afe-82f8-b2bef104bd4e");
			String workspaceId = "a0577400-0cd6-4b78-b1d8-d606d2aa5675";
			InputData input = new InputData.Builder(message).build();
			MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).build();
			MessageResponse response = service.message(options).execute();
			System.out.println(response.getOutput());
			
			resultMap.put("response", response.getOutput());
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) { 
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/CSV")
	public ResponseEntity<Map<String, Object>> csv(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			StrayAnimalService.CSVInsert();
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) { 
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/study/solvedquiz/{email}")
    @ApiOperation(value = "푼 퀴즈 리스트 불러오기")
    public ResponseEntity<Map<String, Object>> findSolvedQuizByEmail(HttpServletRequest req, @PathVariable String email) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = null;

    try {
        User user = new User();
        user.setEmail(email);

        List<Quiz.List> solvedQuizList = studyService.findSolvedQuizByEmail(email);
        
        for (int i = 0; i < solvedQuizList.size(); i++) {
            List<Quiz_choice> list = studyService.findQuizChoiceByQuizId(solvedQuizList.get(i).getId());
            List<String> quizChoiceList = new ArrayList<>();

            quizChoiceList.add(list.get(0).getChoice_1());
            quizChoiceList.add(list.get(0).getChoice_2());
            quizChoiceList.add(list.get(0).getChoice_3());
            quizChoiceList.add(list.get(0).getChoice_4());

            solvedQuizList.get(i).setQuizChoiceList(quizChoiceList);
        }
//
//            List<Quiz> myQuizs = studyService.getMyQuizs(user);
//
            resultMap.put("solvedQuizList", solvedQuizList);
//            resultMap.put("myQuizs", myQuizs);
            resultMap.put("status", true);
            status = HttpStatus.ACCEPTED;

    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
        resultMap.put("message", e.getMessage());
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return new ResponseEntity<Map<String, Object>>(resultMap, status);
}
	
	
}
