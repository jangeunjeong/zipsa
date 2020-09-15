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
public class Stray_animal {
	private long id;
	private String stray_id;
	private String img;
	private String breed;
	private String color;
	private String gender;
	private String age;
	private String weight;
	private String created_at;
	private String deleted_at;
	private String address;
	private String tel;
	private String center_name;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Data
	public static class List {
		private long id;
		private String stray_id;
		private String img;
		private String breed;
		private String gender;
		private String created_at;
		private String deleted_at;
		private String center_name;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Data
	public static class SearchKeyword {
		private String breed;
		private String created_at;
		private String deleted_at;
		private String address;
		private String center_name;
	}
}
