package com.ssafy.project.model;

import com.ssafy.project.model.Item_have.Builder;

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
public class Item {
	private long id;
	private String item_name;
	private String item_price;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String item_name;
		private String item_price;
		
		
		public Item build() {
			return new Item(id, item_name, item_price);
		}
		
	}

}
