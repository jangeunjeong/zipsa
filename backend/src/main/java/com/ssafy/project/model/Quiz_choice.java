package com.ssafy.project.model;

import com.ssafy.project.model.Mission.Builder;

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
public class Quiz_choice {
	private long id;
	private String choice_1;
	private String choice_2;
	private String choice_3;
	private String choice_4;
	private long fk_quiz_id;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String choice_1;
		private String choice_2;
		private String choice_3;
		private String choice_4;
		private long fk_quiz_id;
		
		public Quiz_choice build() {
			return new Quiz_choice(id, choice_1, choice_2, choice_3, choice_4, fk_quiz_id);
		}
		
	}

}
