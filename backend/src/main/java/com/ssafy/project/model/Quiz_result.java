package com.ssafy.project.model;

import com.ssafy.project.model.Quiz_choice.Builder;

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
public class Quiz_result {
	private long id;
	private String user_answer;
	private String is_answer;
	private String fk_user_id;
	private String fk_quiz_id;
	private String quiz_try_time;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String user_answer;
		private String is_answer;
		private String fk_user_id;
		private String fk_quiz_id;
		private String quiz_try_time;

		public Quiz_result build() {
			return new Quiz_result(id, user_answer, is_answer, fk_user_id, fk_quiz_id, quiz_try_time);
		}

	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Result {
		private long user_answer;
		private int is_answer;
		private long fk_quiz_id;
	}

}
