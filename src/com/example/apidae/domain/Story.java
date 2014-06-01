package com.example.apidae.domain;

public class Story {
	int _id;
	String _story_name;

	public Story() {
	}

	public Story(int id, String story_name)
	{
		this._id = id;
		this._story_name = String.valueOf(_story_name);
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_story_name() {
		return _story_name;
	}
	public void set_story_name(String _name) {
		this._story_name = _name;
	}
}
