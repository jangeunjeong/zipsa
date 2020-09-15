package com.ssafy.project.model;

import com.ssafy.project.model.Mission_complete.Builder;

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
public class Mission {
	private long id;
	private String contents;
	private String complete_count;


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String contents;
		private String complete_count;
		
		public Mission build() {
			return new Mission(id, contents, complete_count);
		}

	}

}
