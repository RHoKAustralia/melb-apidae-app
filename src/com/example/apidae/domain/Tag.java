package com.example.apidae.domain;

public class Tag {
	int story_id;
	int tag_id;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(int story_id, int tag_id) {
		this.story_id = story_id;
		this.tag_id = tag_id;
	}

	public int getStory_id() {
		return story_id;
	}
	public void setStory_id(int story_id) {
		this.story_id = story_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}


}
