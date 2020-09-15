package com.ssafy.project.model;

import com.ssafy.project.model.Quiz.Builder;

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
public class Servey {
	private long id;
	private String contents;
	

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String contents;
		
		public Builder withId(long id) {
			this.id = id;
			return this;
		}
		
		public Builder withContents(String contents) {
			this.contents = contents;
			return this;
		}
		
		public Servey build() {
			return new Servey(id, contents);
		}
	}

}
