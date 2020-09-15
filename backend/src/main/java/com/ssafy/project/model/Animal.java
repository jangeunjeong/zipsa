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
public class Animal {
	private long id;
	private String breed;
	private String img;
	private String hair_length;
	private String characteristics;
	private String size;
	private long age;
	private long exercise_requirements;
	private String energy_level;
	private String drool;
	private String snore;
	private String bark;
	private String dig;
	private String social_needs;
	private String less_allergenic;
	private String grooming_needs;
	private String shed;
	private String feature;
	private String summary;
	private String personality;
	private String living_with;
	private String history;
	


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Builder {
		private long id;
		private String breed;
		private String img;
		private String hair_length;
		private String characteristics;
		private String size;
		private long age;
		private long exercise_requirements;
		private String energy_level;
		private String drool;
		private String snore;
		private String bark;
		private String dig;
		private String social_needs;
		private String less_allergenic;
		private String grooming_needs;
		private String shed;
		private String feature;
		private String summary;
		private String personality;
		private String living_with;
		private String history;
		
		public Animal build() {
			return new Animal(id, breed, img, hair_length, characteristics, size, age, exercise_requirements, energy_level, drool, snore, bark, dig, social_needs, less_allergenic, grooming_needs, shed, feature, summary, personality, living_with, history);
		}
		
	}

}
