package com.example.apidae;

public class Tag {
	
	public static enum RID {
		HealthGood(R.drawable.down_health),HealthBad(R.drawable.down_health),
		EducationGood(R.drawable.down_education),EducationBad(R.drawable.down_education),
		MoneyGood(R.drawable.down_money),MoneyBad(R.drawable.down_money),
		FoodGood(R.drawable.down_food),FoodBad(R.drawable.down_food);
		public int value;
		private RID(int id){
			this.value = id;
		}
	}
}
