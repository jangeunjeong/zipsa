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
public class TodayQuiz {
	private long id;
	private String fk_user_id;
	private String fk_quiz_id;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class QuizId {
		private long fk_quiz_id;
	}
}
