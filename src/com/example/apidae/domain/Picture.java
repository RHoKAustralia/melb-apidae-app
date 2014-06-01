package com.example.apidae.domain;

public class Picture {
	int story_id;
	String picture_path;

	public Picture() {
		this.story_id = story_id;
		this.picture_path = picture_path;
	}
	
	public Picture(int story_id, String pic_path) {
		// TODO Auto-generated constructor stub
		this.story_id = story_id;
		this.picture_path = pic_path;
		
	}
	public int getStory_id() {
		return story_id;
	}
	public void setStory_id(int story_id) {
		this.story_id = story_id;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

}
