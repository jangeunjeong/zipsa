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
public class User_certification {
	private long id;
	private String fk_user_id;
	private String fk_animal_id;
	private String animal_name;
	private String img;


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String fk_user_id;
		private String fk_animal_id;
		private String animal_name;
		private String img;
		
		
		public User_certification build() {
			return new User_certification(id, fk_user_id, fk_animal_id, animal_name, img);
		}

	}

}
