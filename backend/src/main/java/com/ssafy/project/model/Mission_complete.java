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
public class Mission_complete {
	private long id;
	private String date_time;
	private long fk_user_id;
	private long fk_mission_id;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String date_time;
		private long fk_user_id;
		private long fk_mission_id;
		
		public Mission_complete build() {
			return new Mission_complete(id, date_time, fk_user_id, fk_mission_id);
		}
		
	}

}
