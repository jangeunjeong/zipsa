package com.ssafy.project.model;

import com.ssafy.project.model.Animal.Builder;

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
public class Item_have {
	private long id;
	private long fk_user_id;
	private long fk_item_id;
	

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private long fk_user_id;
		private long fk_item_id;
		
		public Item_have build() {
			return new Item_have(id, fk_user_id, fk_item_id);
		}
		
		
	}

}
