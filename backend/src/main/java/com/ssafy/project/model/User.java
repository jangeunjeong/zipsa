package com.ssafy.project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {
	private long id;
	private String email;
	private String nickname;
	private long finance;
	private boolean allergy;
	private String housing_type;
	private String working_time;
	private boolean agreement;
	private long fk_animal_id;
	private String login_time;
	private String mission_start_time;
	private int mission_state;
	private String animal_name;


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String email;
		private String nickname;
		private long finance;
		private boolean allergy;
		private String housing_type;
		private String working_time;
		private boolean agreement;
		private long fk_animal_id;
		private String login_time;
		private String mission_start_time;
		private int mission_state;
		private String animal_name;
		
		public User build() {
			return new User(id, email, nickname, finance, allergy, housing_type, working_time, agreement, fk_animal_id, login_time, mission_start_time, mission_state, animal_name);
		}
		
	}


}
