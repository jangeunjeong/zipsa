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
public class Script {
	private long id;
	private String script;
	private long fk_animal_id;
	


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String script;
		private long fk_animal_id;
		
		public Script build() {
			return new Script(id, script, fk_animal_id);
		}
		
	}

}
