package com.ssafy.project.model;

import com.ssafy.project.model.Item.Builder;

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
public class Mission_state {
	private long id;
	private long fk_user_id;
	private long fk_mission_id;
	private boolean day1;
	private boolean day2;
	private boolean day3;
	private boolean day4;
	private boolean day5;
	private boolean day6;
	private boolean day7;
	private boolean is_daily;
	private String start_date;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private long fk_user_id;
		private long fk_mission_id;
		private boolean day1;
		private boolean day2;
		private boolean day3;
		private boolean day4;
		private boolean day5;
		private boolean day6;
		private boolean day7;
		private boolean is_daily;
		private String start_date;

		public Mission_state build() {
			return new Mission_state(id, fk_user_id, fk_mission_id, day1, day2, day3, day4, day5, day6, day7, is_daily,
					start_date);
		}

	}

}
