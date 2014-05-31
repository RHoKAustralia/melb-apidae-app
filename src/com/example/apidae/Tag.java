package com.example.apidae;

public class Tag {
	public static enum RID {
		HealthGood(R.drawable.ic_launcher);
		private int value;
		private RID(int id){
			this.value = id;
		}
	}
}
