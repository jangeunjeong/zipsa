package com.ssafy.project.model;

import com.ssafy.project.model.Quiz_result.Builder;

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
public class Quiz {
	private long id;
	private String contents;
	private String answer;
	private String fk_animal_id;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String contents;
		private String answer;
		private String fk_animal_id;

		public Quiz build() {
			return new Quiz(id, contents, answer, fk_animal_id);
		}
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class List {
		private long id;
		private String contents;
		private String answer;
		private String fk_animal_id;
		private java.util.List<String> quizChoiceList;

	}

}
